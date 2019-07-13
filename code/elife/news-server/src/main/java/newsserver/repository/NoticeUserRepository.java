package newsserver.repository;


import newsserver.entity.NoticeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public interface NoticeUserRepository extends JpaRepository<NoticeUser,Long> {
    /**
     * 该函数返回特定的NoticeUser
     * @return 特定的NoticeUser
     * @param  username 用户名
     * @param noticeId 物业通知的Id*/
    @Query(value = "select * from noticeUser where username=?1 and id=?2", nativeQuery = true)
    NoticeUser findByUsernameAndNoticeId(String username,int noticeId);
    /**
     * 该函数返回特定的NoticeUser
     * @return 特定的NoticeUser
     * @param noticeId 物业通知的Id*/
    @Query(value = "select * from noticeUser where  id=?1", nativeQuery = true)
    List<NoticeUser> findByNoticeId(int noticeId);
    /**
     * 该函数返回特定的NoticeUser
     * @return 特定的NoticeUser
     * @param  username 用户名
     */
    @Query(value = "select * from noticeUser where username=?1", nativeQuery = true)
    List<NoticeUser> findAllByUsername(String username);
    /**
     * 该函数删除特定id的NoticeUser
     * @param  noticeId 物业通知的id
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from noticeUser  where id=?1", nativeQuery = true)
    void deleteByNoticeId(int noticeId);

    /**
     * 该函数判断是否存在NoticeUser，不为0则存在
     * @param  noticeId 物业通知的id
     * @return  返回0代表不存在*/
    @Query(value = "select count(*) from (select * from noticeUser where id=?1 limit 1)as A", nativeQuery = true)
    int existsById(int noticeId);
    /**
     * 该函数返回某个小区所有用户的列表
     * @param  communityId 物业通知的id
     * @return  返回0代表不存在*/
    @Query(value = "select username from user where community_id=?1", nativeQuery = true)
    List<String> findUsernameByCommunityId(int communityId);

}
