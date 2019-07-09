package notice.notice.daoimpl;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import notice.notice.repository.NoticeRepository;
import notice.notice.dao.NoticeDao;
import notice.notice.repository.NoticeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
         List<Notice> notices = new ArrayList<>();
         for (int i=0;i<noticeUsers.size();i++){
             NoticeUser nu1=noticeUsers.get(i);
             Notice n1=noticeRepository.findByNoticeId(nu1.getNoticeId());
             notices.add(n1);
         }
         return notices;
    }
    @Override
    public String deleteByNotcieId(int noticeId){


            NoticeUser n2 = noticeUserRepository.findByNoticeId(noticeId);

            noticeUserRepository.delete(n2);
            return "删除这一条物业信息成功";


    }
    @Override
    public String deleteAllByUsername(String username){

        List<NoticeUser> noticeUsers=noticeUserRepository.findAllByUsername(username);
        int length = noticeUsers.size();
        for (int i=0;i<length;i++){
            NoticeUser n2 = noticeUserRepository.findByNoticeId(noticeUsers.get(i).getNoticeId());
            noticeUserRepository.delete(n2);

        }
        return "删除我的所有物业信息成功";
    }
}
