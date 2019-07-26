package newsserver.serviceimpl;



import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import newsserver.dao.UrgentDao;
import newsserver.entity.Urgent;
import newsserver.entity.UrgentUsed;
import newsserver.service.UrgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
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
    public boolean save(String managerName, String content, int status, int communityId) {
        try{
            urgentDao.save(managerName, content, status, communityId);
            return true;
        }
        catch (Exception e){
            return false;
        }
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
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(urgent.getTime());
            object.put("time", dateStr);
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
    public boolean moveTable(){
        try {
            urgentDao.moveTable();
            return true;
        }
        catch (Exception e){
            return false;
        }
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
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(urgentNew.getTime());
            object.put("time", dateStr);
            object.put("communityId", urgentNew.getCommunityId());
            return object;
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("error","The Id Can Not Be Found");
            return object;
        }
    }
    @Override
    public JSONArray findHistory(int communityId,int page){
        try {
            List<Urgent> hotList = urgentDao.findHistoryHot(communityId);
            Collections.reverse(hotList);
            List<UrgentUsed> coldList = urgentDao.findHistory(communityId);
            JSONArray jsonArray = new JSONArray();
            Iterator<Urgent> hotIter = hotList.iterator();
            Iterator<UrgentUsed> coldIter = coldList.iterator();
            Urgent hot = new Urgent();
            UrgentUsed cold = new UrgentUsed();
            int row = 10;
            int left = row*(page-1);
            int right = row*page;
            int i=0;
            while (hotIter.hasNext()) {
                if(i>=left && i<right) {
                    JSONObject jsonObject = new JSONObject();
                    hot = hotIter.next();
                    jsonObject.put("id", hot.getId());
                    jsonObject.put("content", hot.getContent());
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(hot.getTime());
                    jsonObject.put("time", dateStr);
                    jsonObject.put("managerName", hot.getManagerName());
                    jsonArray.add(jsonObject);
                    System.out.println(jsonObject);
                }
                else {
                    hotIter.next();
                }
                i++;
            }
            while (coldIter.hasNext()) {
                if(i>=left && i<right) {
                    JSONObject jsonObject = new JSONObject();
                    cold = coldIter.next();
                    jsonObject.put("id", cold.getId());
                    jsonObject.put("content", cold.getContent());
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cold.getTime());
                    jsonObject.put("time", dateStr);
                    jsonObject.put("managerName", cold.getManagerName());
                    jsonArray.add(jsonObject);
                    System.out.println(jsonObject);
                }
                else {
                    coldIter.next();
                }
                i++;
            }
            JSONObject allPage = new JSONObject();
            int pageNum = (hotList.size()+coldList.size())/10;
            if(pageNum % row != 0){
                pageNum++;
            }
            allPage.put("pageNum",pageNum);
            jsonArray.add(allPage);
            System.out.println(jsonArray);
            return jsonArray;
        }
        catch (Exception e){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("error","null");
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return  jsonArray;

        }

    }
}
