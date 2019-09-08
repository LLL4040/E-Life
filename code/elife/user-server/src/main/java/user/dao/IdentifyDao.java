package user.dao;

import user.entity.Identify;

/**
 * @author ztHou
 */
public interface IdentifyDao {
    /**
     * save identify
     *
     * @param identify identify
     * @return saved identify
     */
    Identify save(Identify identify);

    /**
     * whether exists phone number
     * * @param phone phone number
     * * @return whether exists phone number
     */
    Boolean exists(String phone);

    /**
     * find identify by phone number
     *
     * @param phone phone number
     * @return identify found
     */
    Identify findByPhone(String phone);

    /**
     * delete identify by phone number
     *
     * @param phone phone number
     */
    void deleteByPhone(String phone);

}
