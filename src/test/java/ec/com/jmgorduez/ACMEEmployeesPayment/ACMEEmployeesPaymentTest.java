package ec.com.jmgorduez.ACMEEmployeesPayment;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.INPUT_FILE_NAME;
import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ACMEEmployeesPaymentTest {

    @Test
    void main() {
    }

    @Test
    void bufferedReader() throws FileNotFoundException {
        assertThat(ACMEEmployeesPayment.bufferedReader().isPresent())
                .isFalse();
        try (BufferedReader bufferedReader
                     = ACMEEmployeesPayment.bufferedReader(new String[]{INPUT_FILE_NAME}).get()) {
            assertThat(bufferedReader.readLine())
                    .isEqualTo(RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
