package com.zooplus.service;

import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.persistence.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class PaymentInnerService {

    @Autowired
    PaymentRepository paymentRepository;

    public PaymentDO makePayment(PaymentDO paymentDO) {
          return paymentRepository.save(paymentDO);
    }

    public PaymentDO getPaymentDetails(long paymentId) throws ZooplusCommonException {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new ZooplusCommonException("Payment Details not found for this id :: " + paymentId));
    }
    public List<PaymentDO> getPaymentDetailsForOrder(long orderId){
        return paymentRepository.getPaymentDetailsForOrder(orderId);
    }
}
