package newsserver.repositoryTest;

import newsserver.entity.News;
import newsserver.repository.NewsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    /**
     * new
     */
    @Autowired
    private NewsRepository newsRepository;
    @Test
    @Transactional
    public void saveNewsTest(){
        News news =new News(55555,"hello","1","test",0,"gugugui",1);
        newsRepository.save(news);
        News newsResult=this.newsRepository.getOne(55555);
        Assert.assertEquals("news save fails", "1",newsResult.getManagerName());
        Assert.assertEquals("news save fails", "hello",newsResult.getContent());
        Assert.assertEquals("news save fails", 0,newsResult.getStatus());
        Assert.assertEquals("news save fails", 1,newsResult.getCommunityId());
        Assert.assertEquals("news save fails","test",newsResult.getTitle());
        Assert.assertEquals("news save fails","gugugui",newsResult.getPhoto());

    }
    @Test
    @Transactional
    public void saveNewsfindNewTest(){
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        List<News> news = newsRepository.findNew(1);
        News newsResult = news.get(news.size()-1);
        Assert.assertEquals("news findNew fails", "1",newsResult.getManagerName());
        Assert.assertEquals("news findNew fails", "hello",newsResult.getContent());
        Assert.assertEquals("news findNew fails", 0,newsResult.getStatus());
        Assert.assertEquals("news findNew fails", 1,newsResult.getCommunityId());
        Assert.assertEquals("news findNew fails","test",newsResult.getTitle());
        Assert.assertEquals("news findNew fails","gugugui",newsResult.getPhoto());
    }
    @Test
    @Transactional
    public void changeStatusTest(){
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","testout",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","testin",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);

        List<News> news = newsRepository.findNew(1);
        News newsResult = news.get(0);
        Assert.assertEquals("news findNew fails", "1",newsResult.getManagerName());
        Assert.assertEquals("news findNew fails", "hello",newsResult.getContent());
        Assert.assertEquals("news findNew fails", 0,newsResult.getStatus());
        Assert.assertEquals("news findNew fails", 1,newsResult.getCommunityId());
        Assert.assertEquals("news findNew fails","testin",newsResult.getTitle());
        Assert.assertEquals("news findNew fails","gugugui",newsResult.getPhoto());
    }
    @Test
    @Transactional
    public void moveTableTest(){

        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","testout",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","testin",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);
        newsRepository.changeStatus(1);
        newsRepository.saveNews("hello","1","test",0,"gugugui",1);

        newsRepository.moveTable(1);
    }

    @Test
    @Transactional
    public void findHistoryTest(){
        News news =new News(55555,"hello","1","test",0,"gugugui",1);
        newsRepository.save(news);
        newsRepository.findHistory(1);
    }
}
