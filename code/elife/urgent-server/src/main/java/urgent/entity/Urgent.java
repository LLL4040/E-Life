package urgent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "urgent")
/**
 * Urgent class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class Urgent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private Date time;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private int status;
    @Column(name = "community_id")
    private int communityId;

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }
    public Date getTime() {

        return time;
    }
    public void setTime(Date time) {

        this.time = time;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {

        this.managerName = managerName;
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

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public Urgent(int id, String managerName, String content, int status, int communityId) {
        this.id = id;
        this.managerName = managerName;
        this.content = content;
        this.status = status;
        this.communityId = communityId;
    }
    public Urgent() {
        super();
    }
}
