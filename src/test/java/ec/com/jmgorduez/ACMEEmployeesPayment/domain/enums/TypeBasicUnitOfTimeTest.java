package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TypeBasicUnitOfTimeTest {

    @Test
    void basicUnitOfTime() {
        assertThat(HOUR.basicUnitOfTime(_09_01, _12_01))
            .isEqualByComparingTo(_3_HOURS);
        assertThat(HOUR.basicUnitOfTime(_09_01, _11_31))
                .isEqualByComparingTo(_2_HOURS_30_MINUTES);
    }
}