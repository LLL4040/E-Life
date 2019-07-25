package newsserver.entityTest;


import newsserver.entity.NewsUsed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsUsedTest {
    private NewsUsed newsUsed =new NewsUsed(55555,"hello","1","test","photo",1);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 55555, newsUsed.getId());
        newsUsed.setId(666666);
        Assert.assertEquals("IdTest fails", 666666, newsUsed.getId());
        newsUsed.setId(55555);
    }
    @Test
    public void ManagerNameTest(){
        Assert.assertEquals("ManagerNameTest fails", "1", newsUsed.getManagerName());
        newsUsed.setManagerName("2");
        Assert.assertEquals("ManagerNameTest fails", "2", newsUsed.getManagerName());
        newsUsed.setManagerName("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "hello", newsUsed.getContent());
        newsUsed.setContent("error");
        Assert.assertEquals("ContentTest fails", "error", newsUsed.getContent());
        newsUsed.setContent("test");
    }
    @Test
    public void PhotoTest(){
        Assert.assertEquals("PhotoTest fails", "photo", newsUsed.getPhoto());
        newsUsed.setPhoto("error");
        Assert.assertEquals("PhotoTest fails", "error", newsUsed.getPhoto());
        newsUsed.setPhoto("photo");
    }
    @Test
    public void TitleTest(){
        Assert.assertEquals("TitleTest fails", "test", newsUsed.getTitle());
        newsUsed.setTitle("error");
        Assert.assertEquals("TitleTest fails", "error", newsUsed.getTitle());
        newsUsed.setTitle("test");
    }
    @Test
    public void CommunityIdTest(){
        Assert.assertEquals("CommunityIdTest fails", 1, newsUsed.getCommunityId());
        newsUsed.setCommunityId(666666);
        Assert.assertEquals("CommunityIdTest fails", 666666, newsUsed.getCommunityId());
        newsUsed.setCommunityId(1);
    }
}

