package newsserver.repository;

import newsserver.entity.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * NewsRepository interface
 *
 * @author wyx
 * @date 2019.07.09
 */
public interface NewsRepository extends JpaRepository<News, Integer> {

    /**
     * save news without auto inc id and default time
     *
     * @param content
     * @param managerName
     * @param title
     * @param status
     * @param photo
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO news(content,managername,title,status,photo,community_id) VALUES (?1,?2,?3,?4,?5,?6) ",nativeQuery = true)
    void saveNews(String content, String managerName, String title, int status, String photo,int communityId);
    /**
     * it will call cStatus procedure to change old one status 0to1
     *
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" call cnStatus(?1) ",nativeQuery = true)
    void changeStatus(int communityId);

    /**
     * it will move hot data to cold table
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" call movenews(?1) ",nativeQuery = true)
    void moveTable(int nonUsed);

    /**
     * find newest urgent  by community
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from news where community_id=?1 and status=0",nativeQuery = true)
    List<News> findNew(int communityId);

    /**
     * return history from hot table
     *
     * @param communityId
     * @return
     */
    @Query(value=" select * from news where community_id=?1",nativeQuery = true)
    List<News> findHistory(int communityId);

}

