package com.zooplus.persistence;

import com.zooplus.model.dataobject.CustomerDO;
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
public interface CustomerRepository extends JpaRepository<CustomerDO, Long> {
    @Query("select c from CustomerDO c where c.customerId=:customerId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<CustomerDO> lockCustomerDetails(@Param("customerId") long customerId);

    @Query("select c from CustomerDO c where c.customerId=:customerId")
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<CustomerDO> findById(@Param("customerId")long customerId);
}
