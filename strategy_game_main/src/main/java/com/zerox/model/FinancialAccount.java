package com.zerox.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @Author: zhuxi
 * @Time: 2021/10/15 16:40
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class FinancialAccount {
    private final StringProperty accountHolder;
    private final IntegerProperty accountNumber;
    private final DoubleProperty accountBalance;

    public void deposit(double amount) {
        accountBalance.set(accountBalance.get() + amount);
    }

    public void withdraw(double amount) {
        accountBalance.set(accountBalance.get() - amount);
    }

    //getters, setters, constructor
    public FinancialAccount(String accountHolder, int accountNumber, double accountBalance) {
        this.accountHolder = new SimpleStringProperty(accountHolder);
        this.accountNumber = new SimpleIntegerProperty(accountNumber);
        this.accountBalance = new SimpleDoubleProperty(accountBalance);
    }

    public String getAccountHolder() {
        return accountHolder.get();
    }

    public StringProperty accountHolderProperty() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder.set(accountHolder);
    }

    public int getAccountNumber() {
        return accountNumber.get();
    }

    public IntegerProperty accountNumberProperty() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber.set(accountNumber);
    }

    public double getAccountBalance() {
        return accountBalance.get();
    }

    public DoubleProperty accountBalanceProperty() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance.set(accountBalance);
    }
}
