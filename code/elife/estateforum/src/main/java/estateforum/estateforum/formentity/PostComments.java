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

public class PostComments {
    @Id
    private int cid;
    private int pid;
    private int commenterId;
    private Timestamp commentsTime;
    private String postComment;

    /**楼数*/
    private int location;

    private PostComments() {super();}


    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setpid(int pid) {
        this.pid = pid;
    }

    public void setCommenter(int commenter) {
        this.commenterId = commenter;
    }

    public void setCommentsTime(Timestamp commentsTime) {
        this.commentsTime = commentsTime;
    }

    public void setpostComment(String postComment) {
        this.postComment = postComment;
    }

    public int getCid() {
        return cid;
    }

    public int getPid() {
        return pid;
    }

    public int getCommenter() {
        return commenterId;
    }

    public Timestamp getCommentsTime() {
        return commentsTime;
    }

    public String getpostComment() {
        return postComment;
    }
}
