package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayableParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public class WorkingTimeParser implements IPayableParser {

    private Function<String, LocalTime> localTimeParse;

    public WorkingTimeParser(Function<String, LocalTime> localTimeParse) {
        this.localTimeParse = localTimeParse;
    }

    @Override
    public IPayable parse(String value) {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        PaymentStrategy paymentStrategy = getWeekDay(value).paymentStrategy(start, end);
        return new WorkingTime(start, end, HOUR::basicUnitOfTime, paymentStrategy::paymentStrategy);
    }

    private LocalTime getStart(String value) {
        return getTimes(value)
                .map(localTimeParse)
                .reduce((start, end) -> start).get();
    }

    private LocalTime getEnd(String value) {
        return getTimes(value)
                .map(localTimeParse)
                .reduce((start, end) -> end).get();
    }

    private Stream<String> getTimes(String value) {
        return Arrays.stream(value.substring(TWO).split(MINUS_CHARACTER));
    }

    private WeekDay getWeekDay(String value) {
        return WeekDay.parse(value.substring(ZERO, TWO));
    }
}
