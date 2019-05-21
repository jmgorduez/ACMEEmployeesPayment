package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.ArrayList;
import java.util.List;

public class EmployeePaySheet implements IEmployeePaySheet {

    List<IPayable> workingTimes;
    private final String employeeName;

    public EmployeePaySheet(String employeeName){
        this.employeeName = employeeName;
        workingTimes = new ArrayList<>();
    }

    @Override
    public Double payment() {
        return workingTimes.stream()
                .map(IPayable::payment).mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public String employeeName() {
        return employeeName;
    }

    @Override
    public void addPayableItem(IPayable workingTime) {
        workingTimes.add(workingTime);
    }
}
