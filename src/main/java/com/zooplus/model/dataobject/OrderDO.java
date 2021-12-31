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
@Table(name = "order_dtl")
public class OrderDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long orderId;

    @Column(name="order_amt")
    private long amount;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="order_amt_curr")
    private String currency;

    @Column(name="customer_id")
    private long customerId;

    @Column(name="order_bal")
    private long orderBal;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
   /*
    @Column(name="order_amt_paid")
    private int orderAmtPaid;
    @Column(name="payer_name")
    private String payer_name;
    @Column(name="order_amt_curr")
    private String currency;
    @Column(name="pay_tool")
    private String payToolType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_dtl_id", nullable = false)
    @JsonIgnore
    private CustomerDO customer;

    public OrderDO(int orderId, int orderAmt, String orderStatus, int orderBal, int orderAmtPaid, String payer_name, String currency, String payToolType, CustomerDO customerDO) {
        this.orderId = orderId;
        this.orderAmt = orderAmt;
        this.orderStatus = orderStatus;
        this.orderBal = orderBal;
        this.orderAmtPaid = orderAmtPaid;
        this.payer_name = payer_name;
        this.currency = currency;
        this.payToolType = payToolType;
        this.customer = customerDO;
    }

    public OrderDO() {
    }

    */
}