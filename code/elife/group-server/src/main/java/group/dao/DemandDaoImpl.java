package group.dao;

import group.entity.Demand;
import group.repository.DemandRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class DemandDaoImpl implements DemandDao{
    private final DemandRepository demandRepository;

    public DemandDaoImpl(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    @Override
    public Demand save(Demand demand){
        return demandRepository.save(demand);
    }

    @Override
    public Demand findById(Long id){
        return demandRepository.findById(id);
    }

    @Override
    public List<Demand> findAllByEndTimeAfterAndCommunityId(String current, Long communityId){
        return demandRepository.findAllByEndTimeAfterAndCommunityId(current, communityId);
    }

    @Override
    public void deleteAllByEndTimeBefore(String setTime){
        demandRepository.deleteAllByEndTimeBefore(setTime);
    }

    @Override
    public void deleteById(Long id){
        demandRepository.deleteById(id);
    }
}
