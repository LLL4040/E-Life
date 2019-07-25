package newsserver.repositoryTest;


import newsserver.entity.Participator;
import newsserver.repository.ParticipatorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipatorRepositoryTest {
    @Autowired
    private ParticipatorRepository participatorRepository;

    @Test
    @Transactional
    public void SaveParticipatorTest(){
        participatorRepository.savePaticipator(1,"hello",0,"1");
    }
    @Test
    @Transactional
    public void findAllParticipatorTest(){
        Participator participator = new Participator(88888,1,"1","test",0);
        participatorRepository.save(participator);
        List<Participator> participators = participatorRepository.findAllParticipator(1,0,10);
        Participator participator1 = participators.get(participators.size()-1);
        Assert.assertEquals("findAllParticipatorTest fails", 88888, participator1.getId());
    }

    @Test
    @Transactional
    public void deleteAllTest(){
        Participator participator = new Participator(88888,1,"1","test",0);
        participatorRepository.save(participator);
        participatorRepository.deleteAll(1);
        try{
            participatorRepository.getOne(88888);

        }
        catch (Exception e){
        }
    }

    @Test
    @Transactional
    public void exitsApply(){
        Participator participator = new Participator(88888,1,"1","test",0);
        participatorRepository.save(participator);
        List<Participator> list = participatorRepository.existApply(1,"1");
    }

}
