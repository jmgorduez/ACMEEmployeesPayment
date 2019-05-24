package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        employeePaySheetUnderTest.addWorkingTime(new WorkingTime(_09_00, _11_30, TestDataGenerator::numberOfHours,
                TestDataGenerator::_15_USD_Per_Hours));
        employeePaySheetUnderTest.addWorkingTime(new WorkingTime(_09_00, _11_30, TestDataGenerator::numberOfHours,
                TestDataGenerator::_20_USD_Per_Hours));
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


    private IPayable mockWorkingTime(){
        return mock(IPayable.class);
    }
}