package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import user.entity.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * whether exists username
     *
     * @param username username
     * @return whether exists username
     */
    Boolean existsByUsername(String username);

    /**
     * whether exists phone
     *
     * @param phone phone
     * @return whether exists phone
     */
    Boolean existsByPhone(String phone);

    /**
     * find user by username
     *
     * @param username username
     * @return user found
     */
    User findByUsername(String username);

    /**
     * find user by phone
     *
     * @param phone phone
     * @return user found
     */
    User findByPhone(String phone);

    /**
     * delete data from database by username
     *
     * @param username username
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsername(String username);

    /**
     * find all user information contains the username
     * @param username username
     * @return all user that contains the username
     */
    List<User> findAllByUsernameContains(String username);

    /**
     * find all user in the given community
     * @param communityId communityId
     * @return all user in the given community
     */
    List<User> findAllByCommunityId(Long communityId);

    /**
     * find all username from community
     * @param communityId community id
     * @return all username
     */
    @Modifying
    @Query(value = "select username from user where community_id = ?1", nativeQuery = true)
    List<String> findUsernameByCommunityId(Long communityId);

}
