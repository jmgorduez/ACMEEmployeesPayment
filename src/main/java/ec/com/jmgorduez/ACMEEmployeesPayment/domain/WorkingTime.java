package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.time.LocalTime;
import java.util.function.Function;

public class WorkingTime implements IPayable {

    public WorkingTime(LocalTime startTime, LocalTime endTime,
                       Function<Float, Float> paymentStrategy){
    }

    @Override
    public Integer payment() {
        return null;
    }
}
