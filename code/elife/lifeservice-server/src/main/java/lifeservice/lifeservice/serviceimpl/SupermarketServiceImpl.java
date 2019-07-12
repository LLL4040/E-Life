package lifeservice.lifeservice.serviceimpl;

import lifeservice.lifeservice.dao.SupermarketDao;
import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@Service
public class SupermarketServiceImpl implements SupermarketService {
    @Autowired
    private SupermarketDao supermarketDao;
    @Override
    public List<Merchant> findSupermarket(int communityId) {
        return supermarketDao.findSupermarket(communityId);
    }
}
