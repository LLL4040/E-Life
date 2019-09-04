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
public class MerchantTest {
    private User user = new User();
    private Merchant merchant = new Merchant(user, "name", "phone", "address", "detail", "type");

    @Test
    public void merchantTest() {
        Assert.assertEquals(merchant.getUser(), user);
        Assert.assertEquals(merchant.getName(), "name");
        Assert.assertEquals(merchant.getPhone(), "phone");
        Assert.assertEquals(merchant.getAddress(), "address");
        Assert.assertEquals(merchant.getDetail(), "detail");
        Assert.assertEquals(merchant.getType(), "type");

        User user1 = new User();
        user1.setUsername("1");

        merchant.setUser(user1);
        merchant.setName("1");
        merchant.setPhone("2");
        merchant.setAddress("3");
        merchant.setDetail("4");
        merchant.setType("5");

        Assert.assertEquals(merchant.getUser(), user1);
        Assert.assertEquals(merchant.getName(), "1");
        Assert.assertEquals(merchant.getPhone(), "2");
        Assert.assertEquals(merchant.getAddress(), "3");
        Assert.assertEquals(merchant.getDetail(), "4");
        Assert.assertEquals(merchant.getType(), "5");

    }
}
