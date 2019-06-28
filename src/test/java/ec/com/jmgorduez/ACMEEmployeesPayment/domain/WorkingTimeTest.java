package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.function.BiFunction;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;

class WorkingTimeTest {

    private WorkingTime workingTimeUnderTest;

    @BeforeEach
    void setUp() {
        workingTimeUnderTest = new WorkingTime.Builder(_09_00, _12_00)
                .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
                .build();
    }

    @Test
    void payment() {
        workingTimeUnderTest = new WorkingTime.Builder(_09_00, _12_00)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
                .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
                .build();
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_45_USD);
        workingTimeUnderTest = new WorkingTime.Builder(_09_00, _12_00)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
                .howMuchToPayFor(TestDataGenerator::_20_USD_Per_Hours)
                .build();
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_60_USD);
        workingTimeUnderTest = new WorkingTime.Builder(_09_00, _11_30)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
                .howMuchToPayFor(TestDataGenerator::_20_USD_Per_Hours)
                .build();
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_50_USD);
    }
}