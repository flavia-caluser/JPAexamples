package com.springapps.jpaexamples.accountapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long orderId;

    @Column
    private Integer balance;

    public Account(){}

    public Account(long orderId, Integer balance) {
        this.orderId = orderId;
        this.balance = balance;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) throws Exception{
        if (balance < 0){
            throw new Exception("neg balance");
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "orderId=" + orderId +
                ", balance=" + balance +
                '}';
    }
}
