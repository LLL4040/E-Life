package newsserver.dao;


import newsserver.entity.Urgent;
import newsserver.entity.UrgentUsed;


import java.util.List;

/**
 * UrgentDao interface
 *
 * @author wyx
 * @date 2019.07.04
 */
public interface UrgentDao {
    /**
     * save Urgent withou time
     *
     * @param content
     * @param managerName
     * @param status
     */
    void save( String content, String managerName, int status, int communityId);

    /**
     * find special Urgent with Id
     *
     * @param id
     * @return
     */
    Urgent findOne(int id);

    /**
     * delete one urgent by ID and return it
     *
     * @param id
     * @return none
     */
    void deleteOne(int id);

    /**
     * find newest urgent by communityId
     *
     * @param communityId
     * @return
     */
    Urgent findNew(int communityId);
    /**
     * move cold table data to history table
     */
    void moveTable();

    /**
     * return history urgent by communityId from clod table
     *
     * @param communityId
     * @return
     */
    List<UrgentUsed> findHistory(int communityId);

    /**
     * return List from chot table
     *
     * @param communityId
     * @return
     */
    List<Urgent> findHistoryHot(int communityId);
}
