package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum PaymentStrategy {
    MO_FR_00_01_09_00(basicUnitOfTime -> _25*basicUnitOfTime.doubleValue()),
    MO_FR_09_01_18_00(basicUnitOfTime -> _15*basicUnitOfTime.doubleValue()),
    MO_FR_18_01_00_00(basicUnitOfTime -> _20*basicUnitOfTime.doubleValue()),
    SA_SU_00_01_09_00(basicUnitOfTime -> _30*basicUnitOfTime.doubleValue()),
    SA_SU_09_01_18_00(basicUnitOfTime -> _20*basicUnitOfTime.doubleValue()),
    SA_SU_18_01_00_00(basicUnitOfTime -> _25*basicUnitOfTime.doubleValue());
    
    private final Function<Float, Double> paymentStrategy;

    PaymentStrategy(Function<Float, Double> paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Double paymentStrategy(Float basicUnitOfTime){
        return paymentStrategy.apply(basicUnitOfTime);
    }
}
