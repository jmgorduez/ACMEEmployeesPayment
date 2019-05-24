package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;

class WorkingTimeTest {

    private WorkingTime workingTimeUnderTest;

    @BeforeEach
    void setUp(){
        workingTimeUnderTest = new WorkingTime(_09_00, _12_00);
    }

    @Test
    void payment() {
        workingTimeUnderTest = new WorkingTime(_09_00, _12_00, TestDataGenerator::numberOfHours,
                TestDataGenerator::_15_USD_Per_Hours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_45_USD);
        workingTimeUnderTest = new WorkingTime(_09_00, _12_00, TestDataGenerator::numberOfHours,
                TestDataGenerator::_20_USD_Per_Hours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_60_USD);
        workingTimeUnderTest = new WorkingTime(_09_00, _11_30, TestDataGenerator::numberOfHours,
                TestDataGenerator::_20_USD_Per_Hours);
        assertThat(workingTimeUnderTest.payment())
                .isEqualByComparingTo(_50_USD);
    }

    @Test
    void setBasicUnitOfTime(){
        BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTimeExpected = TestDataGenerator::numberOfHours;
        workingTimeUnderTest.setBasicUnitOfTime(getBasicUnitOfTimeExpected);
        assertThat(workingTimeUnderTest.getBasicUnitOfTime)
                .isEqualTo(getBasicUnitOfTimeExpected);
    }
}