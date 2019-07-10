package lifeservice.lifeservice.daoimpl;

import lifeservice.lifeservice.dao.MaintainDao;
import lifeservice.lifeservice.entity.Maintain;
import lifeservice.lifeservice.repository.MaintainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Repository

public class MaintainDaoImpl implements MaintainDao {
    @Autowired
    private MaintainRepository maintainRepository;
    @Override
    public long saveMaintian(Maintain maintain) {
        return maintainRepository.save(maintain).getId();

    }

    @Override
    public String managerMaintain(long id,int status, String maintianname, String phone, String managername) {
        try{
            Maintain maintain=maintainRepository.getOne(id);
            maintain.setMaintainname(maintianname);
            maintain.setPhone(phone);
            maintain.setManagername(managername);
            maintain.setStatus(status);
            maintainRepository.save(maintain);
            return "完成管理物业维修";
        }
        catch (Exception e){
            return "管理物业维修失败";
        }

    }

    @Override
    public List<Maintain> findbyUsernameAndStatus(String username) {

        return maintainRepository.findbyUsernameAndStatus(username);
    }

    @Override
    public String userEditMaintain(long id, int status) {
        try {
            Maintain maintain = maintainRepository.getOne(id);
            maintain.setStatus(status);
            return "修改物业维修状态成功";
        }catch (Exception e){
            return "修改物业维修状态失败";
        }

    }

    @Override
    public List<Maintain> findMaintainByCommunityId(int communityId) {

        return maintainRepository.findMaintainByCommunityId(communityId);
    }

    @Override
    public Maintain getOne(long id) {
        return maintainRepository.getOne(id);
    }

    @Override
    public void deleteById(long id) {
        maintainRepository.deleteById(id);

    }

    @Override
    public long countMaintain(int communityId) {
        return maintainRepository.countMaintain(communityId);
    }

}
