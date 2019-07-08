package urgent.dao;

import urgent.entity.Urgent;

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
     * @param id
     * @param content
     * @param managerName
     * @param status
     */
    void save(int id, String content, String managerName, int status, int communityId);

    /**
     * find special Urgent with Id
     *
     * @param id
     * @return
     */
    Urgent findOne(int id);
}
