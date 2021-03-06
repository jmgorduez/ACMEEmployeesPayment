package ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.PaymentStrategy.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.domain.enums.WeekDay.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WeekDayTest {

    @Test
    void parse() {
        assertThat(WeekDay.parse(MO))
                .isEqualTo(MONDAY);
        assertThat(WeekDay.parse(TU))
                .isEqualTo(TUESDAY);
        assertThat(WeekDay.parse(WE))
                .isEqualTo(WEDNESDAY);
        assertThat(WeekDay.parse(TH))
                .isEqualTo(THURSDAY);
        assertThat(WeekDay.parse(FR))
                .isEqualTo(FRIDAY);
        assertThat(WeekDay.parse(SA))
                .isEqualTo(SATURDAY);
        assertThat(WeekDay.parse(SU))
                .isEqualTo(SUNDAY);
        assertThatThrownBy(() -> WeekDay.parse(SU_20_00_21_00_STRING))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void paymentStrategyWorkDays(){
        assertThat(WeekDay.paymentStrategyWorkDays(_00_00, _09_00))
                .isEqualTo(_25_USD_PER_UNIT_OF_TIME);
        assertThat(WeekDay.paymentStrategyWorkDays(_09_00, _18_00))
                .isEqualTo(_15_USD_PER_UNIT_OF_TIME);
        assertThat(WeekDay.paymentStrategyWorkDays(_18_00, _00_00))
                .isEqualTo(_20_USD_PER_UNIT_OF_TIME);
        assertThatThrownBy(() ->  WeekDay.paymentStrategyWorkDays(_00_00, _18_00))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void paymentStrategyWeekendDays(){
        assertThat(WeekDay.paymentStrategyWeekendDays(_00_00, _09_00))
                .isEqualTo(_30_USD_PER_UNIT_OF_TIME);
        assertThat(WeekDay.paymentStrategyWeekendDays(_09_00, _18_00))
                .isEqualTo(_20_USD_PER_UNIT_OF_TIME);
        assertThat(WeekDay.paymentStrategyWeekendDays(_18_00, _00_00))
                .isEqualTo(_25_USD_PER_UNIT_OF_TIME);
        assertThatThrownBy(() ->  WeekDay.paymentStrategyWeekendDays(_00_00, _18_00))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void paymentStrategy(){
        assertThat(MONDAY.paymentStrategy(_09_00,_11_30))
                .isEqualTo(_15_USD_PER_UNIT_OF_TIME);
        assertThat(THURSDAY.paymentStrategy(_18_00,_20_00))
                .isEqualTo(_20_USD_PER_UNIT_OF_TIME);
        assertThat(SUNDAY.paymentStrategy(_18_00,_20_00))
                .isEqualTo(_25_USD_PER_UNIT_OF_TIME);
        assertThat(SATURDAY.paymentStrategy(_00_00,_09_00))
                .isEqualTo(_30_USD_PER_UNIT_OF_TIME);
    }

    @Test
    void getFirstElement(){
        assertThat(WeekDay.getFirstElement(MONDAY, FRIDAY))
                .isEqualTo(MONDAY);
    }

    @Test
    void getValue(){
        assertThat(MONDAY.getValue())
                .isEqualTo(MO);
        assertThat(WEDNESDAY.getValue())
                .isEqualTo(WE);
        assertThat(FRIDAY.getValue())
                .isEqualTo(FR);
    }
}