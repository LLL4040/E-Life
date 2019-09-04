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
@Table(name = "roomspace")
public class RoomSpace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "maxnum")
    private int maxNum;
    @Column(name = "maxfloor")
    private int maxFloor;
    @Column(name = "maxroom")
    private int maxRoom;
    @Column(name = "space")
    private int space;
    @Column(name = "community_id")
    private int communityId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxRoom(int maxRoom) {
        this.maxRoom = maxRoom;
    }

    public int getMaxRoom() {
        return maxRoom;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getSpace() {
        return space;
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

    public RoomSpace(String type,int maxNum,int maxFloor,int maxRoom, int space,int communityId){
        this.maxNum=maxNum;
        this.maxFloor=maxFloor;
        this.maxRoom=maxRoom;
        this.type = type;
        this.space = space;
        this.communityId = communityId;

    }
    public RoomSpace(){
        super();
    }
}
