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

    @RequestMapping(path = "/savePay")
    @ResponseBody
    public boolean savePay(String time, BigDecimal bill, String managerName, String username, int communityId ){
        return payService.save(time,bill,managerName,username,communityId);
    }
    @RequestMapping(path = "/findNew")
    @ResponseBody
    public JSONArray findNew(String username){
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
    public JSONArray findHistory(String username){
        return payService.findHistory(username);
    }
    @RequestMapping(path = "/findUnPayHistory")
    @ResponseBody
    public JSONArray findUnPayHistoryManager(int communityId){
        return payService.findUnPayHistoryManager(communityId);
    }
    @RequestMapping(path = "/findPayHistory")
    @ResponseBody
    public JSONArray findPayHistoryManager(int communityId){
        return payService.findPayHistoryManager(communityId);
    }

    @RequestMapping("ali")
    public void ali(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        payService.ali(response,request);
    }

    @RequestMapping("saveOrders")
    public void saveOrders(String username,int pid,BigDecimal bill) throws IOException, AlipayApiException {
        payService.saveOrders(pid,username,bill);
    }

}
