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
    public void NoticeServiceTest() throws Exception{
        int n1=noticeService.save(notice);

        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");

        noticeUserRepository.save(noticeUser);

        List<Notice> noticeList =noticeService.findByUsername("哮天犬");
        Assert.assertNotNull(noticeList);
        Assert.assertEquals("get notice fail", notice.getNoticeId(), noticeList.get(noticeList.size()-1).getNoticeId());
        Assert.assertEquals("get notice fail", notice.getNoticeContent(), noticeList.get(noticeList.size()-1).getNoticeContent());

    }
    @Test
    public void NoticeServiceTest2()throws Exception{
        int n1=noticeService.save(notice);
        NoticeUser noticeUser=new NoticeUser(n1,"哮天犬");
        noticeUserRepository.save(noticeUser);
        String deleteResult=noticeService.deleteByNotcieId(n1);
        String deleteResult2=noticeService.deleteByNotcieId(n1);
        Assert.assertEquals("delete nitice by id fail","删除这一条物业信息成功",deleteResult);
        Assert.assertEquals("delete nitice by id fail","数据库中不存在这条数据",deleteResult2);
    }
}
