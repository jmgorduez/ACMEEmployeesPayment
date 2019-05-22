package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public enum WeekDay {
    MONDAY(MO, WeekDay::paymentStrategyWorkDays),
    TUESDAY(TU, WeekDay::paymentStrategyWorkDays),
    WEDNESDAY(WE, WeekDay::paymentStrategyWorkDays),
    THURSDAY(TH, WeekDay::paymentStrategyWorkDays),
    FRIDAY(FR, WeekDay::paymentStrategyWorkDays),
    SATURDAY(SA, WeekDay::paymentStrategyWeekendDays),
    SUNDAY(SU, WeekDay::paymentStrategyWeekendDays);

    private final String value;
    private final BiFunction<LocalTime, LocalTime, PaymentStrategy> getPaymentStrategyFor;

    WeekDay(String value, BiFunction<LocalTime, LocalTime, PaymentStrategy> getPaymentStrategyFor) {
        this.value = value;
        this.getPaymentStrategyFor = getPaymentStrategyFor;
    }

    public PaymentStrategy paymentStrategy(LocalTime start, LocalTime end) {
        return getPaymentStrategyFor.apply(start, end);
    }

    public static WeekDay parse(String value) {
        return Arrays.stream(values())
                .filter(isValueEqualTo(value))
                .reduce(WeekDay::getFirstElement)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Predicate<WeekDay> isValueEqualTo(String value) {
        return weekDay -> weekDay.value.equals(value);
    }

    static WeekDay getFirstElement(WeekDay weekDay, WeekDay weekDay2) {
        return weekDay;
    }

    static PaymentStrategy paymentStrategyWorkDays(LocalTime start, LocalTime end) {
        end = end.equals(_00_00) ? _23_59 : end;
        if (areBetween_00_00_And_09_00(start, end)) {
            return _25_USD_PER_UNIT_OF_TIME;
        }
        if (areBetween_09_00_And_18_00(start, end)) {
            return _15_USD_PER_UNIT_OF_TIME;
        }
        if (areBetween_18_00_And_23_59(start, end)) {
            return _20_USD_PER_UNIT_OF_TIME;
        }
        throw new IllegalArgumentException();
    }

    static PaymentStrategy paymentStrategyWeekendDays(LocalTime start, LocalTime end) {
        end = end.equals(_00_00) ? _23_59 : end;
        if (areBetween_00_00_And_09_00(start, end)) {
            return _30_USD_PER_UNIT_OF_TIME;
        }
        if (areBetween_09_00_And_18_00(start, end)) {
            return _20_USD_PER_UNIT_OF_TIME;
        }
        if (areBetween_18_00_And_23_59(start, end)) {
            return _25_USD_PER_UNIT_OF_TIME;
        }
        throw new IllegalArgumentException();
    }

    private static boolean areBetween_00_00_And_09_00(LocalTime start, LocalTime end) {
        return (start.isAfter(_00_00) || start.equals(_00_00)) && (end.isBefore(_09_00) || end.equals(_09_00));
    }

    private static boolean areBetween_09_00_And_18_00(LocalTime start, LocalTime end) {
        return (start.isAfter(_09_00) || start.equals(_09_00)) && (end.isBefore(_18_00) || end.equals(_18_00));
    }

    private static boolean areBetween_18_00_And_23_59(LocalTime start, LocalTime end) {
        return (start.isAfter(_18_00) || start.equals(_18_00)) && (end.isBefore(_23_59) || end.equals(_23_59));
    }
}
