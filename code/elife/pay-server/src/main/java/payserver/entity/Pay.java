package payserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Pay class
 *
 * @Author wyx
 * @Date 2019.07.12
 */
@Entity
@Table(name = "pay")
public class Pay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private String time;
    @Column(name = "status")
    private int status;
    @Column(name = "bill")
    private BigDecimal bill;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "username")
    private String username;
    @Column(name = "community_id")
    private int communityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public Pay(int id, String time, BigDecimal bill,String managerName, int status, String username, int communityId){
        this.id = id;
        this.managerName = managerName;
        this.time = time;
        this.bill = bill;
        this.username = username;
        this.status = status;
        this.communityId = communityId;

    }
    public Pay(){
        super();
    }
}
