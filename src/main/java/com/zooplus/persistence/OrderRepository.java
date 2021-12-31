package com.zooplus.persistence;

import com.zooplus.model.dataobject.OrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;
/**
 * @author germanjit singh version 1.0
 * */
@Repository
public interface OrderRepository extends JpaRepository<OrderDO, Long> {

    @Query("select o from OrderDO o where o.orderId=:orderId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<OrderDO> lockOrderDetails(@Param("orderId") long orderId);

    @Query("select o from OrderDO o where o.orderId=:orderId")
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<OrderDO> findById(@Param("orderId") long orderId);
}
