package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

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

    String getName(String line){
        return line.split(ASSIGNMENT_CHARACTER)[ZERO];
    }

    Stream<String> getWorkingTimes(String line){
        Integer workingTimesStartIndex = line.indexOf(ASSIGNMENT_CHARACTER)+ ONE;
        return Stream.of(line.substring(workingTimesStartIndex).split(COMMA_CHARACTER));
    }
}
