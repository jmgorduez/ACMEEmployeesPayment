package ec.com.jmgorduez.ACMEEmployeesPayment;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.ZERO;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ACMEEmployeesPayment {


    public static void main(String[] args) {

    }

    public static Optional<BufferedReader> bufferedReader(String... args) throws FileNotFoundException {
        if (Arrays.asList(args).isEmpty()) {
            return empty();
        }
        return of(new BufferedReader(new FileReader(args[ZERO])));
    }
}
