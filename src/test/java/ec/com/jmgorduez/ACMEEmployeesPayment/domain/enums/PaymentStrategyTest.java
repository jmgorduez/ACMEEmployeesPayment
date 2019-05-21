package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator._3_HOURS;
import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator._75_USD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentStrategyTest {

    @Test
    void paymentStrategy() {
        assertThat(PaymentStrategy.MO_FR_00_01_09_00.paymentStrategy(_3_HOURS))
        .isEqualByComparingTo(_75_USD);
    }
}