package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum PaymentStrategy {
    MO_FR_00_01_09_00(PaymentStrategy::_25_USD_Per_Unit_Of_Time,
            _00_01, _09_00, MONDAY, THURSDAY, WEDNESDAY, THURSDAY, FRIDAY),
    MO_FR_09_01_18_00(PaymentStrategy::_15_USD_Per_Unit_Of_Time,
            _09_01, _18_00, MONDAY, THURSDAY, WEDNESDAY, THURSDAY, FRIDAY),
    MO_FR_18_01_00_00(PaymentStrategy::_20_USD_Per_Unit_Of_Time,
            _18_01, _00_00, MONDAY, THURSDAY, WEDNESDAY, THURSDAY, FRIDAY),
    SA_SU_00_01_09_00(PaymentStrategy::_30_USD_Per_Unit_Of_Time,
            _00_01, _09_00, SATURDAY, SUNDAY),
    SA_SU_09_01_18_00(PaymentStrategy::_20_USD_Per_Unit_Of_Time,
            _09_01, _18_00, SATURDAY, SUNDAY),
    SA_SU_18_01_00_00(PaymentStrategy::_25_USD_Per_Unit_Of_Time,
            _18_01, _00_00, SATURDAY, SUNDAY);
    
    private final Function<Float, Double> paymentStrategy;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final WeekDay[] relatedWeekDays;

    PaymentStrategy(Function<Float, Double> paymentStrategy,
                    LocalTime startTime, LocalTime endTime,
                    WeekDay... relatedWeekDays) {
        this.paymentStrategy = paymentStrategy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.relatedWeekDays = relatedWeekDays;
    }

    public static PaymentStrategy parse(String value){
        return null;
    }

    public Double paymentStrategy(Float basicUnitOfTime){
        return paymentStrategy.apply(basicUnitOfTime);
    }

    private static Double _15_USD_Per_Unit_Of_Time(Float basicUnitOfTime){
        return _15*basicUnitOfTime.doubleValue();
    }

    private static Double _20_USD_Per_Unit_Of_Time(Float basicUnitOfTime){
        return _20*basicUnitOfTime.doubleValue();
    }

    private static Double _25_USD_Per_Unit_Of_Time(Float basicUnitOfTime){
        return _25*basicUnitOfTime.doubleValue();
    }

    private static Double _30_USD_Per_Unit_Of_Time(Float basicUnitOfTime){
        return _30*basicUnitOfTime.doubleValue();
    }
}
