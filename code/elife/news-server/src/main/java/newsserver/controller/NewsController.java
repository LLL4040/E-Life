package newsserver.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping(path="/api/News")
@RestController
/**
 * NewsController class
 *
 * @Author wyx
 * @Date 2019.07.10
 */
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping(path="/saveNews") // Map ONLY GET Requests
    @ResponseBody
    public
    boolean saveNews (HttpServletRequest request, @RequestParam String  content, @RequestParam String managerName,
                     @RequestParam String title, @RequestParam MultipartFile photo,
                      @RequestParam int communityId) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role)) || !(name.equals(managerName))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return false;
        } else {
            return newsService.save(content, managerName, title, photo, communityId);
        }
    }

    @RequestMapping(path = "/findNews")
    @ResponseBody
    public JSONArray findNew(HttpServletRequest request, int communityId) throws IOException {
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
        return newsService.findNew(communityId);
    }
    @RequestMapping(path = "/deleteOne")
    @ResponseBody
    public boolean deleteOne(HttpServletRequest request, int id){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            return false;
        } else {
            return newsService.deleteOne(id);
        }
    }
    @RequestMapping(path = "/findOne")
    @ResponseBody
    public JSONObject findOne(int id) throws IOException {
        return newsService.findOne(id);
    }
    @RequestMapping(path = "/moveTable")
    @ResponseBody
    public boolean moveTable() throws IOException {
         return newsService.moveTable();
    }
    @RequestMapping(path = "/findHistory")
    @ResponseBody
    public JSONArray findHistory(HttpServletRequest request, int communityId,int page) throws IOException {
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
        return newsService.findHistory(communityId,page);
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
        return newsService.getBigPhoto( path);
    }
}
