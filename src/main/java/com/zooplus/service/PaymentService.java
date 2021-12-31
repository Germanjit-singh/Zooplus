package com.zooplus.service;

import com.zooplus.convertor.PaymentConvertor;
import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.model.request.PaymentDetailRequest;
import com.zooplus.model.response.PaymentDetailResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class PaymentService {

    @Autowired
    PaymentInnerService paymentInnerService;

    @Autowired
    OrderInnerService orderInnerService;

    @Autowired
    CustomerInnerService customerInnerService;

    @Transactional
    public PaymentDetailResponse makePayment(PaymentDetailRequest paymentDetailRequest) throws Exception{
       OrderDO order = orderInnerService.getOrderDetails(paymentDetailRequest.getOrderId());
       Assert.notNull(order, "Order not found for the order id " + paymentDetailRequest.getOrderId());

       CustomerDO customerDO = customerInnerService.getCustomerDetails(order.getCustomerId());
        Assert.notNull(customerDO, "Customer not found for the order id " + paymentDetailRequest.getOrderId());


       order = orderInnerService.lockOrderDetails(paymentDetailRequest.getOrderId());
       order.setOrderBal(Math.addExact(order.getOrderBal(), paymentDetailRequest.getAmount()) );
       order.setOrderStatus(Math.subtractExact
                (paymentDetailRequest.getAmount(),order.getOrderBal() ) >= 0
                ? OrderStatusEnum.SETTLED.name() : OrderStatusEnum.WAIT_FOR_SETTLE.name());

       customerDO = customerInnerService.lockCustomerDetails(order.getCustomerId());
       customerDO.setCustomerBalance(Math.addExact(customerDO.getCustomerBalance(), paymentDetailRequest.getAmount()));

        paymentDetailRequest.setPaymentStatus(getPaymentStatus(order.getAmount(), paymentDetailRequest.getAmount()));
        paymentDetailRequest.setCustomerId(order.getCustomerId());
        paymentDetailRequest.setOrderBal(order.getOrderBal());
        paymentDetailRequest.setRunningBal(customerDO.getCustomerBalance());

        PaymentDO paymentDO = paymentInnerService.makePayment(PaymentConvertor.convertPaymentToPaymentDO(paymentDetailRequest));
       orderInnerService.createOrUpdateOrder(order);

       customerInnerService.createOrUpdateCustomer(customerDO);
       return PaymentConvertor.convertPaymentDOToPayment(paymentDO);
    }

    public PaymentDetailResponse getPaymentDetails(int paymentId) throws ZooplusCommonException {
        return PaymentConvertor.convertPaymentDOToPayment(paymentInnerService.getPaymentDetails(paymentId));
    }

    public List<PaymentDetailResponse> getPaymentDetailsForOrder(int orderId){
        return PaymentConvertor.convertPaymentDOListToPaymentList(paymentInnerService.getPaymentDetailsForOrder(orderId));
    }

    private PaymentStatusEnum getPaymentStatus(long orderAmt, long amountPaid){
        if(orderAmt > amountPaid){
            return PaymentStatusEnum.PARTIAL_PAID;
        } else if(orderAmt < amountPaid) {
            return  PaymentStatusEnum.FULLY_PAID;
        }
        return PaymentStatusEnum.INIT;
    }
}
