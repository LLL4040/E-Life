package group.controller;

import group.service.DemandService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/demand")
@RestController
public class DemandController {
    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @RequestMapping(path = "/addDemand")
    @ResponseBody
    public JSONObject addDemand(HttpServletRequest request, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String content,
                                @RequestParam String username, @RequestParam Long communityId, @RequestParam String title){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return demandService.addDemand(startTime, endTime, content, username, communityId, title);
    }

    @RequestMapping(path = "/deleteDemand")
    @ResponseBody
    public JSONObject deleteDemand(HttpServletRequest request, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return demandService.deleteDemand(id);
    }

    @RequestMapping(path = "/getAllDemand")
    @ResponseBody
    public JSONArray getAllDemand(HttpServletRequest request, @RequestParam Long communityId){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return demandService.getAllDemand(communityId);
    }

    @RequestMapping(path = "/participateDemand")
    @ResponseBody
    public JSONObject participateDemand(HttpServletRequest request, @RequestParam String username, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return demandService.participateDemand(username, id);
    }

    @RequestMapping(path = "/quitDemand")
    @ResponseBody
    public JSONObject quitDemand(@RequestParam String username, Long id){
        return demandService.quitDemand(username, id);
    }

}
