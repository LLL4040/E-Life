package newsserver.serviceTest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.entity.Activity;
import newsserver.entity.News;
import newsserver.repository.ActivityRepository;
import newsserver.repository.NewsRepository;
import newsserver.service.ActivityService;
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
public class ActivityServiceTest {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRepository activityRepository;

    @Test
    public void runTest() throws IOException{
        activityService.getBigPhoto("guigui.jpg");


    }

    @Test
    @Transactional
    public void deleteOneTest(){
        Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
                ,"2020-07-09 11:22:11","admin","hello",0,"guigui.jpg",1);
        activityRepository.save(activity);
        Assert.assertTrue("deleteSuccess",activityService.deleteActivity(99999));
        Assert.assertFalse("deleteSuccess",activityService.deleteActivity(99999));
    }

    @Test
    @Transactional
    public void submitActivityTest(){
        Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
                ,"2020-07-09 11:22:11","admin","hello",0,"guigui.jpg",1);
        activityRepository.save(activity);
        activityService.submitActivity(99999,"admin",1);
        activityService.findAllParticipator(99999,1);
    }
}
