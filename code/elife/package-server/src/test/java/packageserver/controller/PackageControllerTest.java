package packageserver.controller;

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
public class PackageControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void savePackageTest() throws Exception {
        mvc.perform(get("/api/Package/savePackage").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
        .param("communityId","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void findNewTest() throws Exception {
        mvc.perform(get("/api/Package/findNew").param("username","user"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void deleteOne() throws Exception {
        mvc.perform(get("/api/Package/deleteOne").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
                .param("communityId","1").param("id","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void takeOut() throws Exception {
        mvc.perform(get("/api/Package/takeOut").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
                .param("communityId","1").param("id","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void findOneTest() throws Exception {
        mvc.perform(get("/api/Package/findOne").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
                .param("communityId","1").param("id","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void findHistoryTest() throws Exception {
        mvc.perform(get("/api/Package/findHistory").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
                .param("communityId","1").param("Id","1").param("page","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void findHistoryManagerTest() throws Exception {
        mvc.perform(get("/api/Package/findHistoryManager").param("time","2019-09-09 11:11:11")
                .param("managerName","admin").param("username","user")
                .param("communityId","1").param("Id","1").param("page","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
