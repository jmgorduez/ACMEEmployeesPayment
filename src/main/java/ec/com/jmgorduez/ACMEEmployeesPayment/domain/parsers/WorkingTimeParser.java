package ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.WorkingTime;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;

public class WorkingTimeParser implements IWorkingTimeParser {

    private Function<String, LocalTime> localTimeParse;

    public WorkingTimeParser(Function<String, LocalTime> localTimeParse) {
        this.localTimeParse = localTimeParse;
    }

    @Override
    public IPayable[] parseWorkingTimes(String value) {
        try {
            return new IPayable[]{parseWorkingTime(value)};
        } catch (IllegalArgumentException e) {
            return separateInDefinedTimes(value)
                    .map(this::parseWorkingTime)
                    .toArray(IPayable[]::new);
        }
    }

    IPayable parseWorkingTime(String value)
            throws IllegalArgumentException {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        PaymentStrategy paymentStrategy = getWeekDay(value).paymentStrategy(start, end);
        return new WorkingTime(start, end, HOUR::basicUnitOfTime, paymentStrategy::paymentStrategy);
    }

    private Stream<String> separateInDefinedTimes(String value) {
        Queue<LocalTime> times = separateTimes(value);
        List<String> rangesOfTime = new ArrayList<>();
        while (times.size() > ONE) {
            rangesOfTime.add(getWeekDay(value).getValue()
                    .concat(times.poll().toString()).concat(MINUS_CHARACTER)
                    .concat(times.peek().toString()));
        }
        return rangesOfTime.stream();
    }

    private Queue<LocalTime> separateTimes(String value) {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        ArrayDeque<LocalTime> times = new ArrayDeque<>(
                Stream.of(_00_00, _09_00, _18_00, _23_59)
                        .filter(start::isBefore)
                        .filter(end::isAfter).collect(Collectors.toSet()));
        times.addFirst(start);
        times.addLast(end);
        return times;
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
