package com.zooplus.model.request;

import com.zooplus.annotations.NotNull;
import com.zooplus.annotations.NumberValue;
import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.model.Currency;
import lombok.Data;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class PaymentDetailRequest {
    @NumberValue(min = 1)
    @NotNull
    private long orderId;
    @NumberValue(min = 1)
    @NotNull
    private long amount;
    private Currency currency;
    private String payToolType;
    private PaymentStatusEnum paymentStatus;
    private long customerId;
    private long orderBal;
    private long runningBal;
}
