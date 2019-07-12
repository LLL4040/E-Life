package group.controller;

import group.service.DemandService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public JSONObject addDemand(@RequestParam String startTime, @RequestParam String endTime, @RequestParam String content,
                                @RequestParam String username, @RequestParam Long communityId){
        return demandService.addDemand(startTime, endTime, content, username, communityId);
    }

    @RequestMapping(path = "/deleteDemand")
    @ResponseBody
    public JSONObject deleteDemand(@RequestParam Long id){
        return demandService.deleteDemand(id);
    }

    @RequestMapping(path = "/getAllDemand")
    @ResponseBody
    public JSONArray getAllDemand(@RequestParam Long communityId){
        return demandService.getAllDemand(communityId);
    }

    @RequestMapping(path = "/participateDemand")
    @ResponseBody
    public JSONObject participateDemand(@RequestParam String username, @RequestParam Long id){
        return demandService.participateDemand(username, id);
    }

    @RequestMapping(path = "/quitDemand")
    @ResponseBody
    public JSONObject quitDemand(@RequestParam String username, Long id){
        return demandService.quitDemand(username, id);
    }

}
