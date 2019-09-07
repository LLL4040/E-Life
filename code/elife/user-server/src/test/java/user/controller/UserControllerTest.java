package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import user.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void usernameAvailableTest () throws Exception {
        JSONObject object = new JSONObject();
        object.put("a", "b");
        Mockito.when(userService.usernameAvailable("username")).thenReturn(object);
        mvc.perform(MockMvcRequestBuilders.post("/api/user/usernameAvailable").param("username", "username"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.a").value("b"))
                .andDo(print()).andReturn();
    }

    @Test
    public void getManagersTest () throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/user/getManagers").param("id", "1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void getCommunityByIdTest () throws Exception {
        JSONObject object = new JSONObject();
        object.put("a", "b");
        Mockito.when(userService.getCommunityById(1L)).thenReturn(object);
        mvc.perform(MockMvcRequestBuilders.post("/api/user/getCommunityById").param("id", "1"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.a").value("b"))
                .andDo(print()).andReturn();
    }

    @Test
    public void showCommunitiesTest () throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("a", "b");
        jsonArray.appendElement(object);
        Mockito.when(userService.showCommunities()).thenReturn(jsonArray);
        mvc.perform(MockMvcRequestBuilders.post("/api/user/communities"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].a").value("b"))
                .andDo(print()).andReturn();
    }


}
