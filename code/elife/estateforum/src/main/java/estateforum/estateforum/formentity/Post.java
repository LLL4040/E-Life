package estateforum.estateforum.formentity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/
public class Post {
    @Id
    private int pid;
    private String title;
    private String postContent;
    private String postTime;
    private String posterName;
    public Post(){
        super();
    }
    public Post(String title,String postContent,String postTime,String posterName){
        this.title=title;
        this.postContent=postContent;
        this.postTime=postTime;
        this.posterName=posterName;

    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPosterName(String posterId) {
        this.posterName = posterId;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterName() {
        return posterName;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getPostTime() {
        return postTime;
    }
}
