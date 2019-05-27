package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public class WorkingTime implements IPayable {

    private LocalTime startTime;
    private LocalTime endTime;
    BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;
    Function<Float, Double> howMuchToPayFor;

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked,
                       Function<Float, Double> howMuchToPayFor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getNumbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked;
        this.howMuchToPayFor = howMuchToPayFor;
    }

    public WorkingTime(LocalTime startTime, LocalTime endTime, Function<Float, Double> howMuchToPayFor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getNumbersOfUnitsOfTimeWorked = (start, end) -> start.until(end, MINUTES) / _60;
        this.howMuchToPayFor = howMuchToPayFor;
    }

    @Override
    public Double payment() {
        Float numbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked.apply(startTime, endTime);
        return howMuchToPayFor.apply(numbersOfUnitsOfTimeWorked);
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getNumbersOfUnitsOfTimeWorked = getBasicUnitOfTime;
    }
}
