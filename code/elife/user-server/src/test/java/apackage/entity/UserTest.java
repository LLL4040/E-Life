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
public class UserTest {
    private User user = new User("username", "password", "phone", "email", 1, 1L);

    @Test
    public void userTest(){
        Assert.assertEquals(user.getUsername(), "username");
        Assert.assertEquals(user.getPassword(), "password");
        Assert.assertEquals(user.getPhone(), "phone");
        Assert.assertEquals(user.getEmail(), "email");
        Assert.assertEquals((int)user.getRole(), 1);
        Assert.assertEquals((long)user.getCommunityId(), 1L);

        user.setUsername("1");
        user.setPassword("2");
        user.setPhone("3");
        user.setEmail("4");
        user.setRole(5);
        user.setCommunityId(6L);

        Assert.assertEquals(user.getUsername(), "1");
        Assert.assertEquals(user.getPassword(), "2");
        Assert.assertEquals(user.getPhone(), "3");
        Assert.assertEquals(user.getEmail(), "4");
        Assert.assertEquals((int)user.getRole(), 5);
        Assert.assertEquals((long)user.getCommunityId(), 6L);

    }
}
