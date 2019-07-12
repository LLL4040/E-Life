package packageserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Package class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
@Entity
@Table(name = "package")
public class Package implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private String time;
    @Column(name = "status")
    private int status;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "username")
    private String username;
    @Column(name = "community_id")
    private int communityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
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

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public Package(int id, String time, String managerName, int status, String username,int communityId){
        this.id = id;
        this.managerName = managerName;
        this.time = time;
        this.username = username;
        this.status = status;
        this.communityId = communityId;

    }
    public Package(){
        super();
    }
}
