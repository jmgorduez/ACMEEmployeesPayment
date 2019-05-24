package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.EmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

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