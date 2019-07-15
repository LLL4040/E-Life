package group.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface DiscountService {
    /**
     * add discount information
     * @param startTime start time
     * @param endTime end time
     * @param merchantId merchant id
     * @param number number
     * @param content content
     * @param status status
     * @param communityId communityId of merchant
     * @return whether add discount information successfully
     */
    JSONObject addDiscount(String startTime, String endTime, Long merchantId, Integer number, String content, Integer status, Long communityId, String title);

    /**
     * delete discount by id
     * @param id id
     * @return whether delete discount successfully
     */
    JSONObject deleteDiscount(Long id);

    /**
     * find discount messages by merchant id
     * @param merchantId merchant id
     * @return discount list
     */
    JSONArray findDiscountByMerchantId(Long merchantId);

    /**
     * find all by end time after current and community id equals to the given
     * @param communityId community id
     * @return discount list
     */
    JSONArray findDiscountByCommunityId(Long communityId);
}
