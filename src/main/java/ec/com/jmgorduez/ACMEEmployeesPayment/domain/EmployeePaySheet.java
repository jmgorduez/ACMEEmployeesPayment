package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.List;

public class EmployeePaySheet implements IEmployeePaySheet {

    private List<IPayable> workingTimes;

    public EmployeePaySheet(String employeeName){

    }

    @Override
    public Float payment() {
        return null;
    }

    @Override
    public String employeeName() {
        return null;
    }

    @Override
    public void addPayableItem(IPayable workingTime) {

    }
}
