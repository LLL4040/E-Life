package user.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentifyTest {
    private Identify identify = new Identify("123456","123456","2019-07-05 09:04:30");

    @Test
    public void usernameTest(){
        Assert.assertEquals("get phone fails", "123456", identify.getPhone());
        identify.setPhone("12345");
        Assert.assertEquals("set phone fails", "12345", identify.getPhone());
        identify.setPhone("123456");
    }

    @Test
    public void codeTest(){
        Assert.assertEquals("get code fails", "123456", identify.getCode());
        identify.setCode("12345");
        Assert.assertEquals("set code fails", "12345", identify.getCode());
        identify.setCode("123456");
    }

    @Test
    public void timeTest(){
        Assert.assertEquals("get time fails", "2019-07-05 09:04:30", identify.getTime());
        identify.setTime("2019-07-05 09:05:30");
        Assert.assertEquals("set time fails", "2019-07-05 09:05:30", identify.getTime());
        identify.setTime("2019-07-05 09:04:30");
    }
}
