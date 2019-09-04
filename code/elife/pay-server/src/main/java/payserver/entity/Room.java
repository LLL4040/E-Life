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
@Table(name = "room")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "room")
    private String room;
    @Column(name = "parking")
    private String parking;
    @Column(name = "mmoney")
    private BigDecimal managerMoney;
    @Column(name = "pmoney")
    private BigDecimal parkMoney;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public void setManagerMoney(BigDecimal managerMoney) {
        this.managerMoney = managerMoney;
    }

    public BigDecimal getManagerMoney() {
        return managerMoney;
    }

    public void setParkMoney(BigDecimal parkMoney) {
        this.parkMoney = parkMoney;
    }

    public BigDecimal getParkMoney() {
        return parkMoney;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
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

    public Room(String type,String room, int communityId){
        this.type = type;
        this.room = room;
        this.communityId = communityId;

    }
    public Room(){
        super();
    }
}
