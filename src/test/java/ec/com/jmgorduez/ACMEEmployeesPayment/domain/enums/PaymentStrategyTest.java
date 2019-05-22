package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentStrategyTest {

    @Test
    void paymentStrategy() {
        assertThat(PaymentStrategy.MO_FR_00_01_09_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_75_USD);
        assertThat(PaymentStrategy.MO_FR_09_01_18_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_45_USD);
        assertThat(PaymentStrategy.MO_FR_18_01_00_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_60_USD);
        assertThat(PaymentStrategy.SA_SU_00_01_09_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_90_USD);
        assertThat(PaymentStrategy.SA_SU_09_01_18_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_60_USD);
        assertThat(PaymentStrategy.SA_SU_18_01_00_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_75_USD);
    }
}