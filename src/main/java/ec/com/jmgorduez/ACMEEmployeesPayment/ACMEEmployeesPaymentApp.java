package ec.com.jmgorduez.ACMEEmployeesPayment;


import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeeSalaryCalculatorFactory;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.factory.EmployeeSalaryCalculatorFactory;

import java.io.*;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.ThrowingSupplier.unchecked;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ACMEEmployeesPaymentApp {

    private static IEmployeeSalaryCalculatorFactory calculatorFactory
            = new EmployeeSalaryCalculatorFactory();

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = bufferedReader(args).get()) {
            calculatorFactory
                    .employeeSalaryCalculator()
                    .calculateSalary(unchecked(bufferedReader::readLine), System.out::println);
        } catch (IllegalArgumentException error) {
            System.err.println(MESSAGE_ILLEGAL_FORMAT);
        } catch (IOException error) {
            System.err.println(MESSAGE_INVALID_FILE);
        } catch (NoSuchElementException error) {
            System.err.println(MESSAGE_PARAMETER_IS_NECESSARY);
        }
    }

    static Optional<BufferedReader> bufferedReader(String... args)
            throws FileNotFoundException {
        if (asList(args).isEmpty()) {
            return empty();
        }
        try {
            File file = Paths.get(args[ZERO]).toFile();
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

}
