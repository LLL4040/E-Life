package payserver.daoimpl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import payserver.dao.PayDao;
import payserver.entity.Orders;
import payserver.entity.Pay;
import payserver.repository.OrderRepository;
import payserver.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import payserver.util.AlipayConfig;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
/**
 * PayDaoImpl Class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PayDaoImpl implements PayDao {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void save(String time, int status, BigDecimal bill, String managerName, String username, int communityId){
     payRepository.savePay(time,status,bill,managerName,username,communityId);
    }

    @Override
    public Pay findOne(int id){
        return payRepository.getOne(id);
    }

    @Override
    public void deleteOne(int id){
        payRepository.deleteById(id);
    }

    @Override
    public void changeStatus(int id) {
        int waitCar = -11;
        int waitMan = -12;
        int noMan = -2;
        Pay temp = payRepository.getOne(id);
        if(temp.getStatus()==-1) {
            temp.setStatus(-11);
        }
        else if(temp.getStatus()==noMan){
            temp.setStatus(-12);
        }
        else if(temp.getStatus()==waitCar){
            temp.setStatus(1);
        }
        else if(temp.getStatus()==waitMan){
            temp.setStatus(2);
        }
        payRepository.save(temp);
    }
    @Override
    public List<Pay> findNew(String username){
        System.out.println(4);
        return payRepository.findNew(username);
    }

    @Override
    public List<Pay> findHistory(String username,int page){
        return payRepository.findHistory(username,10*(page-1),10);
    }

    @Override
    public List<Pay> findUnPayHistoryManager(int communityId,int page){
        return payRepository.findUnPayManager(communityId,10*(page-1),10);
    }

    @Override
    public List<Pay> findPayHistoryManager(int communityId,int page){
        return payRepository.findPayHistoryManager(communityId,10*(page-1),10);
    }

    @Override
    public void saveOrders(String id,String username, int pid, Date createTime,BigDecimal bill,int status){
        Date current = new Date();
        Orders order = new Orders(id,username,pid,current,bill,status);
        orderRepository.save(order);
    }

    @Override
    public void SaveOrders(Orders orders){
        orderRepository.save(orders);
    }
    @Override
    public List<Orders> getOrders(String username, int pid){
        return orderRepository.getOrders(username, pid);
    }

    @Override
    public Orders getOrder(String uid){
        return orderRepository.getOne(uid);
    }

    @Override
    public int getPageNum(String username){
        List<Pay> temp = payRepository.findPageNum(username);
        int pageNum=temp.size();
        if(pageNum%10!=0){
            pageNum=pageNum/10+1;
        }
        else {
            pageNum=pageNum/10;
        }
        return pageNum;
    }

    @Override
    public int getPageNumManager(int communityId){
        List<Pay> temp = payRepository.findPageNumManager(communityId);
        int pageNum=temp.size();
        if(pageNum%10!=0){
            pageNum=pageNum/10+1;
        }
        else {
            pageNum=pageNum/10;
        }
        return pageNum;
    }

    @Override
    public int getPageNumUnpayManager(int communityId){
        List<Pay> temp = payRepository.findPageNumUnpayManager(communityId);

        int pageNum=temp.size();
        System.out.println(pageNum);
        if(pageNum%10!=0){
            pageNum=pageNum/10+1;
        }
        else {
            pageNum=pageNum/10;
        }
        return pageNum;
    }

    @Override
    public void transfer(HttpServletResponse response, HttpServletRequest request, int communityId, BigDecimal money,String trade_no) throws IOException, AlipayApiException {
        response.setContentType("text/html;charset=utf-8");
        ;
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayFundTransToaccountTransferRequest request1 = new AlipayFundTransToaccountTransferRequest();
        Map<String, String> temp = payRepository.findAccount(communityId);
        String payee_account = temp.get("account");
        String out_biz_no = trade_no + "transfer";
        request1.setBizContent("{" +
                "    \"out_biz_no\":\"" + out_biz_no + "\"," +
                "    \"payee_type\":\"ALIPAY_LOGONID\"," +
                "    \"payee_account\":\"" + payee_account + "\"," +
                "    \"amount\":\"" + money + "\"," +
                "    \"remark\":\"转账备注\"," +
                "  }");
        AlipayFundTransToaccountTransferResponse response1 = alipayClient.execute(request1);
        if (response1.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            //输出
        }
    }
}
