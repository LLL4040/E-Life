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
     * @param status 维修请求的状态*/
    @Query(value = "select * from maintain  where username=?1 and status=?2", nativeQuery = true)
    List<Maintain> findbyUsernameAndStatus(String username, int status);
}
