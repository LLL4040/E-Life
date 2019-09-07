package user.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import user.service.FriendService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class FriendControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FriendService friendService;

    @Test
    public void friendListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/friendList").param("username", "111111"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void friendSearchListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/friendSearchList").param("username", "111111"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void RequestNumberTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/requestNumber").param("username", "111111"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void ResponseListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/responseList").param("username", "111111"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void requestListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/requestList").param("username", "111111").param("username", "111111"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void sendFriendRequestTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/sendFriendRequest").param("username", "111111")
        .param("friend", "22222").param("content", "content"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void acceptRequestTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/acceptRequest").param("username", "111111").param("id", "1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void rejectRequestTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/friend/rejectRequest").param("username", "111111").param("id", "1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }


}
