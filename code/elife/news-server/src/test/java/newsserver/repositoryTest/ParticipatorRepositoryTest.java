package newsserver.repositoryTest;


import newsserver.entity.Activity;
import newsserver.entity.Participator;
import newsserver.repository.ActivityRepository;
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
    @Autowired
    private ActivityRepository activityRepository;
     Activity activity = new Activity(1,"2019-07-09 11:22:11","2019-07-09 11:22:11","hello","admin",
            "test",0,"guigui.jpg",1);


    @Test
    @Transactional
    public void SaveParticipatorTest(){
        activityRepository.save(activity);
        participatorRepository.savePaticipator(1,"hello",0,"user");
    }
    @Test
    @Transactional
    public void findAllParticipatorTest(){
        activityRepository.save(activity);
        Participator participator = new Participator(88888,1,"user","test",0);
        participatorRepository.save(participator);
        List<Participator> participators = participatorRepository.findAllParticipator(1,0,10);
        Participator participator1 = participators.get(participators.size()-1);
        Assert.assertEquals("findAllParticipatorTest fails", 88888, participator1.getId());
    }

    @Test
    @Transactional
    public void deleteAllTest(){
        activityRepository.save(activity);
        Participator participator = new Participator(88888,1,"user","test",0);
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
        activityRepository.save(activity);
        Participator participator = new Participator(88888,1,"user","test",0);
        participatorRepository.save(participator);
        List<Participator> list = participatorRepository.existApply(1,"user");
    }

}
