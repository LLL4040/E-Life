package payserver.dao;





import payserver.entity.Pay;

import java.math.BigDecimal;
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
     * @return list<Pay>all history list</Pay>
     */

    List<Pay> findHistory(String username);

    /**
     * find no take package table
     * @param communityId
     * @return list<Pay>all UnPay history list</Pay>
     */

    List<Pay> findUnPayHistoryManager(int  communityId);

    /**
     * find no take package table
     * @param communityId
     * @return list<Pay>all history Pay list</Pay>
     */

    List<Pay> findPayHistoryManager(int  communityId);


}
