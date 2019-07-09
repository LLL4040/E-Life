package notice.notice;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import notice.notice.repository.NoticeUserRepository;
import notice.notice.service.NoticeService;
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

public class ServiceTest {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeUserRepository noticeUserRepository;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String noticeTime = df.format(new Date());
    Notice notice = new Notice(noticeTime,"您的物业已欠费，请及时缴费","二郎神",1);


    @Test
    public void findByUsernameTest() throws Exception{
        int n1=noticeService.save(notice);

        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");

        noticeUserRepository.save(noticeUser);
        List<Notice> noticeList =noticeService.findByUsername("哮天犬");
        Assert.assertNotNull(noticeList);
        Assert.assertEquals("get notice fail", notice.getNoticeId(), noticeList.get(noticeList.size()-1).getNoticeId());
        Assert.assertEquals("get notice fail", notice.getNoticeContent(), noticeList.get(noticeList.size()-1).getNoticeContent());
        noticeService.deleteByNotcieId(n1);
    }
    @Test
    public void deleteByUsernameTest(){
        int n1=noticeService.save(notice);
        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");
        noticeUserRepository.save(noticeUser);
        String deleteResult=noticeService.deleteAllByUsername("哮天犬");
        Assert.assertEquals("delete fail","删除我的所有物业信息成功",deleteResult);
    }
    @Test
    public void deleteByNotcieIdTest()throws Exception{
        int n1=noticeService.save(notice);
        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");
        noticeUserRepository.save(noticeUser);
        String deleteResult=noticeService.deleteByNotcieId(n1);
        String deleteResult2=noticeService.deleteByNotcieId(n1);
        Assert.assertEquals("delete notice by id fail","删除这一条物业信息成功",deleteResult);
        Assert.assertEquals("delete notice by id fail","数据库中不存在这条数据",deleteResult2);
    }
    @Test
    public void deleteByUsernameAndNoticeIdTest3()throws Exception{
        int n1=noticeService.save(notice);
        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");
        noticeUserRepository.save(noticeUser);
        String deleteResult=noticeService.deleteByUsernameAndNoticeId("哮天犬",n1);
        Assert.assertEquals("delete my One notice by username and id fail","删除我的该条物业信息成功",deleteResult);

    }
}
