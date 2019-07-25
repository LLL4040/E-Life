package newsserver.controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @PostMapping(path="/saveActivity")
    @ResponseBody
    public boolean saveActivity(HttpServletRequest request, String startTime, String endTime, String content,
                                String managerName, String title, MultipartFile photo, int communityId) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            return false;
        }
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
    public JSONArray findAllActivity(HttpServletRequest request, int communityId,int page) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
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
    public JSONArray findNewActivity(HttpServletRequest request, int communityId) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return activityService.findNewActivity(communityId);
    }

    /**
     * deleteActivity
     * @param aid
     * @return boolean
     */
    @RequestMapping(path = "/deleteActivity")
    @ResponseBody
    public boolean deleteActivity(HttpServletRequest request, int aid){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            return false;
        }
        return activityService.deleteActivity(aid);
    }

    /**
     * submitActivity close it
     * @param aid
     * @param managerName
     * @Param communityId
     * @return boolean
     */
    @RequestMapping(path = "/submitActivity")
    @ResponseBody
    public boolean submitActivity(int aid,String managerName,int communityId){
        return activityService.submitActivity(aid,managerName,communityId);
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
    public boolean saveParticipator(HttpServletRequest request, int aid,String content,String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            return false;
        }
        return activityService.saveParticipator(aid,content,0,username);
    }

    /**
     * find All participant to look
     * @param aid
     * @return JSONArray
     */
    @RequestMapping(path = "/findAllParticipator")
    @ResponseBody
    public JSONArray findAllParticipator(HttpServletRequest request, int aid,int page){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return jsonArray;
        }
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
    public boolean disagreeParticipator(HttpServletRequest request, int pid,int status){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            return false;
        }
        return activityService.disagreeParticipator(pid,status);
    }

    @RequestMapping(path = "/photo")
    @ResponseBody
    public JSONObject getBigPhoto(HttpServletRequest request, String path){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "2".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return activityService.getBigPhoto(path);
    }
}
