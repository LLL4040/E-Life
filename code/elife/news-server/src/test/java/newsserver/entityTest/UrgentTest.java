package newsserver.entityTest;

import newsserver.entity.Urgent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentTest {
    private Urgent urgent=new Urgent(55555,"1","test",1,1);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 55555,urgent.getId());
        urgent.setId(666666);
        Assert.assertEquals("IdTest fails", 666666,urgent.getId());
        urgent.setId(55555);
    }
    @Test
    public void ManagerNameTest(){
        Assert.assertEquals("ManagerNameTest fails", "1",urgent.getManagerName());
        urgent.setManagerName("2");
        Assert.assertEquals("ManagerNameTest fails", "2",urgent.getManagerName());
        urgent.setManagerName("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "test",urgent.getContent());
        urgent.setContent("error");
        Assert.assertEquals("ContentTest fails", "error",urgent.getContent());
        urgent.setContent("test");
    }
    @Test
    public void StatusTest(){
        Assert.assertEquals("StatusTest fails", 1,urgent.getStatus());
        urgent.setStatus(666666);
        Assert.assertEquals("StatusTest fails", 666666,urgent.getStatus());
        urgent.setStatus(1);
    }
    @Test
    public void CommunityIdTest(){
        Assert.assertEquals("CommunityIdTest fails", 1,urgent.getCommunityId());
        urgent.setCommunityId(666666);
        Assert.assertEquals("CommunityIdTest fails", 666666,urgent.getCommunityId());
        urgent.setCommunityId(1);
    }
}
