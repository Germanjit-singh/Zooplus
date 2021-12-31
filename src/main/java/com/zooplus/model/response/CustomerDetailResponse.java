package com.zooplus.model.response;


import com.zooplus.model.Currency;
import lombok.Data;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class CustomerDetailResponse extends BaseResponse {
    private String customerName;
    private long customerId;
    private long customerBal;
    private Currency customerBalCurr;
}
