package employeeClassHierarchy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Assignment2Playground {
    public static void main(String[] args){
        isPalindrome("abba");
        isPalindrome("abcd");

        Manager manager1 = new Manager("Manager1", new BigDecimal("200000"));
        Manager manager2 = new Manager("Manager2", new BigDecimal("190000"));
        Manager manager3 = new Manager("Manager3", new BigDecimal("180000"));

        Developer developer1 = new Developer("Developer1", new BigDecimal("150000"));
        Developer developer2 = new Developer("Developer2", new BigDecimal("140000"));
        Developer developer3 = new Developer("Developer3", new BigDecimal("130000"));
        Developer developer4 = new Developer("Developer4", new BigDecimal("110000"));

        Tester tester1 = new Tester("Tester1", new BigDecimal("80000"));
        Tester tester2 = new Tester("Tester2", new BigDecimal("70000"));
        Tester tester3 = new Tester("Tester3", new BigDecimal("60000"));
        Tester tester4 = new Tester("Tester4", new BigDecimal("50000"));

        System.out.println(manager1.getYearlyBonusAmount());

        manager1.addSubOrdinates(developer2);
        manager1.addSubOrdinates(developer1);
        manager1.addSubOrdinates(tester4);

        manager2.addSubOrdinates(developer3);
        manager2.addSubOrdinates(tester2);
        manager2.addSubOrdinates(tester3);

        manager3.addSubOrdinates(developer4);
        manager3.addSubOrdinates(tester1);

        Employees[] managers = new Employees[]{manager1,manager2,manager3};
        Employees[] developers = new Employees[]{developer1,developer2,developer3,developer4};
        Employees[] testers = new Employees[]{tester1,tester2,tester3,tester4};

        ArrayList<String> manager1Subordinates = new ArrayList<>();
        ArrayList<String> manager2Subordinates = new ArrayList<>();
        ArrayList<String> manager3Subordinates = new ArrayList<>();

        for(Employees employee : manager1.getSubordinates()){
            manager1Subordinates.add(employee.getEmployeeName());
        }
        System.out.println(manager1Subordinates);

        for(Employees employee : manager2.getSubordinates()){
            manager2Subordinates.add(employee.getEmployeeName());
        }
        System.out.println(manager2Subordinates);

        for(Employees employee : manager3.getSubordinates()){
            manager3Subordinates.add(employee.getEmployeeName());
        }
        System.out.println(manager3Subordinates);

        BigDecimal managersTotalBonus = BigDecimal.ZERO;
        for(Employees employee : managers){
            managersTotalBonus = managersTotalBonus.add(employee.getYearlyBonusAmount());
        }
        System.out.println("Total Managers Bonus Per Year :"+managersTotalBonus);

        BigDecimal developersTotalBonus = BigDecimal.ZERO;
        for(Employees employee : developers){
            developersTotalBonus = developersTotalBonus.add(employee.getYearlyBonusAmount());
        }
        System.out.println("Total Developers Bonus Per Year :"+developersTotalBonus);


        BigDecimal testersTotalBonus = BigDecimal.ZERO;
        for(Employees employee : testers){
            testersTotalBonus = testersTotalBonus.add(employee.getYearlyBonusAmount());
        }
        System.out.println("Total Testers Bonus Per Year :"+testersTotalBonus);

        System.out.println("Total bonus for all employees: "+(managersTotalBonus.add(developersTotalBonus.add(testersTotalBonus))));
    }

    private static void isPalindrome(String str) {
        String reverseStr = "";
        for (int i = str.length()-1; i >= 0; i--) {
            reverseStr += str.charAt(i);
        }

        if(str.equals(reverseStr))
            System.out.println(str+" is a palindrome.");
        else
            System.out.println(str+" is not a palindrome.");
    }
}
