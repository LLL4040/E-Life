package estateforum.estateforum.controller;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.service.PostCommentsService;
import estateforum.estateforum.service.PostService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public JSONObject addPost(HttpServletRequest request, @RequestParam String title, @RequestParam String postContent, @RequestParam String posterName, @RequestParam int communityId, @RequestParam(required=false) List<MultipartFile> photo)throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        } else {
            net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();

            if(posterName.equals("")||postContent.equals("")||title.equals("")){

                object.put("post", "0");
                object.put("message","信息不能为空");
                return object;

            }
            System.out.println(photo);
            postService.save(title,postContent,posterName,communityId,photo);
            object.put("post", "1");
            object.put("message","发表帖子成功");
            return object;
        }
    }

    @RequestMapping(path = "/findPost")
    @ResponseBody
    public JSONArray findPost(HttpServletRequest request, @RequestParam int communityId, @RequestParam int page, @RequestParam int size)throws IOException{
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
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
    @RequestMapping(path = "/photo")
    @ResponseBody
    public JSONObject getBigPhoto(HttpServletRequest request, String path){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return postService.getBigPhoto( path);
    }

}
