package user.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author ztHou
 */
public interface UserService {

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
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject login(String username, String password);

    /**
     * login with phone number and identify code
     *
     * @param phone phone number
     * @param code  identify code
     * @return whether login successfully 1:success, 0:fail
     */
    JSONObject loginPhone(String phone, String code);

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
     * @return whether change role successfully 1:success, 0:fail
     */
    JSONObject changeManagerRole(String username, Integer role);

    JSONArray showCommunities();

}
