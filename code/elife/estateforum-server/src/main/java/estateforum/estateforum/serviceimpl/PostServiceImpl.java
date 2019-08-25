package estateforum.estateforum.serviceimpl;

import estateforum.estateforum.dao.TagDao;
import estateforum.estateforum.entity.Post;
import estateforum.estateforum.dao.PostDao;
import estateforum.estateforum.entity.Tag;
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
 * @date 2019/07/04
 * @author ztHou
 * @date 2019/08/15
 * */

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private TagDao tagDao;

    @Override
    public String  save(String title,String tag,  String postContent, String posterName, int communityId, List<MultipartFile> photo) throws IOException {
        if(tag == null || "".equals(tag)){
            return "0";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postTime = df.format(new Date());
        String path="";
        if(photo.size() > 0) {
            for (int i = 0; i < photo.size(); i++) {
                MultipartFile tmp = photo.get(i);
                System.out.println(tmp.getOriginalFilename());
                String tmpPath = UUID.randomUUID()+tmp.getOriginalFilename();
                String oo=tmpPath+"/";
                path = path + oo;
                if (!tmp.isEmpty()) {
                    byte[] bytes = tmp.getBytes();
                    BufferedOutputStream bufferedOutputStream = new
                            BufferedOutputStream(new FileOutputStream(new File("./estateforum-server/pic/" + tmpPath)));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                    Thumbnails.of("./estateforum-server/pic/"+tmpPath)
                            .size(160,160)
                            .keepAspectRatio(false)
                            .toFile("./estateforum-server/cpic/"+tmpPath);

                }

            }
        }
        else {
            path = "";
        }

        try{
            if(tagDao.existsByContentAndCommunityId(tag, (long) communityId)){
                if(!tagDao.addTagNum(tag, (long) communityId)){
                    return "0";
                }
            } else {
                Tag tagNew = new Tag(tag, 1, (long) communityId);
                tagDao.save(tagNew);
            }
            Post post = new Post(title, tag, postContent, postTime, posterName, communityId, path);
            return postDao.save(post);
        } catch (Exception e){
            return "0";
        }
    }

    @Override
    public JSONArray findAllByCommunityId(int communityId, int page, int size) throws IOException {
        List<Post> postList = postDao.findAllByCommunityId(communityId,page,size);
        JSONObject jsonObject1 = new JSONObject();
        int count = postDao.count(communityId);
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
                System.out.println(paths);
                int length = paths.length;
                List<String> photos=new ArrayList<>();
                for (int i=0;i<length;i++){
                    System.out.println("图片为"+paths[i]);
                    File file = new File("./estateforum-server/cpic/"+paths[i]);
                    byte[] data = Files.readAllBytes(file.toPath());
                    String photo="data:image/jpg;base64,"+ Base64.encodeBase64String(data);
                    photos.add(photo);
                }
                jsonObject.put("photo" , photos);
                jsonObject.put("path" , paths);
            }
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("tag", temp.getTag());

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
          try{
              Post post = postDao.findPost(pid);
              if(!tagDao.reduceTagNum(post.getTag(), (long) post.getCommunityId())){
                  return;
              }
              postDao.deletePost(pid);
          }catch (Exception e){
          }
    }
    @Override
    public JSONObject getBigPhoto(String path){
        JSONObject jsonObject = new JSONObject();
        try{
            File file = new File("./estateforum-server/pic/" + path);
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

    @Override
    public JSONArray getAllTags(Long communityId){
        JSONArray jsonArray = new JSONArray();
        List<Tag> tagList = tagDao.getAllTagsByCommunityId(communityId);
        tagList.sort(new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                int a = o1.getNum();
                int b = o2.getNum();
                return Integer.compare(a, b);
            }
        });
        for(Tag tag : tagList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tag", tag.getContent());
            jsonObject.put("num", tag.getNum());
            jsonArray.appendElement(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray findAllByCommunityIdAndTag(int communityId, String tag, int page, int size)throws IOException{
        List<Post> postList = postDao.findAllByCommunityIdAndTag(communityId, tag, page, size);
        Tag tag1 = tagDao.findTagByContentAndCommunityId(tag, (long)communityId);
        JSONObject jsonObject1 = new JSONObject();
        int count = tag1.getNum();
        int pageNum = count / size;
        if (count % size != 0){
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
                System.out.println(paths);
                int length = paths.length;
                List<String> photos=new ArrayList<>();
                for (int i=0;i<length;i++){
                    System.out.println("图片为"+paths[i]);
                    File file = new File("./estateforum-server/cpic/"+paths[i]);
                    byte[] data = Files.readAllBytes(file.toPath());
                    String photo="data:image/jpg;base64,"+ Base64.encodeBase64String(data);
                    photos.add(photo);
                }
                jsonObject.put("photo" , photos);
                jsonObject.put("path" , paths);
            }
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("tag", temp.getTag());

            jsonObject.put("posterName", temp.getPosterName());
            jsonObject.put("postContent", temp.getPostContent());

            jsonObject.put("postTime",temp.getPostTime());
            jsonObject.put("id",temp.getId());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }


        return jsonArray;
    }

}
