package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.entity.Manager;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface ManagerRepository extends JpaRepository<Manager, String> {
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
     * find manager by username
     *
     * @param username username
     * @return manager found
     */
    Manager findByUsername(String username);

    /**
     * find all by community id
     * @param communityId community id
     * @return all managers
     */
    List<Manager> findAllByCommunityId(Long communityId);

    /**
     * find manager by phone
     *
     * @param phone phone
     * @return manager found
     */
    Manager findByPhone(String phone);

    /**
     * delete data from database by username
     *
     * @param username username
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsername(String username);
}
