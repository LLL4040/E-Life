package user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.dao.IdentifyDao;
import user.entity.Identify;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentifyServiceTest {
    @Autowired
    private IdentifyService identifyService;
    @Autowired
    private IdentifyDao identifyDao;

    @Test
    public void identifyServiceTest() {
        String phone = "18621805582";
        Assert.assertTrue(identifyService.sendIdentifyCode(phone));
        Identify identify = identifyDao.findByPhone(phone);
        Assert.assertFalse(identifyService.verifyIdentifyCode(phone, "1111111"));
        Assert.assertTrue(identifyService.verifyIdentifyCode(phone, identify.getCode()));

    }
}
