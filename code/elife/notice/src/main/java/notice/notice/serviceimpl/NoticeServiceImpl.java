package notice.notice.serviceimpl;

import notice.notice.dao.NoticeDao;
import notice.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Service

public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
}
