package estateforum.estateforum;

import estateforum.estateforum.entity.Post;
import estateforum.estateforum.entity.PostComments;
import estateforum.estateforum.repository.PostCommentsRepository;
import estateforum.estateforum.repository.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentsRepository postCommentsRepository;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String postTime = df.format(new Date());
    private Post post= new Post("赏月","有小伙伴一起赏月的吗",postTime,"二郎神",1,null);

    @Test
    public void repoTest1(){
        Post postresult = postRepository.save(post);
        String id = postresult.getId();
        Assert.assertNotNull(postresult);
        Assert.assertEquals("get post fail", post.getCommunityId(), postresult.getCommunityId());
        Assert.assertEquals("get post fail", post.getTitle(), postresult.getTitle());
        Assert.assertEquals("get post fail",post.getPostContent(),postresult.getPostContent());
        Assert.assertEquals("get post fail", post.getPosterName(), postresult.getPosterName());
        postRepository.deleteById(id);

    }
    @Test
    public void repoTest2(){
        Post postresult = postRepository.save(post);
        String pid = postresult.getId();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postCommentsTime = df.format(new Date());

        List<PostComments> p1=postCommentsRepository.findAllByPid(pid);
        int location = p1.size()+2;
        PostComments postComments = new PostComments(pid,"我是小二",postCommentsTime,"人家想和哥哥一起赏月嗷",location);
        PostComments postComments1=postCommentsRepository.save(postComments);
        Assert.assertEquals("get postComments fail",postComments.getPid(),postComments1.getPid());
        Assert.assertEquals("get postComments fail",postComments.getCommenterName(),postComments1.getCommenterName());
        Assert.assertEquals("get postComments fail",postComments.getCommentsTime(),postComments1.getCommentsTime());
        Assert.assertEquals("get postComments fail",postComments.getpostComment(),postComments1.getpostComment());
        Assert.assertEquals("get postComments fail",postComments.getLocation(),postComments1.getLocation());
    }
}
