package com.zooplus.convertor;

import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.response.CustomerDetailResponse;
import com.zooplus.util.DateUtil;

import java.sql.Date;
/**
 * @author germanjit singh version 1.0
 * */
public class CustomerConvertor {
    public static CustomerDO convertCustomerToCustomerDO(CustomerDetailRequest customerDetailRequest){
        CustomerDO customer = new CustomerDO();
        customer.setCustomerName(customerDetailRequest.getCustomerName());
        customer.setCustomerBalCurr(Currency.EUR.name());
        Date currDate = DateUtil.getDate();
        customer.setCreatedAt(currDate);
        customer.setUpdatedAt(currDate);
        return customer;
    }

    public static CustomerDetailResponse convertCustomerDoToCustomer(CustomerDO customerDO){
        CustomerDetailResponse customer = new CustomerDetailResponse();
        customer.setCustomerName(customerDO.getCustomerName());
        customer.setCustomerId(customerDO.getCustomerId());
        customer.setCustomerBal(customerDO.getCustomerBalance());
        customer.setCustomerBalCurr(Currency.valueOf(customerDO.getCustomerBalCurr()));

        return customer;
    }
}
