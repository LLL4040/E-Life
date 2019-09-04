package estateforum.estateforum.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/04
 * @author ztHou
 * @date 2019/08/25
 */
@Document(collection = "post")
public class Post {


    @Id
    private String id;
    private String posterName;
    private String title;
    private String tag;
    private String postContent;
    private String postTime;
    private int communityId;
    private String path;


    public Post(String title,String tag, String postContent,String postTime,String posterName,int communityId,String path){
        this.title=title;
        this.postContent=postContent;
        this.postTime=postTime;
        this.posterName=posterName;
        this.tag = tag;
        this.communityId=communityId;
        this.path =path;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String pathList) {
        this.path = pathList;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
