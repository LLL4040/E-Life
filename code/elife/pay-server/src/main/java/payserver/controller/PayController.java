package payserver.controller;

import com.alipay.api.AlipayApiException;
import net.minidev.json.JSONArray;
import payserver.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@RequestMapping(path="/api/Pay")
@RestController
/**
 * PayController class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * savePay status= -1 parkBill status =-2 managerBill
     * @param time
     * @param bill
     * @param managerName
     * @param username
     * @param communityId
     * @return
     */
    @RequestMapping(path = "/savePay")
    @ResponseBody
    public boolean savePay(String time, BigDecimal bill,int status, String managerName, String username, int communityId ){
        return payService.save(time,bill,status,managerName,username,communityId);
    }
    @RequestMapping(path = "/findNew")
    @ResponseBody
    public JSONArray findNew(String username){
        System.out.println(1);
        return payService.findNew(username);
    }
    @RequestMapping(path = "/deleteOne")
    @ResponseBody
    public boolean deleteOne(int id){
        return payService.deleteOne(id);
    }
    @RequestMapping(path = "/payBill")
    @ResponseBody
    public boolean payBill(int id){
        return payService.payBill(id);
    }
    @RequestMapping(path = "/findOne")
    @ResponseBody
    public JSONArray findOne(int id){
        return payService.findOne(id);
    }
    @RequestMapping(path = "/findHistory")
    @ResponseBody
    public JSONArray findHistory(String username, int page){
        return payService.findHistory(username,page);
    }
    @RequestMapping(path = "/findUnPayHistory")
    @ResponseBody
    public JSONArray findUnPayHistoryManager(int communityId, int page){
        return payService.findUnPayHistoryManager(communityId,page);
    }
    @RequestMapping(path = "/findPayHistory")
    @ResponseBody
    public JSONArray findPayHistoryManager(int communityId, int page){
        return payService.findPayHistoryManager(communityId,page);
    }

    @RequestMapping("/ali")
    public void ali(HttpServletResponse response, HttpServletRequest request
            , String id,String bill,String time) throws IOException, AlipayApiException {
        payService.ali(response,request,id,bill,time);
    }
   /* @RequestMapping("/return")
    public void returnUrl(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        System.out.println(12345);
        payService.returnUrl(response,request);
    }*/
    @RequestMapping("/notify")
    public void notifyUrl(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        System.out.println(12345);
        payService.notifyUrl(response,request);
    }

    @RequestMapping("/saveOrders")
    @ResponseBody
    public void saveOrders(String username,int pid,BigDecimal bill) {
        payService.saveOrders(pid,username,bill);
    }

    @RequestMapping("/getOrders")
    @ResponseBody
    public JSONArray getOrders(String username) {
        return payService.getOrders(username);
    }
}
