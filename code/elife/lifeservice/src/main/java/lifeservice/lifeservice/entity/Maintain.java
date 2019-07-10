package lifeservice.lifeservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Entity
@Table(name = "maintain")
public class Maintain {
    @Id
    private  long id;
    private String time;
    private int status;
    private String content;
    private String maintainname;
    private String phone;
    private String managername;
    private String username;

    public Maintain(){

    }
    public Maintain(String time,String content,String username){
        this.time=time;
        this.status=0;
        this.content=content;
        this.username=username;
    }

    public long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getMaintainname() {
        return maintainname;
    }

    public String getPhone() {
        return phone;
    }

    public String getManagername() {
        return managername;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMaintainname(String maintainname) {
        this.maintainname = maintainname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
