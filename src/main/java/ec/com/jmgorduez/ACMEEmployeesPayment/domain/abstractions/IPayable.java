package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.time.LocalTime;
import java.util.function.BiFunction;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public interface IPayable {
    Double payment();
}
