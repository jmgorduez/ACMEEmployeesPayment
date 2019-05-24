package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

public interface IEmployeeSalaryCalculatorFactory {
    IEmployeeSalaryCalculator employeeSalaryCalculator();
}
