package packageserver.controller;

import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import packageserver.service.PackageService;

@RequestMapping(path="/api/Package")
@RestController
/**
 * PackageController class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PackageController {
    @Autowired
    private PackageService packageService;

    @RequestMapping(path = "/savePackage")
    @ResponseBody
    public boolean savePackage(String time, String managerName, String username, int communityId ){
        return packageService.save(time,managerName,username,communityId);
    }
    @RequestMapping(path = "/findNew")
    @ResponseBody
    public JSONArray findNew(String username){
        return packageService.findNew(username);
    }
    @RequestMapping(path = "/deleteOne")
    @ResponseBody
    public boolean deleteOne(int id){
        return packageService.deleteOne(id);
    }
    @RequestMapping(path = "/takeOut")
    @ResponseBody
    public boolean takeOne(int id){
        return packageService.takeOut(id);
    }
    @RequestMapping(path = "/findOne")
    @ResponseBody
    public JSONArray findOne(int id){
        return packageService.findOne(id);
    }
    @RequestMapping(path = "/findHistory")
    @ResponseBody
    public JSONArray findHistory(String username){
        return packageService.findHistory(username);
    }
    @RequestMapping(path = "/findHistoryManager")
    @ResponseBody
    public JSONArray findHistoryManager(int communityId){
        return packageService.findHistoryManager(communityId);
    }
}
