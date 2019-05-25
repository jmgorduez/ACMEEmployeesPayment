package ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static java.util.Optional.ofNullable;

public class EmployeeSalaryCalculator implements IEmployeeSalaryCalculator {

    private Function<String, IEmployeePaySheet> getParseEmployeePaySheet;
    private DecimalFormat decimalFormat = new DecimalFormat();

    public EmployeeSalaryCalculator(Function<String, IEmployeePaySheet> getParseEmployeePaySheet) {
        this.getParseEmployeePaySheet = getParseEmployeePaySheet;
    }

    @Override
    public void calculateSalary(Supplier<String> readLine, Consumer<String> writeOutput) {
        readAllEmployeesPaySheet(readLine)
                .map(this::calculateEmployeeSalary)
                .forEach(writeOutput);
    }

    private Stream<IEmployeePaySheet> readAllEmployeesPaySheet(Supplier<String> readLine) {
        List<IEmployeePaySheet> lines = new ArrayList<>();
        try {
            while (true) {
                lines.add(
                        ofNullable(readLine.get())
                                .map(getParseEmployeePaySheet)
                                .orElseThrow(UnsupportedOperationException::new));
            }
        } catch (UnsupportedOperationException error) {
            return lines.stream();
        }
    }

    private String calculateEmployeeSalary(IEmployeePaySheet employeePaySheet) {
        return THE_AMOUNT_TO_PAY_EMPLOYEE_IS_SALARY_USD
                .replace(EMPLOYEE, employeePaySheet.employeeName())
                .replace(SALARY, decimalFormat.format(employeePaySheet.payment()));
    }
}
