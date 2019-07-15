package group.controller;

import group.service.DiscountService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/discount")
@RestController
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping(path = "/addDiscount")
    @ResponseBody
    public JSONObject addDiscount(@RequestParam String startTime, @RequestParam String endTime, @RequestParam Long merchantId,
                                  @RequestParam Integer number, @RequestParam String content, @RequestParam Integer status,
                                  @RequestParam Long communityId, @RequestParam String title){
        return discountService.addDiscount(startTime, endTime, merchantId, number, content, status, communityId, title);
    }

    @RequestMapping(path = "/deleteDiscount")
    @ResponseBody
    public JSONObject deleteDiscount(@RequestParam Long id){
        return discountService.deleteDiscount(id);
    }

    @RequestMapping(path = "/findDiscountByMerchantId")
    @ResponseBody
    public JSONArray findDiscountByMerchantId(@RequestParam Long merchantId){
        return discountService.findDiscountByMerchantId(merchantId);
    }

    @RequestMapping(path = "/findDiscountByCommunityId")
    @ResponseBody
    public JSONArray findDiscountByCommunityId(@RequestParam Long communityId){
        return discountService.findDiscountByCommunityId(communityId);
    }
}
