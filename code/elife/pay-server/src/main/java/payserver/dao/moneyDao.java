package payserver.dao;

import payserver.entity.ParkPlan;
import payserver.entity.Room;
import payserver.entity.RoomSpace;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wyx
 * @Date 2019.09.03
 */
public interface moneyDao {
    /**
     * save room by communityId
     * @param type
     * @param room
     * @param communityId
     */
    void saveRoom(String type,String room, int communityId);

    /**
     * @param room
     */
    void saveRoomRepo(Room room);

    /**
     * calculate manager money
     * @param money
     * @param communityId
     */
    void calManMoney(BigDecimal money,int communityId);
    /**
     * get room list
     * @param communityId
     * @return
     */
    List<Room> getRoom(int communityId);

    /**
     * get room to insert username
     * @param type
     * @param room
     * @param communityId
     * @return room
     */
    Room getRoomByRoomNumber(String type,String room,int communityId);
    /**
     * get room space list
     * @param communityId
     * @return
     */
    List<RoomSpace> getRoomSpace(int communityId);

    /**
     * save room space list
     * @param type
     * @param space
     * @param communityId
     */
    void saveRoomSpace(String type,int maxNum,int maxFloor,int maxRoom,int space, int communityId);

    /**
     * get park plan list
     * @param communityId
     * @return
     */
    List<ParkPlan> getParkPlan(int communityId);

    /**
     * save park plan
     * @param type
     * @param monthPay
     * @param communityId
     */
    void saveParkPlan(String type, BigDecimal monthPay, int communityId);

    /**
     *
     * @param type
     * @param communityId
     * @return
     */
    ParkPlan getParkPlanOne(String type,int communityId);

    void changeParkPlan(String type,BigDecimal money,int communityId);

    void changeParking(String username,String parking,String type, int communityId);

    void calParking(int communityId);
}

