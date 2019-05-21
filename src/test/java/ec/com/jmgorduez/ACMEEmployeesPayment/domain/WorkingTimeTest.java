package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

class WorkingTimeTest {

    private WorkingTime workingTimeUnderTest;

    @Test
    void payment() {
        workingTimeUnderTest = new WorkingTime(_09_01, _12_01, workedHours -> _15*workedHours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualTo(_45);
        workingTimeUnderTest = new WorkingTime(_09_01, _12_01, workedHours -> _20*workedHours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualTo(_60);
        workingTimeUnderTest = new WorkingTime(_09_01, _11_31, workedHours -> _20*workedHours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualTo(_50);
    }
}