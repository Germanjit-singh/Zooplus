package com.zooplus.model.response;

import com.zooplus.enums.RequestStatus;
import com.zooplus.model.Currency;
import lombok.Data;

import java.util.Map;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class OrderDetailResponse extends BaseResponse{
    private long orderId;
    private long customerId;
    private long origAmount;
    private long amountPaid;
    private String payee_name;
    private long orderBal;
   private Currency orderAmtCurr;
   private Map<String,Double> payToolType;
   private String orderStatus;

}
