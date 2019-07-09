package urgent.controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import urgent.service.UrgentService;
import org.springframework.beans.factory.annotation.Autowired;


@RequestMapping(path="/api/urgent")
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
    public void saveUrgent(int id, String managerName, String content, int status, int communityId){
        urgentService.save(id, managerName, content, status, communityId);
    }

    @RequestMapping("/getUrgent")
    public JSONObject findUrgent(int id) {
        return urgentService.findOne(id);
    }

    @RequestMapping("/getNewUrgent")
    public JSONObject findUrgentNew(int communityId) {
        return urgentService.findNew(communityId);
    }
    @RequestMapping("/deleteUrgent")
    public boolean deleteUrgent(int id){
        return urgentService.deleteOne(id);
    }

    @RequestMapping("/moveTable")
        public void moveTable(){
        urgentService.moveTable();
    }

    @RequestMapping("/findHistory")
    public JSONArray findHistory(int communityId){
        return urgentService.findHistory(communityId);
    }

}
