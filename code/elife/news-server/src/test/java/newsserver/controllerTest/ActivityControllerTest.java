package newsserver.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ActivityControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void Test2() throws Exception {
        MvcResult result = mvc.perform(get("/api/Activity/findAllActivity")
                .param("communityId", "1")
                .param("page","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void Test3() throws Exception {
        MvcResult result = mvc.perform(get("/api/Activity/findNewActivity")
                .param("communityId", "1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void Test4() throws Exception {
        MvcResult result = mvc.perform(get("/api/Activity/deleteActivity").param("aid", "55555"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void Test5() throws Exception {
        MvcResult result = mvc.perform(get("/api/Activity/submitActivity").param("aid", "55555")
                .param("communityId", "1").param("managerName","admin"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void Test9() throws Exception {
        MvcResult result = mvc.perform(get("/api/Activity/photo").param("communityId", "guigui.jpg"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

}
