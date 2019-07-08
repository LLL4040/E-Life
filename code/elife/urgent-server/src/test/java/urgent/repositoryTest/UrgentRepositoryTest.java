package urgent.repositoryTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import urgent.entity.Urgent;
import urgent.repository.UrgentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentRepositoryTest {

    @Autowired
    private UrgentRepository urgentRepository;
    @Test
    @Transactional
    public void saveUrgenttest(){
        urgentRepository.saveUrgent(55555,"1","test",1,1);
        Urgent urgentResult=this.urgentRepository.getOne(55555);
        Assert.assertEquals("urgent save fails", "1",urgentResult.getManagerName());
        Assert.assertEquals("urgent save fails", "test",urgentResult.getContent());
        Assert.assertEquals("urgent save fails", 1,urgentResult.getStatus());
        Assert.assertEquals("urgent save fails", 1,urgentResult.getCommunityId());


    }
}
