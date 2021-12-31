package com.zooplus.service;

import com.zooplus.convertor.OrderConvertor;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.OrderDetailResponse;
import com.zooplus.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class OrderInnerService {
    @Autowired
    OrderRepository orderRepository;

    public OrderDO createOrUpdateOrder(OrderDO orderDO) {
        return orderRepository.save(orderDO);
    }

    public OrderDO getOrderDetails(long orderId) throws ZooplusCommonException {
        return orderRepository.findById(orderId).orElseThrow(() -> new ZooplusCommonException("Order not found for this order id :: " + orderId));
    }
    public OrderDO lockOrderDetails(long orderId) throws ZooplusCommonException {
        return orderRepository.lockOrderDetails(orderId).orElseThrow(() -> new ZooplusCommonException("Order not found for this order id :: " + orderId));
    }
}
