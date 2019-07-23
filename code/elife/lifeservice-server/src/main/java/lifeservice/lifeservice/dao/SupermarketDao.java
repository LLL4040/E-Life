package lifeservice.lifeservice.dao;

import lifeservice.lifeservice.entity.Merchant;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
public interface SupermarketDao {
    /**
     * 该函数返回该小区的所有超市送货的商家列表
     * @param communityId 小区的id
     * @return 返回超市送货商家的列表*/
    List<Merchant> findSupermarket(int communityId);
}
