package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.EmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.WorkingTime;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayableParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._09_00;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeSalaryCalculatorFactoryTest {

    private EmployeeSalaryCalculatorFactory employeeSalaryCalculatorFactoryUnderTest;

    @BeforeEach
    void setUp() {
        employeeSalaryCalculatorFactoryUnderTest = new EmployeeSalaryCalculatorFactory();
    }

    @Test
    void employeeSalaryCalculator() {
        assertThat(employeeSalaryCalculatorFactoryUnderTest.employeeSalaryCalculator())
                .isEqualToIgnoringGivenFields(
                        new EmployeeSalaryCalculator(this::employeePaySheetParser),
                        PARSE_EMPLOYEE_PAY_SHEET);
    }

    private IEmployeePaySheet employeePaySheetParser(String value) {
        return new EmployeePaySheet(ASTRID);
    }
}