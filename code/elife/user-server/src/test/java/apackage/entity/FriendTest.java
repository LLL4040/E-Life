package apackage.entity;

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
public class FriendTest {
    private Friend friend = new Friend("username", "friend", 0, "content");

    @Test
    public void friendTest() {
        Assert.assertEquals(friend.getUsername(), "username");
        Assert.assertEquals(friend.getFriend(), "friend");
        Assert.assertEquals((long)friend.getStatus(), 0);
        Assert.assertEquals(friend.getContent(), "content");

        friend.setUsername("1");
        friend.setFriend("2");
        friend.setContent("3");
        friend.setStatus(1);

        Assert.assertEquals(friend.getUsername(), "1");
        Assert.assertEquals(friend.getFriend(), "2");
        Assert.assertEquals((long)friend.getStatus(), 1);
        Assert.assertEquals(friend.getContent(), "3");


    }
}
