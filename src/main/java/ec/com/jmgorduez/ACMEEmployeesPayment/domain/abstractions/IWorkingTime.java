package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface IWorkingTime extends IPayable {

    void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime);

    void setPaymentStrategy(Function<Float, Double> getPaymentStrategy);
}
