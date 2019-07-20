package lifeservice.lifeservice.serviceimpl;

import lifeservice.lifeservice.dao.MaintainDao;
import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.service.MaintainService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Service

public class MaintainServiceImpl implements MaintainService {
    @Autowired
    private MaintainDao maintainDao;
    @Override
    public long saveMaintian(Maintain maintain) {

        return maintainDao.saveMaintian(maintain) ;
    }

    @Override
    public String managerMaintain(long id, int status,String maintianname, String phone, String managername) {
        return maintainDao.managerMaintain(id,status,maintianname,phone,managername);
    }

    @Override
    public List<Maintain> findbyUsernameAndStatus(String username) {
        return maintainDao.findbyUsernameAndStatus(username);
    }

    @Override
    public String userEditMaintain(long id, int status) {
        return maintainDao.userEditMaintain(id,status);
    }

    @Override
    public JSONArray findMaintainByCommunityId(int communityId, int pageNumber, int pageSize) {
        List<Maintain> maintainList= maintainDao.findMaintainByCommunityId(communityId,pageNumber,pageSize);
        long count =maintainDao.countMaintain(communityId);
        long pageNum = count/pageSize;
        if (count%pageSize!=0){
            pageNum+=1;
        }
        JSONObject object1=new JSONObject();
        object1.put("pageNum",pageNum);
        JSONArray result=new JSONArray();
        result.add(object1);
        for (Maintain maintain:maintainList){
            JSONObject object =new JSONObject();
            object.put("id",maintain.getId());
            object.put("time",maintain.getTime());
            object.put("status",maintain.getStatus());
            object.put("content",maintain.getContent());
            object.put("maintainname",maintain.getMaintainname());
            object.put("phone",maintain.getPhone());
            object.put("managername",maintain.getManagername());
            object.put("username",maintain.getUsername());
            object.put("userphone",maintain.getUserPhone());
            result.add(object);
        }
        return result;
    }

    @Override
    public JSONArray findHaveMaintainByCommunityId(int communityId, int pageNumber, int pageSize) {
        List<Maintain> maintainList = maintainDao.findHaveMaintainByCommunityId(communityId,pageNumber,pageSize);
        long count =maintainDao.countHaveMaintain(communityId);
        long pageNum= count/pageSize;
        if (count%pageSize!=0){
            pageNum+=1;
        }
        JSONObject object1=new JSONObject();
        object1.put("pageNum",pageNum);
        JSONArray result = new JSONArray();
        result.add(object1);
        for (Maintain maintain:maintainList){
            JSONObject object =new JSONObject();
            object.put("id",maintain.getId());
            object.put("time",maintain.getTime());
            object.put("status",maintain.getStatus());
            object.put("content",maintain.getContent());
            object.put("maintainname",maintain.getMaintainname());
            object.put("phone",maintain.getPhone());
            object.put("managername",maintain.getManagername());
            object.put("username",maintain.getUsername());
            object.put("userphone",maintain.getUserPhone());
            result.add(object);
        }
        return result;
    }

    @Override
    public Maintain getOne(long id) {
        return maintainDao.getOne(id);
    }

    @Override
    public boolean deleteById(long id) {
        try{
            maintainDao.deleteById(id);
            return true;
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public long countMaintain(int communityId) {
        return maintainDao.countMaintain(communityId);
    }
}
