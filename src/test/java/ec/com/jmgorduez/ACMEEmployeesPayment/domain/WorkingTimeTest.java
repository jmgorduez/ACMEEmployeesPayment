package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;

class WorkingTimeTest {

    private WorkingTime workingTimeUnderTest;

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
}