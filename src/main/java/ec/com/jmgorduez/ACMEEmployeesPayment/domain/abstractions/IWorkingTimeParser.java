package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

public interface IWorkingTimeParser {
    IPayable parse(String value);
}
