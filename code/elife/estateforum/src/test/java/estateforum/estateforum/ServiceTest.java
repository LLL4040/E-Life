package estateforum.estateforum;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.entity.PostComments;
import estateforum.estateforum.service.PostCommentsService;
import estateforum.estateforum.service.PostService;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentsService postCommentsService;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String postTime = df.format(new Date());
    private Post post= new Post("赏月","有小伙伴一起赏月的吗",postTime,"二郎神",1);

    @Test
    public void PostTest() throws Exception{
        String p1 = postService.save(post);

        Post postResult1 = postService.findPost(p1);

        Assert.assertNotNull(postResult1);
        Assert.assertEquals("get post fail", post.getCommunityId(), postResult1.getCommunityId());
        Assert.assertEquals("get post fail", post.getTitle(), postResult1.getTitle());
        Assert.assertEquals("get post fail",post.getPostContent(),postResult1.getPostContent());
        Assert.assertEquals("get post fail", post.getPosterName(), postResult1.getPosterName());


    }
    @Test
    public void PostCommentsTest() throws Exception{

        String pid = postService.save(post);

        String postCommentsTime = df.format(new Date());
        List<PostComments> p1=postCommentsService.findAllByPid(pid);
        int location = p1.size()+2;
        PostComments postComments = new PostComments(pid,"我是小二",postCommentsTime,"人家想和哥哥一起赏月嗷",location);
        postCommentsService.saveComments(postComments);
        List<PostComments> commentsResult=postCommentsService.findAllByPid(pid);
        Assert.assertEquals("get post fail", postComments.getPid(),commentsResult.get(0).getPid());
        Assert.assertEquals("get post fail",postComments.getCommenterName() , commentsResult.get(0).getCommenterName());
        Assert.assertEquals("get post fail",postComments.getCommentsTime(),commentsResult.get(0).getCommentsTime());
        Assert.assertEquals("get post fail", postComments.getpostComment(), commentsResult.get(0).getpostComment());
        Assert.assertEquals("get post fail", postComments.getLocation(), commentsResult.get(0).getLocation());



    }
}
