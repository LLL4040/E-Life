package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import user.service.MerchantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/merchant")
@RestController
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @RequestMapping(path = "/register")
    @ResponseBody
    public JSONObject register(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                               @RequestParam String name, @RequestParam String merchantPhone, @RequestParam String type,
                               @RequestParam String address, @RequestParam String detail, @RequestParam String phone,
                               @RequestParam String code, @RequestParam Long communityId){
        return merchantService.register(username, password, email, name, merchantPhone, type, address, detail, phone, code, communityId);
    }

    @RequestMapping(path = "/findAllByType")
    @ResponseBody
    public JSONArray findAllByType(HttpServletRequest request, @RequestParam String type){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return merchantService.findAllByType(type);
    }

    @RequestMapping(path = "/findAll")
    public JSONArray findAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(name)) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return merchantService.findAll();
    }

    @RequestMapping(path = "/changeMerchant")
    @ResponseBody
    public JSONObject changeMerchant(@RequestParam Long id, @RequestParam String name, @RequestParam String merchantPhone,
                                     @RequestParam String type, @RequestParam String address, @RequestParam String detail){
        return merchantService.changeMerchant(id, name, merchantPhone, type, address, detail);
    }

    @RequestMapping(path = "/deleteMerchant")
    @ResponseBody
    public JSONObject deleteMerchant(@RequestParam Long id){
        return merchantService.deleteMerchant(id);
    }

    @RequestMapping(path = "/getMerchantById")
    @ResponseBody
    public JSONObject getMerchant(HttpServletRequest request, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return merchantService.getMerchant(id);
    }

    @RequestMapping(path = "/getMerchantByUsername")
    @ResponseBody
    public JSONObject getMerchantByUsername(@RequestParam String username){
        return merchantService.getMerchantByUsername(username);
    }
}
