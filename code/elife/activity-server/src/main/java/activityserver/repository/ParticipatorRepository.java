package activityserver.repository;

import activityserver.entity.Participator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wyx
 * @Date 2019.07.11
 */
public interface ParticipatorRepository extends JpaRepository<Participator, Integer> {

    /**
     * save participator without id
     * @param aid
     * @param content
     * @param status
     * @param username
     * @return null
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO participator(aid,content,status,username) VALUES (?1,?2,?3,?4) ",nativeQuery = true)
    void savePaticipator(int aid,String content,int status,String username);

    /**
     * find all participator by communityId to check status
     * @param activityId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value="select * from participator b where b.aid=?1 ",nativeQuery = true)
    List<Participator> findAllParticipator(int activityId);

    /**
     * delete all the nulluse participator
     * @param activityId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value="delete from participator  where aid=?1 and status=0  ",nativeQuery = true)
    void deleteAll(int activityId);

    /**
     * find user apply or not
     * @param activityId
     * @param username
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Query(value="select * from participator  where aid=?1 and username=?2",nativeQuery = true)
    List<Participator> existApply(int activityId,String username);
}
