package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayableParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

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
        IPayableParser payableParser = new WorkingTimeParser(LocalTime::parse);
        IEmployeePaySheetParser employeePaySheetParser = new EmployeePaySheetParser(payableParser::parse);
        assertThat(employeeSalaryCalculatorFactoryUnderTest.employeeSalaryCalculator())
            .isEqualToComparingFieldByField(new EmployeeSalaryCalculator(employeePaySheetParser::parseEmployeePaySheet));
    }
}