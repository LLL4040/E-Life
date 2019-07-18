package payserver.serviceimpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payserver.dao.PayDao;
import payserver.entity.Orders;
import payserver.entity.Pay;
import payserver.service.PayService;
import payserver.util.AlipayConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


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
        System.out.println(3);
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
        System.out.println(2);
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

    public void ali(HttpServletResponse response, HttpServletRequest request,
                    String id,String bill,String time) throws AlipayApiException, IOException {
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
        String order_number = id;
        //付款金额，从前台获取，必填
        String total_amount = bill;
        System.out.println(bill);
        //订单名称，必填
        String subject = new String("停车费支付");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(aliPayRequest).getBody();
        //输出
        out.println(result);
        //以下写自己的订单代码
    }


    @Override
    public boolean saveOrders(int pid,String username,BigDecimal bill){
        try{
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
            payDao.saveOrders(username+dateStr,username,pid,date,bill,0);
            payDao.changeStatus(pid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public JSONArray getOrders(String username,int pid){
        List<Orders> list = payDao.getOrders(username,pid);
        JSONArray jsonArray =new JSONArray();
        Iterator<Orders> iter = list.iterator();
        while(iter.hasNext()){
            Orders temp = iter.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",temp.getId());
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(temp.getCreateTime());
            jsonObject.put("time",dateStr);
            jsonObject.put("bill",temp.getBill().toString());
            jsonObject.put("status",temp.getStatus());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

   /* @Override
    public void returnUrl(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
            System.out.println(valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        PrintWriter out = response.getWriter();

        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            //e_life 业务逻辑
            Orders orders = payDao.getOrder(out_trade_no);
            int payId = orders.getPayId();
            System.out.println(payId);
            payDao.changeStatus(payId);
            //e_life 业务逻辑
            out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        }else {
            out.println("fail");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }
*/

    @Override
    public void notifyUrl(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, IOException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
            System.out.println(valueStr);
        }
        System.out.println("notify");
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        PrintWriter out = response.getWriter();
        if(signVerified) {
            //验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            Orders orders = payDao.getOrder(out_trade_no);
            int payId = orders.getPayId();
            Pay pay = payDao.findOne(payId);
            BigDecimal money = pay.getBill();
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                if(pay.getStatus()<0) {
                    System.out.println(payId);
                    orders.setStatus(1);
                    payDao.SaveOrders(orders);
                    payDao.changeStatus(payId);
                    payDao.transfer( response, request,pay.getCommunityId(),money,trade_no);
                    out.println("success");
                }
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                if(pay.getStatus()<0) {
                    System.out.println(payId);
                    orders.setStatus(1);
                    payDao.SaveOrders(orders);
                    payDao.changeStatus(payId);
                    payDao.transfer( response, request,pay.getCommunityId(),money,trade_no);
                    out.println("success");
                }

        }else {//验证失败
            out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }
    }
}
