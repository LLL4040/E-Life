package payserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Pay class
 *
 * @Author wyx
 * @Date 2019.07.16
 */
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "pid")
    private int payId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "paid_time")
    private Date payTime;
    @Column(name = "status")
    private int status;
    @Column(name = "bill")
    private BigDecimal bill;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayTime() {
        return payTime;
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


    public Orders(String id, String usernmae, int pid,Date createTime ,BigDecimal bill, int status){
        this.id = id;
        this.username = usernmae;
        this.payId = pid;
        this.bill = bill;
        this.createTime = createTime;
        this.status = status;

    }
    public Orders(){
        super();
    }
}
