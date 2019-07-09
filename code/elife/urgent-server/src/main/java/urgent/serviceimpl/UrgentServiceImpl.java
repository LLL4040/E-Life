package urgent.serviceimpl;



import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import urgent.dao.UrgentDao;
import urgent.entity.Urgent;
import urgent.entity.UrgentUsed;
import urgent.service.UrgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
/**
 * UrgentServiceImpl class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class UrgentServiceImpl implements UrgentService {

    @Autowired
    private UrgentDao urgentDao;

    @Override
    public void save(int id, String managerName, String content, int status, int communityId) {
        urgentDao.save(id, managerName, content, status, communityId);
    }

    @Override
    public JSONObject findOne(int id) {
        try {
            JSONObject object = new JSONObject();
            Urgent urgent = urgentDao.findOne(id);
            object.put("id", urgent.getId());
            object.put("managerName", urgent.getManagerName());
            object.put("content", urgent.getContent());
            object.put("status", urgent.getStatus());
            object.put("time", urgent.getTime());
            object.put("communityId", urgent.getCommunityId());
            return object;
        }
        catch (javax.persistence.EntityNotFoundException e){
            JSONObject object = new JSONObject();
            object.put("error","The Id Can Not Be Found");
            return object;
        }
    }
    @Override
    public boolean deleteOne(int id){
        try{
        urgentDao.deleteOne(id);
        return true;
        }
        catch (org.springframework.dao.EmptyResultDataAccessException e){
            return false;
        }
    }
    @Override
    public void moveTable(){
        urgentDao.moveTable();
    }
    @Override
    public JSONObject findNew(int communityId) {
        try {
            JSONObject object = new JSONObject();
            Urgent urgentNew = urgentDao.findNew(communityId);
            object.put("id", urgentNew.getId());
            object.put("managerName", urgentNew.getManagerName());
            object.put("content", urgentNew.getContent());
            object.put("status", urgentNew.getStatus());
            object.put("time", urgentNew.getTime());
            object.put("communityId", urgentNew.getCommunityId());
            return object;
        }
        catch (javax.persistence.EntityNotFoundException e){
            JSONObject object = new JSONObject();
            object.put("error","The Id Can Not Be Found");
            return object;
        }
    }
    @Override
    public JSONArray findHistory(int communityId){
        List<Urgent> hotList = urgentDao.findHistoryHot(communityId);
        List<UrgentUsed> coldList = urgentDao.findHistory(communityId);
        JSONArray jsonArray = new JSONArray();
        Iterator<Urgent> hotIter = hotList.iterator();
        Iterator<UrgentUsed> coldIter = coldList.iterator();
        Urgent hot = new Urgent();
        UrgentUsed cold = new UrgentUsed();
        while(hotIter.hasNext()){
            JSONObject jsonObject= new JSONObject();
            hot = hotIter.next();
            jsonObject.put("id",hot.getId());
            jsonObject.put("content",hot.getContent());
            jsonObject.put("time",hot.getTime());
            jsonObject.put("managerName",hot.getManagerName());
            jsonArray.add(jsonObject);
            System.out.println(jsonObject);
        }
        while(coldIter.hasNext()){
            JSONObject jsonObject= new JSONObject();
            cold = coldIter.next();
            jsonObject.put("id",cold.getId());
            jsonObject.put("content",cold.getContent());
            jsonObject.put("time",cold.getTime());
            jsonObject.put("managerName",cold.getManagerName());
            jsonArray.add(jsonObject);
            System.out.println(jsonObject);
        }
        System.out.println(jsonArray);
        return jsonArray;


    }
}
