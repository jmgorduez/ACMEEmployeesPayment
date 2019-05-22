package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

public interface IPayableParser {
    IPayable parse(String value);
}
