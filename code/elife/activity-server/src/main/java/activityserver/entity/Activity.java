package activityserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activity")
/**
 * Activity class
 *
 * @Author wyx
 * @Date 2019.07.11
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;


    @Column(name = "stime")
    private String startTime;


    @Column(name = "etime")
    private String endTime;

    @Column(name = "content")
    private String content;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private int status;

    @Column(name = "photo")
    private String photo;

    @Column(name = "community_id")

    private int communityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public Activity(int id, String content,String startTime,String endTime,String managerName
            , String title,int status,String photo,int communityId){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.managerName = managerName;
        this.title = title;
        this.status = status;
        this.photo = photo;
        this.communityId = communityId;
    }
    public Activity(){
        super();
    }
}
