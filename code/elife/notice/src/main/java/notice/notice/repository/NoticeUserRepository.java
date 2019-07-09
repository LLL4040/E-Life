package notice.notice.repository;

import notice.notice.entity.NoticeUser;
import org.springframework.data.jpa.repository.JpaRepository;

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
        List<NoticeUser> findAllByUsernameAndNoticeId(String username,int noticeId);
    /**
     * 该函数返回特定的NoticeUser
     * @return 特定的NoticeUser
     * @param  username 用户名
     */

    List<NoticeUser> findAllByUsername(String username);
    /**
     * 该函数删除特定id的NoticeUser
     * @param  noticeId 物业通知的id
     */
    void deleteByNoticeId(int noticeId);
}
