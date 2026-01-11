package com.eureka.inheritance;

import java.math.BigDecimal;

public abstract class Account {//Abstract classes cannot have object created from them

    protected String accountNumber;
    protected BigDecimal accountBalance;

    private Account(){

    }

    public Account(String accountNumber, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    /**
     * Method that withdraws money from an account
     * @param withDrawAmount
     * @return accountbalance
     */

    public BigDecimal withDraw(BigDecimal withDrawAmount){
        this.accountBalance=accountBalance.subtract(withDrawAmount);
        return getAccountBalance();
    }


    /**
     * method that deposits money to the account
     * @param depositAmount
     * @return accountBalance
     */
    public BigDecimal deposit(BigDecimal depositAmount){
        accountBalance=accountBalance.add(depositAmount);
        return getAccountBalance();
    }


    abstract void printAccountDetails();

}
