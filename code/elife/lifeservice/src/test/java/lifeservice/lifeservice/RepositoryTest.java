package lifeservice.lifeservice;

import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.repository.MaintainRepository;
import lifeservice.lifeservice.repository.MerchantRepository;
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

public class RepositoryTest {
    @Autowired
    private MaintainRepository maintainRepository;
    @Autowired
    MerchantRepository merchantRepository;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String maintainTime = df.format(new Date());
    Maintain maintain=new Maintain(maintainTime,"我家窗户破了","哪吒","12345");

    @Test
    public void repofindbyUsernameAndStatus() throws Exception{
        long m1=maintainRepository.save(maintain).getId();
        List<Maintain> maintainList=maintainRepository.findbyUsernameAndStatus("哪吒");
        Assert.assertNotNull(maintainList);
        maintainRepository.deleteById(m1);
    }
    @Test
    public void repofindMaintainByCommunityId() throws Exception{
        long m1=maintainRepository.save(maintain).getId();
        List<Maintain> maintainList=maintainRepository.findMaintainByCommunityId(1,1,1);
        Assert.assertNotNull(maintainList);
        maintainRepository.deleteById(m1);
    }
    @Test
    public void findComputerMaintain() throws Exception{

        List<Merchant> merchantList=merchantRepository.findComputerMaintain(1);
        Assert.assertNotNull(merchantList);
    }
    @Test
    public void findSupermarket() throws Exception{

        List<Merchant> merchantList=merchantRepository.findSupermarket(1);
        Assert.assertNotNull(merchantList);
    }
}
