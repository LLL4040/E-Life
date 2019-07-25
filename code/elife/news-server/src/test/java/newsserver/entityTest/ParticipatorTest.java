package newsserver.entityTest;


import newsserver.entity.Activity;
import newsserver.entity.Participator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipatorTest {
    private Activity activity = new Activity(99999,"todayHaveFun","2019-07-09 11:22:11"
            ,"2020-07-09 11:22:11","1","hello",0,"guigui.jpg",1);

    private Participator participator = new Participator(88888,99999,"1","test",0);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 88888, participator.getId());
        participator.setId(666666);
        Assert.assertEquals("IdTest fails", 666666, participator.getId());
        participator.setId(88888);
    }
    @Test
    public void ActivityIdTest(){
        Assert.assertEquals("ActivityIdTest fails", 99999, participator.getActivityId());
        participator.setActivityId(666666);
        Assert.assertEquals("ActivityIdTest fails", 666666, participator.getActivityId());
        participator.setActivityId(99999);
    }
    @Test
    public void UsernameTest(){
        Assert.assertEquals("ActivityIdTest fails", "1", participator.getUsername());
        participator.setUsername("666666");
        Assert.assertEquals("ActivityIdTest fails", "666666", participator.getUsername());
        participator.setUsername("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "test", participator.getContent());
        participator.setContent("error");
        Assert.assertEquals("ContentTest fails", "error", participator.getContent());
        participator.setContent("test");
    }
    @Test
    public void StatusTest(){
        Assert.assertEquals("StatusTest fails", 0, participator.getStatus());
        participator.setStatus(666666);
        Assert.assertEquals("StatusTest fails", 666666, participator.getStatus());
        participator.setStatus(0);
    }
}
