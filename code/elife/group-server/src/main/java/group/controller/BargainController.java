package group.controller;

import group.service.BargainService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/bargain")
@RestController
public class BargainController {
    private final BargainService bargainService;

    public BargainController(BargainService bargainService) {
        this.bargainService = bargainService;
    }

    @RequestMapping(path = "/addBargain")
    @ResponseBody
    public JSONObject addBargain(@RequestParam String startTime, @RequestParam String endTime, @RequestParam String title,
                                 @RequestParam Long merchantId, @RequestParam String goods, @RequestParam String content,
                                 @RequestParam Integer status){
        return bargainService.addBargain(startTime, endTime, title, merchantId, goods, content, status);
    }

    @RequestMapping(path = "/deleteBargain")
    @ResponseBody
    public JSONObject deleteBargain(@RequestParam Long id){
        return bargainService.deleteBargain(id);
    }

    @RequestMapping(path = "/getAllBargain")
    @ResponseBody
    public JSONArray getAllBargain(){
        return bargainService.getAllBargain();
    }

    @RequestMapping(path = "/getBargainByMerchantId")
    @ResponseBody
    public JSONArray getBargainByMerchantId(@RequestParam Long id){
        return bargainService.getBargainByMerchantId(id);
    }
}
