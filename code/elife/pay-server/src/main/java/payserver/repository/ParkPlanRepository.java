package payserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import payserver.entity.ParkPlan;

import java.math.BigDecimal;
import java.util.List;


/**
 * ParkPlanRepository interface
 *
 * @author wyx
 * @date 2019.09.02
 */
public interface ParkPlanRepository extends JpaRepository<ParkPlan, Integer> {


    @Query(value=" select * from parkplan where community_Id=?1 ",nativeQuery = true)
    List<ParkPlan> getParkPlan(int communityId);

    /**
     * get one parkplan by type and communityId
     * @param type
     * @param communityId
     * @return
     */
    @Query(value=" select * from parkplan where type=?1 and community_Id=?2 ",nativeQuery = true)
    ParkPlan getParkPlanOne(String type,int communityId);

    /**
     * save roomSpace without id
     *
     * @param type
     * @param monthPay
     * @param communityId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" INSERT INTO parkplan(`type`,`monthpay`,`community_id`) VALUES (?1,?2,?3) ",nativeQuery = true)
    void saveParkPlan(String type, BigDecimal monthPay, int communityId);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value=" update parkplan set monthpay = ?2 where community_Id =?3 and type=?1",nativeQuery = true)
    void changeParkPlan(String type, BigDecimal pmoney, int communityId);


}

