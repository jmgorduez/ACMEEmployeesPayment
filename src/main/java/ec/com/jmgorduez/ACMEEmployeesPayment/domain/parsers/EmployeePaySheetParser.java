package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.EmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTimeParser;

import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public class EmployeePaySheetParser implements IEmployeePaySheetParser {

    private IWorkingTimeParser workingTimeParser;
    private BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked;

    public EmployeePaySheetParser(IWorkingTimeParser workingTimeParser,
                                  BiFunction<LocalTime, LocalTime, Float> getNumbersOfUnitsOfTimeWorked){
        this.workingTimeParser = workingTimeParser;
        this.getNumbersOfUnitsOfTimeWorked = getNumbersOfUnitsOfTimeWorked;
    }

    @Override
    public IEmployeePaySheet parseEmployeePaySheet(String line) {
        IEmployeePaySheet employeePaySheet = new EmployeePaySheet(getName(line), getNumbersOfUnitsOfTimeWorked);
        getWorkingTimes(line)
                .map(workingTimeParser::parseWorkingTimes)
                .forEach(employeePaySheet::addWorkingTime);
        return employeePaySheet;
    }

    String getName(String line){
        return line.split(ASSIGNMENT_CHARACTER)[ZERO];
    }

    Stream<String> getWorkingTimes(String line){
        Integer workingTimesStartIndex = line.indexOf(ASSIGNMENT_CHARACTER)+ ONE;
        return Stream.of(line.substring(workingTimesStartIndex).split(COMMA_CHARACTER));
    }
}
