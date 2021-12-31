package com.zooplus.controller;

import com.zooplus.enums.RequestStatus;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.BaseResponse;
import com.zooplus.service.OrderService;
import com.zooplus.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
/**
 * @author germanjit singh version 1.0
 * */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    /**
     * Rest API to fetch details of order based on order id
     * @param orderId
     * */
    @GetMapping("/getOrderDetails/{id}")
    public ResponseEntity<Object> getOrderDetails(@PathVariable(value = "id") long orderId) {
        try {
            Assert.isTrue(NumberUtil.greaterThanZero(orderId), "OrderId cannot be less than zero");
            return ResponseEntity.ok().body(orderService.getOrderDetails(orderId));
        }catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }

 /*   @PostMapping("/createOrderAndPay")
    public Object createOrderAndPay(@RequestBody OrderDetailRequest orderDetailRequest) {
        try {
            Assert.isTrue(NumberUtil.greaterThanZero(orderDetailRequest.getCustomerId()), "CustomerId cannot be less than zero");
            return orderService.createOrderAndPay(orderDetailRequest);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }*/
    /**
     * Rest API to create order in system
     * @param orderDetailRequest
     * */
    @PostMapping("/createOrder")
    public Object createOrder(@RequestBody OrderDetailRequest orderDetailRequest) {
        try {
            Assert.isTrue(NumberUtil.greaterThanZero(orderDetailRequest.getCustomerId()), "CustomerId cannot be less than zero");
            return orderService.createOrUpdateOrder(orderDetailRequest);
        } catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
    /**
     * Rest API to fetch details of order balance based on order id
     * @param orderId
     * */
    @GetMapping("/getOrderBalance/{id}")
    public ResponseEntity<Object> getOrderBalance(@PathVariable(value = "id") long orderId) {
        try {
            Assert.isTrue(NumberUtil.greaterThanZero(orderId), "OrderId cannot be less than zero");
            return ResponseEntity.ok().body(orderService.getOrderBalance(orderId));
        } catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
}
