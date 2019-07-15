package estateforum.estateforum.controller;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.service.PostCommentsService;
import estateforum.estateforum.service.PostService;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public JSONObject addPost(@RequestParam String title, @RequestParam String postContent, @RequestParam String posterName, @RequestParam int communityId ){
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();

        if(posterName.equals("")||postContent.equals("")||title.equals("")){
            object.put("post", "0");
            object.put("message","信息不能为空");
            return object;

        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postTime = df.format(new Date());
        Post post=new Post(title,postContent,postTime,posterName,communityId);
        postService.save(post);
        object.put("post", "1");
        object.put("message","发表帖子成功");
        return object;
    }
    @RequestMapping(path = "/findPost")
    @ResponseBody
    public List<Post> findPost(@RequestParam int communityId,@RequestParam int page,@RequestParam int size){
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
