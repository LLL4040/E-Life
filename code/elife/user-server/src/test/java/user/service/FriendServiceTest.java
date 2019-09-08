package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import user.dao.UserDao;
import user.entity.Community;
import user.entity.User;
import user.repository.CommunityRepository;

import static org.mockito.Mockito.when;

import java.util.ArrayList;


/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendServiceTest {

    @Autowired
    private FriendServiceImpl friendService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private CommunityRepository communityRepository;

    @Test
    public void friendSearchListTest() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("username", "password", "phone", "email", 1, 1L);
        users.add(user);
        when(userDao.findAllByUsernameContains("username")).thenReturn(users);
        Community community = new Community("communityName", "communityInfo", "manager", "password",
                "phone", "email", "address", "account");
        community.setId(1L);
        ArrayList<Community> communities = new ArrayList<>();
        communities.add(community);
        when(communityRepository.findAll()).thenReturn(communities);

        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("username", "username");
        object.put("community", "communityName");
        jsonArray.appendElement(object);

        Assert.assertEquals(jsonArray, friendService.friendSearchList("username"));


    }
}
