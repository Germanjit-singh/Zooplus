package com.zooplus.model.request;

import com.zooplus.annotations.NotNull;
import com.zooplus.annotations.NumberValue;
import com.zooplus.model.Currency;
import lombok.Data;

import java.util.Map;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class OrderDetailRequest {
    private long orderId;
    @NumberValue
    @NotNull
    private long customerId;
    @NumberValue
    @NotNull
    private long amount;

    private Currency currency;
    private long amountPaid;
    private String payerName;
    private Map<String,Double> payToolType;
}
