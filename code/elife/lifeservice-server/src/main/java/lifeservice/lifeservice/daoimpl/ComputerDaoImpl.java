package lifeservice.lifeservice.daoimpl;

import lifeservice.lifeservice.dao.ComputerDao;
import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.repository.MaintainRepository;
import lifeservice.lifeservice.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Repository

public class ComputerDaoImpl implements ComputerDao {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> findComputerMaintain(int communityId) {
        return merchantRepository.findComputerMaintain(communityId);
    }
}
