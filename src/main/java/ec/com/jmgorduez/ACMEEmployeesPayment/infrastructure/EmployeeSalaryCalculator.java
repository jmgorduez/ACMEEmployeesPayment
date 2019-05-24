package ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static java.util.Optional.ofNullable;

public class EmployeeSalaryCalculator implements IEmployeeSalaryCalculator {

    private Function<String, IEmployeePaySheet> parseEmployeePaySheet;
    private DecimalFormat decimalFormat = new DecimalFormat();

    public EmployeeSalaryCalculator(Function<String, IEmployeePaySheet> parseEmployeePaySheet) {
        this.parseEmployeePaySheet = parseEmployeePaySheet;
    }

    @Override
    public void calculateSalary(Supplier<String> readLine, Consumer<String> writeOutput) {
        try {
            while (true) {
                writeOutput
                        .accept(ofNullable(readLine.get())
                                .map(parseEmployeePaySheet)
                                .map(this::calculateEmployeeSalary)
                                .orElseThrow(RuntimeException::new));
            }
        } catch (RuntimeException error) {
        }
    }

    String calculateEmployeeSalary(IEmployeePaySheet employeePaySheet) {
        return THE_AMOUNT_TO_PAY_EMPLOYEE_IS_SALARY_USD
                .replace(EMPLOYEE, employeePaySheet.employeeName())
                .replace(SALARY, decimalFormat.format(employeePaySheet.payment()));
    }
}
