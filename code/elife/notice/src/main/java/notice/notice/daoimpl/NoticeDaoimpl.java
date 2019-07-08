package notice.notice.daoimpl;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import notice.notice.repository.NoticeRepository;
import notice.notice.dao.NoticeDao;
import notice.notice.repository.NoticeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Repository

public class NoticeDaoimpl implements NoticeDao {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeUserRepository noticeUserRepository;
    @Override
    public int save(Notice notice){
       return noticeRepository.save(notice).getNoticeId();
    }
    @Override
    public List<Notice> findByUsername(String username){
         List<NoticeUser> noticeUsers=noticeUserRepository.
    }
}
