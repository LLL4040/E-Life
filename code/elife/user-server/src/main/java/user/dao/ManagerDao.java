package user.dao;

import user.entity.Manager;

/**
 * @author ztHou
 */
public interface ManagerDao {
    /**
     * save manager in database
     * @param manager manager
     * @return manager saved
     */
    Manager save(Manager manager);

    /**
     * whether exists username in database
     * @param username username
     * @return whether exists username
     */
    Boolean existByUsername(String username);

    /**
     * whether exists phone in database
     * @param phone phone
     * @return whether exists phone
     */
    Boolean existsByPhone(String phone);

    /**
     * find manager by username
     * @param username username
     * @return manager found
     */
    Manager findByUsername(String username);

    /**
     * find manager by phone
     * @param phone phone
     * @return manager found
     */
    Manager findByPhone(String phone);

    /**
     * delete data from database by username
     * @param username username
     */
    void deleteByUsername(String username);
}
