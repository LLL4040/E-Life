package activityserver.serviceimpl;

import activityserver.dao.ActivityDao;
import activityserver.entity.Activity;
import activityserver.entity.Participator;
import activityserver.service.ActivityService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;

    public JSONArray listToJsonArray (List<Activity> list) throws IOException {
        JSONArray jsonArray =new JSONArray();
        Iterator<Activity> iter = list.iterator();
        while(iter.hasNext()){
            Activity temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            File file = new File("./activity-server/pic/"+temp.getPhoto());
            byte[] data = Files.readAllBytes(file.toPath());
            String photo= Base64.encodeBase64String(data);
            jsonObject.put("photo" , "data:image/jpg;base64,"+photo);
            jsonObject.put("id",temp.getId());
            jsonObject.put("status",temp.getStatus());
            jsonObject.put("title",temp.getTitle());
            jsonObject.put("startTime",temp.getStartTime());
            jsonObject.put("endTime",temp.getEndTime());
            jsonObject.put("content",temp.getContent());
            jsonObject.put("managerName",temp.getManagerName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @Override
    public boolean saveActivity(String startTime, String endTime, String content,
                        String managerName, String title, int status, MultipartFile photo, int communityId){
        try {
            String path = UUID.randomUUID() + photo.getOriginalFilename();
            if (!photo.isEmpty()) {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream bufferedOutputStream = new
                        BufferedOutputStream(new FileOutputStream(new File("./activity-server-server/pic/" + path)));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
           activityDao.saveActivity(startTime,endTime,content,managerName,title,status,path,communityId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findAllActivity(int communityId) throws IOException {
        List<Activity> list = activityDao.findAllActivity(communityId);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findNewActivity(int communityId) throws IOException {
        List<Activity> list = activityDao.findNewActivity(communityId);
        return listToJsonArray(list);
    }

    @Override
    public boolean deleteActivity(int aid){
        try{
            activityDao.deleteActivity(aid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean submitActivity(int aid){
        try {
            activityDao.changeStatusAct(aid, 1);
            activityDao.deleteAllDisPar(aid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean saveParticipator(int aid,String content,int status,String username){
        try{
            if(activityDao.existApply(aid,username)) {
                activityDao.saveParticipator(aid, content, status, username);
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findAllParticipator(int aid){
        JSONArray jsonArray = new JSONArray();
        List<Participator> list = activityDao.findAllParticipator(aid);
        Iterator<Participator> iter = list.iterator();
        while (iter.hasNext()){
            JSONObject jsonObject = new JSONObject();
            Participator participator = iter.next();
            jsonObject.put("id",participator.getId());
            jsonObject.put("username",participator.getUsername());
            jsonObject.put("content",participator.getContent());
            jsonObject.put("status",participator.getStatus());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public boolean disagreeParticipator(int pid,int status){
        try{
            activityDao.changeStatusPar(pid,status);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
