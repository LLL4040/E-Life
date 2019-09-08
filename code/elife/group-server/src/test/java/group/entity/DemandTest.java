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
public class DemandTest {
    private Demand demand = new Demand();

    @Test
    public void demandTest(){
        demand.setId(0L);
        demand.setCommunityId(1L);
        demand.setContent("content");
        demand.setStartTime("start");
        demand.setEndTime("end");
        demand.setTitle("title");
        demand.setUsername("username");

        Assert.assertEquals(0, (long)demand.getId());
        Assert.assertEquals(1, (long)demand.getCommunityId());
        Assert.assertEquals("content", demand.getContent());
        Assert.assertEquals("start", demand.getStartTime());
        Assert.assertEquals("end", demand.getEndTime());
        Assert.assertEquals("title", demand.getTitle());
        Assert.assertEquals("username", demand.getUsername());
    }
}
