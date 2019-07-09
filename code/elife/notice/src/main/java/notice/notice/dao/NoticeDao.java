package notice.notice.dao;

import notice.notice.entity.Notice;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public interface NoticeDao {
    /**
     * 该函数增加一条物业通知
     * @return 返回生成的物业通知的id
     * @param  notice 要存的物业通知*/
    int save(Notice notice);
    /**
     * 该函数查询特定用户的所有物业通知
     * @return 返回特定用户的物业通知的id
     * @param  username 用户名*/
    List<Notice> findByUsername(String username);
    /**
     * 该函数删除特定用户的所有物业通知
     * @param username 用户名
     * @return 返回删除成功的信息*/
    String deleteAllByUsername(String username);
    /**
     * 该函数删除特定一条物业通知,包括notice表中信息和noticeUser表中信息
     * @param noticeId 物业通知id
     * @return 返回删除成功的信息*/
    String deleteByNotcieId(int noticeId);
    /**
     * 该函数删除特定一条物业通知,包括notice表中信息和noticeUser表中信息
     * @param noticeId 物业通知id
     * @param username 用户名
     * @return 返回删除成功的信息*/
    String deleteByUsernameAndNoticeId(String username,int noticeId);
}
