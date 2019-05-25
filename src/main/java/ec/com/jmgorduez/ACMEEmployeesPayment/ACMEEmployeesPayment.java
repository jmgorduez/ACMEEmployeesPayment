package ec.com.jmgorduez.ACMEEmployeesPayment;


import ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory.EmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure.abstractions.IEmployeeSalaryCalculator;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.ThrowingSupplier.unchecked;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ACMEEmployeesPayment {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = bufferedReader(args).get()) {
            employeeSalaryCalculator()
                    .calculateSalary(unchecked(bufferedReader::readLine), System.out::println);
        } catch (IllegalArgumentException error) {
            System.err.println(MESSAGE_ILLEGAL_FORMAT);
        } catch (IOException error) {
            System.err.println(MESSAGE_INVALID_FILE);
        }
    }

    static Optional<BufferedReader> bufferedReader(String... args)
            throws FileNotFoundException {
        if (asList(args).isEmpty()) {
            return empty();
        }
        try {
            File file = new File(ACMEEmployeesPayment.class.getResource(args[ZERO]).getPath());
            if (doesNotExistFile(file)) {
                throw new FileNotFoundException();
            }
            return of(new BufferedReader(new FileReader(file)));
        } catch (NullPointerException error) {
            throw new FileNotFoundException();
        }
    }

    private static boolean doesNotExistFile(File file) {
        return !file.exists();
    }

    private static IEmployeeSalaryCalculator employeeSalaryCalculator() {
        return new EmployeeSalaryCalculatorFactory()
                .employeeSalaryCalculator();
    }
}
