package employeeClassHierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employees{

    private static final BigDecimal yearlyBonusRate = new BigDecimal("20.0");
    private static final String jobTitle = "Manager";
    private static final String jobDuty = "Managing people";

    private List<Employees> subordinates= new ArrayList<>();

    public Manager(String employeeName, BigDecimal employeeSalary) {
        super(employeeName, employeeSalary, Manager.jobTitle, Manager.jobDuty, Manager.yearlyBonusRate);
    }

    public void addSubOrdinates(Employees employee){
        subordinates.add(employee);
    }

    public List<Employees> getSubordinates() {
        return subordinates;
    }
}