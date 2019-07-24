package group.controller;

import group.service.DiscountService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public JSONObject addDiscount(HttpServletRequest request, @RequestParam String startTime, @RequestParam String endTime, @RequestParam Long merchantId,
                                  @RequestParam Integer number, @RequestParam String content, @RequestParam Integer status,
                                  @RequestParam Long communityId, @RequestParam String title, @RequestParam MultipartFile photo){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("2".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return discountService.addDiscount(startTime, endTime, merchantId, number, content, status, communityId, title, photo);
    }

    @RequestMapping(path = "/deleteDiscount")
    @ResponseBody
    public JSONObject deleteDiscount(HttpServletRequest request, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("2".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return discountService.deleteDiscount(id);
    }

    @RequestMapping(path = "/findDiscountByMerchantId")
    @ResponseBody
    public JSONArray findDiscountByMerchantId(HttpServletRequest request, @RequestParam Long merchantId){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("2".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return discountService.findDiscountByMerchantId(merchantId);
    }

    @RequestMapping(path = "/findDiscountByCommunityId")
    @ResponseBody
    public JSONArray findDiscountByCommunityId(HttpServletRequest request, @RequestParam Long communityId){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return discountService.findDiscountByCommunityId(communityId);
    }

    @RequestMapping(path = "/photo")
    @ResponseBody
    public JSONObject getBigPhoto(HttpServletRequest request, String path){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return discountService.getBigPhoto(path);
    }
}
