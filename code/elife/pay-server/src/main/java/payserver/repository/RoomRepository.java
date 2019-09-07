package payserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import payserver.entity.Room;

import java.math.BigDecimal;
import java.util.List;


/**
 * RoomRepository interface
 *
 * @author wyx
 * @date 2019.07.16
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {


    @Query(value=" select * from room where community_Id=?1 ",nativeQuery = true)
    List<Room> getRoom(int communityId);

    @Query(value=" select * from room where type=?1 and room=?2 and community_Id=?3 ",nativeQuery = true)
    Room getRoomByRoomNumber(String type,String room,int communityId);

    /**
     * save room without id
     *
     * @param type
     * @param room
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO room(type,room,community_id) VALUES (?1,?2,?3) ",nativeQuery = true)
    void saveRoom(String type,String room, int communityId);

    /**
     * calculate manager money
     * @param type
     * @param mmoney
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" update room set mmoney = ?2 where community_Id =?3 and type=?1",nativeQuery = true)
    void calManager(String type, BigDecimal mmoney, int communityId);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" update room set parking = ?2 where community_Id =?4 and type=?3 and username=?1",nativeQuery = true)
    void changeParking(String username,String parking,String type,  int communityId);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" update room set pmoney = ?2 where community_Id =?3 and room=?1 and type=?4",nativeQuery = true)
    void calParking(String room, BigDecimal pmoney, int communityId,String type);
}

