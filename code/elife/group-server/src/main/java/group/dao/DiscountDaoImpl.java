package group.dao;

import group.entity.Discount;
import group.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class DiscountDaoImpl implements DiscountDao{
    private final DiscountRepository discountRepository;

    public DiscountDaoImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount save(Discount discount){
        return discountRepository.save(discount);
    }

    @Override
    public Discount findById(Long id){
        return discountRepository.findById(id);
    }

    @Override
    public List<Discount> findAllByEndTimeAfterAndCommunityId(String current, Long communityId){
        return discountRepository.findAllByEndTimeAfterAndCommunityId(current, communityId);
    }

    @Override
    public List<Discount> findByMerchantId(Long merchantId){
        return discountRepository.findByMerchantId(merchantId);
    }

    @Override
    public void deleteAllByEndTimeBefore(String setTime){
        discountRepository.deleteAllByEndTimeBefore(setTime);
    }

    @Override
    public void deleteById(Long id){
        discountRepository.deleteById(id);
    }
}
