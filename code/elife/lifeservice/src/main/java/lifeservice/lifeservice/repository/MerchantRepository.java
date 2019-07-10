package lifeservice.lifeservice.repository;

import lifeservice.lifeservice.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    /**
     * 该函数返回该小区的所有商家列表
     * @param communityId 小区的id
     * @return 返回电脑维修商家的列表*/
    @Query(value = "select * from (merchant natural join (select username from user where community_id=1)as A) where (type='周边餐饮'or type='超市购物'or type='休闲娱乐')",nativeQuery = true)
    List<Merchant> findComputerMaintain(int communityId);
}
