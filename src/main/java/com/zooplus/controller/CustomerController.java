package com.zooplus.controller;

import com.zooplus.enums.RequestStatus;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.response.BaseResponse;
import com.zooplus.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author germanjit singh version 1.0
 * */
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Rest API to create customer into system
     * @param customerDetailRequest
     * */
    @PostMapping(value = "/createCustomer")
    public Object createCustomer(@RequestBody CustomerDetailRequest customerDetailRequest) {
        try {
            return customerService.createCustomer(customerDetailRequest);
        }catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
    /**
     * Rest API to fetch details of customer based on customer id
     * @param customerId
     * */
    @GetMapping("/getCustomerDetails/{id}")
    public ResponseEntity<Object> getCustomerDetails(@PathVariable(value = "id") int customerId) {
        try {
            return ResponseEntity.ok().body(customerService.getCustomerDetails(customerId));
        }catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
    /**
     * Rest API to fetch details of customer Balance based on customer id
     * @param customerId
     * */
    @GetMapping("/getCustomerBalance/{id}")
    public ResponseEntity<Object> getCustomerBalance(@PathVariable(value = "id") int customerId) {
        try {
            return ResponseEntity.ok().body(customerService.getCustomerBalance(customerId));
        } catch (ZooplusCommonException e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getErrorMsg(), RequestStatus.FAIL));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage(), RequestStatus.FAIL));
        }
    }
}
