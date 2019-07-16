package payserver.service;

import net.minidev.json.JSONArray;

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
}
