package newsserver.service;

import newsserver.entity.Notice;
import newsserver.entity.NoticeUser;

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
     * 该函数增加一条noticeUser
     * @return 返回生成的物业通知的id
     * @param  noticeUser 要存noticeUser*/
    int saveNoticeUser(NoticeUser noticeUser);
    /**
     * 该函数查询特定管理员发布的的所有物业通知
     * @return 返回特定管理员发布的的所有物业通知
     * @param managerName 管理员名
     * @param pageNumber 当前页数
     * @param pageSize 每页数据量*/
    List<Notice> managerFindNotice(String managerName,int pageNumber,int pageSize);
    /**
     * 该函数查询特定用户的物业通知
     * @return 返回特定用户的物业通知的id
     * @param  username 用户名*/
    List<Notice> findByUsername(String username);
    /**
     * 该函数删除特定用户的所有物业通知
     * @param username 用户名
     * @return  返回删除成功的消息*/
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
    /**
     * 这个函数根据communityId返回一个小区内所有的居民名字
     * @param communityId 小区号
     * @return 返回居民名字的列表*/
    List<String> findUsernameByCommunityId(int communityId);
    /**
     * 根据noticeid返回noticeUser的列表
     * @param noticeId notice 的id
     * @return 返回noticeUser的列表*/
    List<NoticeUser> findbyNoticeId(int noticeId);
    /**
     * 该函数返回某管理员发的所有通知数
     * @return 该函数返回某管理员发的所有通知数
     * @param managername*/
    int countByManagerName(String managername);
}
