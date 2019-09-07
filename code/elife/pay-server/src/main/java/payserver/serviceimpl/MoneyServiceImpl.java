package payserver.serviceimpl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payserver.dao.moneyDao;
import payserver.entity.ParkPlan;
import payserver.entity.Room;
import payserver.entity.RoomSpace;
import payserver.service.MoneyService;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
/**
 * @Author wyx
 * @Date 2019.09.03
 */
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    moneyDao moneyDao;

    @Override
    public boolean initRoom(String type ,int maxNum,int maxFloor,int maxRoom,int communityId,int roomSpace){
        try{
            moneyDao.saveRoomSpace(type,maxNum,maxFloor,maxRoom,roomSpace,communityId);
            String room="";
            for(int  i=1;i<=maxNum;i++){
                for(int j=1;j<=maxFloor;j++){
                    for(int k=1;k<=maxRoom;k++){
                        if(i<10){
                            room= room +'0'+i;
                        }
                        else{
                            room= room+i;
                        }
                        if(j<10){
                            room= room +'0'+j;
                        }
                        else{
                            room= room+j;
                        }
                        if(k<10){
                            room= room +'0'+k;
                        }
                        else{
                            room= room+k;
                        }
                        moneyDao.saveRoom(type,room,communityId);
                        room = "";

                    }
                }
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray getRoomSpace(int communityId){
        List<RoomSpace> list = moneyDao.getRoomSpace(communityId);
        JSONArray jsonArray =new JSONArray();
        Iterator<RoomSpace> iter = list.iterator();
        while(iter.hasNext()){
            RoomSpace temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            jsonObject.put("type",temp.getType());
            jsonObject.put("space",temp.getSpace());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonObject.put("maxNum",temp.getMaxNum());
            jsonObject.put("maxFloor",temp.getMaxFloor());
            jsonObject.put("maxRoom",temp.getMaxRoom());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public boolean saveUsername(String username,String type,String room,int communityId){
        try{
            Room temp = moneyDao.getRoomByRoomNumber(type,room,communityId);
            temp.setUsername(username);
            moneyDao.saveRoomRepo(temp);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean calManMoney(BigDecimal money,int communityId){
        try{
            moneyDao.calManMoney(money,communityId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean initParkPlan(BigDecimal Amoney,BigDecimal Bmoney,int communityId){
        System.out.println(moneyDao.getParkPlan(communityId).isEmpty());
        if(moneyDao.getParkPlan(communityId).isEmpty()){
            moneyDao.saveParkPlan("A",Amoney,communityId);
            moneyDao.saveParkPlan("B",Bmoney,communityId);
        }
        else {
            moneyDao.changeParkPlan("A",Amoney,communityId);
            moneyDao.changeParkPlan("B",Bmoney,communityId);
        }
        return true;
    }

    @Override
    public  boolean changeParking(String username,String parking,String type, int communityId){
        moneyDao.changeParking(username, parking, type, communityId);
        return true;
    }
    @Override
    public boolean   calParking( int communityId){
        moneyDao.calParking(communityId);
        return true;
    }

    @Override
    public JSONObject getRoomByRoomNumber(String type,String room,int communityId){
        Room temp = moneyDao.getRoomByRoomNumber(type, room, communityId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",temp.getUsername());
        jsonObject.put("type",temp.getType());
        jsonObject.put("room",temp.getRoom());
        jsonObject.put("parking",temp.getParking());
        jsonObject.put("pmoney",temp.getParkMoney());
        jsonObject.put("mmoney",temp.getManagerMoney());
        jsonObject.put("communityId",temp.getCommunityId());
        return jsonObject;
    }

    @Override
    public JSONArray getParkPlan(int communityId){
        List<ParkPlan> list = moneyDao.getParkPlan(communityId);
        JSONArray jsonArray =new JSONArray();
        Iterator<ParkPlan> iter = list.iterator();
        while(iter.hasNext()){
            ParkPlan temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            jsonObject.put("type",temp.getType());
            jsonObject.put("montyPay",temp.getMonthPay());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
