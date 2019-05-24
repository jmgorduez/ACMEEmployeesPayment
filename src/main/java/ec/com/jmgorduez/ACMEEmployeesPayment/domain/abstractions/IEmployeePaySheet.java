package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

public interface IEmployeePaySheet extends IPayable {
    String employeeName();
    void addWorkingTime(IWorkingTime workingTime);
}
