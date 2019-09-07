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
public class ParticipateTest {
    private Participate participate = new Participate("username", 0L);

    @Test
    public void participateTest(){
        Assert.assertEquals("username", participate.getUsername());
        Assert.assertEquals(0, (long)participate.getDemand());

        participate = new Participate();
        participate.setId(1L);
        participate.setUsername("username");
        participate.setDemand(0L);

        Assert.assertEquals("username", participate.getUsername());
        Assert.assertEquals(0, (long)participate.getDemand());
        Assert.assertEquals(1, (long)participate.getId());

    }
}
