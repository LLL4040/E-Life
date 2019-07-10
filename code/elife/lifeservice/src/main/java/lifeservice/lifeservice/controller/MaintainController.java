package lifeservice.lifeservice.controller;

import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@RequestMapping(path = "/api")
@Controller
public class MaintainController {
    @Autowired
    private MaintainService maintainService;

    @RequestMapping(path = "/addMaintain")
    @ResponseBody
    public String addMaintain(@RequestParam String content, @RequestParam String usernname){
        if("".equals(content)||"".equals(usernname)){
            return "信息不能为空";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String maintainTime = df.format(new Date());
        Maintain maintain=new Maintain(maintainTime,content,usernname);
        if(maintainService.saveMaintian(maintain)!=0){
            return "增加物业维修请求成功";
        }else {
            return "增加物业维护请求失败";
        }

    }
    @RequestMapping(path = "/managerFindMaintain")
    @ResponseBody
    public List<Maintain> managerFindMaintain(@RequestParam int communityId){

        return null;
    }
    @RequestMapping(path = "/userFindMaintain")
    @ResponseBody
    public List<Maintain> userFindMaintain(@RequestParam String username,@RequestParam int status){
        List<Maintain> maintainList= maintainService.findbyUsernameAndStatus(username,status);
        if(!maintainList.isEmpty()){
            return maintainList;
        }
        else {
            return null;
        }
    }
    @RequestMapping(path = "/manageMaintain")
    @ResponseBody String manageMaintain(@RequestParam long id,@RequestParam String maintainname,@RequestParam String phone,@RequestParam String managername){
        String managerResult= maintainService.managerMaintain(id,maintainname,phone,managername);
        if(managerResult.equals("完成管理物业维修")){
            return "完成管理物业维修";
        }
        return "管理物业维修失败";
    }
    @RequestMapping(path = "/editMaintain")
    @ResponseBody String editMaintain(@RequestParam long id,@RequestParam int status){
        String editResult=maintainService.userEditMaintain(id, status);
        if(editResult.equals("修改物业维修状态成功")){
            return "修改物业维修状态成功";
        }
        return "修改物业维修状态失败";
    }
}
