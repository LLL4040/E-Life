package lifeservice.lifeservice.serviceimpl;

import lifeservice.lifeservice.dao.ComputerDao;
import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 符永锐
 * @date 2019/07/09*/
@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private ComputerDao computerDao;
    @Override
    public List<Merchant> findComputerMaintain(int communityId) {
        return computerDao.findComputerMaintain(communityId);
    }
}
