package newsserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * News class
 *
 * @Author wyx
 * @Date 2019.07.09
 */
@Entity
@Table(name = "newsused")
public class NewsUsed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private Date time;
    @Column(name = "content")
    private String content;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "title")
    private String title;
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

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
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
    public NewsUsed( int id, String content,String managerName,String title,String photo,int communityId){
        this.id = id;
        this.content = content;
        this.managerName = managerName;
        this.title = title;
        this.photo = photo;
        this.communityId = communityId;

    }
    public NewsUsed(){
        super();
    }
}
