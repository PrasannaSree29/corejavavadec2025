package com.eureka.inheritance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountsListPlayground {
    public static void main(String[] args) {

        List<CheckingAccount> checkingAccountList= new ArrayList<>();
        CheckingAccount checkingAccount1 = new CheckingAccount("CHK1",new BigDecimal("100"));
        CheckingAccount checkingAccount2 = new CheckingAccount("CHK2",new BigDecimal("200"));

        checkingAccountList.add(checkingAccount1);
        checkingAccountList.add(checkingAccount2);

        checkingAccount1.deposit(new BigDecimal(10));
        checkingAccount1.withDraw(new BigDecimal("10"));
        checkingAccount2.deposit(new BigDecimal(10));
        checkingAccount2.withDraw(new BigDecimal("10"));

        List<SavingsAccount> savingsAccountList = new ArrayList<>();
        SavingsAccount savingsAccount1 = new SavingsAccount("SAV1",new BigDecimal("100"));
        SavingsAccount savingsAccount2 = new SavingsAccount("SAV2",new BigDecimal("200"));

        savingsAccountList.add(savingsAccount1);
        savingsAccountList.add(savingsAccount2);

        savingsAccount1.deposit(new BigDecimal("15"));
        savingsAccount1.withDraw(new BigDecimal("15"));
        savingsAccount2.deposit(new BigDecimal("15"));
        savingsAccount2.withDraw(new BigDecimal("15"));

       //calculateBankAccountTotals(checkingAccountList);

       List<Account> accountList = new ArrayList<>();
       accountList.add(checkingAccount1);// able to add subclass objects to listt referance of superclass
        accountList.add(checkingAccount2);
        accountList.add(savingsAccount1);
        accountList.add(savingsAccount2);

        calculateBankAccountTotals(accountList);

        anotherCalculateBankAccountTotals(checkingAccountList);
        anotherCalculateBankAccountTotals(savingsAccountList);
        anotherCalculateBankAccountTotals(accountList);


    }

    /**
     * Method that calculates the totals of account balances sent in the input list
     * (?extends account) is a generic definition which specifies that the list can have objects of type account or its subclasses
     * (?super account) is a generic definition which specifies that the list can have objects of type account or its subclasses
     * @param accountList - accepts list containg objects of type accounts or its subclasses
     */


private static void anotherCalculateBankAccountTotals(List<? extends Account> accountList){

    BigDecimal totalBankBalance = BigDecimal.ZERO;
    for(Account eachAccount : accountList){
        totalBankBalance = totalBankBalance.add(eachAccount.getAccountBalance());
    }
    System.out.println("total bank balance from another method is "+totalBankBalance);
}

    /**
     * Method that calculates the totals of account balances sent in the input list
     * @param accountList - acceptsa list containg objects of type accounts only
     */


    public static void calculateBankAccountTotals(List<Account> accountList){
        BigDecimal totalBanksBalance = BigDecimal.ZERO;
        for (Account eachAccount : accountList) {
            totalBanksBalance=totalBanksBalance.add(eachAccount.getAccountBalance());
        }
        System.out.println("Total Bank Balance is "+totalBanksBalance);

    }

}
