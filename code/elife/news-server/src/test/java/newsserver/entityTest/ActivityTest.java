package newsserver.entityTest;


import newsserver.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTest {

    private Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
            ,"2020-07-09 11:22:11","1","hello",0,"guigui.jpg",1);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 99999, activity.getId());
        activity.setId(666666);
        Assert.assertEquals("IdTest fails", 666666, activity.getId());
        activity.setId(99999);
    }
    @Test
    public void startTimeTest(){
        Assert.assertEquals("startTimeTest fails", "2019-07-09 11:22:11", activity.getStartTime());
        activity.setStartTime("2021-07-09 11:22:11");
        Assert.assertEquals("startTimeTest fails", "2021-07-09 11:22:11", activity.getStartTime());
        activity.setStartTime("2019-07-09 11:22:11");
    }
    @Test
    public void endTimeTest(){
        Assert.assertEquals("endTimeTest fails", "2020-07-09 11:22:11", activity.getEndTime());
        activity.setEndTime("2021-07-09 11:22:11");
        Assert.assertEquals("endTimeTest fails", "2021-07-09 11:22:11", activity.getEndTime());
        activity.setEndTime("2020-07-09 11:22:11");
    }
    @Test
    public void ManagerNameTest(){
        Assert.assertEquals("ManagerNameTest fails", "1", activity.getManagerName());
        activity.setManagerName("2");
        Assert.assertEquals("ManagerNameTest fails", "2", activity.getManagerName());
        activity.setManagerName("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "todayHaveFun", activity.getContent());
        activity.setContent("error");
        Assert.assertEquals("ContentTest fails", "error", activity.getContent());
        activity.setContent("todayHaveFun");
    }
    @Test
    public void PhotoTest(){
        Assert.assertEquals("PhotoTest fails", "guigui.jpg", activity.getPhoto());
        activity.setPhoto("error");
        Assert.assertEquals("PhotoTest fails", "error", activity.getPhoto());
        activity.setPhoto("guigui.jpg");
    }
    @Test
    public void TitleTest(){
        Assert.assertEquals("TitleTest fails", "hello", activity.getTitle());
        activity.setTitle("error");
        Assert.assertEquals("TitleTest fails", "error", activity.getTitle());
        activity.setTitle("hello");
    }
    @Test
    public void StatusTest(){
        Assert.assertEquals("StatusTest fails", 0, activity.getStatus());
        activity.setStatus(666666);
        Assert.assertEquals("StatusTest fails", 666666, activity.getStatus());
        activity.setStatus(0);
    }
    @Test
    public void CommunityIdTest(){
        Assert.assertEquals("CommunityIdTest fails", 1, activity.getCommunityId());
        activity.setCommunityId(666666);
        Assert.assertEquals("CommunityIdTest fails", 666666, activity.getCommunityId());
        activity.setCommunityId(1);
    }

}
