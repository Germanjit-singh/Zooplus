package com.zooplus.model.response;

import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.model.Currency;
import lombok.Data;

import java.util.Date;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class PaymentDetailResponse extends BaseResponse{
    private long orderId;
    private long amount;
    private Currency currency;
    private PaymentStatusEnum paymentStatus;
    private long orderBal;
    private long customerRunningBal;
    private String payToolType;
    private Date created_at;
    private Date updated_at;
}
