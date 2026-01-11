package employeeClassHierarchy;

import java.math.BigDecimal;

public class Employees {

    private String employeeName;
    private BigDecimal employeeSalary;
    private String jobTitle;
    private String jobDuty;
    private BigDecimal yearlyBonusRate;

    private Employees(){
    }

    public Employees(String employeeName, BigDecimal employeeSalary, String jobTitle, String jobDuty, BigDecimal yearlyBonusRate){
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.jobTitle = jobTitle;
        this.jobDuty = jobDuty;
        this.yearlyBonusRate = yearlyBonusRate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public BigDecimal getYearlyBonusAmount(){
        return employeeSalary.multiply(yearlyBonusRate.divide(new BigDecimal("100")));
    }

}
