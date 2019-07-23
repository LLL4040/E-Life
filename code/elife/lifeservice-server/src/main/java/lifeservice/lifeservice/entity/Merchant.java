package lifeservice.lifeservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Entity
public class Merchant {
    @Id
    long id;
    private String username;
    private String name;
    private String phone;
    private String address;
    private String detail;
    private String type;
    public Merchant(){

    }
    public Merchant(String username,String name,String phone,String address,String detail,String type){
        this.username=username;
        this.name=name;
        this.phone=phone;
        this.address=address;
        this.detail=detail;
        this.type=type;
    }
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setType(String type) {
        this.type = type;
    }
}
