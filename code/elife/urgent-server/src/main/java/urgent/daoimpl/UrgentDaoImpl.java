package urgent.daoimpl;

import net.minidev.json.JSONObject;
import urgent.dao.UrgentDao;
import urgent.entity.Urgent;
import urgent.entity.UrgentUsed;
import urgent.repository.UrgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import urgent.repository.UrgentUsedRepository;

import java.util.List;

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
    @Autowired
    private UrgentUsedRepository urgentUsedRepository;

    @Override
    public void save(String managerName, String content, int status, int communityId){
        urgentRepository.changeStatus(communityId);
        urgentRepository.saveUrgent(managerName,content,status,communityId);

    }

    @Override
    public Urgent findOne(int id){

        return urgentRepository.getOne(id);
    }
    @Override
    public void deleteOne(int id){
        urgentRepository.deleteById(id);

    }
    @Override
    public void moveTable(){
        urgentRepository.moveTable(1);
    }
    @Override
    public Urgent findNew(int communityId){
        return urgentRepository.findNew(communityId);
    }
    @Override
    public List<UrgentUsed> findHistory(int communityId){
        return urgentUsedRepository.findHistory(communityId);
    }
    @Override
    public List<Urgent> findHistoryHot(int communityId){
        return urgentRepository.findHistory(communityId);
    }
}
