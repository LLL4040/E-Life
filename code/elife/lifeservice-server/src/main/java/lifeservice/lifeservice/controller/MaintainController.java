package lifeservice.lifeservice.controller;

import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.service.MaintainService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@RequestMapping(path = "/api/maintain")
@Controller
public class MaintainController {
    @Autowired
    private MaintainService maintainService;

    @RequestMapping(path = "/addMaintain")
    @ResponseBody
    public JSONObject addMaintain(HttpServletRequest request, @RequestParam String content, @RequestParam String username, @RequestParam String userphone){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        } else {
            net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
            if ("".equals(content) || "".equals(username) || "".equals(userphone)) {
                object.put("addMaintain", "0");
                object.put("message", "信息不能为空");
                return object;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String maintainTime = df.format(new Date());
            Maintain maintain = new Maintain(maintainTime, content, username, userphone);
            long saveReult = maintainService.saveMaintian(maintain);
            System.out.println(saveReult);
            if (saveReult != 0) {
                object.put("addMaintain", "1");
                object.put("message", "增加物业维修请求成功");
                return object;
            } else {
                object.put("addMaintain", "0");
                object.put("message", "增加物业维护请求失败");
                return object;
            }
        }
    }
    /**
     * 难点*/
    @RequestMapping(path = "/managerFindUnMaintain")
    @ResponseBody
    public JSONArray managerFindUnMaintain(@RequestParam int communityId, @RequestParam int pageNumber, @RequestParam int pageSize){

        return maintainService.findMaintainByCommunityId(communityId,pageNumber,pageSize);
    }
    @RequestMapping(path = "/managerFindHaveMaintain")
    @ResponseBody
    public JSONArray managerFindHaveMaintain(@RequestParam int communityId,@RequestParam int pageNumber,@RequestParam int pageSize){

        return maintainService.findHaveMaintainByCommunityId(communityId,pageNumber,pageSize);
    }
    @RequestMapping(path = "/userFindMaintain")
    @ResponseBody
    public JSONArray userFindMaintain(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
        } else {
            List<Maintain> maintainList = maintainService.findbyUsernameAndStatus(username);
            for(Maintain maintain : maintainList){
                JSONObject object = new JSONObject();
                object.put("id", maintain.getId());
                object.put("time", maintain.getTime());
                object.put("content", maintain.getContent());
                object.put("status", maintain.getStatus());
                object.put("maintainname", maintain.getMaintainname());
                object.put("phone", maintain.getPhone());
                jsonArray.add(object);
            }
        }
        return jsonArray;
    }
    @RequestMapping(path = "/manageMaintain")
    @ResponseBody JSONObject manageMaintain(@RequestParam long id,@RequestParam int status,@RequestParam String maintainname,@RequestParam String phone,@RequestParam String managername){
        String managerResult= maintainService.managerMaintain(id,status,maintainname,phone,managername);
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        if(managerResult.equals("完成管理物业维修")){
            object.put("manageMaintain", "1");
            object.put("message","完成管理物业维修");
            return object;

        }
        object.put("manageMaintain", "0");
        object.put("message","管理物业维修失败");
        return object;
    }
    @RequestMapping(path = "/editMaintain")
    @ResponseBody JSONObject editMaintain(@RequestParam long id,@RequestParam int status){
        String editResult=maintainService.userEditMaintain(id, status);
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        if(editResult.equals("修改物业维修状态成功")){
            object.put("manageMaintain", "1");
            object.put("message","修改物业维修状态成功");
            return object;
        }
        object.put("manageMaintain", "0");
        object.put("message","修改物业维修状态失败");
        return object;
    }
    @RequestMapping(path = "/countMaintain")
    @ResponseBody
    public long countMaintain(@RequestParam int communityId){

        return maintainService.countMaintain(communityId);
    }
}
