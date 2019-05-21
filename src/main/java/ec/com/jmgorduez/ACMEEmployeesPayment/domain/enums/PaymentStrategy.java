package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.util.function.Function;

public enum PaymentStrategy {
    MO_FR_00_01_09_00,
    MO_FR_09_01_18_00,
    MO_FR_18_01_00_00,
    SA_SU_00_01_09_00,
    SA_SU_09_01_18_00,
    SA_SU_18_01_00_00;
    
    private final Function<Integer, Integer> paymentStrategy = null;
}
