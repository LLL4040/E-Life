package lifeservice.lifeservice;

import com.mysql.cj.protocol.x.Notice;
import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.service.ComputerService;
import lifeservice.lifeservice.service.MaintainService;
import lifeservice.lifeservice.service.SupermarketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {
    @Autowired
    private MaintainService maintainService;
    @Autowired
    private SupermarketService supermarketService;
    @Autowired
    private ComputerService computerService;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String maintainTime = df.format(new Date());
    Maintain maintain=new Maintain(maintainTime,"我家窗户破了","哪吒","12345");
    @Test
    public void saveMaintian() throws Exception{
        long m1=maintainService.saveMaintian(maintain);
        Maintain maintainResult = maintainService.getOne(m1);
        Assert.assertNotNull(maintainResult);
        Assert.assertEquals("get maintain fail", maintain.getId(), maintainResult.getId());
        Assert.assertEquals("get maintain fail", maintain.getContent(), maintainResult.getContent());
        Assert.assertEquals("get maintain fail", maintain.getUsername(), maintainResult.getUsername());
        Assert.assertEquals("get maintain fail", maintain.getUserPhone(), maintainResult.getUserPhone());
        Assert.assertEquals("get maintain fail", maintain.getStatus(), maintainResult.getStatus());
        Assert.assertEquals("get maintain fail", maintain.getTime(), maintainResult.getTime());
        boolean b1=maintainService.deleteById(m1);
        Assert.assertEquals("get maintain fail", true, b1);
        boolean b2=maintainService.deleteById(m1);
        Assert.assertEquals("get maintain fail",false,b2);

    }
    @Test
    public void managerMaintain() throws Exception{
        long m1=maintainService.saveMaintian(maintain);
        Maintain maintainResult = maintainService.getOne(m1);
        String managerResult=maintainService.managerMaintain(m1,1,"土地老爷","123","二郎神");
        Assert.assertEquals("get maintain fail", "完成管理物业维修", managerResult);
        //maintainService.deleteById(m1);

    }
    @Test
    public void findMaintainByCommunityId() throws Exception{

        List<Maintain> maintainList=maintainService.findMaintainByCommunityId(1);
        Assert.assertNotNull(maintainList);
    }
    @Test
    public void findbyUsernameAndStatus() throws Exception{

        List<Maintain> maintainList=maintainService.findbyUsernameAndStatus("哪吒");
        Assert.assertNotNull(maintainList);
    }
    @Test
    public void findSupermarket() throws Exception{

        List<Merchant> merchantList=supermarketService.findSupermarket(1);
        Assert.assertNotNull(merchantList);
    }
    @Test
    public void findComputerMaintain() throws Exception{

        List<Merchant> merchantList=computerService.findComputerMaintain(1);
        Assert.assertNotNull(merchantList);
    }

}
