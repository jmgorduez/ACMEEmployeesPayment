package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
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
                .isEqualToIgnoringGivenFields(MO_10_00_12_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(TH_12_00_14_00_STRING))
                .isEqualToIgnoringGivenFields(TH_12_00_14_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
        assertThat(workingTimeParserUnderTest.parseWorkingTime(SU_20_00_21_00_STRING))
                .isEqualToIgnoringGivenFields(SU_20_00_21_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
        assertThatThrownBy(() -> workingTimeParserUnderTest.parseWorkingTime(MO_00_00_12_00_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void parseWorkingTimes() {
        Queue<IPayable> result
                = new ArrayDeque<>(Arrays.asList(workingTimeParserUnderTest.parseWorkingTimes(MO_10_00_12_00_STRING)));
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_10_00_12_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
        result = new ArrayDeque<>(Arrays.asList(workingTimeParserUnderTest.parseWorkingTimes(MO_00_00_12_00_STRING)));
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_00_00_09_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
        assertThat(result.poll())
                .isEqualToIgnoringGivenFields(MO_09_00_12_00, GET_NUMBERS_OF_UNITS_OF_TIME_WORKED, HOW_MUCH_TO_PAY_FOR);
    }

    @Test
    void separateTimes() {
        Queue<String> result
                = workingTimeParserUnderTest.separateInDefinedTimes(MO_00_00_12_00_STRING);
        assertThat(result)
                .contains(_00_00.toString(), _09_00.toString(), _12_00.toString());
        assertThat(result.size())
                .isEqualTo(THREE);
    }

    @Test
    void separateInDefinedTimes(){
        List<String> result
                = workingTimeParserUnderTest.separateInDefinedWorkingTimes(MO_00_00_12_00_STRING)
                .collect(Collectors.toList());
        assertThat(result)
                .contains(MO_00_00_09_00_STRING, MO_09_00_12_00_STRING);
        assertThat(result.size())
                .isEqualTo(TWO);
    }
}