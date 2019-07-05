package notice.notice.daoimpl;

import notice.notice.repository.NoticeRepository;
import notice.notice.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Repository

public class NoticeDaoimpl implements NoticeDao {
    @Autowired
    private NoticeRepository noticeRepository;
}
