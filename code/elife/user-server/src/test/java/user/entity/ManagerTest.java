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
public class ManagerTest {
    private Manager manager = new Manager("username", "password", "phone", "email", 1, 1L);

    @Test
    public void managerTest(){
        Assert.assertEquals(manager.getUsername(), "username");
        Assert.assertEquals(manager.getPassword(), "password");
        Assert.assertEquals(manager.getPhone(), "phone");
        Assert.assertEquals(manager.getEmail(), "email");
        Assert.assertEquals((int)manager.getRole(), 1);
        Assert.assertEquals((long)manager.getCommunityId(), 1L);

        manager.setUsername("1");
        manager.setPassword("2");
        manager.setPhone("3");
        manager.setEmail("4");
        manager.setRole(5);
        manager.setCommunityId(6L);

        Assert.assertEquals(manager.getUsername(), "1");
        Assert.assertEquals(manager.getPassword(), "2");
        Assert.assertEquals(manager.getPhone(), "3");
        Assert.assertEquals(manager.getEmail(), "4");
        Assert.assertEquals((int)manager.getRole(), 5);
        Assert.assertEquals((long)manager.getCommunityId(), 6L);

    }

}
