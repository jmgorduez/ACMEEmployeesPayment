package ec.com.jmgorduez.ACMEEmployeesPayment;

import ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ACMEEmployeesPaymentTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void main() {
        ACMEEmployeesPayment.main(new String[]{INPUT_FILE_NAME});
        assertThat(outContent.toString())
                .isEqualTo(RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00
                        .concat(System.lineSeparator())
                        .concat(ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00)
                        .concat(System.lineSeparator())
                        .concat(JUANMA_MO_09_00_12_00_TU_09_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00)
                        .concat(System.lineSeparator()));
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
