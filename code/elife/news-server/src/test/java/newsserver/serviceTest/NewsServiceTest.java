package newsserver.serviceTest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.entity.News;
import newsserver.repository.NewsRepository;
import newsserver.service.NewsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {
    @Autowired
    private NewsService newsSevice;
    @Autowired
    private NewsRepository newsRepository;

    @Test
    @Transactional
    public void findNewTest() throws IOException {
        newsRepository.changeStatus(1);
        //得在路径自己添加一个测试的图片文件
        newsRepository.saveNews("hello","1","test",0,"guigui.jpg",1);
        JSONArray jsonArray = newsSevice.findNew(1);
        JSONObject jsonObject =(JSONObject) jsonArray.get(jsonArray.size()-1);
        Assert.assertEquals("get new fail", "1", jsonObject.getAsString("managerName"));
        Assert.assertEquals("get new fail","test",jsonObject.getAsString("title"));
        Assert.assertEquals("get new fail","hello",jsonObject.getAsString("content"));
        Assert.assertNotNull(jsonObject.getAsString("id"));
        Assert.assertNotNull(jsonObject.getAsString("time"));
        Assert.assertNotNull(jsonObject.getAsString("photo"));
    }

    @Test
    @Transactional
    public void moveTableTest(){
        newsSevice.moveTable();
    }

    @Test
    @Transactional
    public void deleteOneTest(){
        News news =new News(55555,"hello","1","test",0,"gugugui",1);
        newsRepository.save(news);
        Assert.assertEquals("delete new fail",true,newsSevice.deleteOne(55555));
        Assert.assertEquals("delete new fail",false,newsSevice.deleteOne(55555));

    }

    @Test
    @Transactional
    public void findHistoryTest() throws IOException {
        //E盘upload目录下必须有guigui.jpg
        News news =new News(55555,"hello","1","test",0,"guigui.jpg",1);
        newsRepository.save(news);
        Assert.assertNotNull(newsSevice.findHistory(1,1));
    }
}
