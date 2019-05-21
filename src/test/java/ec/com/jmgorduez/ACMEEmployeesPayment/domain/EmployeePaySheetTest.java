package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
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