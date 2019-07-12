package newsserver.serviceimpl;

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
            File file = new File("./news-server/pic/"+temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo=Base64.encodeBase64String(data);
            jsonObject.put("photo" , "data:image/jpg;base64,"+photo);
            jsonObject.put("id",temp.getId());
            jsonObject.put("time",temp.getTime());
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
            File file = new File("./news-server/pic/" + temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            jsonObject.put("id", temp.getId());
            jsonObject.put("time", temp.getTime());
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
    public JSONArray findHistory(int communityId) throws IOException {
        List<News> hotTable = newsDao.findHistoryHot(communityId);
        List<NewsUsed> coldTable = newsDao.findHistory(communityId);
        JSONArray jsonArray = new JSONArray();
        Iterator<News> hot = hotTable.iterator();
        Iterator<NewsUsed> cold = coldTable.iterator();
        while (hot.hasNext()) {
            News temp = hot.next();
            JSONObject jsonObject = new JSONObject();
            File file = new File("./news-server/pic/" + temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            jsonObject.put("id", temp.getId());
            jsonObject.put("time", temp.getTime());
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("content", temp.getContent());
            jsonObject.put("managerName", temp.getManagerName());
            jsonArray.add(jsonObject);
        }
        while (cold.hasNext()) {
            NewsUsed temp = cold.next();
            JSONObject jsonObject = new JSONObject();
            File file = new File("./news-server/pic/" + temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo = Base64.encodeBase64String(data);
            jsonObject.put("photo", "data:image/jpg;base64," + photo);
            jsonObject.put("id", temp.getId());
            jsonObject.put("time", temp.getTime());
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("content", temp.getContent());
            jsonObject.put("managerName", temp.getManagerName());
            jsonArray.add(jsonObject);

        }
        return jsonArray;
    }
}
