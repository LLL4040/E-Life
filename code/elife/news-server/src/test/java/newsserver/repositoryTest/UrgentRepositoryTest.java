package newsserver.repositoryTest;

import newsserver.entity.Urgent;
import newsserver.repository.UrgentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentRepositoryTest {

    @Autowired
    private UrgentRepository urgentRepository;
    @Test
    @Transactional
    public void getONETest(){
        urgentRepository.changeStatus(1);
        Urgent urgent = new Urgent(55556,"admin","test",0,1);
        urgentRepository.save(urgent);
        //urgentRepository.changeStatus(1);
        Urgent urgentResult=this.urgentRepository.getONE(55556);
        Assert.assertEquals("urgent findNew fails", 55556,urgentResult.getId());
        Assert.assertEquals("urgent findNew fails", "admin",urgentResult.getManagerName());
        Assert.assertEquals("urgent findNew fails", "test",urgentResult.getContent());
        Assert.assertEquals("urgent findNew fails", 0,urgentResult.getStatus());
        Assert.assertEquals("urgent findNew fails", 1,urgentResult.getCommunityId());

    }
    @Test
    public void changeStatus(){
        urgentRepository.changeStatus(1);
        Urgent urgent = new Urgent(55800,"admin","test",0,1);
        urgentRepository.save(urgent);
        urgentRepository.changeStatus(1);
        Urgent urgentResult=this.urgentRepository.getONE(55800);
        Assert.assertEquals("urgent findNew fails", 55800,urgentResult.getId());
        Assert.assertEquals("urgent findNew fails", "admin",urgentResult.getManagerName());
        Assert.assertEquals("urgent findNew fails", "test",urgentResult.getContent());
        Assert.assertEquals("urgent findNew fails", 1,urgentResult.getStatus());
        Assert.assertEquals("urgent findNew fails", 1,urgentResult.getCommunityId());
        urgentRepository.deleteById(55800);
    }

    @Test
    @Transactional
    public void saveUrgentgetNewTest(){
        urgentRepository.changeStatus(1);
        urgentRepository.saveUrgent("admin","oop",0,1);
        Urgent urgentResult=this.urgentRepository.findNew(1);
        Assert.assertEquals("urgent save fails", "admin",urgentResult.getManagerName());
        Assert.assertEquals("urgent save fails", "oop",urgentResult.getContent());
        Assert.assertEquals("urgent save fails", 0,urgentResult.getStatus());
        Assert.assertEquals("urgent save fails", 1,urgentResult.getCommunityId());

    }

    @Test
    @Transactional
    public void moveTableTest(){
        urgentRepository.changeStatus(1);
        Urgent urgent = new Urgent(5555,"admin","test",0,1);
        urgentRepository.save(urgent);
        urgentRepository.moveTable(1);
    }
    @Test
    @Transactional
    public void findHistoryTest(){
        urgentRepository.changeStatus(1);
        Urgent urgent = new Urgent(5556,"admin","test",0,1);
        urgentRepository.save(urgent);
        urgentRepository.findHistory(1);
    }

}
