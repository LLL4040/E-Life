package group.service;

import group.dao.DemandDao;
import group.dao.ParticipateDao;
import group.entity.Demand;
import group.entity.Participate;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ztHou
 */
@Service
public class DemandServiceImpl implements DemandService{
    private final DemandDao demandDao;
    private final ParticipateDao participateDao;

    public DemandServiceImpl(DemandDao demandDao, ParticipateDao participateDao) {
        this.demandDao = demandDao;
        this.participateDao = participateDao;
    }

    @Override
    public JSONObject addDemand(String startTime, String endTime, String content, String username, Long communityId){
        JSONObject result = new JSONObject();
        result.put("add", 0);
        try{
            Demand demand = new Demand(username, startTime, endTime, content, communityId);
            Long demandId = demandDao.save(demand).getId();
            Participate participate = new Participate(username, demandId);
            participateDao.save(participate);
            result.put("add", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONObject deleteDemand(Long id){
        JSONObject result = new JSONObject();
        result.put("delete", 0);
        try{
            demandDao.deleteById(id);
            result.put("delete", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONArray getAllDemand(Long communityId){
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        List<Demand> demandList = demandDao.findAllByEndTimeAfterAndCommunityId(dateTime, communityId);
        for(Demand demand: demandList){
            JSONObject object = new JSONObject();
            object.put("id", demand.getId());
            object.put("startTime", demand.getStartTime());
            object.put("endTime", demand.getEndTime());
            object.put("content", demand.getContent());
            object.put("username", demand.getUsername());
            object.put("communityId", demand.getCommunityId());
            object.put("total", participateDao.countDistinctByDemand(demand.getId()));
            jsonArray.appendElement(object);
        }
        return jsonArray;
    }

    @Override
    public JSONObject participateDemand(String username, Long id){
        JSONObject result = new JSONObject();
        result.put("participate", 0);
        try{
            Participate participate = new Participate(username, id);
            if(!participateDao.existsByUsernameAndDemand(username, id)){
                participateDao.save(participate);
            }
            result.put("participate", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }

    @Override
    public JSONObject quitDemand(String username, Long id){
        JSONObject result = new JSONObject();
        result.put("quit", 0);
        try{
            participateDao.deleteByUsernameAndDemand(username, id);
            result.put("quit", 1);
            return result;
        }catch (Exception e){
            return result;
        }
    }
}
