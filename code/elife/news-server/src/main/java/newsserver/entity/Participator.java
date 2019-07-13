package newsserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "participator")
/**
 *Participator class
 *
 * @Author wyx
 * @Date 2019.07.11
 */
public class Participator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "aid")
    private int activityId;

    @Column(name = "username")
    private String username;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getActivityId() {
        return activityId;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Participator(int id,int activityId,String username,String content,int status){
        this.id = id;
        this.activityId = activityId;
        this.username = username;
        this.content = content;
        this.status = status;
    }
    public Participator(){
        super();
    }
}
