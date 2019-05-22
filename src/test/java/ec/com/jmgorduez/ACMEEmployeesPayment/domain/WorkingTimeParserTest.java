package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.MO_10_00_12_00;
import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.MO_10_00_12_00_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WorkingTimeParserTest {

    private WorkingTimeParser workingTimeParserUnderTest;

    @BeforeEach
    void setUp() {
        workingTimeParserUnderTest = new WorkingTimeParser();
    }

    @Test
    void parse() {
        assertThat(workingTimeParserUnderTest.parse(MO_10_00_12_00_STRING))
                .isEqualToComparingFieldByField(MO_10_00_12_00);
    }
}