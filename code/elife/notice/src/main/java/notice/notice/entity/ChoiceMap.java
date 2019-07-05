package notice.notice.entity;

import java.io.Serializable;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public class ChoiceMap  implements Serializable {
    private int id;
    private String username;
    public ChoiceMap(){

    }
    public ChoiceMap(int id, String username){
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
}
