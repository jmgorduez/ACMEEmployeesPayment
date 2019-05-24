package ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayableParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.EmployeeSalaryCalculator;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.time.LocalTime;

public class EmployeeSalaryCalculatorFactory implements IEmployeeSalaryCalculatorFactory {
    @Override
    public IEmployeeSalaryCalculator employeeSalaryCalculator() {
        IPayableParser payableParser = new WorkingTimeParser(LocalTime::parse);
        IEmployeePaySheetParser employeePaySheetParser = new EmployeePaySheetParser(payableParser::parse);
        return new EmployeeSalaryCalculator(employeePaySheetParser::parseEmployeePaySheet);
    }
}
