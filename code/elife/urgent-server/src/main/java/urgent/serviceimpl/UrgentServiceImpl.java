package urgent.serviceimpl;



import net.minidev.json.JSONObject;
import urgent.dao.UrgentDao;
import urgent.entity.Urgent;
import urgent.service.UrgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Urgent urgent = new Urgent();
            urgent = urgentDao.findOne(id);
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
}
