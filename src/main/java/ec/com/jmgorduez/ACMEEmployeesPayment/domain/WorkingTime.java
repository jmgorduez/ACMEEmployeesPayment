package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTime;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public class WorkingTime implements IWorkingTime {

    private LocalTime startTime;
    private LocalTime endTime;
    BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;
    Function<Float, Double> paymentStrategy;

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime,
                       Function<Float, Double> paymentStrategy) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getBasicUnitOfTime = getBasicUnitOfTime;
        this.paymentStrategy = paymentStrategy;
    }

    public WorkingTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getBasicUnitOfTime = (start, end) -> start.until(end, MINUTES) / _60;
        this.paymentStrategy = Float::doubleValue;
    }

    @Override
    public Double payment() {
        return paymentStrategy.apply(getBasicUnitOfTime.apply(startTime, endTime));
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getBasicUnitOfTime = getBasicUnitOfTime;
    }

    @Override
    public void setPaymentStrategy(Function<Float, Double> paymentStrategy) {

    }
}
