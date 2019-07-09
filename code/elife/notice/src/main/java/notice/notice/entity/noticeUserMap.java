package notice.notice.entity;

import java.io.Serializable;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/

public class noticeUserMap implements Serializable {

    private int id;
    private String username;
    public noticeUserMap(){

    }
    public noticeUserMap(int id, String username){
        this.id=id;
        this.username=username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof noticeUserMap){
            noticeUserMap key = (noticeUserMap)o ;
            if(this.id == key.getId() && this.username.equals(key.getUsername())){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

}
