package apackage.dao;


import org.springframework.stereotype.Repository;
import apackage.entity.Identify;
import apackage.repository.IdentifyRepository;

/**
 * @author ztHou
 */
@Repository
public class IdentifyDaoImpl implements IdentifyDao {
    private final IdentifyRepository identifyRepository;

    public IdentifyDaoImpl(IdentifyRepository identifyRepository) {
        this.identifyRepository = identifyRepository;
    }

    @Override
    public Boolean exists(String phone) {
        return identifyRepository.existsByPhone(phone);
    }

    @Override
    public Identify save(Identify identify) {
        return identifyRepository.save(identify);
    }

    @Override
    public Identify findByPhone(String phone) {
        return identifyRepository.findByPhone(phone);
    }

    @Override
    public void deleteByPhone(String phone) {
        identifyRepository.deleteByPhone(phone);
    }
}
