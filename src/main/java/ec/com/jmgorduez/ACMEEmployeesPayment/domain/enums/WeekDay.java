package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum WeekDay {
    MONDAY(MO),
    TUESDAY(TU),
    WEDNESDAY(WE),
    THURSDAY(TH),
    FRIDAY(FR),
    SATURDAY(SA),
    SUNDAY(SU);

    private final String value;

    WeekDay(String value) {
        this.value = value;
    }

    public static WeekDay parse(String value){
        return Arrays.stream(values())
                .filter(isValueEqualTo(value))
                .reduce(WeekDay::getFirstElement)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Predicate<WeekDay> isValueEqualTo(String value) {
        return weekDay -> weekDay.value.equals(value);
    }

    private static WeekDay getFirstElement(WeekDay weekDay, WeekDay weekDay2){
        return weekDay;
    }
}
