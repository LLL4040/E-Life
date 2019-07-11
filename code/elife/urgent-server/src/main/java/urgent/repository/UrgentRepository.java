package urgent.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import urgent.entity.Urgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
     * @param managerName
     * @param content
     * @param status
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO urgent(managername,content,status,community_id) VALUES (?1,?2,?3,?4) ",nativeQuery = true)
    void saveUrgent( String managerName, String content, int status, int communityId);

    /**
     * it will call cStatus procedure to change old one status 0to1
     *
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" call cStatus(?1) ",nativeQuery = true)
    void changeStatus(int communityId);

    /**
     * it will move hot data to cold table
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" call moveurgent(?1) ",nativeQuery = true)
    void moveTable(int nonused);

    /**
     * find newest urgent  by community
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from urgent where community_id=?1 and status=0",nativeQuery = true)
    Urgent findNew(int communityId);

    /**
     * return history from hot table
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from urgent where community_id=?1",nativeQuery = true)
    List<Urgent> findHistory(int communityId);
    @Query(value=" select * from urgent where id=?1 ",nativeQuery = true)
    Urgent getONE(int id);

}
