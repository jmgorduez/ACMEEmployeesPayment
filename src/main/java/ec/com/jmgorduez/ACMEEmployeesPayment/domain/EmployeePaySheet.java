package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.List;

public class EmployeePaySheet implements IEmployeePaySheet {

    private List<IPayable> workingTimes;
    private final String employeeName;

    public EmployeePaySheet(String employeeName){
        this.employeeName = employeeName;
    }

    @Override
    public Float payment() {
        return null;
    }

    @Override
    public String employeeName() {
        return employeeName;
    }

    @Override
    public void addPayableItem(IPayable workingTime) {

    }
}
