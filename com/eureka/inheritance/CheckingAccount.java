package com.eureka.inheritance;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

    public static final BigDecimal cashBackrate = new BigDecimal("2.0");

    public CheckingAccount(String accountNumber, BigDecimal accountBalance){
        super(accountNumber, accountBalance);
    }

    /**
     * custom overided withdraw method that implements cashback
     * @param withDrawlAmount
     * @return
     */
    @Override
    public BigDecimal withDraw(BigDecimal withDrawlAmount){
        BigDecimal cashBackAmount = withDrawlAmount.multiply(cashBackrate.divide(new BigDecimal("100")));
        accountBalance=accountBalance.subtract(withDrawlAmount);
        super.deposit(cashBackAmount);
        return getAccountBalance();
    }

    @Override
    void printAccountDetails(){
        System.out.println("Checking Account #: "+getAccountNumber()+" Account balance :"+getAccountBalance()+
                ", Cashback rate :"+cashBackrate);
    }


}
