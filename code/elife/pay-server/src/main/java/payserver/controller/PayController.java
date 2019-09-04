package payserver.controller;

import com.alipay.api.AlipayApiException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import payserver.dao.PayDao;
import payserver.service.MoneyService;
import payserver.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
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

    @Autowired
    private PayDao payDao;

    @Autowired
    private MoneyService moneyService;

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
    public boolean savePay(HttpServletRequest request, String time, BigDecimal bill,int status, String managerName, String username, int communityId ){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (StringUtils.isEmpty(name) || !("1".equals(role))  || !(name.equals(managerName))) {
            return false;
        }
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
    public JSONArray findHistory(HttpServletRequest request, String username, int page){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isEmpty(name) || !("0".equals(role))  || !(name.equals(username))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return payService.findHistory(username,page);
    }
    @RequestMapping(path = "/findUnPayHistory")
    @ResponseBody
    public JSONArray findUnPayHistoryManager(HttpServletRequest request, int communityId, int page){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return payService.findUnPayHistoryManager(communityId,page);
    }
    @RequestMapping(path = "/findPayHistory")
    @ResponseBody
    public JSONArray findPayHistoryManager(HttpServletRequest request, int communityId, int page){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isEmpty(name) || !("1".equals(role))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", 0);
            jsonArray.add(jsonObject);
            return jsonArray;
        }
        return payService.findPayHistoryManager(communityId,page);
    }

    @RequestMapping("/ali")
    public void ali(HttpServletResponse response, HttpServletRequest request
            , String id,String bill,String time) throws IOException, AlipayApiException {
        payService.ali(response,request,id,bill,time);
    }
    @RequestMapping("/aliMobile")
    public void aliMobile(HttpServletResponse response, HttpServletRequest request
            , String id,String bill,String time) throws IOException, AlipayApiException {
         payService.aliMobile(response,request,id,bill,time);
    }

    @RequestMapping("/return")
    public void returnUrl(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        System.out.println(12345);
        payService.returnUrl(response,request);
        System.out.println("跳转成功");
    }
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
    public JSONArray getOrders(String username, int pid) {
        return payService.getOrders(username,pid);
    }

    @RequestMapping("/getOrderInfo")
    @ResponseBody
    public Object getOrderInfo(String username, int pid, BigDecimal bill) {
        int status=payDao.findOne(pid).getStatus();
        if(status==-1||status==-2) {
            payService.saveOrders(pid, username, bill);
        }
        return payService.getOrders(username,pid).get(0);
    }

    /**
     * 小区管理员初始化小区的房间号
     * 例如A类型房间 100 平方米 有15栋这样的楼 一栋楼有20层 一层有4户
     * maxnum=15 maxfloor=20 maxroom=4 roomSpace=100
     * 生成 A010101这样的字符串存入数据库
     * @param type
     * @param maxNum
     * @param maxFloor
     * @param maxRoom
     * @param roomSpace
     * @param communityId
     * @return
     */
    @RequestMapping("/saveRoom")
    @ResponseBody
    public boolean saveRoom(String type ,int maxNum,int maxFloor,int maxRoom,int roomSpace,int communityId) {
       return moneyService.initRoom(type, maxNum, maxFloor, maxRoom,communityId,roomSpace);
    }

    /**
     * 获取一个小区的房间范围即roomspace表 获取 type maxnum maxfloor 等范围找roomnuber给管理员搜索room
     * @param communityId
     * @return
     */
    @RequestMapping("/getRoomSpace")
    @ResponseBody
    public JSONArray getRoomSpace(int communityId) {
        return moneyService.getRoomSpace(communityId);
    }

    /**
     * 用room number 获取room 给管理员修改
     * @param type
     * @param room
     * @param communityId
     * @return
     */
    @RequestMapping("/getRoomByRoomNumber")
    @ResponseBody
    public JSONObject getRoomByRoomNumber(String type,String room,int communityId) {
        return moneyService.getRoomByRoomNumber(type, room, communityId);
    }

    /**
     * 给管理员修改room房间的用户名
     * @param username
     * @param type
     * @param room
     * @param communityId
     * @return
     */
    @RequestMapping("/saveUsername")
    @ResponseBody
    public boolean saveUsername(String username,String type,String room,int communityId) {
        return moneyService.saveUsername(username, type, room, communityId);
    }

    /**
     * 给管理员保存停车方案 A是小车包月费 B是电动车包月费
     * @param Amoney
     * @param Bmoney
     * @param communityId
     * @return
     */
    @RequestMapping("/saveParkPlan")
    @ResponseBody
    public boolean saveParkPlan(BigDecimal Amoney,BigDecimal Bmoney,int communityId) {
        return moneyService.initParkPlan(Amoney,Bmoney, communityId);
    }

    /**
     * 自动计算管理费步骤一 更新room表
     * @param money
     * @param communityId
     * @return
     */
    @RequestMapping("/calManMoney")
    @ResponseBody
    public boolean calManMoney(BigDecimal money,int communityId) {
        return moneyService.calManMoney(money,communityId);
    }

    /**
     * 自动计算管理费步骤2 存入账单表
     * @param managerName
     * @param communityId
     * @return
     */
    @RequestMapping(path = "/AutoSaveManPay")
    @ResponseBody
    public boolean autoSaveManPay( String managerName, int communityId ){
        return payService.autoSaveManPay(managerName,communityId);
    }

    /**
     * 给管理员修改用户的停车方案 形如A2B2这样的字符串AB大写，默认A0B0
     * @param username
     * @param parking
     * @param type
     * @param communityId
     * @return
     */
    @RequestMapping("/changeParking")
    @ResponseBody
    public boolean changeParking(String username,String parking,String type, int communityId){
        moneyService.changeParking(username, parking, type, communityId);
        return true;
    }

    /**
     * 计算停车账单步骤一 更新room 表
     * @param communityId
     * @return
     */
    @RequestMapping("/calParking")
    @ResponseBody
    public boolean calParkingMoney(int communityId) {
        return moneyService.calParking(communityId);
    }

    /**
     * 计算停车账单步骤2 更新账单表
     * @param managerName
     * @param communityId
     * @return
     */
    @RequestMapping(path = "/AutoSaveParkPay")
    @ResponseBody
    public boolean autoSaveParkPay( String managerName, int communityId ){
        return payService.autoSaveParkPay(managerName,communityId);
    }
}
