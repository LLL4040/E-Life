package newsserver.daoimpl;

;
import newsserver.dao.NoticeDao;
import newsserver.entity.Notice;
import newsserver.entity.NoticeUser;
import newsserver.repository.NoticeRepository;
import newsserver.repository.NoticeUserRepository;
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

public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeUserRepository noticeUserRepository;
    @Override
    public int save(Notice notice){
       return noticeRepository.save(notice).getNoticeId();
    }

    @Override
    public int saveNoticeUser(NoticeUser noticeUser) {
        return noticeUserRepository.save(noticeUser).getNoticeId();
    }

    @Override
    public List<Notice> managerFindNotice(String managerName,int pageNumber,int pageSize) {
        return noticeRepository.findByManagerName(managerName,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Notice> findByUsername(String username){
         List<NoticeUser> noticeUsers=noticeUserRepository.findAllByUsernameAndStatus(username, 1);
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

           if(noticeRepository.existsByNoticeId(noticeId)!=0){
               Notice notice=noticeRepository.findByNoticeId(noticeId);
               noticeRepository.delete(notice);
               if(noticeUserRepository.existsById(noticeId)!=0){
                   List<NoticeUser> n2 = noticeUserRepository.findByNoticeId(noticeId);
                   int length=n2.size();
                   for(int i=0;i<length;i++){
                       noticeUserRepository.delete(n2.get(i));
                   }
               }

               return "删除这一条物业信息成功";
           }
            return "数据库中不存在这条数据";


    }
    @Override
    public String deleteAllByUsername(String username){

        List<NoticeUser> noticeUsers=noticeUserRepository.findAllByUsername(username);
        int length = noticeUsers.size();
        for (int i=0;i<length;i++){
            NoticeUser nu=noticeUsers.get(i);
            nu.setStatus(0);
            noticeUserRepository.save(nu);
        }
        return "删除我的所有物业信息成功";
    }
    @Override
    public String deleteByUsernameAndNoticeId(String username,int noticeId){

        NoticeUser nu=noticeUserRepository.findByUsernameAndNoticeId(username,noticeId);
        nu.setStatus(0);
        noticeUserRepository.save(nu);
        return "删除我的该条物业信息成功";
    }

    @Override
    public List<String> findUsernameByCommunityId(int communityId) {
        return noticeUserRepository.findUsernameByCommunityId(communityId);
    }

    @Override
    public List<NoticeUser> findbyNoticeId(int noticeId) {
        return noticeUserRepository.findByNoticeId(noticeId);
    }

    @Override
    public int countByManagerName(String managername) {
        return noticeRepository.countByManagerName(managername);
    }
}
