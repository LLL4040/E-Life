package packageserver.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import packageserver.service.PackageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public JSONArray findNew(HttpServletRequest request, String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("0".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
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
    public JSONArray findHistory(String username,int page){
        return packageService.findHistory(username,page);
    }
    @RequestMapping(path = "/findHistoryManager")
    @ResponseBody
    public JSONArray findHistoryManager(int communityId,int page){
        return packageService.findHistoryManager(communityId,page);
    }
}
