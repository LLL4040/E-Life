package user.service;

import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import user.dao.MerchantDao;
import user.dao.UserDao;
import user.entity.Merchant;
import user.entity.User;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantServiceTest {

    @Autowired
    private MerchantServiceImpl merchantService;

    @MockBean
    private MerchantDao merchantDao;

    @MockBean
    private UserDao userDao;

    @MockBean
    private UserService userService;

    @Test
    public void registerTest() {
        JSONObject object = new JSONObject();
        object.put("register", 1);
        Mockito.when(userService.registerMerchant("username", "password", "phone",
                "code", "email", 1L)).thenReturn(object);
        User user = new User("username", "password", "phone", "email", 1, 1L);
        Mockito.when(userDao.findByUsername("username")).thenReturn(user);
        Assert.assertEquals(object, merchantService.register("username", "password", "email",
                "name", "merchant", "type", "address", "detail", "phone", "code", 1L));
    }

    @Test
    public void getMerchantTest() {
        User user = new User();
        Merchant merchant = new Merchant(user, "name", "phone", "address", "detail", "type");
        Mockito.when(merchantDao.findById(1L)).thenReturn(merchant);
        JSONObject object = new JSONObject();
        object.put("id", merchant.getId());
        object.put("merchantPhone", merchant.getPhone());
        object.put("detail", merchant.getDetail());
        object.put("type", merchant.getType());
        object.put("address", merchant.getAddress());
        object.put("name", merchant.getName());
        object.put("username", merchant.getUser().getUsername());
        object.put("email", merchant.getUser().getEmail());
        object.put("phone", merchant.getUser().getPhone());
        object.put("communityId", merchant.getUser().getCommunityId());
        Assert.assertEquals(object, merchantService.getMerchant(1L));
    }

    @Test
    public void getMerchantByUsernameTest() {
        User user = new User();
        Merchant merchant = new Merchant(user, "name", "phone", "address", "detail", "type");
        Mockito.when(merchantDao.findByUsername("name")).thenReturn(merchant);
        JSONObject object = new JSONObject();
        object.put("id", merchant.getId());
        object.put("name", merchant.getName());
        object.put("merchantPhone", merchant.getPhone());
        object.put("detail", merchant.getDetail());
        object.put("type", merchant.getType());
        object.put("address", merchant.getAddress());
        Assert.assertEquals(object, merchantService.getMerchantByUsername("name"));
    }

}
