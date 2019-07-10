package newsserver.entityTest;


import newsserver.entity.News;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {
    private News news =new News(55555,"hello","1","test",0,"photo",1);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 55555, news.getId());
        news.setId(666666);
        Assert.assertEquals("IdTest fails", 666666, news.getId());
        news.setId(55555);
    }
    @Test
    public void ManagerNameTest(){
        Assert.assertEquals("ManagerNameTest fails", "1", news.getManagerName());
        news.setManagerName("2");
        Assert.assertEquals("ManagerNameTest fails", "2", news.getManagerName());
        news.setManagerName("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "hello", news.getContent());
        news.setContent("error");
        Assert.assertEquals("ContentTest fails", "error", news.getContent());
        news.setContent("test");
    }
    @Test
    public void PhotoTest(){
        Assert.assertEquals("PhotoTest fails", "photo", news.getPhoto());
        news.setPhoto("error");
        Assert.assertEquals("PhotoTest fails", "error", news.getPhoto());
        news.setPhoto("photo");
    }
    @Test
    public void TitleTest(){
        Assert.assertEquals("TitleTest fails", "test", news.getTitle());
        news.setTitle("error");
        Assert.assertEquals("TitleTest fails", "error", news.getTitle());
        news.setTitle("test");
    }
    @Test
    public void StatusTest(){
        Assert.assertEquals("StatusTest fails", 0, news.getStatus());
        news.setStatus(666666);
        Assert.assertEquals("StatusTest fails", 666666, news.getStatus());
        news.setStatus(0);
    }
    @Test
    public void CommunityIdTest(){
        Assert.assertEquals("CommunityIdTest fails", 1, news.getCommunityId());
        news.setCommunityId(666666);
        Assert.assertEquals("CommunityIdTest fails", 666666, news.getCommunityId());
        news.setCommunityId(1);
    }
}

