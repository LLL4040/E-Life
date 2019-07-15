package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import user.service.MerchantService;

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
    public JSONArray findAllByType(@RequestParam String type){
        return merchantService.findAllByType(type);
    }

    @RequestMapping(path = "/findAll")
    public JSONArray findAll(){
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
    public JSONObject getMerchant(@RequestParam Long id){
        return merchantService.getMerchant(id);
    }

    @RequestMapping(path = "/getMerchantByUsername")
    @ResponseBody
    public JSONObject getMerchantByUsername(@RequestParam String username){
        return merchantService.getMerchantByUsername(username);
    }
}
