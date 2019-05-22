package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

public class WorkingTime implements IPayable {

    private LocalTime startTime;
    private LocalTime endTime;
    private BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;
    private Function<Float, Double> paymentStrategy;

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime,
                       Function<Float, Double> paymentStrategy) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.getBasicUnitOfTime = getBasicUnitOfTime;
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public Double payment() {
        return paymentStrategy.apply(getBasicUnitOfTime.apply(startTime, endTime));
    }
}
