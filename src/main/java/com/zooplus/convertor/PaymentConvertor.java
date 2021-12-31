package com.zooplus.convertor;

import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.model.request.PaymentDetailRequest;
import com.zooplus.model.response.PaymentDetailResponse;
import com.zooplus.util.DateUtil;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author germanjit singh version 1.0
 * */
public class PaymentConvertor {
    public static PaymentDO convertPaymentToPaymentDO(PaymentDetailRequest paymentDetailRequest){
        PaymentDO payment = new PaymentDO();
        payment.setAmount(paymentDetailRequest.getAmount());
        payment.setPayToolType(paymentDetailRequest.getPayToolType());
        payment.setCurrency(paymentDetailRequest.getCurrency().name());
        payment.setOrderId(paymentDetailRequest.getOrderId());
        payment.setPaymentStatus(paymentDetailRequest.getPaymentStatus().name());
        payment.setOrderBal(paymentDetailRequest.getOrderBal());
        payment.setRunningBal(paymentDetailRequest.getRunningBal());
        Date currDate = DateUtil.getDate();
        payment.setCreatedAt(currDate);
        payment.setUpdatedAt(currDate);
        payment.setCustomerId(paymentDetailRequest.getCustomerId());
        return payment;
    }

    public static PaymentDetailResponse convertPaymentDOToPayment(PaymentDO paymentDO){
        PaymentDetailResponse payment = new PaymentDetailResponse();
        payment.setAmount(paymentDO.getAmount());
        payment.setPayToolType(paymentDO.getPayToolType());
        payment.setCurrency(Currency.valueOf(paymentDO.getCurrency()));
        payment.setOrderId(paymentDO.getOrderId());
        payment.setPaymentStatus(PaymentStatusEnum.valueOf(paymentDO.getPaymentStatus()));
        payment.setOrderBal(paymentDO.getOrderBal());
        payment.setCustomerRunningBal(paymentDO.getRunningBal());
        payment.setCreated_at(paymentDO.getCreatedAt());
        payment.setUpdated_at(paymentDO.getUpdatedAt());
        return payment;
    }

    public static List<PaymentDetailResponse> convertPaymentDOListToPaymentList(List<PaymentDO> paymentDOList){
       return paymentDOList.stream().map(x -> convertPaymentDOToPayment(x)).collect(Collectors.toList());
    }
}
