package group.repository;

import group.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author ztHou
 */
public interface ParticipateRepository extends JpaRepository<Participate, String> {
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
