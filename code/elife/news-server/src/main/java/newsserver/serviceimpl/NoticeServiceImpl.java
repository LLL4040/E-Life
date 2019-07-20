package newsserver.serviceimpl;


import newsserver.dao.NoticeDao;
import newsserver.entity.Notice;
import newsserver.entity.NoticeUser;
import newsserver.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Service

public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public int save(Notice notice){
        return noticeDao.save(notice);
    }

    @Override
    public int saveNoticeUser(NoticeUser noticeUser) {
        return noticeDao.saveNoticeUser(noticeUser);
    }

    @Override
    public List<Notice> managerFindNotice(String managerName,int pageNumber,int pageSize) {
        return noticeDao.managerFindNotice(managerName,pageNumber,pageSize);
    }

    @Override
    public List<Notice> findByUsername(String username){
        return noticeDao.findByUsername(username);
    }
    @Override
    public String deleteAllByUsername(String username){
       return noticeDao.deleteAllByUsername(username);
    }
    @Override
    public String deleteByNotcieId(int noticeId){
        return noticeDao.deleteByNotcieId(noticeId);
    }
    @Override
    public String deleteByUsernameAndNoticeId(String username,int noticeId){
        return noticeDao.deleteByUsernameAndNoticeId(username,noticeId);
    }

    @Override
    public List<String> findUsernameByCommunityId(int communityId) {
        return noticeDao.findUsernameByCommunityId(communityId);
    }

    @Override
    public List<NoticeUser> findbyNoticeId(int noticeId) {
        return noticeDao.findbyNoticeId(noticeId);
    }

    @Override
    public int countByManagerName(String managername) {
        return noticeDao.countByManagerName(managername);
    }
}
