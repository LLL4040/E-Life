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
     * @param pageNumber 当前的页数
     * @param pageSize 当前的页中数据的数量
     */
    @Query(value = "select * from (maintain natural join (select username from user where community_id=?1)as A) where status=0 order by id limit ?2,?3",nativeQuery = true)
    List<Maintain> findMaintainByCommunityId(int communityId,int pageNumber,int pageSize);


    /**
     * 该函数返回特定小区的所有的已处理和已完成的请求
     * @return 返回请求单的列表
     * @param communityId 小区id
     * @param pageNumber 当前的页数
     * @param pageSize 当前的页中数据的数量
     */
    @Query(value = "select * from (maintain natural join (select username from user where community_id=?1)as A) where status<>0 order by id limit ?2,?3",nativeQuery = true)
    List<Maintain> findHaveMaintainByCommunityId(int communityId,int pageNumber,int pageSize);
    /**
     * 该函数返回特定小区的未处理的维修请求数目
     * @return 返回未处理的维修请求数目
     * @param communityId 小区id
     */
    @Query(value = "select count(*) from (maintain natural join (select username from user where community_id=?1)as A) where status=0",nativeQuery = true)
    long countMaintain(int communityId);
    /**
     * 该函数返回已处理和处理中维修请求的数量
     * @return
     * @param communityId */
    @Query(value = "select count(*) from (maintain natural join (select username from user where community_id=?1)as A) where status<>0",nativeQuery = true)
    long countHaveMaintain(int communityId);
}
