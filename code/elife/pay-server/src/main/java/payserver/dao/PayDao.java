package payserver.dao;





import com.alipay.api.AlipayApiException;
import payserver.entity.Orders;
import payserver.entity.Pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * PayDao interface
 *
 * @author wyx
 * @date 2019.07.12
 */
public interface PayDao {

    /**
     * save Pay without id
     * @param time
     * @param status
     * @param bill
     * @param managerName
     * @param username
     * @param communityId
     */
    void save(String time, int status, BigDecimal bill, String managerName, String username, int communityId);

    /**
     * set status to 1 just  Pay
     * @param id
     */
    void changeStatus(int id);

    /**
     * find special Pay with Id
     *
     * @param id
     * @return
     */
    Pay findOne(int id);

    /**
     * delete one package by ID and return it
     *
     * @param id
     * @return none
     */
    void deleteOne(int id);

    /**
     * find newest UnPay List<Pay></Pay> by username
     *
     * @param username
     * @return
     */
    List<Pay> findNew(String username);

    /**
     * find history table
     * @param username
     * @param page
     * @return list<Pay>all history list</Pay>
     */

    List<Pay> findHistory(String username,int page);

    /**
     * find no take package table
     * @param communityId
     * @param page
     * @return list<Pay>all UnPay history list</Pay>
     */

    List<Pay> findUnPayHistoryManager(int  communityId,int page);

    /**
     * find no take package table
     * @param communityId
     * @param page
     * @return list<Pay>all history Pay list</Pay>
     */

    List<Pay> findPayHistoryManager(int  communityId,int page);

    /**
     * save otders
     * @param id
     * @param username
     * @param pid
     * @param createTime
     * @param bill
     * @param status
     */
    void saveOrders(String id,String username, int pid, Date createTime,BigDecimal bill, int status);

    /**
     * save orders orders
     * @param orders
     */
    void SaveOrders(Orders orders);

    /**
     * get orders with username
     * @param username
     * @param pid
     * @return Orders List
     */
    List<Orders> getOrders(String username, int pid);

    /**
     * find order one by id
     * @param oid
     * @return order
     */
    Orders getOrder(String oid);




    /**
     * transfer money
     * @param response
     * @param request
     * @param communityId
     * @param money
     * @param trade_no
     * @throws IOException
     * @throws AlipayApiException
     */
    void transfer(HttpServletResponse response, HttpServletRequest request, int communityId, BigDecimal money,String trade_no) throws IOException, AlipayApiException;

    /**
     * get pagenum
     * @param username
     * @return
     */
    int getPageNum(String username);

    /**
     * get pay manager
     * @param communityId
     * @return
     */
    int getPageNumManager(int communityId);

    /**
     * get page unpay manager
     * @param communityId
     * @return
     */
    int getPageNumUnpayManager(int communityId);
}
