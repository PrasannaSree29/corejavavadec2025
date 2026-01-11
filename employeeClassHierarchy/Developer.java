package employeeClassHierarchy;

import java.math.BigDecimal;

public class Developer extends Employees{

    private static final BigDecimal yearlyBonusRate = new BigDecimal("10.0");
    private static final String jobTitle = "Developer";
    private static final String jobDuty = "Build Applications";

    public Developer(String employeeName, BigDecimal employeeSalary) {
        super(employeeName, employeeSalary, Developer.jobTitle, Developer.jobDuty, Developer.yearlyBonusRate);
    }
}
