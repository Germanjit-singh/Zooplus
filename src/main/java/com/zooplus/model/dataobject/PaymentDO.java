package com.zooplus.model.dataobject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author germanjit singh version 1.0
 * */
@Data
@Getter
@Setter
@Entity
@Table(name="payment_dtl")
public class PaymentDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;
    @Column(name="order_id")
    private long orderId;
    @Column(name="customer_id")
    private long customerId;
    @Column(name="order_bal")
    private long orderBal;
    @Column(name="amount")
    private long amount;
    @Column(name="payer_name")
    private String payer_name;
    @Column(name="order_amt_curr")
    private String currency;
    @Column(name="pay_tool")
    private String payToolType;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    @Column(name="running_balance")
    private long runningBal;
    @Column(name="payment_status")
    private String paymentStatus;
}
