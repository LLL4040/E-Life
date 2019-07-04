package estateforum.estateforum.forumcontroller;

import estateforum.estateforum.formentity.Post;
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

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

@RequestMapping(path = "/api")
@Controller
public class PostCommentsController {
    @Autowired
    PostService postService;
    @Autowired
    PostCommentsService postCommentsService;

}
