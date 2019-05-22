package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.util.List;

public interface IPayableParser {
    IPayable parse(String value);
}
