package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;

public class EmployeeSalaryCalculatorFactory implements IEmployeeSalaryCalculatorFactory {
    @Override
    public IEmployeeSalaryCalculator employeeSalaryCalculator() {
        WorkingTimeParser payableParser = new WorkingTimeParser(LocalTime::parse);
        EmployeePaySheetParser employeePaySheetParser = new EmployeePaySheetParser(payableParser::parseWorkingTimes,
                HOUR::basicUnitOfTime);
        return new EmployeeSalaryCalculator(employeePaySheetParser::parseEmployeePaySheet);
    }
}
