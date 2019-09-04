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
public class CommunityTest {
    private Community community = new Community("communityName", "communityInfo", "manager", "password",
            "phone", "email", "address", "account");

    @Test
    public void communityTest() {

        Assert.assertEquals(community.getAccount(), "account");
        Assert.assertEquals(community.getAddress(), "address");
        Assert.assertEquals(community.getCommunityInfo(), "communityInfo");
        Assert.assertEquals(community.getCommunityName(), "communityName");
        Assert.assertEquals(community.getEmail(), "email");
        Assert.assertEquals(community.getManager(), "manager");
        Assert.assertEquals(community.getPassword(), "password");
        Assert.assertEquals(community.getPhone(), "phone");

        community.setAccount("1");
        community.setAddress("2");
        community.setCommunityInfo("3");
        community.setCommunityName("4");
        community.setEmail("5");
        community.setManager("6");
        community.setPassword("7");
        community.setPhone("8");

        Assert.assertEquals(community.getAccount(), "1");
        Assert.assertEquals(community.getAddress(), "2");
        Assert.assertEquals(community.getCommunityInfo(), "3");
        Assert.assertEquals(community.getCommunityName(), "4");
        Assert.assertEquals(community.getEmail(), "5");
        Assert.assertEquals(community.getManager(), "6");
        Assert.assertEquals(community.getPassword(), "7");
        Assert.assertEquals(community.getPhone(), "8");

    }
}
