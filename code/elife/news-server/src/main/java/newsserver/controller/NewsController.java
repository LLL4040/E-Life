package newsserver.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import newsserver.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    boolean saveNews (@RequestParam String  content, @RequestParam String managerName,
                     @RequestParam String title, @RequestParam MultipartFile photo,
                      @RequestParam int communityId) throws IOException {
        return newsService.save(content,managerName,title,photo,communityId);
    }
    @PostMapping(path="/save") // Map ONLY GET Requests
    @ResponseBody
    public
    String save ( @RequestParam MultipartFile photo) throws IOException {
        newsService.save("ss","1","hh",photo,1);
        return "success";
    }
    @RequestMapping(path = "/findNews")
    @ResponseBody
    public JSONArray findNew(int communityId) throws IOException {
        return newsService.findNew(communityId);
    }
    @RequestMapping(path = "/deleteOne")
    @ResponseBody
    public boolean deleteOne(int id){
        return newsService.deleteOne(id);
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
    public JSONArray findHistory(int communityId) throws IOException {
        return newsService.findHistory(communityId);
    }
}
