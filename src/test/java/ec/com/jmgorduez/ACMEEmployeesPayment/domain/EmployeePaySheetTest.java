package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeePaySheetTest {

    private EmployeePaySheet employeePaySheetUnderTest;

    @BeforeEach
    void setUp() {
        employeePaySheetUnderTest = new EmployeePaySheet.Builder(ASTRID)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
                .build();
    }

    @Test
    void payment() {
        EmployeePaySheet.Builder builder = new EmployeePaySheet.Builder(ASTRID)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours);
        IPayable workingTime = mockWorkingTime();
        when(workingTime.payment()).thenReturn(_37_USD_50_c);
        builder.addWorkingTime(workingTime);
        workingTime = mockWorkingTime();
        when(workingTime.payment()).thenReturn(_50_USD);
        builder.addWorkingTime(workingTime);
        employeePaySheetUnderTest = builder.build();
        assertThat(employeePaySheetUnderTest.payment())
                .isEqualByComparingTo(_87_USD_50_c);
    }

    @Test
    void employeeName() {
        assertThat(employeePaySheetUnderTest.employeeName())
                .isEqualTo(ASTRID);
    }

    private IPayable mockWorkingTime() {
        return mock(IPayable.class);
    }
}