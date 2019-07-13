package newsserver.repository;


import newsserver.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * ActivityRepository class
 *
 * @Author wyx
 * @Date 2019.07.11
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    /**
     * save activity without id
     *
     * @param startTime
     * @param endTime
     * @param content
     * @param managerName
     * @param title
     * @param status
     * @param photo
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO activity(stime,etime,content,managername,title,status,photo,community_id) VALUES (?1,?2,?3,?4,?5,?6,?7,?8) ",nativeQuery = true)
    void saveActivity(String startTime, String endTime, String content, String managerName, String title, int status, String photo, int communityId);

    /**
     * find activity devided by communityId
     * @param communityId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value="select * from activity b where b.community_id=?1",nativeQuery = true)
    List<Activity> findAllActivity(int communityId);

    /**
     * find activity limit 5
     * @param communityId
     * @return List<Activiyy>max five rows</>
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" select * from activity b where b.community_id=?1 order by id desc limit 5",nativeQuery = true)
    List<Activity> findNew(int communityId);

}
