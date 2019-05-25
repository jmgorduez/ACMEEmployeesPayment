package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class WorkingTimeParserTest {

    private WorkingTimeParser workingTimeParserUnderTest;

    @BeforeEach
    void setUp() {
        workingTimeParserUnderTest = new WorkingTimeParser(LocalTime::parse);
    }

    @Test
    void parse() {
        assertThat(workingTimeParserUnderTest.parseWorkingTime(MO_10_00_12_00_STRING))
                .isEqualToIgnoringGivenFields(MO_10_00_12_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(TH_12_00_14_00_STRING))
                .isEqualToIgnoringGivenFields(TH_12_00_14_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(SU_20_00_21_00_STRING))
                .isEqualToIgnoringGivenFields(SU_20_00_21_00, GET_BASIC_UNIT_OF_TIME, PAYMENT_STRATEGY);
    }

    @Test
    void parseCompactWorkingTime(){
        assertThat(workingTimeParserUnderTest.parseCompactWorkingTime(RENE_MO_00_00_12_00_TU_10_00_12_00))
                .contains(MO_00_00_09_00, MO_09_00_12_00, TU_10_00_12_00);
    }
}