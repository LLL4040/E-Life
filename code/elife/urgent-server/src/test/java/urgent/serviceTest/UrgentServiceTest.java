package urgent.serviceTest;

import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import urgent.entity.Urgent;
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
}

