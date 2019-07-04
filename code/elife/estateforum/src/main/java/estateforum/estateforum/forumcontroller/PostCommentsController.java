package estateforum.estateforum.forumcontroller;

import estateforum.estateforum.formentity.Post;
import estateforum.estateforum.formentity.PostComments;
import estateforum.estateforum.forumservice.PostCommentsService;
import estateforum.estateforum.forumservice.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@RequestMapping(path = "/api")
@Controller
public class PostCommentsController {

    @Autowired
    PostCommentsService postCommentsService;
    @RequestMapping(path = "/findComments")
    public List<PostComments> findComment(@RequestParam int pid){
        return postCommentsService.findAllByPid(pid);
    }
    @RequestMapping(path = "/addComments")
    public String addComments(@RequestParam int pid,@RequestParam  String commenterName,String postComment){
        if(postComment.equals("")){
            return "评论内容不能为空";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentsTime = df.format(new Date());
        PostComments postComments = new PostComments(pid,commenterName,commentsTime,postComment);
        postCommentsService.saveComments(postComments);
        return "发表评论成功";
    }
}
