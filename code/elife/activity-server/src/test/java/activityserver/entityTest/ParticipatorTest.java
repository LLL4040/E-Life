package activityserver.entityTest;

import activityserver.entity.Participator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipatorTest {
    //数据库中手动写一个activityId=1和username=1的来测试
    private Participator participator = new Participator(88888,1,"1","test",0);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 88888, participator.getId());
        participator.setId(666666);
        Assert.assertEquals("IdTest fails", 666666, participator.getId());
        participator.setId(88888);
    }
    @Test
    public void ActivityIdTest(){
        Assert.assertEquals("ActivityIdTest fails", 1, participator.getActivityId());
        participator.setActivityId(666666);
        Assert.assertEquals("ActivityIdTest fails", 666666, participator.getActivityId());
        participator.setActivityId(1);
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
