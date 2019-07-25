package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.entity.Community;

import javax.transaction.Transactional;

/**
 * @author ztHou
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {
    /**
     * find community by id
     * @param id id
     * @return community found
     */
    Community findById(Long id);

    /**
     * delete community by id
     * @param id id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);

    /**
     * whether exists manager
     * @param manager manager
     * @return whether exists manager
     */
    Boolean existsByManager(String manager);

    /**
     * whether exists phone
     * @param phone phone
     * @return whether exists phone
     */
    Boolean existsByPhone(String phone);

    /**
     * find community by manager
     * @param manager manager
     * @return community
     */
    Community findByManager(String manager);

    /**
     * find community by phone
     * @param phone phone
     * @return community
     */
    Community findByPhone(String phone);
}
