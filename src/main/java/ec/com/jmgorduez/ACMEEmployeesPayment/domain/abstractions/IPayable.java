package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.time.LocalTime;
import java.util.function.BiFunction;

public interface IPayable {
    Double payment();
    void setBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime);
}
