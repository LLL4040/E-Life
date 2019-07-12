package lifeservice.lifeservice.dao;

import lifeservice.lifeservice.entity.Merchant;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
public interface ComputerDao {
    /**
     * 该函数返回该小区的所有商家列表
     * @param communityId 小区的id
     * @return 返回电脑维修商家的列表*/
    List<Merchant> findComputerMaintain(int communityId);
}
