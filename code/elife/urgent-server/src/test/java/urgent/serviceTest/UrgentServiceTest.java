package urgent.serviceTest;

import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import urgent.service.UrgentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentServiceTest {
    @Autowired
    private UrgentService urgentService;

    @Test
    @Transactional
    public void saveTest(){
        urgentService.save(55555,"1","test",1,1);
        JSONObject urgentResult = urgentService.findOne(55555);
        Assert.assertNotNull(urgentResult);
        Assert.assertEquals("get urgent fail", "55555", urgentResult.getAsString("id"));
        Assert.assertEquals("get urgent fail", "1", urgentResult.getAsString("managerName"));
        Assert.assertEquals("get urgent fail","test",urgentResult.getAsString("content"));
        Assert.assertEquals("get urgent fail","1",urgentResult.getAsString("status"));
        Assert.assertEquals("get urgent fail","1",urgentResult.getAsString("communityId"));
        Assert.assertNotNull(urgentService.findOne(55555));

    }
    @Test
    @Transactional
    public void findOneTest(){
        urgentService.save(55555,"1","test",1,1);
        JSONObject urgentResult = urgentService.findOne(55555);
        Assert.assertNotNull(urgentResult);
        Assert.assertEquals("findOneTest fail", "55555", urgentResult.getAsString("id"));
        Assert.assertEquals("findOneTest fail", "1", urgentResult.getAsString("managerName"));
        Assert.assertEquals("findOneTest fail","test",urgentResult.getAsString("content"));
        Assert.assertEquals("findOneTest fail","1",urgentResult.getAsString("status"));
        Assert.assertEquals("findOneTest fail","1",urgentResult.getAsString("communityId"));
        Assert.assertNotNull(urgentService.findOne(55555));

    }
    @Test
    @Transactional
    public void moveTableTest(){
        urgentService.moveTable();
    }
    @Test
    @Transactional
    public void deleteOneTest(){
        urgentService.save(55555,"1","test",1,1);
        JSONObject urgentResult = urgentService.findOne(55555);
        Assert.assertNotNull(urgentResult);
        boolean temp = urgentService.deleteOne(55555);
        Assert.assertEquals("deleteOneTest fail",true,temp);
    }
}

