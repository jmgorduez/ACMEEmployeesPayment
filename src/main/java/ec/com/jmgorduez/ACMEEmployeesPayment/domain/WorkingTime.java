package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTime;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    @Override
    public Double payment() {
        return paymentStrategy.apply(getBasicUnitOfTime.apply(startTime, endTime));
    }

    @Override
    public void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {

    }

    @Override
    public void setPaymentStrategy(Function<Float, Double> paymentStrategy) {

    }
}
