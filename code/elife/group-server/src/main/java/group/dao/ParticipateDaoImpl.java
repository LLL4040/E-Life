package group.dao;

import group.entity.Participate;
import group.repository.ParticipateRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ztHou
 */
@Repository
public class ParticipateDaoImpl implements ParticipateDao{
    private final ParticipateRepository participateRepository;

    public ParticipateDaoImpl(ParticipateRepository participateRepository) {
        this.participateRepository = participateRepository;
    }

    @Override
    public Participate save(Participate participate){
        return participateRepository.save(participate);
    }

    @Override
    public void deleteByUsernameAndDemand(String username, Long demand){
        participateRepository.deleteByUsernameAndDemand(username, demand);
    }

    @Override
    public Boolean existsByUsernameAndDemand(String username, Long demand){
        return participateRepository.existsByUsernameAndDemand(username, demand);
    }

    @Override
    public Integer countDistinctByDemand(Long demand){
        return participateRepository.countDistinctByDemand(demand);
    }
}
