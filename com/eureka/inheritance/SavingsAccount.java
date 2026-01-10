package com.eureka.inheritance;

import java.math.BigDecimal;

public class SavingsAccount extends Account{


    private static final BigDecimal savingsAPR=new BigDecimal("3.0");

    public SavingsAccount(String accountNumber, BigDecimal accountBalance) {
        super(accountNumber, accountBalance);//calling super class accounts parameterrized constructer
    }

    @Override
    void printAccountDetails(){
        System.out.println("savinging Account #: "+getAccountNumber()+" Account balance :"+getAccountBalance()+
                ", Intrest APR :"+savingsAPR);
    }

    /**
     *Method that isused to calculate intrest amount to be deposited each month into the savings account.
     * @return accountBalance
     */
    public BigDecimal depositIntrestIntoAccount(){
        BigDecimal intrestAmount = accountBalance.multiply(savingsAPR.divide(new BigDecimal("100")));
        super.deposit(intrestAmount);
        return getAccountBalance();
    }
}
