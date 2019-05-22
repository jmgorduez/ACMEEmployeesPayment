package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum WeekDay {
    MONDAY(MO, null),
    TUESDAY(TU, null),
    WEDNESDAY(WE, null),
    THURSDAY(TH, null),
    FRIDAY(FR, null),
    SATURDAY(SA, null),
    SUNDAY(SU, null);

    private final String value;
    private final BiFunction<LocalTime, LocalTime, PaymentStrategy> getPaymentStrategyFor;

    WeekDay(String value, BiFunction<LocalTime, LocalTime, PaymentStrategy> getPaymentStrategyFor) {
        this.value = value;
        this.getPaymentStrategyFor = getPaymentStrategyFor;
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

    static PaymentStrategy paymentStrategyWorkDays(LocalTime start, LocalTime end){
        return null;
    }
}
