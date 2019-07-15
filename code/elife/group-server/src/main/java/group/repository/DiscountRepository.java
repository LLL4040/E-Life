package group.repository;

import group.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface DiscountRepository extends JpaRepository<Discount, String> {
    /**
     * find discount by id
     * @param id id
     * @return discount found
     */
    Discount findById(Long id);

    /**
     * whether exists id
     * @param id id
     * @return whether exists id
     */
    Boolean existsById(Long id);

    /**
     * find all discount where end time after current time
     * @param current current time
     * @param communityId communityId
     * @return discount meets the sequence
     */
    List<Discount> findAllByEndTimeAfterAndCommunityId(String current, Long communityId);

    /**
     * find all discount message by merchant id
     * @param merchantId merchant id
     * @return discount
     */
    List<Discount> findByMerchantId(Long merchantId);

    /**
     * whether exists discount by merchant
     * @param merchantId merchant id
     * @return whether exists discount
     */
    Boolean existsByMerchantId(Long merchantId);

    /**
     * delete all discount where end time before setTime
     * @param setTime set time
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByEndTimeBefore(String setTime);

    /**
     * delete discount of the id
     * @param id id
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);

}
