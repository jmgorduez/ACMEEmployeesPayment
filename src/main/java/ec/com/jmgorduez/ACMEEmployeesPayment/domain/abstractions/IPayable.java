package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.time.LocalTime;
import java.util.function.BiFunction;

public interface IPayable {
    Double payment();
}
