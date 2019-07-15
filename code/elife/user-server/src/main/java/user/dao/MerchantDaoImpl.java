package user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import user.entity.Merchant;
import user.repository.MerchantRepository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class MerchantDaoImpl implements MerchantDao {
    private final MerchantRepository merchantRepository;

    public MerchantDaoImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant findById(Long id){
        return merchantRepository.findById(id);
    }

    @Override
    public List<Merchant> findAll(){
        return merchantRepository.findAll();
    }

    @Override
    public Merchant findByUsername(String username){
        return merchantRepository.findByUser_Username(username);
    }

    @Override
    public List<Merchant> findAllByType(String type){
        return merchantRepository.findAllByType(type);
    }

    @Override
    public List<Merchant> findAllByUsername(String username){
        return merchantRepository.findAllByUser_Username(username);
    }

    @Override
    public void deleteById(Long id){
        merchantRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id){
        return merchantRepository.existsById(id);
    }
}
