package notice.notice;

import notice.notice.entity.Notice;
import notice.notice.entity.NoticeUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityTest {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String noticeTime = df.format(new Date());
    Notice notice = new Notice(noticeTime,"您的物业已欠费，请及时缴费","二郎神",1);
    NoticeUser noticeUser =new NoticeUser(1,"哪吒");
    @Test
    public void noticeTimeTest(){
        Assert.assertEquals("get noticeTime fail",noticeTime,notice.getNoticeTime());
        String noticeTime2 = df.format(new Date());
        notice.setNoticeTime(noticeTime2);
        Assert.assertEquals("get noticeTime fail",noticeTime2,notice.getNoticeTime());
        notice.setNoticeTime(noticeTime);
    }
    @Test
    public void noticeContentTest(){
        Assert.assertEquals("get noticeContent fail","您的物业已欠费，请及时缴费",notice.getNoticeContent());
        notice.setNoticeContent("请您快点缴费");
        Assert.assertEquals("get noticeContent fail","请您快点缴费",notice.getNoticeContent());
        notice.setNoticeContent("您的物业已欠费，请及时缴费");
    }
    @Test
    public void managerNameTest(){
        Assert.assertEquals("get managername fail","二郎神",notice.getManagerName());
        notice.setManagerName("哮天犬");
        Assert.assertEquals("get managername fail","哮天犬",notice.getManagerName());
        notice.setManagerName("二郎神");
    }
    @Test
    public void comunityIdTest(){
        Assert.assertEquals("get comunityId fail",1,notice.getCommunityId());
        notice.setCommunityId(2);
        Assert.assertEquals("get comunityId fail",2,notice.getCommunityId());
        notice.setCommunityId(1);

    }
    @Test
    public void NoticeUserTest(){
        Assert.assertEquals("get noticeUserId fail",1,noticeUser.getNoticeId());
        noticeUser.setNoticeId(2);
        Assert.assertEquals("get noticeUserId fail",2,noticeUser.getNoticeId());
        noticeUser.setNoticeId(1);
        Assert.assertEquals("get noticeUserName fail","哪吒",noticeUser.getUsername());
        noticeUser.setUsername("三太子");
        Assert.assertEquals("get noticeUserName fail","三太子",noticeUser.getUsername());
        noticeUser.setUsername("哪吒");
    }
}
