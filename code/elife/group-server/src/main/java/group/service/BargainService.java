package group.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface BargainService {


    /**
     * add bargain
     * @param startTime start time
     * @param endTime end time
     * @param title title of the bargain
     * @param merchantId merchantId
     * @param goods goods
     * @param content content
     * @param status status
     * @return whether add bargain successfully
     */
    JSONObject addBargain(String startTime, String endTime, String title, Long merchantId, String goods, String content, Integer status);

    /**
     * delete bargain
     * @param id id
     * @return whether delete bargain successfully
     */
    JSONObject deleteBargain(Long id);

    /**
     * get all bargain
     * @return all bargain
     */
    JSONArray getAllBargain();
}
