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
    public void save(int id, String managerName, String content, int status){
        urgentDao.save(id, managerName, content , status);
    }

    @Override
    public JSONObject findOne(int id) {
        JSONObject object = new JSONObject();
        Urgent urgent = new Urgent();
        urgent = urgentDao.findOne(id);
        object.put("id", urgent.getId());
        object.put("managerName",urgent.getManagerName());
        object.put("content",urgent.getContent());
        object.put("status",urgent.getStatus());
        object.put("time",urgent.getTime());
        return object;
    }
}
