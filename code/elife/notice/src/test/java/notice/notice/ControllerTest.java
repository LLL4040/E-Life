package notice.notice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void addNotice() throws Exception {
        mvc.perform(get("/api/addNotice").param("content","你水表欠费了今晚停水").param("managerName","二郎神").param("communityId","1").param("username","哮天犬").param("isMass","0"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void findMyNotice() throws Exception {
        mvc.perform(get("/api/findMyNotice").param("username","哮天犬"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void deleteOneNotice() throws Exception {

        mvc.perform(get("/api/deleteOneNotice").param("noticeId","9"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void deleteMyNotice() throws Exception {
        mvc.perform(get("/api/addNotice").param("content","你水表欠费了今晚停水").param("managerName","二郎神").param("communityId","1").param("username","哮天犬").param("isMass","0"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
        mvc.perform(get("/api/deleteMyNotice").param("username","哮天犬"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
