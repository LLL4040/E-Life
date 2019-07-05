package notice.notice.entity;

import notice.notice.entity.ChoiceMap;

import javax.persistence.*;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
@Entity
@Table(name = "noticeUser")
@IdClass(ChoiceMap.class)
public class NoticeUser {
    @Id
    @Column(name = "id")
    private int noticeId;
    @Id
    @Column(name = "username")
    private String username;
    public NoticeUser(){

    }
    public NoticeUser(int noticeId,String username){
        this.noticeId=noticeId;
        this.username=username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getUsername() {
        return username;
    }

    public int getNoticeId() {
        return noticeId;
    }
}
