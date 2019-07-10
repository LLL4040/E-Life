package lifeservice.lifeservice.serviceimpl;

import lifeservice.lifeservice.dao.MaintainDao;
import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.service.MaintainService;
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
    public List<Maintain> findMaintainByCommunityId(int communityId) {
        return maintainDao.findMaintainByCommunityId(communityId);
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
