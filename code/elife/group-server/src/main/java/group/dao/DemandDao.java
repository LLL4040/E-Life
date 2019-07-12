package group.dao;

import group.entity.Demand;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface DemandDao {
    /**
     * save demand
     * @param demand demand
     * @return demand saved
     */
    Demand save(Demand demand);

    /**
     * find demand by id
     * @param id id
     * @return demand found
     */
    Demand findById(Long id);

    /**
     * get all demand
     * @param current current time
     * @param communityId community id
     * @return demand
     */
    List<Demand> findAllByEndTimeAfterAndCommunityId(String current, Long communityId);

    /**
     * delete all demand where end time before setTime
     * @param setTime set time
     */
    void deleteAllByEndTimeBefore(String setTime);

    /**
     * delete demand of the id
     * @param id id
     */
    void deleteById(Long id);
}
