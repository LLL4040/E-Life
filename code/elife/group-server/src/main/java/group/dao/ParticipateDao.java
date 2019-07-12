package group.dao;

import group.entity.Participate;

import javax.transaction.Transactional;

/**
 * @author ztHou
 */
public interface ParticipateDao {
    /**
     * save participate
     * @param participate participate
     * @return participate saved
     */
    Participate save(Participate participate);

    /**
     * delete data by username and demand id
     * @param username username
     * @param demand demand
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByUsernameAndDemand(String username, Long demand);

    /**
     * whether exists username in the demand
     * @param username username
     * @param demand demand
     * @return whether exists username in the demand
     */
    Boolean existsByUsernameAndDemand(String username, Long demand);

    /**
     * count total user number of the chosen demand
     * @param demand demand
     * @return total user number of the chosen demand
     */
    Integer countDistinctByDemand(Long demand);
}
