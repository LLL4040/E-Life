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
public class BargainTest {
    private Bargain bargain = new Bargain();

    @Test
    public void bargainTest(){
        bargain.setStartTime("start");
        bargain.setEndTime("end");
        bargain.setContent("content");
        bargain.setGoods("goods");
        bargain.setId(1L);
        Merchant merchant = new Merchant();
        bargain.setMerchant(merchant);
        bargain.setStatus(0);
        bargain.setTitle("title");

        Assert.assertEquals("start", bargain.getStartTime());
        Assert.assertEquals("end", bargain.getEndTime());
        Assert.assertEquals("content", bargain.getContent());
        Assert.assertEquals("goods", bargain.getGoods());
        Assert.assertEquals(1, (long)bargain.getId());
        Assert.assertEquals(merchant, bargain.getMerchant());
        Assert.assertEquals(0, (int)bargain.getStatus());
        Assert.assertEquals("title", bargain.getTitle());
    }

}
