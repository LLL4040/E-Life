package user.dao;

import org.springframework.stereotype.Repository;
import user.entity.Manager;
import user.repository.ManagerRepository;

import java.util.List;

/**
 * @author ztHou
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {
    private final ManagerRepository managerRepository;

    public ManagerDaoImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Boolean existByUsername(String username) {
        return managerRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return managerRepository.existsByPhone(phone);
    }

    @Override
    public Manager findByUsername(String username) {
        return managerRepository.findByUsername(username);
    }

    @Override
    public Manager findByPhone(String phone) {
        return managerRepository.findByPhone(phone);
    }

    @Override
    public List<Manager> findAllByCommunityId(Long communityId){
        return managerRepository.findAllByCommunityId(communityId);
    }

    @Override
    public void deleteByUsername(String username) {
        managerRepository.deleteByUsername(username);
    }
}
