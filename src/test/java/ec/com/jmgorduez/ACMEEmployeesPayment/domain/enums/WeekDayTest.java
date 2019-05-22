package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekDayTest {

    @Test
    void parse() {
        assertThat(WeekDay.parse(MO))
                .isEqualTo(MONDAY);
        assertThat(WeekDay.parse(TU))
                .isEqualTo(TUESDAY);
        assertThat(WeekDay.parse(WE))
                .isEqualTo(WEDNESDAY);
        assertThat(WeekDay.parse(TH))
                .isEqualTo(THURSDAY);
        assertThat(WeekDay.parse(FR))
                .isEqualTo(FRIDAY);
        assertThat(WeekDay.parse(SA))
                .isEqualTo(SATURDAY);
        assertThat(WeekDay.parse(SU))
                .isEqualTo(SUNDAY);
    }
}