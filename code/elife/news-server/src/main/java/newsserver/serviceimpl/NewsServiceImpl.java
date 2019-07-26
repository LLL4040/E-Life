package newsserver.serviceimpl;

import net.coobird.thumbnailator.Thumbnails;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.dao.NewsDao;
import newsserver.entity.News;
import newsserver.entity.NewsUsed;
import newsserver.service.NewsService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
/**
 * newsService class
 *
 * @Author wyx
 * @Date 2019.07.10
 */
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public boolean save(String content, String managerName, String title, MultipartFile photo, int communityId) throws IOException {
        try {
            String path = UUID.randomUUID() + photo.getOriginalFilename();
            if (!photo.isEmpty()) {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream bufferedOutputStream = new
                        BufferedOutputStream(new FileOutputStream(new File("./news-server/pic/" + path)));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            Thumbnails.of("./news-server/pic/"+path)
                    .size(80,80)
                    .keepAspectRatio(false)
                    .toFile("./news-server/cpic/"+path);
            newsDao.save(content, managerName, title, 0, path, communityId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findNew(int communityId) throws IOException {
        List<News> list = newsDao.findNew(communityId);
        JSONArray jsonArray =new JSONArray();
        Iterator<News> iter = list.iterator();
        while(iter.hasNext()){
            News temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            File file = new File("./news-server/cpic/"+temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo=Base64.encodeBase64String(data);
            jsonObject.put("photo" , "data:image/jpg;base64,"+photo);
            jsonObject.put("id",temp.getId());
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(temp.getTime());
            jsonObject.put("path", temp.getPhoto());
            jsonObject.put("time", dateStr);
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("content",temp.getContent());
            jsonObject.put("managerName",temp.getManagerName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @Override
    public JSONObject findOne(int id) throws IOException {
            JSONObject jsonObject = new JSONObject();
        try {
            News temp = newsDao.findOne(id);
            File file = new File("./news-server/cpic/" + temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            jsonObject.put("id", temp.getId());
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(temp.getTime());
            jsonObject.put("path", temp.getPhoto());
            jsonObject.put("time", dateStr);
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("content", temp.getContent());
            jsonObject.put("managerName", temp.getManagerName());
            return jsonObject;
        }
        catch (Exception e){
            jsonObject.put("error","something wrong");
            return jsonObject;
        }
    }

    @Override
    public boolean deleteOne(int id){
        try{
            newsDao.deleteOne(id);
            return true;
        }
        catch (Exception e) {
        return false;
        }
    }

    @Override
    public boolean moveTable(){
        try{
            newsDao.moveTable();
            return true;
        }
        catch (Exception e) {

        return false;
        }
    }

    @Override
    public JSONArray findHistory(int communityId,int page) throws IOException {
        List<News> hotTable = newsDao.findHistoryHot(communityId);
        Collections.reverse(hotTable);
        List<NewsUsed> coldTable = newsDao.findHistory(communityId);
        JSONArray jsonArray = new JSONArray();
        Iterator<News> hot = hotTable.iterator();
        Iterator<NewsUsed> cold = coldTable.iterator();
        int row = 5;
        int left = row*(page-1);
        int right = row*page;
        int i=0;
        while (hot.hasNext()) {
            if(i>=left && i<right) {
                News temp = hot.next();
                JSONObject jsonObject = new JSONObject();
                File file = new File("./news-server/cpic/" + temp.getPhoto());
                byte[] data = Files.readAllBytes(file.toPath());
                String photo = Base64.encodeBase64String(data);
                jsonObject.put("photo", "data:image/jpg;base64," + photo);
                jsonObject.put("id", temp.getId());
                jsonObject.put("path", temp.getPhoto());
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(temp.getTime());
                jsonObject.put("time", dateStr);
                jsonObject.put("title", temp.getTitle());
                jsonObject.put("content", temp.getContent());
                jsonObject.put("managerName", temp.getManagerName());
                jsonArray.add(jsonObject);
            }
            else {
                hot.next();
            }
            i++;
        }
        while (cold.hasNext()) {
            if(i>=left && i<right) {
                NewsUsed temp = cold.next();
                JSONObject jsonObject = new JSONObject();
                File file = new File("./news-server/cpic/" + temp.getPhoto());
                byte[] data = Files.readAllBytes(file.toPath());
                String photo = Base64.encodeBase64String(data);
                jsonObject.put("photo", "data:image/jpg;base64," + photo);
                jsonObject.put("id", temp.getId());
                jsonObject.put("path", temp.getPhoto());
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(temp.getTime());
                jsonObject.put("time", dateStr);
                jsonObject.put("title", temp.getTitle());
                jsonObject.put("content", temp.getContent());
                jsonObject.put("managerName", temp.getManagerName());
                jsonArray.add(jsonObject);
            }
            else {
                cold.next();
            }
            i++;

        }
        JSONObject allPage = new JSONObject();
        int pageNum = (hotTable.size()+coldTable.size())/row;
        if(pageNum % row != 0){
            pageNum++;
        }
        allPage.put("pageNum",pageNum);
        jsonArray.add(allPage);
        return jsonArray;
    }

    @Override
    public JSONObject getBigPhoto(String path){
        JSONObject jsonObject = new JSONObject();
        try{
            File file = new File("./news-server/pic/" + path);
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("error","notfound");
            return jsonObject;
        }
    }
}
