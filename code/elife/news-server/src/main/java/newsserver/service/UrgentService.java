package newsserver.service;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * UrgentService interface
 *
 * @author wyx
 * @date 2019.07.04
 */
public interface UrgentService {

    /**
     * save Urgent without time
     *
     * @param managerName
     * @param content
     * @param status
     */
    boolean save(String managerName, String content, int status, int communityId);

    /**
     * find special Urgent with id
     *
     * @param id
     * @return JSONObject
     */
    JSONObject findOne(int id);

    /**
     * delete Urgent by Id
     *
     * @param id
     * @return boolean delete success or not
     */
    boolean deleteOne(int id);

    /**
     * move cold data to history data table
     */
    boolean moveTable();

    /**
     * find newest Urgent by communityId return JSONObject
     *
     * @param communityId
     * @return
     */
    JSONObject findNew(int communityId);

    /**
     * find history table from teo table
     *
     * @param communityId
     * @return
     */
    JSONArray findHistory(int communityId);
}
