package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

public interface IEmployeePaySheetParser {
    IEmployeePaySheet parseEmployeePaySheet(String line);
}
