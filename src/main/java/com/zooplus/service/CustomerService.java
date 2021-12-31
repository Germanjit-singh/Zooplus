package com.zooplus.service;

import com.zooplus.convertor.CustomerConvertor;
import com.zooplus.convertor.OrderConvertor;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.CustomerDetailResponse;
import com.zooplus.model.response.OrderDetailResponse;
import com.zooplus.persistence.CustomerRepository;
import com.zooplus.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class CustomerService {

    @Autowired
    CustomerInnerService customerInnerService;

    public CustomerDetailResponse createCustomer(CustomerDetailRequest customerDetailRequest){
        return CustomerConvertor.convertCustomerDoToCustomer(customerInnerService.createOrUpdateCustomer(CustomerConvertor.convertCustomerToCustomerDO(customerDetailRequest)));
    }
    public CustomerDetailResponse getCustomerDetails(int customerId) throws ZooplusCommonException {
        return CustomerConvertor.convertCustomerDoToCustomer(customerInnerService.getCustomerDetails(customerId));
    }

    public String getCustomerBalance(long customerId) throws ZooplusCommonException {
        CustomerDetailResponse customerDetailResponse = CustomerConvertor.convertCustomerDoToCustomer(customerInnerService.getCustomerDetails(customerId));
        return customerDetailResponse.getCustomerBal() + " " + customerDetailResponse.getCustomerBalCurr();
    }
}
