package group.repository;

import group.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface DemandRepository extends JpaRepository<Demand, String> {
    /**
     * find demand by id
     * @param id id
     * @return demand
     */
    Demand findById(Long id);

    /**
     * find all demand where end time after current time
     * @param current current time
     * @param communityId communityId
     * @return demand meets the sequence
     */
    List<Demand> findAllByEndTimeAfterAndCommunityId(String current, Long communityId);

    /**
     * delete all demand where end time before setTime
     * @param setTime set time
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByEndTimeBefore(String setTime);

    /**
     * delete demand of the id
     * @param id id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);

}
