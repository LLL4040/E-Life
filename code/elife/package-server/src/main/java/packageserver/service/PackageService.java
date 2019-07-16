package packageserver.service;

import net.minidev.json.JSONArray;


/**
 * packageService interface
 *
 * @Author wyx
 * @Date 2019.07.12
 */
public interface PackageService {
    /**
     * save package without id and status is 0
     * @param time
     * @param managerName
     * @param username
     * @param communityId
     * @return
     */
    boolean save(String time, String managerName, String username, int communityId);

    /**
     * find List<package> turn to JSON
     *
     * @param username
     * @return JSONArray
     */
    JSONArray findNew(String username);

    /**
     * find specific News with ID
     *
     * @param id
     * @return JSONArray
     */
    JSONArray findOne(int id);

    /**
     * delete special package with ID
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
    boolean takeOut(int id);

    /**
     * move hot data to cold table
     * @param  username
     * @param page
     * @return boolean
     */
    JSONArray findHistory(String username,int page);

    /**
     * manager find those package not taken
     * @param communityId
     * @param page
     * @return JSONArray
     */
    JSONArray findHistoryManager(int communityId,int page);
}
