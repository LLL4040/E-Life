package estateforum.estateforum;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void deletePostTest() throws Exception{
        mvc.perform(get("/api/post/deletePost").param("id","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void addCommentsTest() throws Exception{
        mvc.perform(post("/api/postComments/addComments").param("pid","1").param("commenterName","我是小二").param("postComment","人家想和哥哥一起赏月嗷"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
    @Test
    public void deleteCommentsTest() throws Exception{
        mvc.perform(get("/api/postComments/deleteComments").param("pid","1").param("location","1"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

}
