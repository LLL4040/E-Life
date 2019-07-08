package urgent.daoimpl;

import urgent.dao.UrgentDao;
import urgent.entity.Urgent;
import urgent.repository.UrgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
/**
 * UrgentDaoImpl class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class UrgentDaoImpl implements UrgentDao{
    @Autowired
    private UrgentRepository urgentRepository;

    @Override
    public void save(int id, String managerName, String content, int status, int communityId){
        urgentRepository.changeStatus(communityId);
        urgentRepository.saveUrgent(id,managerName,content,status,communityId);

    }

    @Override
    public Urgent findOne(int id){

        return urgentRepository.getOne(id);
    }
    @Override
    public void deleteOne(int id){
        urgentRepository.deleteById(id);

    }

}
