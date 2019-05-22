package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentStrategyTest {

    @Test
    void paymentStrategy() {
        assertThat(MO_FR_00_01_09_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_75_USD);
        assertThat(MO_FR_09_01_18_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_45_USD);
        assertThat(MO_FR_18_01_00_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_60_USD);
        assertThat(SA_SU_00_01_09_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_90_USD);
        assertThat(SA_SU_09_01_18_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_60_USD);
        assertThat(SA_SU_18_01_00_00.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_75_USD);
    }

    @Test
    void parse(){
        assertThat(PaymentStrategy.parse(MO_10_00_12_00_STRING))
                .isEqualTo(MO_FR_09_01_18_00);
        assertThat(PaymentStrategy.parse(TH_19_00_20_00_STRING))
                .isEqualTo(MO_FR_18_01_00_00);
        assertThat(PaymentStrategy.parse(SU_20_00_21_00_STRING))
                .isEqualTo(SA_SU_18_01_00_00);
        assertThatThrownBy(() -> PaymentStrategy.parse(MO_01_00_21_00_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }
}