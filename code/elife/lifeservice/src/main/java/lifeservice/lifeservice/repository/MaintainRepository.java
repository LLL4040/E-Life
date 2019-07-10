package lifeservice.lifeservice.repository;

import lifeservice.lifeservice.entity.Maintain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
public interface  MaintainRepository extends JpaRepository<Maintain,Long> {

    /**
     * 该函数返回用户发的所有的待处理的请求
     * @return 返回请求单的列表
     * @param username 用户名
     */
    @Query(value = "select * from maintain  where username=?1 and (status=0 or 1)", nativeQuery = true)
    List<Maintain> findbyUsernameAndStatus(String username);
    /**
     * 该函数返回特定小区的所有的待处理的请求
     * @return 返回请求单的列表
     * @param communityId 小区id
     */
    @Query(value = "select * from (maintain natural join (select username from user where community_id=?1)as A);",nativeQuery = true)
    List<Maintain> findMaintainByCommunityId(int communityId);

    /**
     * 该函数返回特定小区的未处理的维修请求数目
     * @return 返回未处理的维修请求数目
     * @param communityId 小区id
     */
    @Query(value = "select count(*) from (maintain natural join (select username from user where community_id=?1)as A) where status=0",nativeQuery = true)
    long countMaintain(int communityId);
}
