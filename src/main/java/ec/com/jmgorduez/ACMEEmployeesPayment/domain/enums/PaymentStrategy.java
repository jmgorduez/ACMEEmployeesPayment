package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum PaymentStrategy {
    _15_USD_PER_UNIT_OF_TIME(PaymentStrategy::_15_USD_Per_Unit_Of_Time),
    _20_USD_PER_UNIT_OF_TIME(PaymentStrategy::_20_USD_Per_Unit_Of_Time),
    _25_USD_PER_UNIT_OF_TIME(PaymentStrategy::_25_USD_Per_Unit_Of_Time),
    _30_USD_PER_UNIT_OF_TIME(PaymentStrategy::_30_USD_Per_Unit_Of_Time);
    
    private final Function<Float, Double> paymentStrategy;

    PaymentStrategy(Function<Float, Double> paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
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
