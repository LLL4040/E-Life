package estateforum.estateforum.controller;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.service.PostCommentsService;
import estateforum.estateforum.service.PostService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
@RequestMapping(path = "/api/post")
@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostCommentsService postCommentsService;
    @RequestMapping(path = "/addPost")
    @ResponseBody
    public JSONObject addPost(@RequestParam String title, @RequestParam String postContent, @RequestParam String posterName, @RequestParam int communityId, @RequestParam List<MultipartFile> photo)throws IOException {
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();

        if(posterName.equals("")||postContent.equals("")||title.equals("")||photo.isEmpty()){
            object.put("post", "0");
            object.put("message","信息不能为空");
            return object;

        }
        if (photo.isEmpty()){
            System.out.println("这是空的");
        }else {
            System.out.println("这不是空的");
        }

        postService.save(title,postContent,posterName,communityId,photo);
        object.put("post", "1");
        object.put("message","发表帖子成功");
        return object;
    }
    @RequestMapping(path = "/findPost")
    @ResponseBody
    public JSONArray findPost(@RequestParam int communityId, @RequestParam int page, @RequestParam int size)throws IOException{
        return postService.findAllByCommunityId(communityId,page,size);
    }
    @RequestMapping(path = "/deletePost")
    @ResponseBody JSONObject deletePost(@RequestParam String id){
        postService.deletePost(id);
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        object.put("delete", "1");
        object.put("message","删除成功");
        return object;
    }

}
