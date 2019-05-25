package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import javax.swing.text.html.Option;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
                .mapToDouble(IPayable::payment)
                .sum();
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getBasicUnitOfTime = getBasicUnitOfTime;
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
        workingTime.setBasicUnitOfTime(getBasicUnitOfTime);
        this.workingTimes.add(workingTime);
    }
}
