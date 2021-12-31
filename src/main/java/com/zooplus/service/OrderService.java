package com.zooplus.service;


import com.zooplus.convertor.OrderConvertor;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.OrderDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class OrderService {

    @Autowired
    OrderInnerService orderInnerService;

    @Autowired
    CustomerInnerService customerInnerService;

    @Autowired
    PaymentService paymentService;

    /*public OrderDetailResponse createOrderAndPay(OrderDetailRequest orderDetailRequest) throws ZooplusCommonException {
        CustomerDO customerDO = customerInnerService.getCustomerDetails(orderDetailRequest.getCustomerId());
        orderDetailRequest.setCustomerId(customerDO.getCustomerId());
        customerDO.setCustomerBalance(Math.addExact(customerDO.getCustomerBalance(),
                (orderDO.getOrderAmtPaid() > orderDO.getOrderAmt())
                ? Math.subtractExact(orderDO.getOrderAmtPaid(), orderDO.getOrderAmt()): 0));
        customerService.update(customerDO);
        return null; //OrderConvertor.reconvertToOrderDetailResponse(orderDO);
    }*/
    public OrderDetailResponse getOrderDetails(long orderId) throws ZooplusCommonException {
        return OrderConvertor.reconvertToOrderDetailResponse(orderInnerService.getOrderDetails(orderId));
    }
    @Transactional
    public OrderDetailResponse createOrUpdateOrder(OrderDetailRequest orderDetailRequest) throws ZooplusCommonException {
        CustomerDO customerDO = customerInnerService.getCustomerDetails(orderDetailRequest.getCustomerId());

        Assert.notNull(customerDO, "Customer not found for the customer id " + orderDetailRequest.getCustomerId());

        orderDetailRequest.setCustomerId(customerDO.getCustomerId());
        OrderDO orderDO = OrderConvertor.convertOrderToOrderDO(orderDetailRequest);

        customerDO = customerInnerService.lockCustomerDetails(orderDetailRequest.getCustomerId());
        customerDO.setCustomerBalance(Math.addExact(customerDO.getCustomerBalance(), orderDO.getOrderBal()));
        OrderDO orderDOResult = orderInnerService.createOrUpdateOrder(orderDO);
        customerInnerService.createOrUpdateCustomer(customerDO);
        return OrderConvertor.reconvertToOrderDetailResponse(orderDOResult);
    }

    public String getOrderBalance(long orderId) throws ZooplusCommonException {
        OrderDetailResponse orderDetailResponse = OrderConvertor.reconvertToOrderDetailResponse(orderInnerService.getOrderDetails(orderId));
        return orderDetailResponse.getOrderBal() + " " + orderDetailResponse.getOrderAmtCurr().name();
    }
}
