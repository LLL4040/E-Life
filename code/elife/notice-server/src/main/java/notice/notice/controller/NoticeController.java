package notice.notice.controller;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import notice.notice.repository.NoticeUserRepository;
import notice.notice.service.NoticeService;
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
@RequestMapping(path = "/api")
@Controller

public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(path = "/addNotice")
    @ResponseBody
    public String addNotice(@RequestParam String content,@RequestParam String managerName,@RequestParam int communityId, @RequestParam String username,@RequestParam int isMass){
        if("".equals(content)||"".equals(managerName)||communityId==0){
            return "信息不能为空";
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
        return "发布物业通知成功";
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
    public String deleteOneNotice(@RequestParam int noticeId){
        if(noticeService.deleteByNotcieId(noticeId).equals("删除这一条物业信息成功")){
            return "删除这一条物业信息成功";
        }
        return "删除这一条物业信息失败";
    }
    /**
     * 用户功能，删除我的通知列表中的某一条通知*/
    @RequestMapping(path = "/deleteMyOneNotice")
    @ResponseBody
    public String deleteMyOneNotice(@RequestParam String username,@RequestParam int noticeId){
        if(noticeService.deleteByUsernameAndNoticeId(username,noticeId).equals("删除我的该条物业信息成功")){
            return "删除我的该条物业信息成功";
        }
        return "删除我的该条物业信息失败";
    }
    /**
     * 用户功能，删除我的通知列表中的所有条通知*/
    @RequestMapping(path = "/deleteMyNotice")
    @ResponseBody
    public String deleteMyNotice(@RequestParam String username){
        noticeService.deleteAllByUsername(username);
        return "删除我的所有通知成功";
    }
}
