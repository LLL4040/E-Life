package user.dao;

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
public class IdentifyDaoTest {
    @Autowired
    private IdentifyDao identifyDao;

    @Test
    public void identifyDaoTest() {
        Identify identify = new Identify("123456", "123456", "2019-07-05 09:04:30");
        Identify identifySave = identifyDao.save(identify);
        Assert.assertTrue(identifyDao.exists("123456"));
        Identify identifyResult = identifyDao.findByPhone("123456");
        Assert.assertEquals("identifyDao fail", identifySave.getPhone(), identifyResult.getPhone());
        Assert.assertEquals("identifyDao fail", identifySave.getCode(), identifyResult.getCode());
        Assert.assertEquals("identifyDao fail", identifySave.getTime(), identifyResult.getTime());
        identifyDao.deleteByPhone("123456");
        Assert.assertFalse(identifyDao.exists("123456"));
    }
}
