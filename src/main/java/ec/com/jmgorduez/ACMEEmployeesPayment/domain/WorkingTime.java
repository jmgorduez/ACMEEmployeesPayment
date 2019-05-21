package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public class WorkingTime implements IPayable {

    private float workedHours;
    private Function<Float, Float> paymentStrategy;

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       Function<Float, Float> paymentStrategy) {
        this.workedHours = startTime.until(endTime, MINUTES) / _60;
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public Double payment() {
        return paymentStrategy.apply(workedHours).doubleValue();
    }
}
