package ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator;

import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._15;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants._20;

public class TestDataGenerator {

    public static final Integer ONE = 1;

    public static final Float _2_HOURS_30_MINUTES = 2.5F;
    public static final Float _3_HOURS = 3F;

    public static final Double _45_USD = 45D;
    public static final Double _50_USD = 50D;
    public static final Double _60_USD = 60D;
    public static final Double _75_USD = 75D;
    public static final Double _87_USD_50_c = 87.5D;
    public static final Double _90_USD = 90D;

    public static final LocalTime _09_01 = LocalTime.parse("09:01");
    public static final LocalTime _12_01 = LocalTime.parse("12:01");
    public static final LocalTime _11_31 = LocalTime.parse("11:31");

    public static final String ASTRID = "ASTRID";

    public static Float _15_USD_Per_Hours(Float hours){
        return _15*hours;
    }

    public static Float _20_USD_Per_Hours(Float hours){
        return _20*hours;
    }
}
