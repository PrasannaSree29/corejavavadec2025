package com.eureka.inheritance;

import java.math.BigDecimal;

public class AccountsPlayground {
    public static void main(String[] args){
        CheckingAccount checkingAccount1 = new CheckingAccount("CHK1",new BigDecimal("100"));
        checkingAccount1.deposit(new BigDecimal("10"));
        System.out.println("Balance after deposit is "+checkingAccount1.deposit(new BigDecimal("20")));
        System.out.println("balance after withdrawl in checking Account "+checkingAccount1.getAccountNumber()+
                "is "+checkingAccount1.deposit(new BigDecimal("20")));
        System.out.println("Balance after withdrawl in checking account "+checkingAccount1.getAccountNumber()+
                "is "+checkingAccount1.withDraw(new BigDecimal("10")));

        SavingsAccount savingsAccount1=new SavingsAccount("SAV1",new BigDecimal("100"));
        System.out.println("Balance after deposit in savings account "+savingsAccount1.getAccountNumber()+
                "is "+savingsAccount1.deposit(new BigDecimal("20")));
        System.out.println("Balance after withdrawl in savings account "+savingsAccount1.getAccountNumber()+
                "is "+savingsAccount1.withDraw(new BigDecimal("10")));
        System.out.println("Balance after intrest deposit in savings account "+savingsAccount1.getAccountNumber()+
                "is "+savingsAccount1.depositIntrestIntoAccount());

        //polymorphism- super class referance variable point to a subclass object

        Account checkingAccount2 = new CheckingAccount("CHK2",new BigDecimal("250"));
        Account savingsAccount2 = new SavingsAccount("SAV2",new BigDecimal("380"));

        checkingAccount2.deposit(new BigDecimal("15"));
        checkingAccount2.withDraw(new BigDecimal("15"));
        System.out.println("Balance in checking account "+checkingAccount2.getAccountNumber()+" is "+checkingAccount1.getAccountBalance());

        savingsAccount2.deposit(new BigDecimal("15"));
        savingsAccount2.withDraw(new BigDecimal("15"));
        System.out.println("Balance in Savings account "+savingsAccount2.getAccountNumber()+" is "+savingsAccount2.getAccountBalance());

        //BigDecimal testBD=new BigDecimal("100");

        System.out.println("Is checking account2 an Account ?"+(checkingAccount2 instanceof Account));
        System.out.println("Is checking account1 an Account ?"+(checkingAccount1 instanceof Account));
//        System.out.println("Is Bigdecimal an Account ?"+(testBD instanceof Account));
// this is not even compiling because bigdecimal is not part of account

        checkingAccount1.printAccountDetails();
        checkingAccount2.printAccountDetails();
        savingsAccount1.printAccountDetails();
        savingsAccount2.printAccountDetails();


        //this only works because of javas polymorphism
        Account[] accountsArray = new Account[]{checkingAccount1,checkingAccount2,savingsAccount1,savingsAccount2};
        calculateTotalBankDepositBalance(accountsArray);

    }

    /**
     * Method that takes an account array and calculated the sum of the all bank balances
     * @param accountsArray that contains all checking and saving accounts in the bank-polymorphism in action
     */
    private static void calculateTotalBankDepositBalance(Account[] accountsArray){
        BigDecimal totalBankBalance= BigDecimal.ZERO;
        for (Account eachAccount : accountsArray) {
            totalBankBalance=totalBankBalance.add(eachAccount.getAccountBalance());
        }
        System.out.println("total bank deposit balance is "+totalBankBalance);
    }

}

