package payserver.controller;

import net.minidev.json.JSONArray;
import payserver.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
