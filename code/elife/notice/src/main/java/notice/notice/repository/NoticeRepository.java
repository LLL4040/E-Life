package notice.notice.repository;

import notice.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    /**
     * 该函数根据noticeId返回物业通知
     * @return  返回得到的一条物业通知
     * @param  noticeId 物业通知的id*/
    Notice findByNoticeId(int noticeId);
    /**
     * 该函数根据noticeId删除一条物业通知
     * @param noticeId 物业通知的id
     */
    void deleteByNoticeId(int noticeId);
}
