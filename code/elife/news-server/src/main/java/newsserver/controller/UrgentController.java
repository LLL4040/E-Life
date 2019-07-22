package newsserver.controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.service.UrgentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;


@RequestMapping(path="/api/Urgent")
@RestController
/**
 * UrgentController class
 * @author wyx
 * @date 2019.07.04
 */
public class UrgentController {

    @Autowired
    private UrgentService urgentService;

    @RequestMapping("/saveUrgent")
    public boolean saveUrgent(String managerName, String content, int communityId){
        return urgentService.save(managerName, content, 0, communityId);
    }

    @RequestMapping("/getUrgent")
    public JSONObject findUrgent(int id) {
        return urgentService.findOne(id);
    }

    @RequestMapping("/getNewUrgent")
    public JSONObject findUrgentNew(int communityId) {

        System.out.println("1");
        return urgentService.findNew(communityId);
    }
    @RequestMapping("/deleteUrgent")
    public boolean deleteUrgent(int id){
        return urgentService.deleteOne(id);
    }

    @RequestMapping("/moveTable")
        public boolean moveTable(){
        return urgentService.moveTable();
    }

    @RequestMapping("/findHistory")
    public JSONArray findHistory(int communityId,int page){
        return urgentService.findHistory(communityId,page);
    }

}
