package user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ztHou
 */
@Entity
@Table(name = "community")
public class Community implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "communityname")
    private String communityName;

    @Column(name = "communityinfo")
    private String communityInfo;

    @Column(name = "manager")
    private String manager;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "account")
    private String account;

    public Community(){}

    public Community(String communityName, String communityInfo, String manager, String password,
                     String phone, String email, String address, String account){
        this.address = address;
        this.communityName = communityName;
        this.communityInfo = communityInfo;
        this.manager = manager;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getCommunityInfo() {
        return communityInfo;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setCommunityInfo(String communityInfo) {
        this.communityInfo = communityInfo;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}

