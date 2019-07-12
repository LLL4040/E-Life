package group.dao;

import group.entity.Discount;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ztHou
 */
public interface DiscountDao {
    /**
     * save discount
     * @param discount discount
     * @return discount saved
     */
    Discount save(Discount discount);

    /**
     * find discount by id
     * @param id id
     * @return discount found
     */
    Discount findById(Long id);

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
