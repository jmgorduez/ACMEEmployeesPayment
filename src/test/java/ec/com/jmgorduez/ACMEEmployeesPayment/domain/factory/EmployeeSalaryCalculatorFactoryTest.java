package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.EmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.ASTRID;
import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.EMPLOYEE_PAY_SHEET_PARSER;
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
                        EMPLOYEE_PAY_SHEET_PARSER);
    }

    private IEmployeePaySheet employeePaySheetParser(String value) {
        return new EmployeePaySheet.Builder(ASTRID)
                .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
                .build();
    }
}