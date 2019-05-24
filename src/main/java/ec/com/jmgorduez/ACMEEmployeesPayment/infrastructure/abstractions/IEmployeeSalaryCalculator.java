package ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface IEmployeeSalaryCalculator {
    void calculateSalary(Supplier<String> readLine, Consumer<String> writeOutput);
}
