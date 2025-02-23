package payserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import payserver.entity.Orders;
import java.util.List;


/**
 * OrderRepository interface
 *
 * @author wyx
 * @date 2019.07.16
 */
public interface OrderRepository extends JpaRepository<Orders, String> {

    /**
     * find newest Pay  by username
     *
     * @param username
     * @param pid
     * @return List
     */
    @Query(value=" select * from orders where username=?1 and pid=?2 and status=0 order by id desc",nativeQuery = true)
    List<Orders> getOrders(String username,int pid);
}

