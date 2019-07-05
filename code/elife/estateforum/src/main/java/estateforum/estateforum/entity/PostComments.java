package estateforum.estateforum.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04*/

public class PostComments {

    @Id
    private String commenterName;
    private int pid;


    private String commentsTime;
    private String postComment;

    /**楼数*/
    private int location;

    public PostComments() {super();}

    public PostComments(int pid,String commenterName,String commentsTime,String postComment,int location){
        this.pid=pid;
        this.commenterName=commenterName;
        this.commentsTime=commentsTime;
        this.postComment=postComment;
        this.location=location;

    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }


    public void setpid(int pid) {
        this.pid = pid;
    }

    public void setCommenterName(String commenter) {
        this.commenterName = commenter;
    }

    public void setCommentsTime(String commentsTime) {
        this.commentsTime = commentsTime;
    }

    public void setpostComment(String postComment) {
        this.postComment = postComment;
    }



    public int getPid() {
        return pid;
    }

    public  String getCommenterName() {
        return commenterName;
    }

    public String getCommentsTime() {
        return commentsTime;
    }

    public String getpostComment() {
        return postComment;
    }
}
