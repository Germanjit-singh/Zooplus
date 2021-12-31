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
@Table(name="customer_dtl")
public class CustomerDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="customer_bal")
    private long customerBalance;
    @Column(name="customer_bal_curr")
    private String customerBalCurr;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;
    public CustomerDO(int customerId, String customerName, int customerBalance) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerBalance = customerBalance;
    }


    public CustomerDO() {
    }
}
