package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import user.dao.ManagerDao;
import user.dao.UserDao;
import user.entity.Community;
import user.entity.Manager;
import user.entity.User;
import user.repository.CommunityRepository;


import java.util.ArrayList;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserDao userDao;
    @MockBean
    private ManagerDao managerDao;
    @MockBean
    private IdentifyService identifyService;
    @MockBean
    private CommunityRepository communityRepository;

    @Test
    public void findUsernameByPhoneTest () {
        User user = new User("username", "password", "phone", "email", 1, 1L);
        Mockito.when(userDao.findByPhone("phone")).thenReturn(user);
        Assert.assertEquals("username", userService.findUsernameByPhone("phone"));
    }

    @Test
    public void registerUserTest () {
        Mockito.when(identifyService.verifyIdentifyCode("phone", "code")).thenReturn(true);
        JSONObject result = new JSONObject();
        result.put("register", 1);
        Assert.assertEquals(result, userService.registerUser("username", "password", "phone", "code", "email", 1L));
    }

    @Test
    public void loginManagerTest () {
        JSONObject result = new JSONObject();
        Mockito.when(managerDao.existByUsername("username")).thenReturn(true);
        Manager manager = new Manager("username", "password", "phone", "email", 1, 1L);
        Mockito.when(managerDao.findByUsername("username")).thenReturn(manager);
        result.put("login", 1);
        result.put("username", manager.getUsername());
        result.put("communityId", manager.getCommunityId());
        result.put("phone", manager.getPhone());
        result.put("email", manager.getEmail());
        result.put("final", "0");
        Assert.assertEquals(result, userService.loginManager("username", "password"));

        result = new JSONObject();
        Mockito.when(managerDao.existByUsername("username")).thenReturn(false);
        Mockito.when(communityRepository.existsByManager("username")).thenReturn(true);
        Community community = new Community("communityName", "communityInfo", "manager", "password",
                "phone", "email", "address", "account");
        Mockito.when(communityRepository.findByManager("username")).thenReturn(community);
        result.put("login", 1);
        result.put("username", community.getManager());
        result.put("communityId", community.getId());
        result.put("phone", community.getPhone());
        result.put("email", community.getEmail());
        result.put("final", "1");
        Assert.assertEquals(result, userService.loginManager("username", "password"));
    }

    @Test
    public void showCommunitiesTest () {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Community> communityList = new ArrayList<>();
        Community community = new Community("communityName", "communityInfo", "manager", "password",
                "phone", "email", "address", "account");
        communityList.add(community);
        Mockito.when(communityRepository.findAll()).thenReturn(communityList);
        JSONObject object = new JSONObject();
        object.put("id", community.getId());
        object.put("name", community.getCommunityName());
        object.put("information", community.getCommunityInfo());
        jsonArray.appendElement(object);
        Assert.assertEquals(jsonArray, userService.showCommunities());
    }
}
