package ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator;

import ec.com.jmgorduez.ACMEEmployeesPayment.domain.WorkingTime;

import java.time.LocalTime;

import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.MO_FR_09_01_18_00;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.TypeBasicUnitOfTime.HOUR;
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

    public static final LocalTime _09_00 = LocalTime.parse("09:00");
    public static final LocalTime _10_00 = LocalTime.parse("10:00");
    public static final LocalTime _11_30 = LocalTime.parse("11:30");
    public static final LocalTime _12_00 = LocalTime.parse("12:00");
    public static final LocalTime _14_00 = LocalTime.parse("14:00");
    public static final LocalTime _20_00 = LocalTime.parse("20:00");
    public static final LocalTime _21_00 = LocalTime.parse("21:00");

    public static final WorkingTime MO_10_00_12_00 = new WorkingTime(_10_00, _12_00,
            HOUR::basicUnitOfTime, MO_FR_09_01_18_00::paymentStrategy);
    public static final WorkingTime TH_12_00_14_00 = new WorkingTime(_12_00, _14_00,
            HOUR::basicUnitOfTime, MO_FR_09_01_18_00::paymentStrategy);
    public static final WorkingTime SU_20_00_21_00 = new WorkingTime(_20_00, _21_00,
            HOUR::basicUnitOfTime, MO_FR_09_01_18_00::paymentStrategy);

    public static final String ASTRID = "ASTRID";

    public static final String ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00
            = "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";

    public static Double _15_USD_Per_Hours(Float hours){
        return _15*hours.doubleValue();
    }

    public static Double _20_USD_Per_Hours(Float hours){
        return _20*hours.doubleValue();
    }
}
