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
public class DiscountTest {
    private Discount discount = new Discount("start", "end", 0L, 1, "content",
            2, 3L, "title", "photo");
    @Test
    public void discountTest(){
        Assert.assertEquals("start", discount.getStartTime());
        Assert.assertEquals("end", discount.getEndTime());
        Assert.assertEquals(0, (long)discount.getMerchantId());
        Assert.assertEquals(1, (int)discount.getNumber());
        Assert.assertEquals("content", discount.getContent());
        Assert.assertEquals(2, (int)discount.getStatus());
        Assert.assertEquals(3, (long)discount.getCommunityId());
        Assert.assertEquals("title", discount.getTitle());
        Assert.assertEquals("photo", discount.getPhoto());

        discount = new Discount();
        discount.setId(4L);
        discount.setStartTime("start");
        discount.setEndTime("end");
        discount.setMerchantId(0L);
        discount.setNumber(1);
        discount.setContent("content");
        discount.setStatus(2);
        discount.setCommunityId(3L);
        discount.setTitle("title");
        discount.setPhoto("photo");

        Assert.assertEquals("start", discount.getStartTime());
        Assert.assertEquals("end", discount.getEndTime());
        Assert.assertEquals(0, (long)discount.getMerchantId());
        Assert.assertEquals(1, (int)discount.getNumber());
        Assert.assertEquals("content", discount.getContent());
        Assert.assertEquals(2, (int)discount.getStatus());
        Assert.assertEquals(3, (long)discount.getCommunityId());
        Assert.assertEquals("title", discount.getTitle());
        Assert.assertEquals("photo", discount.getPhoto());
        Assert.assertEquals(4, (long)discount.getId());


    }
}
