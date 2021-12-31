package com.zooplus.controller;

import com.zooplus.enums.RequestStatus;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.request.PaymentDetailRequest;
import com.zooplus.model.response.BaseResponse;
import com.zooplus.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author germanjit singh version 1.0
 * */
@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /**
     * Rest API to initiate a payment against an order in system
     * @param paymentDetailRequest
     * */
    @PostMapping("/makePayment")
    public Object makePayment(@RequestBody PaymentDetailRequest paymentDetailRequest){
        try{
            return paymentService.makePayment(paymentDetailRequest);
        } catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }

    /**
     * Rest API to fetch details of payments based on payment id
     * @param paymentId
     * */
    @GetMapping("/getPaymentDetails/{id}")
    public ResponseEntity<Object> getPaymentDetails(@PathVariable(value = "id") int paymentId)  {
       try{
        return ResponseEntity.ok().body(paymentService.getPaymentDetails(paymentId));
       } catch (ZooplusCommonException e) {
           return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
       } catch (Exception e) {
           return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
       }
    }

    /**
     * Rest API to fetch detail list of payments based on order id
     * @param orderId
     * */
    @GetMapping("/getPaymentDetails/order/{id}")
    public ResponseEntity<Object> getPaymentDetailsForOrder(@PathVariable(value = "id") int orderId){
        try{
        return ResponseEntity.ok().body(paymentService.getPaymentDetailsForOrder(orderId));
        } catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
}
