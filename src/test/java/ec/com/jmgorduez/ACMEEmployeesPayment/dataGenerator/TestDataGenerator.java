package ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.WorkingTime;

import java.io.File;
import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static java.time.temporal.ChronoUnit.MINUTES;

public class TestDataGenerator {
    public static final String INPUT_FILE_PATH = "src-test-resources-inputFile-".replace("-", File.separator);
    public static final String INPUT_FILE_NAME = INPUT_FILE_PATH.concat("input.txt");
    public static final String INPUT_FILE_NAME_COMPACT_PAYSHEET
            = INPUT_FILE_PATH.concat("inputCompactPaySheet.txt");
    public static final String INPUT_EMPTY_FILE_NAME
            = INPUT_FILE_PATH.concat("emptyInput.txt");
    public static final String INPUT_FILE_NAME_ILLEGAL_FORMAT
            = INPUT_FILE_PATH.concat("inputIllegalFormat.txt");
    public static final String INVALID_INPUT_FILE_NAME
            = INPUT_FILE_PATH.concat("invalidInput.txt");

    public static final Integer ONE = 1;

    public static final Float _2_HOURS_30_MINUTES = 2.5F;
    public static final Float _3_HOURS = 3F;

    public static final Double _37_USD_50_c = 37.5D;
    public static final Double _45_USD = 45D;
    public static final Double _50_USD = 50D;
    public static final Double _60_USD = 60D;
    public static final Double _75_USD = 75D;
    public static final Double _87_USD_50_c = 87.5D;
    public static final Double _90_USD = 90D;

    public static final Integer THREE = 3;

    public static final LocalTime _10_00 = LocalTime.parse("10:00");
    public static final LocalTime _11_30 = LocalTime.parse("11:30");
    public static final LocalTime _12_00 = LocalTime.parse("12:00");
    public static final LocalTime _14_00 = LocalTime.parse("14:00");
    public static final LocalTime _20_00 = LocalTime.parse("20:00");
    public static final LocalTime _21_00 = LocalTime.parse("21:00");

    public static final WorkingTime MO_00_00_09_00 = new WorkingTime.Builder(_00_00, _09_00)
            .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
            .howMuchToPayFor(TestDataGenerator::_25_USD_Per_Hours)
            .build();
    public static final WorkingTime MO_09_00_12_00 = new WorkingTime.Builder(_09_00, _12_00)
            .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
            .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
            .build();
    public static final WorkingTime MO_10_00_12_00 = new WorkingTime.Builder(_10_00, _12_00)
            .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
            .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
            .build();
    public static final WorkingTime TH_12_00_14_00 = new WorkingTime.Builder(_12_00, _14_00)
            .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
            .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
            .build();
    public static final WorkingTime SU_20_00_21_00 = new WorkingTime.Builder(_20_00, _21_00)
            .numbersOfUnitsOfTimeWorked(TestDataGenerator::numberOfHours)
            .howMuchToPayFor(TestDataGenerator::_15_USD_Per_Hours)
            .build();

    public static final String ASTRID = "ASTRID";

    public static final String EMPTY_STRING = "";

    public static final String ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00
            = "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
    public static final String RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00
            = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
    public static final String RENE_MO_00_00_12_00_TU_10_00_12_00
            = "RENE=MO00:00-12:00,TU10:00-12:00";
    public static final String JUANMA_MO_09_00_12_00_TU_09_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00
            = "JUANMA=MO09:00-12:00,TU09:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";

    public static final String THE_AMOUNT_TO_PAY_RENE_IS_215_USD = "The amount to pay RENE is: 215 USD";
    public static final String THE_AMOUNT_TO_PAY_RENE_IS_300_USD = "The amount to pay RENE is: 300 USD";
    public static final String THE_AMOUNT_TO_PAY_ASTRID_IS_85_USD = "The amount to pay ASTRID is: 85 USD";
    public static final String THE_AMOUNT_TO_PAY_JUANMA_IS_245_USD = "The amount to pay JUANMA is: 245 USD";

    public static final String MO_00_00_12_00_STRING = "MO00:00-12:00";
    public static final String MO_00_00_09_00_STRING = "MO00:00-09:00";
    public static final String MO_09_00_12_00_STRING = "MO09:00-12:00";
    public static final String MO_10_00_12_00_STRING = "MO10:00-12:00";
    public static final String TH_12_00_14_00_STRING = "TH12:00-14:00";
    public static final String SU_20_00_21_00_STRING = "SU20:00-21:00";

    public static final String GET_NUMBERS_OF_UNITS_OF_TIME_WORKED = "getNumbersOfUnitsOfTimeWorked";
    public static final String HOW_MUCH_TO_PAY_FOR = "howMuchToPayFor";
    public static final String EMPLOYEE_PAY_SHEET_PARSER = "employeePaySheetParser";

    public static Double _15_USD_Per_Hours(Float hours) {
        return _15 * hours.doubleValue();
    }

    public static Double _20_USD_Per_Hours(Float hours) {
        return _20 * hours.doubleValue();
    }

    public static Double _25_USD_Per_Hours(Float hours) {
        return _25 * hours.doubleValue();
    }

    public static Float numberOfHours(LocalTime start, LocalTime end) {
        return start.until(end, MINUTES) / _60;
    }
}
