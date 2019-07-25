package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface UserService {
    /**
     * find username by phone
     *
     * @param phone user's phone
     * @return username
     */
    String findUsernameByPhone(String phone);

    /**
     * whether username typed by user available
     *
     * @param username username typed by user
     * @return whether username available
     */
    JSONObject usernameAvailable(String username);

    /**
     * whether username typed by user available
     *
     * @param username username typed by user
     * @return whether username available in manager
     */
    JSONObject usernameAvailableManager(String username);

    /**
     * whether phone typed by user available
     *
     * @param phone phone typed by user
     * @return whether phone available
     */
    JSONObject phoneAvailable(String phone);

    /**
     * whether phone typed by user available
     *
     * @param phone phone typed by user
     * @return whether phone available in mabager
     */
    JSONObject phoneAvailableManager(String phone);

    /**
     * register into database as a user
     *
     * @param username    username
     * @param password    password
     * @param phone       phone
     * @param code        identify code
     * @param email       email
     * @param communityId community id chosen by user
     * @return whether register successfully 1:success, 0:fail
     */
    JSONObject registerUser(String username, String password, String phone, String code, String email, Long communityId);

    /**
     * register into database as a merchant
     *
     * @param username    username
     * @param password    password
     * @param phone       phone
     * @param code        identify code
     * @param email       email
     * @param communityId community id chosen by user
     * @return whether register successfully 1:success, 0:fail
     */
    JSONObject registerMerchant(String username, String password, String phone, String code, String email, Long communityId);

    /**
     * register into database as a manager
     *
     * @param username    username
     * @param password    password
     * @param phone       phone
     * @param code        identify code
     * @param email       email
     * @param communityId community id chosen by manager
     * @return whether register successfully 1:success, 0:fail
     */
    JSONObject registerManager(String username, String password, String phone, String code, String email, Long communityId);

    /**
     * login with username and password
     *
     * @param username username
     * @param password password
     * @param id id
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject login(String username, String password, String id);

    /**
     * login with phone number and identify code
     *
     * @param phone phone number
     * @param code  identify code
     * @param id id
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject loginPhone(String phone, String code, String id);

    /**
     * login with username and password
     *
     * @param username username
     * @param password password
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject loginManager(String username, String password);

    /**
     * login with phone number and identify code
     *
     * @param phone phone number
     * @param code  identify code
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject loginPhoneManager(String phone, String code);

    /**
     * reset password with phone number and identify code
     *
     * @param phone    phone number
     * @param code     identify code
     * @param password new password
     * @return whether reset password successfully 1:success, 0:fail
     */
    JSONObject findPassword(String phone, String code, String password);

    /**
     * reset password with phone number and identify code
     *
     * @param phone    phone number
     * @param code     identify code
     * @param password new password
     * @return whether reset password successfully 1:success, 0:fail
     */
    JSONObject findPasswordManager(String phone, String code, String password);

    /**
     * change manager role
     *
     * @param username username of manager being changed
     * @param role role
     * @return whether change role successfully 1:success, 0:fail
     */
    JSONObject changeManagerRole(String username, Integer role);

    /**
     * show communities
     * @return communities
     */
    JSONArray showCommunities();

    /**
     * get community by id
     * @param id id
     * @return community name
     */
    JSONObject getCommunityById(Long id);

    /**
     * get username list by communityId
     * @param communityId community id
     * @return username list
     */
    JSONArray getUsername(Long communityId);

    /**
     * change email
     * @param username username
     * @param email email
     * @return whether change successfully
     */
    JSONObject changeEmail(String username, String email);
}
