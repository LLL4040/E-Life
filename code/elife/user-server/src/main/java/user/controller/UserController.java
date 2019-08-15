package user.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public JSONObject changeManagerRole(HttpServletRequest request, @RequestParam String username,@RequestParam Integer role){
        HttpSession session = request.getSession();
        String matter = (String) session.getAttribute("final");
        if("1".equals(matter)){
            return userService.changeManagerRole(username, role);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return jsonObject;
        }
    }


    @PostMapping(path = "/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, @RequestParam String username, @RequestParam String password, @RequestParam String id) {
        JSONObject result = ("1".equals(id)) ? userService.loginManager(username, password) : userService.login(username, password, id);
        if ("1".equals(result.getAsString("login"))) {
            HttpSession session = request.getSession();
            System.out.println(session);
            String name = (String) session.getAttribute("username");
            if (StringUtils.isEmpty(name)) {
                session.setAttribute("username", username);
                if ("1".equals(result.getAsString("final"))) {
                    session.setAttribute("role", id);
                    session.setAttribute("final", "1");
                } else {
                    session.setAttribute("role", id);
                }
            } else if (!(name.equals(username))) {
                JSONObject err = new JSONObject();
                err.put("login", -1);
                return err;
            }
        }
        return result;
    }

    @RequestMapping(path = "/logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(name)) {
            result.put("logout", 0);
        } else {
            session.removeAttribute("username");
            session.removeAttribute("role");
            session.removeAttribute("final");
            result.put("logout", 1);
        }
        return result;
    }

    @PostMapping(path = "/loginPhone")
    @ResponseBody
    public JSONObject loginPhone(HttpServletRequest request, @RequestParam String phone, @RequestParam String code, @RequestParam String id) {
        JSONObject result = ("1".equals(id)) ? userService.loginPhoneManager(phone, code) : userService.loginPhone(phone, code, id);
        if ("1".equals(result.getAsString("login"))) {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            if (StringUtils.isEmpty(name)) {
                name = userService.findUsernameByPhone(phone);
                session.setAttribute("username", name);
                if ("1".equals(result.getAsString("final"))) {
                    session.setAttribute("role", id);
                    session.setAttribute("final", "1");
                } else {
                    session.setAttribute("role", id);
                }
            } else if (!(name.equals(userService.findUsernameByPhone(phone)))) {
                JSONObject err = new JSONObject();
                err.put("login", -1);
                return err;
            }
        }
        return result;
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

    @RequestMapping(path = "/getUsers")
    @ResponseBody
    public JSONArray getUsername(HttpServletRequest request, @RequestParam Long communityId){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if(!StringUtils.isEmpty(name) && "1".equals(role)){
            return userService.getUsername(communityId);
        } else {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            jsonArray.appendElement(jsonObject);
            return jsonArray;
        }
    }

    @RequestMapping(path = "/changeEmail")
    @ResponseBody
    public JSONObject changeEmail(HttpServletRequest request, @RequestParam String username, @RequestParam String email){
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONObject object = new JSONObject();
        object.put("login", 0);
        if(StringUtils.isEmpty(user) || !user.equals(username) || !"0".equals(role)){
            return object;
        } else {
            return userService.changeEmail(username, email);
        }
    }

    @RequestMapping(path = "/changeEmailManager")
    @ResponseBody
    public JSONObject changeEmailManager(HttpServletRequest request, @RequestParam String username, @RequestParam String email){
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONObject object = new JSONObject();
        object.put("login", 0);
        if(StringUtils.isEmpty(user) || !user.equals(username) || !"1".equals(role)){
            return object;
        } else {
            return userService.changeEmailManager(username, email);
        }
    }

    @RequestMapping(path = "/getManagers")
    @ResponseBody
    public JSONArray getManagers(HttpServletRequest request, @RequestParam String id){
        HttpSession session = request.getSession();
        String matter = (String) session.getAttribute("final");
        if("1".equals(matter)){
            return userService.getManagers(id);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.appendElement(jsonObject);
            return jsonArray;
        }
    }

}
