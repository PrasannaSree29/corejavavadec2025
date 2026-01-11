package employeeClassHierarchy;

import java.math.BigDecimal;

public class Tester extends Employees{

    private static final BigDecimal yearlyBonusRate = new BigDecimal("5.0");
    private static final String jobTitle = "Tester";
    private static final String jobDuty = "Testing Applications";

    public Tester(String employeeName, BigDecimal employeeSalary) {
        super(employeeName, employeeSalary, Tester.jobTitle, Tester.jobDuty, Tester.yearlyBonusRate);
    }
}
