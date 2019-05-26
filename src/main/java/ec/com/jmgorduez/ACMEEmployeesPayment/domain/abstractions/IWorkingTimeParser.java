package ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions;

@FunctionalInterface
public interface IWorkingTimeParser {
    IPayable[] parseWorkingTimes(String value);
}
