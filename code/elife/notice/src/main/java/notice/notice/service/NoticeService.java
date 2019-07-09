package notice.notice.service;

import notice.notice.dao.NoticeDao;
import notice.notice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Service
public interface NoticeService {
    /**
     * 该函数增加一条物业通知
     * @return 返回生成的物业通知的id
     * @param  notice 要存的物业通知*/
    int save(Notice notice);
    /**
     * 该函数查询特定用户的物业通知
     * @return 返回特定用户的物业通知的id
     * @param  username 用户名*/
    List<Notice> findByUsername(String username);
    /**
     * 该函数删除特定用户的所有物业通知
     * @param username 用户名*/
    void deleteAllByUsername(String username);
    /**
     * 该函数删除特定一条物业通知
     * @param noticeId 物业通知id*/
    String deleteByNotcieId(int noticeId);

}
