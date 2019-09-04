package payserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Room class
 *
 * @Author wyx
 * @Date 2019.09.02
 */
@Entity
@Table(name = "parkplan")
public class ParkPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "monthpay")
    private BigDecimal monthPay;
    @Column(name = "community_id")
    private int communityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMonthPay(BigDecimal monthPay) {
        this.monthPay = monthPay;
    }

    public BigDecimal getMonthPay() {
        return monthPay;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public ParkPlan(String type, BigDecimal monthPay,int communityId){
        this.type = type;
        this.monthPay = monthPay;
        this.communityId = communityId;

    }
    public ParkPlan(){
        super();
    }
}
