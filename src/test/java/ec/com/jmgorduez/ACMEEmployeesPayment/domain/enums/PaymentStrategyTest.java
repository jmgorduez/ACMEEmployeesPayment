package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentStrategyTest {

    @Test
    void paymentStrategy() {
        assertThat(_15_USD_PER_UNIT_OF_TIME.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_45_USD);
        assertThat(_20_USD_PER_UNIT_OF_TIME.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_60_USD);
        assertThat(_25_USD_PER_UNIT_OF_TIME.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_75_USD);
        assertThat(_30_USD_PER_UNIT_OF_TIME.paymentStrategy(_3_HOURS))
                .isEqualByComparingTo(_90_USD);
    }
}