package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface MerchantService {
    /**
     * merchant register
     * @param username username
     * @param password password
     * @param email email
     * @param name merchant name
     * @param merchantPhone merchant phone
     * @param type merchant type
     * @param address merchant address
     * @param detail merchant detail
     * @param phone user phone
     * @param code user identify code
     * @return whether register successfully
     */
    JSONObject register(String username, String password, String email, String name, String merchantPhone, String type,
                        String address, String detail, String phone, String code, Long communityId);

    /**
     * change some values of merchant
     * @param id id
     * @param name name
     * @param merchantPhone merchantPhone
     * @param type type
     * @param address address
     * @param detail detail
     * @return whether change merchant successfully
     */
    JSONObject changeMerchant(Long id, String name, String merchantPhone, String type,
                              String address, String detail);

    /**
     * get all merchant by type
     * @param type type
     * @return all merchant of the merchant
     */
    JSONArray findAllByType(String type);

    /**
     * find all merchant
     * @return all merchant
     */
    JSONArray findAll();

    /**
     * delete merchant data
     * @param id id
     * @return whether delete successfully
     */
    JSONObject deleteMerchant(Long id);

    /**
     * get merchant by id
     * @param id merchant id
     * @return merchant
     */
    JSONObject getMerchant(Long id);

    /**
     * get merchant by username
     * @param username username
     * @return merchant found
     */
    JSONObject getMerchantByUsername(String username);
}
