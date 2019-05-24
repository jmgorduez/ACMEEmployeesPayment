package ec.com.jmgorduez.ACMEEmployeesPayment;


import ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory.EmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.ThrowingSupplier.unchecked;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.ZERO;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ACMEEmployeesPayment {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = bufferedReader(args).get()) {
            employeeSalaryCalculator()
                    .calculateSalary(unchecked(bufferedReader::readLine), System.out::println);
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    static Optional<BufferedReader> bufferedReader(String... args) throws FileNotFoundException {
        if (asList(args).isEmpty()) {
            return empty();
        }
        return of(new BufferedReader(new InputStreamReader(ACMEEmployeesPayment.class.getResourceAsStream(args[ZERO]))));
    }

    private static IEmployeeSalaryCalculator employeeSalaryCalculator() {
        return new EmployeeSalaryCalculatorFactory()
                .employeeSalaryCalculator();
    }
}
