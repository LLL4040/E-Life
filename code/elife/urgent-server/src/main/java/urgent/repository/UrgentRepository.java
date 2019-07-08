package urgent.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import urgent.entity.Urgent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UrgentRepository interface
 *
 * @author wyx
 * @date 2019.07.04
 */
public interface UrgentRepository extends JpaRepository<Urgent, Integer> {

    /**
     * save Urgent entity without time,
     * because database will create local time with null
     *
      * @param id
     * @param managerName
     * @param content
     * @param status
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO urgent(id,managername,content,status,community_id) VALUES (?1,?2,?3,?4,?5) ",nativeQuery = true)
    void saveUrgent(int id, String managerName, String content, int status, int communityId);
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" call cStatus(?1) ",nativeQuery = true)
    void changeStatus(int communityId);
}
