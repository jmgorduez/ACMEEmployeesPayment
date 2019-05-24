package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class EmployeePaySheet implements IEmployeePaySheet {

    List<IPayable> workingTimes;
    private final String employeeName;
    BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;

    public EmployeePaySheet(String employeeName,
                            BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime){
        this.employeeName = employeeName;
        workingTimes = new ArrayList<>();
        this.getBasicUnitOfTime = getBasicUnitOfTime;
    }

    @Override
    public Double payment() {
        return workingTimes.stream()
                .map(IPayable::payment).mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {

    }

    @Override
    public String employeeName() {
        return employeeName;
    }

    @Override
    public void addWorkingTime(IPayable workingTime) {
        workingTime.setBasicUnitOfTime(getBasicUnitOfTime);
        workingTimes.add(workingTime);
    }
}
