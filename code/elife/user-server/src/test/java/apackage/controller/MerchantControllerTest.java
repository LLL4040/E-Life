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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import user.service.MerchantService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ztHou
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MerchantController.class)
public class MerchantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MerchantService merchantService;

    @Test
    public void registerTest () throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", "username");
        params.add("password", "password");
        params.add("email", "email");
        params.add("name", "name");
        params.add("merchantPhone", "merchantPhone");
        params.add("type", "type");
        params.add("address", "address");
        params.add("detail", "detail");
        params.add("phone", "phone");
        params.add("code", "code");
        params.add("communityId", "1");
        mvc.perform(MockMvcRequestBuilders.post("/api/merchant/register").params(params))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void findAllByTypeTest () throws Exception {
        JSONArray jsonArray = new JSONArray();
        Mockito.when(merchantService.findAllByType("type")).thenReturn(jsonArray);
        mvc.perform(MockMvcRequestBuilders.post("/api/merchant/findAllByType").param("type", "type"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void findAllTest () throws Exception {
        JSONArray jsonArray = new JSONArray();
        Mockito.when(merchantService.findAll()).thenReturn(jsonArray);
        mvc.perform(MockMvcRequestBuilders.post("/api/merchant/findAll"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void getMerchantByUsernameTest () throws Exception {
        JSONObject object = new JSONObject();
        object.put("a", "b");
        Mockito.when(merchantService.getMerchantByUsername("username")).thenReturn(object);
        mvc.perform(MockMvcRequestBuilders.post("/api/merchant/getMerchantByUsername").param("username", "username"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.a").value("b"))
                .andDo(print()).andReturn();
    }
}
