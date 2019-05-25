package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

import java.util.List;

public interface IWorkingTimeParser {
    IPayable[] parseWorkingTimes(String value);
}
