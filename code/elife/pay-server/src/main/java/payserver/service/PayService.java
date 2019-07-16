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
     * @param managerName
     * @param username
     * @param communityId
     * @return
     */
    boolean save(String time, BigDecimal bill, String managerName, String username, int communityId);

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
     * @return boolean
     */
    JSONArray findHistory(String username);

    /**
     * manager find those pay not pay
     * @param communityId
     * @return JSONArray
     */
    JSONArray findUnPayHistoryManager(int communityId);

    /**
     * manager find those pay  payed
     * @param communityId
     * @return JSONArray
     */
    JSONArray findPayHistoryManager(int communityId);

    /**
     * use aliPay interface
     * @param response
     * @param request
     * @throws AlipayApiException
     * @throws IOException
     */
    void ali(HttpServletResponse response, HttpServletRequest request) throws AlipayApiException, IOException;

    /**
     * saveOrders username to make orderId
     * @param pid
     * @param username
     * @param bill
     * @return
     */
    boolean saveOrders(int pid,String username,BigDecimal bill);
}
