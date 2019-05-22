package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;

class TypeBasicUnitOfTimeTest {

    @Test
    void basicUnitOfTime() {
        assertThat(HOUR.basicUnitOfTime(_09_00, _12_00))
            .isEqualByComparingTo(_3_HOURS);
        assertThat(HOUR.basicUnitOfTime(_09_00, _11_30))
                .isEqualByComparingTo(_2_HOURS_30_MINUTES);
    }
}