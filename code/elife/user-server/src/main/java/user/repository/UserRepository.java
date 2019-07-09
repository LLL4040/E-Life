package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.entity.User;

import javax.transaction.Transactional;

/**
 * @author ztHou
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * whether exists username
     * @param username username
     * @return whether exists username
     */
    Boolean existsByUsername(String username);

    /**
     * whether exists phone
     * @param phone phone
     * @return whether exists phone
     */
    Boolean existsByPhone(String phone);

    /**
     * find user by username
     * @param username username
     * @return user found
     */
    User findByUsername(String username);

    /**
     * find user by phone
     * @param phone phone
     * @return user found
     */
    User findByPhone(String phone);

    /**
     * delete data from database by username
     * @param username username
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsername(String username);

}
