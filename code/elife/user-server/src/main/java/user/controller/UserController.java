package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import user.service.UserService;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/usernameAvailable")
    @ResponseBody
    public JSONObject usernameAvailable(@RequestParam String username) {
        return userService.usernameAvailable(username);
    }

    @RequestMapping(path = "/usernameAvailableManager")
    @ResponseBody
    public JSONObject usernameAvailableManager(@RequestParam String username) {
        return userService.usernameAvailableManager(username);
    }

    @RequestMapping(path = "/phoneAvailable")
    @ResponseBody
    public JSONObject phoneAvailable(@RequestParam String phone, @RequestParam String id) {
        return ("1".equals(id)) ? userService.phoneAvailableManager(phone) : userService.phoneAvailable(phone);
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public JSONObject register(@RequestParam String username, @RequestParam String password, @RequestParam String phone,
                               @RequestParam String code, @RequestParam String email, @RequestParam Long communityId) {
        return userService.registerUser(username, password, phone, code, email, communityId);
    }

    @PostMapping(path = "/registerManager")
    @ResponseBody
    public JSONObject registerManager(@RequestParam String username, @RequestParam String password, @RequestParam String phone,
                               @RequestParam String code, @RequestParam String email, @RequestParam Long communityId) {
        return userService.registerManager(username, password, phone, code, email, communityId);
    }

    @RequestMapping(path = "/changeManagerStatus")
    @ResponseBody
    public JSONObject changeManagerRole(@RequestParam String username,@RequestParam Integer role){
        return userService.changeManagerRole(username, role);
    }


    @PostMapping(path = "/login")
    @ResponseBody
    public JSONObject login(@RequestParam String username, @RequestParam String password, @RequestParam String id) {
        return ("1".equals(id)) ? userService.loginManager(username, password) : userService.login(username, password, id);
    }

    @PostMapping(path = "/loginPhone")
    @ResponseBody
    public JSONObject loginPhone(@RequestParam String phone, @RequestParam String code, @RequestParam String id) {
        return ("1".equals(id)) ? userService.loginPhoneManager(phone, code) : userService.loginPhone(phone, code);
    }

    @PostMapping(path = "/findPassword")
    @ResponseBody
    public JSONObject findPassword(@RequestParam String phone, @RequestParam String code, @RequestParam String password, @RequestParam String id) {
        return ("1".equals(id)) ? userService.findPasswordManager(phone, code, password) : userService.findPassword(phone, code, password);
    }

    @RequestMapping(path = "/communities")
    @ResponseBody
    public JSONArray showCommunities(){
        return userService.showCommunities();
    }

    @RequestMapping(path = "/getCommunityById")
    @ResponseBody
    public JSONObject getCommunityById(@RequestParam Long id){
        return userService.getCommunityById(id);
    }


}
