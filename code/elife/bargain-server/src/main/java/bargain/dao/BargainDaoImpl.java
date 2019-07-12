package bargain.dao;

import bargain.entity.Bargain;
import bargain.repository.BargainRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class BargainDaoImpl implements BargainDao{
    private final BargainRepository bargainRepository;

    public BargainDaoImpl(BargainRepository bargainRepository) {
        this.bargainRepository = bargainRepository;
    }

    @Override
    public Bargain findById(Long id){
        return bargainRepository.findById(id);
    }

    @Override
    public List<Bargain> findAll(){
        return bargainRepository.findAll();
    }

    @Override
    public Bargain save(Bargain bargain){
        return bargainRepository.save(bargain);
    }

    @Override
    public void deleteById(Long id){
        bargainRepository.deleteById(id);
    }

    @Override
    public List<Bargain> findAllByGoodsContaining(String goods){
        return bargainRepository.findAllByGoodsContaining(goods);
    }
}
