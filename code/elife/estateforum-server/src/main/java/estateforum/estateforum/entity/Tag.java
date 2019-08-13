package estateforum.estateforum.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ztHou
 * @date 2019/08/13
 */
@Entity
@Document(collection = "tag")
public class Tag {
    @Id
    private String id;

    private String content;

    private Integer num;

    public Tag(){
    }

    public Tag(String content, Integer num){
        this.content = content;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
