package apackage.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import apackage.service.FriendService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/friend")
@RestController
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @RequestMapping(path = "/friendList")
    @ResponseBody
    public JSONArray friendList(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        System.out.println(name);
        System.out.println(username);
        System.out.println(role);
        System.out.println(name.equals(username));
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return friendService.friendList(username);
    }

    @RequestMapping(path = "/friendSearchList")
    @ResponseBody
    public JSONArray friendSearchList(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return friendService.friendSearchList(username);
    }

    @RequestMapping(path = "/requestNumber")
    @ResponseBody
    public JSONObject requestNumber(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        JSONObject object = new JSONObject();
        object.put("number", friendService.responseList(username).size());
        return object;
    }

    @RequestMapping(path = "/responseList")
    @ResponseBody
    public JSONArray responseList(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return friendService.responseList(username);
    }

    @RequestMapping(path = "/requestList")
    @ResponseBody
    public JSONArray requestList(HttpServletRequest request, @RequestParam String username){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return friendService.requestList(username);
    }

    @RequestMapping(path = "/sendFriendRequest")
    @ResponseBody
    public JSONObject sendFriendRequest(HttpServletRequest request, @RequestParam String username, @RequestParam String friend,
                                        @RequestParam String content){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return friendService.sendFriendRequest(username, friend, content);
    }

    @RequestMapping(path = "/acceptRequest")
    @ResponseBody
    public JSONObject acceptRequest(HttpServletRequest request, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return friendService.acceptRequest(id);
    }

    @RequestMapping(path = "/rejectRequest")
    @ResponseBody
    public JSONObject rejectRequest(HttpServletRequest request, @RequestParam Long id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return friendService.rejectRequest(id);
    }

    @RequestMapping(path = "/deleteFriend")
    @ResponseBody
    public JSONObject deleteFriend(HttpServletRequest request, @RequestParam String username, @RequestParam String friend){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || "1".equals(role) || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
        return friendService.deleteFriend(username, friend);
    }

}
