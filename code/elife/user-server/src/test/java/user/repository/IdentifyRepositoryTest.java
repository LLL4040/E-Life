package user.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.entity.Identify;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentifyRepositoryTest {
    @Autowired
    private IdentifyRepository identifyRepository;

    @Test
    public void identifyRepositoryTest(){
        Identify identify = new Identify("123456","123456","2019-07-05 09:04:30");
        Identify identifySave = identifyRepository.save(identify);
        Identify identifyResult = identifyRepository.findByPhone("123456");
        Assert.assertEquals("save or find fail", identifySave.getPhone(), identifyResult.getPhone());
        Assert.assertEquals("save or find fail", identifySave.getCode(), identifyResult.getCode());
        Assert.assertEquals("save or find fail", identifySave.getTime(), identifyResult.getTime());

        Assert.assertTrue(identifyRepository.existsByPhone("123456"));
        identifyRepository.deleteByPhone("123456");
        Assert.assertFalse(identifyRepository.existsByPhone("123456"));

    }
}
