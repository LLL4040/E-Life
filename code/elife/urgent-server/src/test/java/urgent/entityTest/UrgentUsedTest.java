package urgent.entityTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import urgent.entity.UrgentUsed;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentUsedTest {
    private UrgentUsed urgentUsed=new UrgentUsed(55555,"1","test",1);
    @Test
    public void IdTest(){
        Assert.assertEquals("IdTest fails", 55555,urgentUsed.getId());
        urgentUsed.setId(666666);
        Assert.assertEquals("IdTest fails", 666666,urgentUsed.getId());
        urgentUsed.setId(55555);
    }
    @Test
    public void ManagerNameTest(){
        Assert.assertEquals("ManagerNameTest fails", "1",urgentUsed.getManagerName());
        urgentUsed.setManagerName("2");
        Assert.assertEquals("ManagerNameTest fails", "2",urgentUsed.getManagerName());
        urgentUsed.setManagerName("1");
    }
    @Test
    public void ContentTest(){
        Assert.assertEquals("ContentTest fails", "test",urgentUsed.getContent());
        urgentUsed.setContent("error");
        Assert.assertEquals("ContentTest fails", "error",urgentUsed.getContent());
        urgentUsed.setContent("test");
    }
    @Test
    public void CommunityIdTest(){
        Assert.assertEquals("CommunityIdTest fails", 1,urgentUsed.getCommunityId());
        urgentUsed.setCommunityId(666666);
        Assert.assertEquals("CommunityIdTest fails", 666666,urgentUsed.getCommunityId());
        urgentUsed.setCommunityId(1);
    }
}
