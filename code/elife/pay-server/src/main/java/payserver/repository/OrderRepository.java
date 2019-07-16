package payserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import payserver.entity.Orders;
import payserver.entity.Pay;

import java.math.BigDecimal;
import java.util.List;


/**
 * OrderRepository interface
 *
 * @author wyx
 * @date 2019.07.16
 */
public interface OrderRepository extends JpaRepository<Orders, String> {


}

