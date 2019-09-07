package payserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import payserver.entity.RoomSpace;

import java.util.List;


/**
 * RoomSpaceRepository interface
 *
 * @author wyx
 * @date 2019.07.16
 */
public interface RoomSpaceRepository extends JpaRepository<RoomSpace, Integer> {

    /**
     * get room space list
     * @param communityId
     * @return
     */
    @Query(value=" select * from roomspace where community_Id=?1 ",nativeQuery = true)
    List<RoomSpace> getRoomSpace(int communityId);

    /**
     * save roomSpace without id
     *
     * @param type
     * @param space
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO roomspace(type,maxnum,maxfloor,maxroom,space,community_id) VALUES (?1,?2,?3,?4,?5,?6) ",nativeQuery = true)
    void saveRoomSpace(String type,int maxNum,int maxFloor,int maxRoom,int space, int communityId);
}

