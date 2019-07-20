package newsserver.repository;


import newsserver.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    /**
     * 该函数查询特定管理员发布的的所有物业通知
     * @return 返回特定管理员发布的的所有物业通知
     * @param pageNumber 当前页数
     * @param pageSize 每页数据量
     * @param managerName 管理员名*/
    @Query(value = "select * from notice where managername=?1 order by id  limit ?2,?3", nativeQuery = true)
    List<Notice> findByManagerName(String managerName,int pageNumber,int pageSize);
    /**
     * 该函数返回某管理员发的所有通知数
     * @return 该函数返回某管理员发的所有通知数
     * @param managername*/
    @Query(value = "select  count(*) from notice where managername=?1",nativeQuery = true)
    int countByManagerName(String managername);
    /**
     * 该函数根据noticeId返回物业通知
     * @return  返回得到的一条物业通知
     * @param  noticeId 物业通知的id*/
    @Query(value = "select * from notice where id=?1", nativeQuery = true)
    Notice findByNoticeId(int noticeId);
    /**
     * 该函数根据noticeId删除一条物业通知,好了现在可以用了
     * @param noticeId 物业通知的id
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "delete from notice  where id=?1", nativeQuery = true)
    void deleteByNoticeId(int noticeId);
    /**
     * 该函数判断是否存在某条物业通知，不为0则存在
     * @param  noticeId 物业通知的id
     * @return  返回0代表不存在*/
    @Query(value = "select count(*) from (select * from notice where id=?1 limit 1)as A", nativeQuery = true)
    int existsByNoticeId(int noticeId);
}
