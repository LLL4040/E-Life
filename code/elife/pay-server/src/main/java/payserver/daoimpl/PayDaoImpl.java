package payserver.daoimpl;

import payserver.dao.PayDao;
import payserver.entity.Pay;
import payserver.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
/**
 * PayDaoImpl Class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PayDaoImpl implements PayDao {
    @Autowired
    private PayRepository packageRepository;


    @Override
    public void save(String time, int status, BigDecimal bill, String managerName, String username, int communityId){
     packageRepository.savePay(time,status,bill,managerName,username,communityId);
    }

    @Override
    public Pay findOne(int id){
        return packageRepository.getOne(id);
    }

    @Override
    public void deleteOne(int id){
        packageRepository.deleteById(id);
    }

    @Override
    public void changeStatus(int id) {
        Pay temp = packageRepository.getOne(id);
        temp.setStatus(1);
        packageRepository.save(temp);
    }
    @Override
    public List<Pay> findNew(String username){
        return packageRepository.findNew(username);
    }

    @Override
    public List<Pay> findHistory(String username){
        return packageRepository.findHistory(username);
    }

    @Override
    public List<Pay> findUnPayHistoryManager(int communityId){
        return packageRepository.findUnPayManager(communityId);
    }

    @Override
    public List<Pay> findPayHistoryManager(int communityId){
        return packageRepository.findPayHistoryManager(communityId);
    }
   }
