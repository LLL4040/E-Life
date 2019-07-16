package payserver.serviceimpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payserver.dao.PayDao;
import payserver.entity.Pay;
import payserver.service.PayService;
import payserver.util.AlipayConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
/**
 * PackageService class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    public JSONArray listToJsonArray (List<Pay> list){
        JSONArray jsonArray =new JSONArray();
        Iterator<Pay> iter = list.iterator();
        while(iter.hasNext()){
            Pay temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            jsonObject.put("time",temp.getTime());
            jsonObject.put("username",temp.getUsername());
            jsonObject.put("bill",temp.getBill());
            jsonObject.put("status",temp.getStatus());
            jsonObject.put("managerName",temp.getManagerName());
            jsonObject.put("communityId",temp.getCommunityId());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @Override
    public boolean save(String time, BigDecimal bill,int status ,String managerName, String username, int communityId) {

            payDao.save(time, status,bill,managerName, username, communityId);
            return true;

    }

    @Override
    public JSONArray findNew(String username){
        List<Pay> list = payDao.findNew(username);
        return listToJsonArray(list);
    }
    @Override
    public JSONArray findOne(int id){
            List<Pay> list =new ArrayList<Pay>();
            list.add(0, payDao.findOne(id));
            return listToJsonArray(list);
    }

    @Override
    public boolean deleteOne(int id){
        try{
            payDao.deleteOne(id);
            return true;
        }
        catch (Exception e) {
        return false;
        }
    }

    @Override
    public boolean payBill(int id){
        try{
            payDao.changeStatus(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public JSONArray findHistory(String username, int page) {
        List<Pay> list = payDao.findHistory(username,page);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findUnPayHistoryManager(int communityId, int page){
        List<Pay> list = payDao.findUnPayHistoryManager(communityId,page);
        return listToJsonArray(list);
    }

    @Override
    public JSONArray findPayHistoryManager(int communityId, int page){
        List<Pay> list = payDao.findPayHistoryManager(communityId, page);
        return listToJsonArray(list);
    }

    @Override

    public void ali(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        Date date=new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss").format(date);
        String order_number = "abcd"+dateStr;
        //付款金额，从前台获取，必填
        String total_amount = new String("20");
        //订单名称，必填
        String subject = new String("支付宝沙箱支付（名称随便起）");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(aliPayRequest).getBody();
        //输出
        out.println(result);//以下写自己的订单代码
    }

    @Override
    public boolean saveOrders(int pid,String username,BigDecimal bill){
        try{
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss").format(date);
            payDao.saveOrders(username+dateStr,pid,date,bill,0);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
