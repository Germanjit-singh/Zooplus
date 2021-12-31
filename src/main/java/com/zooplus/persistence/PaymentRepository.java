package com.zooplus.persistence;

import com.zooplus.model.dataobject.PaymentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author germanjit singh version 1.0
 * */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentDO, Long>{
    @Query("select p from PaymentDO p where p.orderId=:orderId")
    List<PaymentDO> getPaymentDetailsForOrder(@Param("orderId") long orderId);

}
