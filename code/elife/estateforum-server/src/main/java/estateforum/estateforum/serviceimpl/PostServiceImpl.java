package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.service.PostService;
import net.coobird.thumbnailator.Thumbnails;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Override
    public String  save(String title, String postContent, String posterName, int communityId, List<MultipartFile> photo) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postTime = df.format(new Date());
        String path="";
        if(photo.size() > 0) {
            for (int i = 0; i < photo.size(); i++) {
                MultipartFile tmp = photo.get(i);
                path = path + UUID.randomUUID() + tmp.getOriginalFilename() + "/";
                if (!tmp.isEmpty()) {
                    byte[] bytes = tmp.getBytes();
                    BufferedOutputStream bufferedOutputStream = new
                            BufferedOutputStream(new FileOutputStream(new File("./estateforum-server/pic/" + tmp)));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                    Thumbnails.of("./estateforum-server/pic/" + tmp)
                            .size(80, 80)
                            .keepAspectRatio(false)
                            .toFile("./estateforum-server/cpic/" + tmp);
                }

            }
        }
        else {
            path = "";
        }

        Post post=new Post(title,postContent,postTime,posterName,communityId,path);
        return postDao.save(post);
    }

    @Override
    public JSONArray findAllByCommunityId(int communityId, int page, int size) throws IOException {
        List<Post> postList = postDao.findAllByCommunityId(communityId,page,size);
        JSONObject jsonObject1 = new JSONObject();
        int count = postDao.findAllByCommunityId(communityId,1,1000000000).size();
        int pageNum = count/size;
        if (count%size!=0){
            pageNum=pageNum+1;
        }
        jsonObject1.put("pageNum",pageNum);
        JSONArray jsonArray =new JSONArray();
        jsonArray.add(jsonObject1);
        Iterator<Post> iter = postList.iterator();
        while(iter.hasNext()){
            Post temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            if(temp.getPath().equals("")){
                jsonObject.put("photo" , null);
            }else {
                String[]paths = temp.getPath().split("/");
                int length = paths.length;
                List<String> photos=new ArrayList<>();
                for (int i=0;i<length;i++){
                    File file = new File("./estateforum-server/pic/"+paths[i]);
                    byte[] data = Files.readAllBytes(file.toPath());
                    String photo="data:iamge/jpg;base64"+ Base64.encodeBase64String(data);
                    photos.add(photo);
                }
                jsonObject.put("photo" , photos);
            }
            jsonObject.put("title",temp.getTitle());

            jsonObject.put("posterName", temp.getPosterName());
            jsonObject.put("postContent", temp.getPostContent());

            jsonObject.put("postTime",temp.getPostTime());
            jsonObject.put("id",temp.getId());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }


        return jsonArray;
    }

    @Override
    public Post findPost(String id){
        return postDao.findPost(id);
    }
    @Override
    public void deletePost(String pid){
          postDao.deletePost(pid);
    }

}
