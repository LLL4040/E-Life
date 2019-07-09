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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentRepositoryTest {

    @Autowired
    private UrgentRepository urgentRepository;
    @Test
    @Transactional
    public void findNewTest(){
        urgentRepository.changeStatus(1);
        urgentRepository.saveUrgent(55556,"1","test",0,1);
        Urgent urgentResult=this.urgentRepository.findNew(1);
        Assert.assertEquals("urgent findNew fails", 55556,urgentResult.getId());
        Assert.assertEquals("urgent findNew fails", "1",urgentResult.getManagerName());
        Assert.assertEquals("urgent findNew fails", "test",urgentResult.getContent());
        Assert.assertEquals("urgent findNew fails", 0,urgentResult.getStatus());
        Assert.assertEquals("urgent findNew fails", 1,urgentResult.getCommunityId());

    }
    @Test
    @Transactional
    public void saveUrgentTest(){
        urgentRepository.saveUrgent(55555,"1","test",0,1);
        Urgent urgentResult=this.urgentRepository.getOne(55555);
        Assert.assertEquals("urgent save fails", "1",urgentResult.getManagerName());
        Assert.assertEquals("urgent save fails", "test",urgentResult.getContent());
        Assert.assertEquals("urgent save fails", 0,urgentResult.getStatus());
        Assert.assertEquals("urgent save fails", 1,urgentResult.getCommunityId());

    }
    @Test
    @Transactional
    public void changStatusTest(){
        urgentRepository.saveUrgent(55555,"1","test",0,1);
        urgentRepository.changeStatus(1);
        Urgent urgentResult=this.urgentRepository.getOne(55555);
        Assert.assertEquals("urgent changeStatus fails", 1,urgentResult.getStatus());

    }
    @Test
    @Transactional
    public void moveTableTest(){
        urgentRepository.saveUrgent(55555,"1","test",0,1);
        urgentRepository.moveTable(1);
    }
    @Test
    @Transactional
    public void findHistoryTest(){
        urgentRepository.saveUrgent(55555,"1","test",0,1);
        urgentRepository.findHistory(1);
    }

}
