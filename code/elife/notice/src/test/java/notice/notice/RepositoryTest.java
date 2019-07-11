package notice.notice;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import notice.notice.repository.NoticeRepository;
import notice.notice.repository.NoticeUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeUserRepository noticeUserRepository;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String noticeTime = df.format(new Date());
    Notice notice = new Notice(noticeTime,"您的物业已欠费，请及时缴费","二郎神",1);
    @Test
    public void noticerepoTest(){
        Notice noticeReslut = noticeRepository.save(notice);
        int n1=noticeReslut.getNoticeId();
        NoticeUser noticeUser=new NoticeUser(n1,"哪吒");
        noticeUserRepository.save(noticeUser);

        Assert.assertNotNull(noticeReslut);
        Assert.assertEquals("get notice fail", notice.getNoticeTime(), noticeReslut.getNoticeTime());
        Assert.assertEquals("get notice fail", notice.getNoticeContent(), noticeReslut.getNoticeContent());
        Assert.assertEquals("get notice fail", notice.getManagerName(), noticeReslut.getManagerName());
        Assert.assertEquals("get notice fail", notice.getCommunityId(), noticeReslut.getCommunityId());

        int exist=noticeRepository.existsByNoticeId(n1);
        Assert.assertEquals("get notice fail", 1, exist);
        Notice notice1=noticeRepository.findByNoticeId(n1);
        noticeRepository.delete(notice1);
        exist=noticeRepository.existsByNoticeId(n1);
        Assert.assertEquals("get notice fail", 0, exist);

    }
    @Test
    public void noticeUserrepoTest(){
        Notice noticeReslut = noticeRepository.save(notice);
        int n1=noticeReslut.getNoticeId();
        NoticeUser noticeUser=new NoticeUser(n1,"哪吒");
        noticeUserRepository.save(noticeUser);

        int exist =noticeUserRepository.existsById(n1);
        Assert.assertEquals("exist notice fail", 1, exist);
        NoticeUser noticeUser1=noticeUserRepository.findByUsernameAndNoticeId("哪吒",n1);
        Assert.assertNotNull(noticeUser1);
        List<NoticeUser> noticeUserList=noticeUserRepository.findAllByUsername("哪吒");
        Assert.assertNotNull(noticeUserList);


        noticeUserRepository.delete(noticeUser1);


    }
}
