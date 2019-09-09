package payserver.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import payserver.dao.moneyDao;
import payserver.entity.ParkPlan;
import payserver.entity.Room;
import payserver.entity.RoomSpace;
import payserver.repository.ParkPlanRepository;
import payserver.repository.RoomRepository;
import payserver.repository.RoomSpaceRepository;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Repository
/**
 * @author wyx
 * @Date 2019.09.03
 */
public class MoneyDaoImpl implements moneyDao {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomSpaceRepository roomSpaceRepository;
    @Autowired
    ParkPlanRepository parkPlanRepository;

    @Override
    public void saveRoom(String type, String room, int communityId) {
        roomRepository.saveRoom(type, room, communityId);
    }

    @Override
    public void saveRoomRepo(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> getRoom(int communityId) {
        return roomRepository.getRoom(communityId);
    }

    @Override
    public Room getRoomByRoomNumber(String type, String room, int communityId) {
        return roomRepository.getRoomByRoomNumber(type, room, communityId);
    }

    @Override
    public void calManMoney(BigDecimal money, int communityId) {
        List<RoomSpace> roomSpaces = roomSpaceRepository.getRoomSpace(communityId);
        List<Room> rooms = roomRepository.getRoom(communityId);


        for (int j = 0; j < roomSpaces.size(); j++) {
            RoomSpace roomSpace = roomSpaces.get(j);
            String type = roomSpace.getType();
            int space = roomSpace.getSpace();
            BigDecimal bignum2 = new BigDecimal(space);
            BigDecimal mmoney = money.multiply(bignum2);
            roomRepository.calManager(type, mmoney, communityId);
        }
    }

    @Override
    public List<RoomSpace> getRoomSpace(int communityId) {
        return roomSpaceRepository.getRoomSpace(communityId);
    }

    @Override
    public void saveRoomSpace(String type, int maxNum, int maxFloor, int maxRoom, int space, int communityId) {
        roomSpaceRepository.saveRoomSpace(type, maxNum, maxFloor, maxRoom, space, communityId);
    }

    @Override
    public List<ParkPlan> getParkPlan(int communityId) {
        return parkPlanRepository.getParkPlan(communityId);
    }

    @Override
    public void saveParkPlan(String type, BigDecimal monthPay, int communityId) {
        parkPlanRepository.saveParkPlan(type, monthPay, communityId);
    }

    @Override
    public ParkPlan getParkPlanOne(String type, int communityId) {
        return parkPlanRepository.getParkPlanOne(type, communityId);
    }

    @Override
    public void changeParkPlan(String type, BigDecimal money, int communityId) {
        parkPlanRepository.changeParkPlan(type, money, communityId);
    }

    @Override
    public void changeParking(String username, String parking, String type, int communityId) {
        roomRepository.changeParking(username, parking, type, communityId);
    }

    @Override
    public void calParking(int communityId) {

        List<ParkPlan> parkPlans = parkPlanRepository.getParkPlan(communityId);
        List<Room> rooms = roomRepository.getRoom(communityId);
        ParkPlan AparkPlan = parkPlans.get(0);
        String Atype = AparkPlan.getType();
        BigDecimal AmonthPay = AparkPlan.getMonthPay();
        ParkPlan BparkPlan = parkPlans.get(1);
        String Btype = BparkPlan.getType();
        BigDecimal BmonthPay = BparkPlan.getMonthPay();

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            String park = room.getParking();
            if(park.charAt(1)!=48&&park.charAt(3)!=48) {
                BigDecimal bignumB = new BigDecimal(park.charAt(3)-48);
                BigDecimal Bmoney = BmonthPay.multiply(bignumB);
                BigDecimal bignumA = new BigDecimal(park.charAt(1)-48);
                BigDecimal Amoney = AmonthPay.multiply(bignumA);
                BigDecimal allMoney = Bmoney.add(Amoney);
                roomRepository.calParking(room.getRoom(), allMoney, communityId,room.getType());
            }
        }


    }
}
