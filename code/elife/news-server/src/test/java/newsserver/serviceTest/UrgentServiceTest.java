package newsserver.serviceTest;

import net.minidev.json.JSONObject;
import newsserver.entity.Urgent;
import newsserver.repository.UrgentRepository;
import newsserver.service.UrgentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentServiceTest {
    @Autowired
    private UrgentService urgentService;
    @Autowired
    private UrgentRepository urgentRepository;
    @Test
    @Transactional
    public void saveTest(){
        urgentService.save("1","test",0,1);
        JSONObject urgentResult = urgentService.findNew(1);
        Assert.assertNotNull(urgentResult);
        Assert.assertEquals("get urgent fail", "1", urgentResult.getAsString("managerName"));
        Assert.assertEquals("get urgent fail","test",urgentResult.getAsString("content"));
        Assert.assertEquals("get urgent fail","0",urgentResult.getAsString("status"));
        Assert.assertEquals("get urgent fail","1",urgentResult.getAsString("communityId"));

    }
    @Test
    @Transactional
    public void findNewTest(){
        urgentService.save("1","hhh",0,1);
        JSONObject urgentResult = urgentService.findNew(1);
        Assert.assertNotNull(urgentResult);
        Assert.assertEquals("findOneTest fail", "1", urgentResult.getAsString("managerName"));
        Assert.assertEquals("findOneTest fail","hhh",urgentResult.getAsString("content"));
        Assert.assertEquals("findOneTest fail","0",urgentResult.getAsString("status"));
        Assert.assertEquals("findOneTest fail","1",urgentResult.getAsString("communityId"));

    }
    @Test
    @Transactional
    public void moveTableTest(){
        urgentService.moveTable();
    }
    @Test
    @Transactional
    public void deleteOneTest(){
        Urgent urgent = new Urgent(55555,"1","jjj",0,1);
        urgentRepository.save(urgent);
        boolean tempt = urgentService.deleteOne(55555);
        Assert.assertEquals("deleteOneTest fail",true,tempt);
        boolean temp = urgentService.deleteOne(55555);
        Assert.assertEquals("deleteOneTest fail",false,temp);
    }
}

