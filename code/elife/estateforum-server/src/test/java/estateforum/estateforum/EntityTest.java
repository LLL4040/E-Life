package estateforum.estateforum;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.entity.PostComments;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class EntityTest {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String postTime = df.format(new Date());
    private Post post= new Post("赏月","有小伙伴一起赏月的吗",postTime,"二郎神",1,null);
    @Test
    public void titleTest(){
        Assert.assertEquals("get title fail","赏月",post.getTitle());
        post.setTitle("赏月2");
        Assert.assertEquals("get title fail","赏月2",post.getTitle());
        post.setTitle("赏月");
    }
    @Test
    public void postContentTest(){
        Assert.assertEquals("get postContent fail","有小伙伴一起赏月的吗",post.getPostContent());
        post.setPostContent("有小伙伴一起赏月的吗2");
        Assert.assertEquals("get postContent fail","有小伙伴一起赏月的吗2",post.getPostContent());
        post.setPostContent("有小伙伴一起赏月的吗");
    }
    @Test
    public void postNameTest(){
        Assert.assertEquals("get posterName fail","二郎神",post.getPosterName());
        post.setPosterName("二郎神2");
        Assert.assertEquals("get posterName fail","二郎神2",post.getPosterName());
        post.setPosterName("二郎神");
    }
    @Test
    public void communityIdTest(){
        Assert.assertEquals("get communityId fail",1,post.getCommunityId());
        post.setCommunityId(2);
        Assert.assertEquals("get communityId fail",2,post.getCommunityId());
        post.setCommunityId(1);
    }

    String postCommentsTime = df.format(new Date());

    PostComments postComments = new PostComments("2","我是小二",postCommentsTime,"人家想和哥哥一起赏月嗷",1);
    @Test
    public void pidTest(){
        Assert.assertEquals("get pid fail","2",postComments.getPid());
        postComments.setPid("1");
        Assert.assertEquals("get pid fail","1",postComments.getPid());
        postComments.setPid("2");
    }
    @Test
    public void commentsNameTest(){
        Assert.assertEquals("get commentsName fail","我是小二",postComments.getCommenterName());
        postComments.setCommenterName("我是万小二");
        Assert.assertEquals("get commentsName fail","我是万小二",postComments.getCommenterName());
        postComments.setCommenterName("我是小二");
    }
    @Test
    public void commentsTimeTest(){
        Assert.assertEquals("get commentsTime fail",postCommentsTime,postComments.getCommentsTime());
        String postCommentsTime2 = df.format(new Date());
        postComments.setCommentsTime(postCommentsTime2);
        Assert.assertEquals("get commentsTime fail",postCommentsTime2,postComments.getCommentsTime());
        postComments.setCommentsTime(postCommentsTime);
    }
}
