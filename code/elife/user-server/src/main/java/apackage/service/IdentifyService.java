package apackage.service;

/**
 * @author ztHou
 */
public interface IdentifyService {
    /**
     * send identify code to phone
     *
     * @param phone phone number
     * @return true as successfully send, false as the bad ending
     */
    Boolean sendIdentifyCode(String phone);

    /**
     * verify identify code to phone with the code user typed in
     *
     * @param phone user's phone
     * @param code  code typed in by user
     * @return whether the verification code entered by the user the same as in the database
     */
    Boolean verifyIdentifyCode(String phone, String code);


}
