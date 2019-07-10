package lifeservice.lifeservice;


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
    public void addMaintain() throws Exception {
        mvc.perform(get("/api/addMaintain").param("content","我家墙壁落灰了").param("username","哮天犬").param("userphone","1234"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void managerFindMaintain() throws Exception {
        mvc.perform(get("/api/managerFindMaintain").param("communityId","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void userFindMaintain() throws Exception {
        mvc.perform(get("/api/userFindMaintain").param("username","哪吒"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void manageMaintain() throws Exception {
        mvc.perform(get("/api/manageMaintain").param("id","12").param("status","1").param("maintainname","土地小老头").param("phone","12315").param("managername","二郎神"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void editMaintain() throws Exception {
        mvc.perform(get("/api/editMaintain").param("id","12").param("status","3"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void countMaintain() throws Exception {
        mvc.perform(get("/api/countMaintain").param("communityId","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
