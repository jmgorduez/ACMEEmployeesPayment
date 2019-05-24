package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.EmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.function.Function;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public class EmployeePaySheetParser implements IEmployeePaySheetParser {

    private Function<String, IPayable> getPayableItem;

    public EmployeePaySheetParser(Function<String, IPayable> getPayableItem){
        this.getPayableItem = getPayableItem;
    }

    @Override
    public IEmployeePaySheet parseEmployeePaySheet(String line) {
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
