package newsserver.controller;


import net.minidev.json.JSONObject;
import newsserver.entity.Notice;
import newsserver.entity.NoticeUser;
import newsserver.service.NoticeService;
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
 * @date 2019/07/05*/
@RequestMapping(path = "/api/notice")
@Controller

public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(path = "/addNotice")
    @ResponseBody
    public JSONObject addNotice(@RequestParam String content, @RequestParam String managerName, @RequestParam int communityId, @RequestParam String username, @RequestParam int isMass){
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        if("".equals(content)||"".equals(managerName)||communityId==0){
            object.put("addNotice", "0");
            object.put("message","信息不能为空");
            return object;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String noticeTime = df.format(new Date());
        Notice notice=new Notice(noticeTime,content,managerName,communityId);
        /**先在notice数据库中存*/
         int noticeId = noticeService.save(notice);

         if(isMass==0){
             NoticeUser noticeUser=new NoticeUser(noticeId,username);
             noticeService.saveNoticeUser(noticeUser);

         }else {
             List<String> usernameList=noticeService.findUsernameByCommunityId(communityId);
             int length=usernameList.size();
             for (int i=0;i<length;i++){
                 NoticeUser noticeUser1=new NoticeUser(noticeId,usernameList.get(i));
                 noticeService.saveNoticeUser(noticeUser1);
             }
         }
        object.put("addNotice", "1");
        object.put("message","发布物业通知成功");
        return object;
    }
    @RequestMapping(path = "/ManagerFindNotice")
    @ResponseBody
    public List<Notice> managerFindNotice(@RequestParam String managerName,@RequestParam int pageNumber,@RequestParam int pageSize){

        return noticeService.managerFindNotice(managerName,pageNumber,pageSize);
    }
    @RequestMapping(path = "/findMyNotice")
    @ResponseBody
    public List<Notice> findMyNotice(@RequestParam String username){

        return noticeService.findByUsername(username);
    }
    /**
     * 管理员功能，删除数据库中一条物业信息，包括notice表中和noticeUser表中*/
    @RequestMapping(path = "/deleteOneNotice")
    @ResponseBody
    public JSONObject deleteOneNotice(@RequestParam int noticeId){
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        if(noticeService.deleteByNotcieId(noticeId).equals("删除这一条物业信息成功")){
            object.put("deleteOneNotice", "1");
            object.put("message","删除这一条物业信息成功");
            return object;

        }
        object.put("deleteOneNotice", "0");
        object.put("message","删除这一条物业信息失败");
        return object;

    }
    /**
     * 用户功能，删除我的通知列表中的某一条通知*/
    @RequestMapping(path = "/deleteMyOneNotice")
    @ResponseBody
    public JSONObject deleteMyOneNotice(@RequestParam String username,@RequestParam int noticeId){
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        if(noticeService.deleteByUsernameAndNoticeId(username,noticeId).equals("删除我的该条物业信息成功")){
            object.put("eleteMyOneNotice", "1");
            object.put("message","删除我的该条物业信息成功");
            return object;

        }
        object.put("deleteMyOneNotice", "0");
        object.put("message","删除我的该条物业信息失败");
        return object;

    }
    /**
     * 用户功能，删除我的通知列表中的所有条通知*/
    @RequestMapping(path = "/deleteMyNotice")
    @ResponseBody
    public JSONObject deleteMyNotice(@RequestParam String username){
        noticeService.deleteAllByUsername(username);
        net.minidev.json.JSONObject object = new net.minidev.json.JSONObject();
        object.put("deleteMyNotice", "1");
        object.put("message","删除我的所有通知成功");
        return object;

    }
}
