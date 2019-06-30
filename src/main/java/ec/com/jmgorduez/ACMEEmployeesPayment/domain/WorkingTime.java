package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._15;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public class WorkingTime implements IPayable {

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;
    private final Function<Float, Double> howMuchToPayFor;

    private WorkingTime(Builder builder) {
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.getNumbersOfUnitsOfTimeWorked = builder.getNumbersOfUnitsOfTimeWorked;
        this.howMuchToPayFor = builder.howMuchToPayFor;
    }

    @Override
    public Double payment() {
        Float numbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked.apply(startTime, endTime);
        return howMuchToPayFor.apply(numbersOfUnitsOfTimeWorked);
    }

    public static class Builder {
        private LocalTime startTime;
        private LocalTime endTime;
        private BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;
        private Function<Float, Double> howMuchToPayFor;

        public Builder(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.getNumbersOfUnitsOfTimeWorked = (start, end) -> start.until(end, MINUTES) / _60;
            this.howMuchToPayFor = amountHour -> Double.valueOf(amountHour*_15);
        }

        public Builder numbersOfUnitsOfTimeWorked(BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked) {
            this.getNumbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked;
            return this;
        }

        public Builder howMuchToPayFor(Function<Float, Double> howMuchToPayFor) {
            this.howMuchToPayFor = howMuchToPayFor;
            return this;
        }

        public WorkingTime build() {
            return new WorkingTime(this);
        }
    }
}
