package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheet;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.function.Function;

public class EmployeePaySheetParser implements IEmployeePaySheetParser {
    @Override
    public IEmployeePaySheet parseEmployeePaySheet(String line, Function<String, IPayable> getPayableItem) {
        return null;
    }
}
