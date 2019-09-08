package group.entity;

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
    private Merchant merchant = new Merchant("username", "name", "phone",
            "address", "detail", "type");

    @Test
    public void merchantTest(){
        Assert.assertEquals("username", merchant.getUsername());
        Assert.assertEquals("name", merchant.getName());
        Assert.assertEquals("phone", merchant.getPhone());
        Assert.assertEquals("address", merchant.getAddress());
        Assert.assertEquals("detail", merchant.getDetail());
        Assert.assertEquals("type", merchant.getType());

        merchant = new Merchant();
        merchant.setId(0L);
        merchant.setUsername("username");
        merchant.setName("name");
        merchant.setPhone("phone");
        merchant.setAddress("address");
        merchant.setDetail("detail");
        merchant.setType("type");

        Assert.assertEquals(0, (long)merchant.getId());
        Assert.assertEquals("username", merchant.getUsername());
        Assert.assertEquals("name", merchant.getName());
        Assert.assertEquals("phone", merchant.getPhone());
        Assert.assertEquals("address", merchant.getAddress());
        Assert.assertEquals("detail", merchant.getDetail());
        Assert.assertEquals("type", merchant.getType());
    }
}
