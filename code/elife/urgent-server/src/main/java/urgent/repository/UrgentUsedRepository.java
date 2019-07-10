package urgent.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import urgent.entity.UrgentUsed;

import java.util.List;

/**
 * UrgentRepository interface
 *
 * @author wyx
 * @date 2019.07.04
 */
public interface UrgentUsedRepository extends JpaRepository<UrgentUsed, Integer> {
    /**
     * return history urgent from cold table
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from urgentused where community_id=?1",nativeQuery = true)
    List<UrgentUsed> findHistory(int communityId);
}