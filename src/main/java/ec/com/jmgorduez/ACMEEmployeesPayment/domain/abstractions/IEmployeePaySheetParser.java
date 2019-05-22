package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.util.function.Function;

public interface IEmployeePaySheetParser {
    IEmployeePaySheet parseEmployeePaySheet(String line, Function<String, IPayable> getPayableItem);
}
