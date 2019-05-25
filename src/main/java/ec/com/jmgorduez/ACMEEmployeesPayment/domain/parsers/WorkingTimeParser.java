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

    private class Pair<T> {
        private T left;
        private T right;

        private Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }

        public T getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }
    }

    private Function<String, LocalTime> localTimeParse;

    public WorkingTimeParser(Function<String, LocalTime> localTimeParse) {
        this.localTimeParse = localTimeParse;
    }

    @Override
    public IPayable parseWorkingTime(String value) {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        PaymentStrategy paymentStrategy = getWeekDay(value).paymentStrategy(start, end);
        return new WorkingTime(start, end, HOUR::basicUnitOfTime, paymentStrategy::paymentStrategy);
    }

    @Override
    public List<IPayable> parseCompactWorkingTime(String value) {
        return separateInDefinedTimes(value).stream()
                .map(toWorkingTime(value))
                .collect(Collectors.toList());
    }

    private Function<Pair<LocalTime>, WorkingTime> toWorkingTime(String value) {
        return localTimePair ->
                new WorkingTime(localTimePair.left,
                        localTimePair.right,
                        getWeekDay(value)
                                .paymentStrategy(localTimePair.left, localTimePair.right)::paymentStrategy);
    }

    private List<Pair<LocalTime>> separateInDefinedTimes(String value) {
        LocalTime start = getStart(value);
        LocalTime end = getEnd(value);
        ArrayDeque<LocalTime> times = new ArrayDeque<>(
                Stream.of(_00_00, _09_00, _18_00, _23_59)
                        .filter(start::isBefore)
                        .filter(end::isAfter)
                        .collect(Collectors.toSet()));
        times.addFirst(start);
        times.addLast(end);
        List<Pair<LocalTime>> rangesOfTime = new ArrayList<>();
        while (times.size() > ONE) {
            rangesOfTime.add(new Pair<>(times.poll(), times.peek()));
        }
        return rangesOfTime;
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
