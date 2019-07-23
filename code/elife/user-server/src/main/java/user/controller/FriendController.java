package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import user.service.FriendService;

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
    public JSONArray friendList(@RequestParam String username){
        return friendService.friendList(username);
    }

    @RequestMapping(path = "/friendSearchList")
    @ResponseBody
    public JSONArray friendSearchList(@RequestParam String username){
        return friendService.friendSearchList(username);
    }

    @RequestMapping(path = "/requestNumber")
    @ResponseBody
    public JSONObject requestNumber(@RequestParam String username){
        JSONObject object = new JSONObject();
        object.put("number", friendService.responseList(username).size());
        return object;
    }

    @RequestMapping(path = "/responseList")
    @ResponseBody
    public JSONArray responseList(@RequestParam String username){
        return friendService.responseList(username);
    }

    @RequestMapping(path = "/requestList")
    @ResponseBody
    public JSONArray requestList(@RequestParam String username){
        return friendService.requestList(username);
    }

    @RequestMapping(path = "/sendFriendRequest")
    @ResponseBody
    public JSONObject sendFriendRequest(@RequestParam String username, @RequestParam String friend,
                                        @RequestParam String content){
        return friendService.sendFriendRequest(username, friend, content);
    }

    @RequestMapping(path = "/acceptRequest")
    @ResponseBody
    public JSONObject acceptRequest(@RequestParam Long id){
        return friendService.acceptRequest(id);
    }

    @RequestMapping(path = "/rejectRequest")
    @ResponseBody
    public JSONObject rejectRequest(@RequestParam Long id){
        return friendService.rejectRequest(id);
    }

    @RequestMapping(path = "/deleteFriend")
    @ResponseBody
    public JSONObject deleteFriend(@RequestParam String username, @RequestParam String friend){
        return friendService.deleteFriend(username, friend);
    }

}
