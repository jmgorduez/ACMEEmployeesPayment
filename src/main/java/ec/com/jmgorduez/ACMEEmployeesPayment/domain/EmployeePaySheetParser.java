package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants;

import java.util.function.Function;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public class EmployeePaySheetParser implements IEmployeePaySheetParser {
    @Override
    public IEmployeePaySheet parseEmployeePaySheet(String line, Function<String, IPayable> getPayableItem) {
        IEmployeePaySheet result = new EmployeePaySheet(getName(line));
        getWorkingTimes(line)
                .map(getPayableItem)
                .forEach(result::addPayableItem);
        return result;
    }

    private String getName(String line){
        return line.split(ASSIGNMENT_CHARACTER)[ZERO];
    }

    private Stream<String> getWorkingTimes(String line){
        return Stream.of(line.substring(line.indexOf(ASSIGNMENT_CHARACTER)).split(COMMA_CHARACTER));
    }
}
