package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;


public final class EmployeePaySheet implements IEmployeePaySheet {

    private final List<IPayable> workingTimes;
    private final String employeeName;
    private final BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;

    private EmployeePaySheet(Builder builder) {
        this.employeeName = builder.employeeName;
        this.getNumbersOfUnitsOfTimeWorked = builder.getNumbersOfUnitsOfTimeWorked;
        workingTimes = builder.workingTimes;
    }

    @Override
    public Double payment() {
        return workingTimes.stream()
                .mapToDouble(IPayable::payment)
                .sum();
    }

    @Override
    public String employeeName() {
        return employeeName;
    }


    public static class Builder {
        private final String employeeName;
        private BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;
        private List<IPayable> workingTimes;

        public Builder(String employeeName) {
            this.employeeName = employeeName;
            this.getNumbersOfUnitsOfTimeWorked = TypeBasicUnitOfTime.HOUR::basicUnitOfTime;
            this.workingTimes = new ArrayList<>();
        }

        public Builder numbersOfUnitsOfTimeWorked(BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked) {
            this.getNumbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked;
            return this;
        }

        public Builder addWorkingTime(IPayable... workingTimes) {
            Arrays.stream(workingTimes)
                    .map(Optional::ofNullable)
                    .forEach(this::addWorkingTime);
            return this;
        }

        private void addWorkingTime(Optional<IPayable> workingTimeOptional) {
            IPayable workingTime = workingTimeOptional.orElseThrow(IllegalArgumentException::new);
            this.workingTimes.add(workingTime);
        }

        public EmployeePaySheet build() {
            return new EmployeePaySheet(this);
        }
    }
}
