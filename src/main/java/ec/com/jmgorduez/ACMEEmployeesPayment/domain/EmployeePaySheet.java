package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EmployeePaySheet implements IEmployeePaySheet {

    List<IWorkingTime> workingTimes;
    private final String employeeName;
    private BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;

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
    public String employeeName() {
        return employeeName;
    }

    @Override
    public void addWorkingTime(IWorkingTime workingTime) {
        workingTime.setBasicUnitOfTime(getBasicUnitOfTime);
        workingTimes.add(workingTime);
    }
}
