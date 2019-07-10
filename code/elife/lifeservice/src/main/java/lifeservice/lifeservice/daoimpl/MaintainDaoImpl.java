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
    public String managerMaintain(long id, String maintianname, String phone, String managername) {
        Maintain maintain=maintainRepository.getOne(id);
        maintain.setMaintainname(maintianname);
        maintain.setPhone(phone);
        maintain.setManagername(managername);
        maintain.setStatus(1);
        maintainRepository.save(maintain);
        return "完成管理物业维修";
    }

    @Override
    public List<Maintain> findbyUsernameAndStatus(String username, int status) {

        return maintainRepository.findbyUsernameAndStatus(username,status);
    }

    @Override
    public String userEditMaintain(long id, int status) {
        Maintain maintain = maintainRepository.getOne(id);
        maintain.setStatus(status);
        return "修改物业维修状态成功";
    }
}
