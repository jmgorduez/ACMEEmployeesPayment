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
            return separateInDefinedWorkingTimes(value)
                    .map(this::parseWorkingTime)
                    .toArray(IPayable[]::new);
        }
    }

    IPayable parseWorkingTime(String value)
            throws IllegalArgumentException {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        PaymentStrategy paymentStrategy
                = getWeekDay(value).paymentStrategy(start, end);
        return new WorkingTime.Builder(start, end)
                .numbersOfUnitsOfTimeWorked(HOUR::basicUnitOfTime)
                .howMuchToPayFor(paymentStrategy::howMuchToPayFor)
                .build();
    }

    Stream<String> separateInDefinedWorkingTimes(String value) {
        Queue<String> separatedTimes = separateInDefinedTimes(value);
        List<String> rangesOfTime = new ArrayList<>();
        while (isNotEmpty(separatedTimes)) {
            rangesOfTime.add(getWeekDayString(value)
                    .concat(separatedTimes.poll()).concat(MINUS_CHARACTER)
                    .concat(separatedTimes.peek()));
        }
        return rangesOfTime.stream();
    }

    Queue<String> separateInDefinedTimes(String value) {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        ArrayDeque<String> definedTimes = new ArrayDeque<>(
                Stream.of(_00_00, _09_00, _18_00, _23_59)
                        .filter(start::isBefore)
                        .filter(end::isAfter)
                        .map(LocalTime::toString).collect(Collectors.toList()));
        definedTimes.addFirst(start.toString());
        definedTimes.addLast(end.toString());
        return definedTimes;
    }

    private boolean isNotEmpty(Queue<String> separateTimes) {
        return separateTimes.size() > ONE;
    }

    private LocalTime getStart(String value) {
        return getTimes(value)
                .map(localTimeParse)
                .reduce(this::getFirstElement).get();
    }

    private LocalTime getEnd(String value) {
        return getTimes(value)
                .map(localTimeParse)
                .reduce(this::getSecondElement).get();
    }

    private LocalTime getFirstElement(LocalTime first, LocalTime second) {
        return first;
    }

    private LocalTime getSecondElement(LocalTime first, LocalTime second) {
        return second;
    }

    private Stream<String> getTimes(String value) {
        return Arrays.stream(value.substring(TWO).split(MINUS_CHARACTER));
    }

    private String getWeekDayString(String value) {
        return value.substring(ZERO, TWO);
    }

    private WeekDay getWeekDay(String value) {
        return WeekDay.parse(getWeekDayString(value));
    }
}
