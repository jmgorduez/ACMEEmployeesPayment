package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.util.List;

public interface IWorkingTimeParser {
    IPayable parseWorkingTime(String value);
    List<IPayable> parseCompactWorkingTime(String value);
}
