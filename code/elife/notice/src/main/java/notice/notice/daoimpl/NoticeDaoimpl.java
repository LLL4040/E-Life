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
         List<NoticeUser> noticeUsers=noticeUserRepository.findAllByUsername(username);
         List<Notice> notices = null;
         for (int i=0;i<noticeUsers.size();i++){
             NoticeUser nu1=noticeUsers.get(i);
             Notice n1=noticeRepository.findByNoticeId(nu1.getNoticeId());
             notices.add(n1);
         }
         return notices;
    }
    @Override
    public void deleteAllByNotcieId(int noticeId){
        noticeRepository.deleteByNoticeId(noticeId);
        noticeUserRepository.deleteByNoticeId(noticeId);
    }
    @Override
    public String deleteAllByUsername(String username){

        List<NoticeUser> noticeUsers=noticeUserRepository.findAllByUsername(username);
        int length = noticeUsers.size();
        for (int i=0;i<length;i++){
            noticeRepository.deleteByNoticeId(noticeUsers.get(i).getNoticeId());
            noticeUserRepository.deleteByNoticeId(noticeUsers.get(i).getNoticeId());
        }
        return "删除成功";
    }
}
