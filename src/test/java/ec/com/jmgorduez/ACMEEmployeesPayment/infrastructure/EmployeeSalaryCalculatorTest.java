package ec.com.jmgorduez.ACMEEmployeesPayment.infrastructure;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IEmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.abstractions.IWorkingTimeParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.EmployeePaySheetParser;
import ec.com.jmgorduez.ACMEEmployeesPayment.domain.parsers.WorkingTimeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeSalaryCalculatorTest {

    private EmployeeSalaryCalculator employeeSalaryCalculatorUnderTest;
    private IEmployeePaySheetParser employeePaySheetParser;
    private IWorkingTimeParser payableParser;
    private Queue<String> linesToRead;
    private List<String> outputs;

    @BeforeEach
    void setUp() {
        payableParser = new WorkingTimeParser(LocalTime::parse);
        employeePaySheetParser = new EmployeePaySheetParser(payableParser::parse);
        employeeSalaryCalculatorUnderTest = new EmployeeSalaryCalculator(employeePaySheetParser::parseEmployeePaySheet);
        linesToRead = Stream.of(ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00,
                RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00,
                JUANMA_MO_09_00_12_00_TU_09_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00)
                .collect(Collectors.toCollection(ArrayDeque::new));
        outputs = new ArrayList<>();
    }

    @Test
    void calculateSalary() {
        assertThat(outputs.isEmpty())
                .isTrue();
        employeeSalaryCalculatorUnderTest.calculateSalary(linesToRead::poll, outputs::add);
        assertThat(outputs)
                .contains(THE_AMOUNT_TO_PAY_ASTRID_IS_85_USD,
                        THE_AMOUNT_TO_PAY_RENE_IS_215_USD,
                        THE_AMOUNT_TO_PAY_JUANMA_IS_245_USD);
    }
}