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
    BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;
    Function<Float, Double> getPaymentStrategy;

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime,
                       Function<Float, Double> getPaymentStrategy) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getBasicUnitOfTime = getBasicUnitOfTime;
        this.getPaymentStrategy = getPaymentStrategy;
    }

    public WorkingTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getBasicUnitOfTime = (start, end) -> start.until(end, MINUTES) / _60;
        this.getPaymentStrategy = Float::doubleValue;
    }

    @Override
    public Double payment() {
        return getPaymentStrategy.apply(getBasicUnitOfTime.apply(startTime, endTime));
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getBasicUnitOfTime = getBasicUnitOfTime;
    }
}
