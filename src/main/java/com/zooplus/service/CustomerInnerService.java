package com.zooplus.service;

import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author germanjit singh version 1.0
 * */
@Service
public class CustomerInnerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDO createOrUpdateCustomer(CustomerDO customerDO) {
        return customerRepository.save(customerDO);
    }

    public CustomerDO getCustomerDetails(long customerId) throws ZooplusCommonException {
        return customerRepository.findById(customerId).orElseThrow(() -> new ZooplusCommonException("Customer not found for this customer id :: " + customerId));
    }

    public CustomerDO lockCustomerDetails(long customerId) throws ZooplusCommonException {
        return customerRepository.lockCustomerDetails(customerId).orElseThrow(() -> new ZooplusCommonException("Customer not found for this Customer id :: " + customerId));
    }
}
