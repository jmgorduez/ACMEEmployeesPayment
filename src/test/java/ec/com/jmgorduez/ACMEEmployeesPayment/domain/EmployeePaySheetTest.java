package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._15;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._20;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeePaySheetTest {

    private EmployeePaySheet employeePaySheetUnderTest;

    @BeforeEach
    void setUp() {
        employeePaySheetUnderTest = new EmployeePaySheet(ASTRID);
    }

    @Test
    void payment() {
        employeePaySheetUnderTest.addPayableItem(new WorkingTime(_09_01, _11_31, workedHours -> _15*workedHours));
        employeePaySheetUnderTest.addPayableItem(new WorkingTime(_09_01, _11_31, workedHours -> _20*workedHours));
        assertThat(employeePaySheetUnderTest.payment())
                .isEqualTo(_87_USD_50_c);
    }

    @Test
    void employeeName() {
        assertThat(employeePaySheetUnderTest.employeeName())
                .isEqualTo(ASTRID);
    }

    @Test
    void addPayableItem() {
        assertThat(employeePaySheetUnderTest.workingTimes.isEmpty())
                .isTrue();
        employeePaySheetUnderTest.addPayableItem(new WorkingTime(_09_01, _11_31, Float::floatValue));
        assertThat(employeePaySheetUnderTest.workingTimes.size())
                .isEqualTo(ONE);
    }
}