package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkingTimeParserTest {

    private WorkingTimeParser workingTimeParserUnderTest;

    @BeforeEach
    void setUp() {
        workingTimeParserUnderTest = new WorkingTimeParser(LocalTime::parse);
    }

    @Test
    void parseWorkingTime() {
        assertThat(workingTimeParserUnderTest.parseWorkingTime(MO_10_00_12_00_STRING))
                .isEqualToIgnoringGivenFields(MO_10_00_12_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(TH_12_00_14_00_STRING))
                .isEqualToIgnoringGivenFields(TH_12_00_14_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(SU_20_00_21_00_STRING))
                .isEqualToIgnoringGivenFields(SU_20_00_21_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThatThrownBy(() -> workingTimeParserUnderTest.parseWorkingTime(MO_00_00_12_00_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void parseWorkingTimes() {
        Queue<IPayable> result
                = new ArrayDeque<>(Arrays.asList(workingTimeParserUnderTest.parseWorkingTimes(MO_10_00_12_00_STRING)));
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_10_00_12_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        result = new ArrayDeque<>(Arrays.asList(workingTimeParserUnderTest.parseWorkingTimes(MO_00_00_12_00_STRING)));
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_00_00_09_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_09_00_12_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
    }
}