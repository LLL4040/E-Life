package newsserver.repository;


import newsserver.entity.NewsUsed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * NewsUsedRepository interface
 *
 * @author wyx
 * @date 2019.07.10
 */
public interface NewsUsedRepository extends JpaRepository<NewsUsed, Integer> {

    /**
     * return history table from newsUsed
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from newsused where community_id=?1",nativeQuery = true)
    List<NewsUsed> findHistory(int communityId);

}

