package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class EmployeePaySheet implements IEmployeePaySheet {

    List<IPayable> workingTimes;
    private final String employeeName;
    BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;

    public EmployeePaySheet(String employeeName,
                            BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked){
        this.employeeName = employeeName;
        workingTimes = new ArrayList<>();
        this.getNumbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked;
    }

    @Override
    public Double payment() {
        return workingTimes.stream()
                .mapToDouble(IPayable::payment)
                .sum();
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getNumbersOfUnitsOfTimeWorked = getBasicUnitOfTime;
    }

    @Override
    public String employeeName() {
        return employeeName;
    }

    @Override
    public void addWorkingTime(IPayable... workingTimes) {
        Arrays.stream(workingTimes)
                .map(Optional::ofNullable)
                .forEach(this::addWorkingTime);

    }

    private void addWorkingTime(Optional<IPayable> workingTimeOptional){
        IPayable workingTime = workingTimeOptional.orElseThrow(IllegalArgumentException::new);
        workingTime.setBasicUnitOfTime(getNumbersOfUnitsOfTimeWorked);
        this.workingTimes.add(workingTime);
    }
}
