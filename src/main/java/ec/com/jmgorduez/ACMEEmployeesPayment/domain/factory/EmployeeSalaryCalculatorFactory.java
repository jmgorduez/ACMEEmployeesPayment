package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;

public class EmployeeSalaryCalculatorFactory implements IEmployeeSalaryCalculatorFactory {
    @Override
    public IEmployeeSalaryCalculator employeeSalaryCalculator() {
        IWorkingTimeParser payableParser = new WorkingTimeParser(LocalTime::parse);
        IEmployeePaySheetParser employeePaySheetParser = new EmployeePaySheetParser(payableParser::parse,
                HOUR::basicUnitOfTime);
        return new EmployeeSalaryCalculator(employeePaySheetParser::parseEmployeePaySheet);
    }
}
