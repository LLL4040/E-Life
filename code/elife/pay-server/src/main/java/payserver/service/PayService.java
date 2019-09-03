package payserver.service;

import com.alipay.api.AlipayApiException;
import net.minidev.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * payService interface
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public interface PayService {
    /**
     * save pay without id and status is 0
     * @param time
     * @param bill
     * @param status
     * @param managerName
     * @param username
     * @param communityId
     * @return
     */
    boolean save(String time, BigDecimal bill, int status, String managerName, String username, int communityId);

    /**
     * find List<pay> turn to JSON
     *
     * @param username
     * @return JSONArray
     */
    JSONArray findNew(String username);

    /**
     * find specific pay with ID
     *
     * @param id
     * @return JSONArray
     */
    JSONArray findOne(int id);

    /**
     * delete special pay with ID
     *
     * @param id
     * @return boolean
     */
    boolean deleteOne(int id);

    /**
     * change status
     * @param id
     * @return boolean
     */
    boolean payBill(int id);

    /**
     * move hot data to cold table
     * @param  username
     * @param page
     * @return boolean
     */
    JSONArray findHistory(String username, int page);

    /**
     * manager find those pay not pay
     * @param communityId
     * @param page
     * @return JSONArray
     */
    JSONArray findUnPayHistoryManager(int communityId, int page);

    /**
     * manager find those pay  payed
     * @param communityId
     * @param page
     * @return JSONArray
     */
    JSONArray findPayHistoryManager(int communityId, int page);

    /**
     * use aliPay interface
     * @param response
     * @param request
     * @param bill
     * @param id
     * @param time
     * @throws AlipayApiException
     * @throws IOException
     */
    void ali(HttpServletResponse response, HttpServletRequest request
    ,String id,String bill,String time) throws AlipayApiException, IOException;


    /**
     * use aliPay interface by mobilePhone
     * @param response
     * @param request
     * @param bill
     * @param id
     * @param time
     * @throws AlipayApiException
     * @throws IOException
     */
    void aliMobile(HttpServletResponse response, HttpServletRequest request
            ,String id,String bill,String time) throws AlipayApiException, IOException;
/**
     * aliBaBa return url
     * @param response
     * @param request
     * @throws AlipayApiException
     * @throws IOException
     */


    void returnUrl(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, IOException;

    /**
     * aliBaBa notify url
     * @param response
     * @param request
     * @throws AlipayApiException
     * @throws IOException
     */
    void notifyUrl(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, IOException;


    /**
     * saveOrders username to make orderId
     * @param pid
     * @param username
     * @param bill
     * @return
     */
    boolean saveOrders(int pid,String username,BigDecimal bill);

    /**
     * get Orders by username
     * @param username
     * @param pid
     * @return JSONArray
     */
    JSONArray getOrders(String username, int pid);
}
