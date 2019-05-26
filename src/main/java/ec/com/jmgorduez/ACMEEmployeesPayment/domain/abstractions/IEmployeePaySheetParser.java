package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

@FunctionalInterface
public interface IEmployeePaySheetParser {
    IEmployeePaySheet parseEmployeePaySheet(String line);
}
