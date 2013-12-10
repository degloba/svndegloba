package com.insacosa.presentation;

import java.io.Serializable;
import java.util.Date;

import domain.sharedkernel.Money;

/**
 * Orders as seen by client creating it on a list.
 * 
 * @author Rafał Jamróz
 */
public class TipusDto implements Serializable {

    private Long orderId;
    private Money totalCost;
    private Date submitDate;


    public TipusDto() {
    }

    public TipusDto(Long orderId, Money totalCost, Date submitDate) {
        this.orderId = orderId;
        this.totalCost = totalCost;
        this.submitDate = submitDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Money totalCost) {
        this.totalCost = totalCost;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }


}
