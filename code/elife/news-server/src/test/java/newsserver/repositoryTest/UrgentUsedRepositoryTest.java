package newsserver.repositoryTest;


import newsserver.repository.UrgentRepository;
import newsserver.repository.UrgentUsedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UrgentUsedRepositoryTest {

    @Autowired
    private UrgentUsedRepository urgentUsedRepository;
    @Autowired
    private UrgentRepository urgentRepository;
    @Test
    @Transactional
    public void findHistoryTest(){
        urgentRepository.saveUrgent("admin","test",0,1);
        urgentRepository.moveTable(1);
        urgentUsedRepository.findHistory(1);
    }

}

