package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.function.BiFunction;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EmployeePaySheetTest {

    private EmployeePaySheet employeePaySheetUnderTest;

    @BeforeEach
    void setUp() {
        employeePaySheetUnderTest = new EmployeePaySheet(ASTRID,
                TestDataGenerator::numberOfHours);
    }

    @Test
    void payment() {
        IPayable workingTime = mockWorkingTime();
        when(workingTime.payment()).thenReturn(_37_USD_50_c);
        employeePaySheetUnderTest.addWorkingTime(workingTime);
        workingTime = mockWorkingTime();
        when(workingTime.payment()).thenReturn(_50_USD);
        employeePaySheetUnderTest.addWorkingTime(workingTime);
        assertThat(employeePaySheetUnderTest.payment())
                .isEqualByComparingTo(_87_USD_50_c);
    }

    @Test
    void employeeName() {
        assertThat(employeePaySheetUnderTest.employeeName())
                .isEqualTo(ASTRID);
    }

    @Test
    void addWorkingTime() {
        assertThat(employeePaySheetUnderTest.workingTimes.isEmpty())
                .isTrue();
        employeePaySheetUnderTest.addWorkingTime(mockWorkingTime());
        assertThat(employeePaySheetUnderTest.workingTimes.size())
                .isEqualTo(ONE);
    }

    @Test
    void addWorkingTimeVerifyIfSetBasicUnitOfTimeIsCalled() {
        IPayable workingTime = mockWorkingTime();
        employeePaySheetUnderTest.addWorkingTime(workingTime);
        verify(workingTime, times(ONE)).setBasicUnitOfTime(any());
    }

    @Test
    void setBasicUnitOfTime(){
        BiFunction<LocalTime, LocalTime, Float> getBasicUnitOfTimeExpected
                = TestDataGenerator::numberOfHours;
        employeePaySheetUnderTest.setBasicUnitOfTime(getBasicUnitOfTimeExpected);
        assertThat(employeePaySheetUnderTest.getBasicUnitOfTime)
                .isEqualTo(getBasicUnitOfTimeExpected);
    }


    private IPayable mockWorkingTime(){
        return mock(IPayable.class);
    }
}