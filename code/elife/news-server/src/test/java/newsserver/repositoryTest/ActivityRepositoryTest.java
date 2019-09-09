package newsserver.repositoryTest;


import newsserver.entity.Activity;
import newsserver.repository.ActivityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRepositoryTest {
    @Autowired
    ActivityRepository activityRepository;

    @Test
    @Transactional
    public void saveNewsTest(){
        activityRepository.saveActivity("2019-07-09 11:22:11","2019-07-09 11:22:11","hello","admin",
                "test",0,"guigui.jpg",1);

    }

    @Test
    @Transactional
    public void findAllActivityTest(){
        Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
                ,"2020-07-09 11:22:11","admin","hello",0,"guigui.jpg",1);
        activityRepository.save(activity);
        List<Activity> list = activityRepository.findAllActivity(1,0,10);
        Activity activity1 = list.get(0);


    }

    @Test
    @Transactional
    public void findNewTest(){
         Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
                ,"2020-07-09 11:22:11","admin","hello",0,"guigui.jpg",1);
        activityRepository.save(activity);
        List<Activity> list = activityRepository.findNew(1);
        Activity activity1 = list.get(0);

    }

    @Test
    public void findPageNumTest(){
        activityRepository.findPageNum(1);
    }
}
