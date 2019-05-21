package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.time.LocalTime;
import java.util.function.BiFunction;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._60;
import static java.time.temporal.ChronoUnit.MINUTES;

public enum TypeBasicUnitOfTime {
    HOUR((start, end) -> start.until(end, MINUTES) / _60);

    private final BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime;

    TypeBasicUnitOfTime(BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTime) {
        this.getBasicUnitOfTime = getBasicUnitOfTime;
    }

    public final Float basicUnitOfTime(LocalTime start, LocalTime end){
        return getBasicUnitOfTime.apply(start, end);
    }
}
