package ec.com.jmgorduez.ACMEEmployeesPayment.domain;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IPayable;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.MO_FR_09_01_18_00;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeePaySheetParserTest {

    private EmployeePaySheetParser employeePaySheetParserUnderTest;
    private Queue<IPayable> payables;

    @BeforeEach
    void setUp() {
        this.employeePaySheetParserUnderTest = new EmployeePaySheetParser();
        this.payables = Stream.of(MO_10_00_12_00, TH_12_00_14_00, SU_20_00_21_00)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Test
    void parseEmployeePaySheet() {
        EmployeePaySheet employeePaySheetExpected = new EmployeePaySheet(ASTRID);
        payables.stream().forEach(employeePaySheetExpected::addPayableItem);
        assertThat(employeePaySheetParserUnderTest.parseEmployeePaySheet(
                ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00, this::getWorkingTime))
                .isEqualToComparingFieldByField(employeePaySheetExpected);
    }

    private IPayable getWorkingTime(String value) {
        return payables.poll();
    }
}