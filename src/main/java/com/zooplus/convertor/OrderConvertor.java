package com.zooplus.convertor;

import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.OrderDetailResponse;
import com.zooplus.util.DateUtil;

import java.sql.Date;

/**
 * @author germanjit singh version 1.0
 * */
public class OrderConvertor {

    public static OrderDO convertOrderToOrderDO(OrderDetailRequest orderDetailRequest){
        OrderDO order = new OrderDO();
        order.setOrderStatus(OrderStatusEnum.INIT.name());
        order.setAmount(orderDetailRequest.getAmount());
        order.setOrderBal(orderDetailRequest.getAmount() * -1);
        order.setCurrency(orderDetailRequest.getCurrency().name());
        order.setCustomerId(orderDetailRequest.getCustomerId());
        Date currDate = DateUtil.getDate();
        order.setCreatedAt(currDate);
        order.setUpdatedAt(currDate);
        return order;
    }
    public static OrderDetailResponse reconvertToOrderDetailResponse(OrderDO orderDO){
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setOrderId(orderDO.getOrderId());
        orderDetailResponse.setOrigAmount(orderDO.getAmount());
        orderDetailResponse.setOrderAmtCurr(Currency.getCurrency(orderDO.getCurrency()));
        orderDetailResponse.setOrderBal(orderDO.getOrderBal());
        orderDetailResponse.setCustomerId(orderDO.getCustomerId());
        orderDetailResponse.setOrderStatus(orderDO.getOrderStatus());
        return orderDetailResponse;
    }
}

