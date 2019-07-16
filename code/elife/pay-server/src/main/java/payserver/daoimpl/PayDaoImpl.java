package payserver.daoimpl;


import payserver.dao.PayDao;
import payserver.entity.Orders;
import payserver.entity.Pay;
import payserver.repository.OrderRepository;
import payserver.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.Date;
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
    @Autowired
    private OrderRepository orderRepository;


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
        if(temp.getStatus()==-1) {
            temp.setStatus(1);
        }
        else{
            temp.setStatus(2);
        }
        packageRepository.save(temp);
    }
    @Override
    public List<Pay> findNew(String username){
        return packageRepository.findNew(username);
    }

    @Override
    public List<Pay> findHistory(String username,int page){
        return packageRepository.findHistory(username,10*(page-1),10*page-1);
    }

    @Override
    public List<Pay> findUnPayHistoryManager(int communityId,int page){
        return packageRepository.findUnPayManager(communityId,10*(page-1),10*page-1);
    }

    @Override
    public List<Pay> findPayHistoryManager(int communityId,int page){
        return packageRepository.findPayHistoryManager(communityId,10*(page-1),10*page-1);
    }

    @Override
    public void saveOrders(String id, int pid, Date createTime,BigDecimal bill,int status){
        Date current = new Date();
        Orders order = new Orders(id,pid,current,bill,status);
        orderRepository.save(order);
    }
   }
