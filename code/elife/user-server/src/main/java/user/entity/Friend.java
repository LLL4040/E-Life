package user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ztHou
 */
@Entity
@Table(name = "friend")
public class Friend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "friend")
    private String friend;

    @Column(name = "status")
    private Integer status;

    @Column(name = "content")
    private String content;

    public Friend(){}

    public Friend(String username, String friend, Integer status){
        this.username = username;
        this.friend = friend;
        this.status = status;
    }

    public Friend(String username, String friend, Integer status, String content){
        this.username = username;
        this.friend = friend;
        this.status = status;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }


}

