package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum PaymentStrategy {
    MO_FR_00_01_09_00(PaymentStrategy::_25_USD_Per_Unit_Of_Time),
    MO_FR_09_01_18_00(PaymentStrategy::_15_USD_Per_Unit_Of_Time),
    MO_FR_18_01_00_00(PaymentStrategy::_20_USD_Per_Unit_Of_Time),
    SA_SU_00_01_09_00(PaymentStrategy::_30_USD_Per_Unit_Of_Time),
    SA_SU_09_01_18_00(PaymentStrategy::_20_USD_Per_Unit_Of_Time),
    SA_SU_18_01_00_00(PaymentStrategy::_25_USD_Per_Unit_Of_Time);
    
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
