package payserver.serviceimpl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payserver.dao.PayDao;
import payserver.entity.Pay;
import payserver.service.PayService;;import java.math.BigDecimal;
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
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    public JSONArray listToJsonArray (List<Pay> list){
        JSONArray jsonArray =new JSONArray();
        Iterator<Pay> iter = list.iterator();
        while(iter.hasNext()){
            Pay temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            jsonObject.put("time",temp.getTime());
            jsonObject.put("username",temp.getUsername());
            jsonObject.put("bill",temp.getBill());
            jsonObject.put("status",temp.getStatus());
            jsonObject.put("managerName",temp.getManagerName());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @Override
    public boolean save(String time, BigDecimal bill, String managerName, String username, int communityId) {

            payDao.save(time, 0,bill,managerName, username, communityId);
            return true;

    }

    @Override
    public JSONArray findNew(String username){
        List<Pay> list = payDao.findNew(username);
        return listToJsonArray(list);
    }
    @Override
    public JSONArray findOne(int id){
            List<Pay> list =new ArrayList<Pay>();
            list.add(0, payDao.findOne(id));
            return listToJsonArray(list);
    }

    @Override
    public boolean deleteOne(int id){
        try{
            payDao.deleteOne(id);
            return true;
        }
        catch (Exception e) {
        return false;
        }
    }

    @Override
    public boolean payBill(int id){
        try{
            payDao.changeStatus(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findHistory(String username) {
        List<Pay> list = payDao.findHistory(username);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findUnPayHistoryManager(int communityId){
        List<Pay> list = payDao.findUnPayHistoryManager(communityId);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findPayHistoryManager(int communityId){
        List<Pay> list = payDao.findPayHistoryManager(communityId);
        return listToJsonArray(list);
    }
}
