package newsserver.controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(path="/api/Activity")
@RestController
/**
 * ActivityController Class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class ActivityController {
    @Autowired
    ActivityService activityService;

    /**
     * save Activity without id
     * @param startTime
     * @param endTime
     * @param content
     * @param managerName
     * @param title
     * @param photo
     * @param communityId
     * @return true or false
     * @throws IOException
     */
    @PostMapping(path="/saveActivity") // Map ONLY GET Requests
    @ResponseBody
    public boolean saveActivity(String startTime, String endTime, String content,
                                String managerName, String title, MultipartFile photo, int communityId) throws IOException {
        return activityService.saveActivity(startTime,endTime,content,managerName,title,0,photo,communityId);
    }

    /**
     * find All Activity by communityId
     * @param communityId
     * @return JSONArray
     * @throws IOException
     */
    @RequestMapping(path = "/findAllActivity")
    @ResponseBody
    public JSONArray findAllActivity(int communityId,int page) throws IOException {
        return activityService.findAllActivity(communityId,page);
    }

    /**
     * find 5 Activity by communityId
     * @param communityId
     * @return JSONArray
     * @throws IOException
     */
    @RequestMapping(path = "/findNewActivity")
    @ResponseBody
    public JSONArray findNewActivity(int communityId) throws IOException {
        return activityService.findNewActivity(communityId);
    }

    /**
     * deleteActivity
     * @param aid
     * @return boolean
     */
    @RequestMapping(path = "/deleteActivity")
    @ResponseBody
    public boolean deleteActivity(int aid){
        return activityService.deleteActivity(aid);
    }

    /**
     * submitActivity close it
     * @param aid
     * @return boolean
     */
    @RequestMapping(path = "/submitActivity")
    @ResponseBody
    public boolean submitActivity(int aid){
        return activityService.submitActivity(aid);
    }

    /**
     * save participant
     * @param aid
     * @param content
     * @param username
     * @return boolean
     */
    @RequestMapping(path = "/saveParticipator")
    @ResponseBody
    public boolean saveParticipator(int aid,String content,String username){
        return activityService.saveParticipator(aid,content,0,username);
    }

    /**
     * find All participant to look
     * @param aid
     * @return JSONArray
     */
    @RequestMapping(path = "/findAllParticipator")
    @ResponseBody
    public JSONArray findAllParticipator(int aid,int page){
        return activityService.findAllParticipator(aid,page);
    }

    /**
     * disagree Participant
     * @param pid
     * @param status
     * @return boolean
     */
    @RequestMapping(path = "/disagreeParticipator")
    @ResponseBody
    public boolean disagreeParticipator(int pid,int status){
        return activityService.disagreeParticipator(pid,status);
    }

    @RequestMapping(path = "/photo")
    @ResponseBody
    public JSONObject getBigPhoto(String path){
        return activityService.getBigPhoto( path);
    }
}
