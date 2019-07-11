package group.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface DemandService {
    /**
     * add demand
     * @param startTime startTime
     * @param endTime endTime
     * @param content content
     * @param username username publish the demand
     * @param communityId communityId
     * @return whether add successfully
     */
    JSONObject addDemand(String startTime, String endTime, String content, String username, Long communityId);

    /**
     * delete demand
     * @param id id
     * @return whether delete demand successfully
     */
    JSONObject deleteDemand(Long id);

    /**
     * get all demand of the community_id
     * @param communityId community_id
     * @return demand list
     */
    JSONArray getAllDemand(Long communityId);

    /**
     * username participate into demand id
     * @param username username
     * @param id id
     * @return whether participate successfully
     */
    JSONObject participateDemand(String username, Long id);

    /**
     * username quit from demand id
     * @param username username
     * @param id id
     * @return whether quit successfully
     */
    JSONObject quitDemand(String username, Long id);
}
