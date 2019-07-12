package packageserver.serviceimpl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import packageserver.dao.PackageDao;
import packageserver.entity.Package;
import newsserver.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
/**
 * PackageService class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDao packageDao;

    public JSONArray listToJsonArray (List<Package> list){
        JSONArray jsonArray =new JSONArray();
        Iterator<Package> iter = list.iterator();
        while(iter.hasNext()){
            Package temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            jsonObject.put("time",temp.getTime());
            jsonObject.put("status",temp.getStatus());
            jsonObject.put("managerName",temp.getManagerName());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @Override
    public boolean save(String time, String managerName, String username, int communityId) {
        try {
            packageDao.save(time, 0,managerName, username, communityId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findNew(String username){
        List<Package> list = packageDao.findNew(username);
        return listToJsonArray(list);
    }
    @Override
    public JSONArray findOne(int id){
            List<Package> list =new ArrayList<Package>();
            list.add(0,packageDao.findOne(id));
            return listToJsonArray(list);
    }

    @Override
    public boolean deleteOne(int id){
        try{
            packageDao.deleteOne(id);
            return true;
        }
        catch (Exception e) {
        return false;
        }
    }

    @Override
    public boolean takeOut(int id){
        try{
            packageDao.changeStatus(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findHistory(String username) {
        List<Package> list = packageDao.findHistory(username);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findHistoryManager(int communityId){
        List<Package> list = packageDao.findHistoryManager(communityId);
        return listToJsonArray(list);
    }
}
