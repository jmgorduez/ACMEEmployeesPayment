package ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure;

import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EmployeeSalaryCalculator implements IEmployeeSalaryCalculator {
    @Override
    public void calculateSalary(Supplier<String> readLine, Consumer<String> writeOutput) {

    }
}
