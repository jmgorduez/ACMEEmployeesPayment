package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;

import java.util.List;

public class EmplayeePaySheet implements IPayable {

    private List<IPayable> workingTimes;

    @Override
    public Float payment() {
        return null;
    }
}
