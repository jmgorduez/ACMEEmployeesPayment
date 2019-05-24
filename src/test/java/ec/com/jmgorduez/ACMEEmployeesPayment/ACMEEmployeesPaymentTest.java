package ec.com.jmgorduez.ACMEEmployeesPayment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ACMEEmployeesPaymentTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String[] args = new String[]{INPUT_FILE_NAME};
    private Queue<String> linesExpected;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        linesExpected = Stream
                .of(RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00,
                        ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00,
                        JUANMA_MO_09_00_12_00_TU_09_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00,
                        EMPTY_STRING)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void main() {
        ACMEEmployeesPayment.main(new String[]{INPUT_FILE_NAME});
        assertThat(outContent.toString())
                .isEqualTo(THE_AMOUNT_TO_PAY_RENE_IS_215_USD.concat(System.lineSeparator())
                        .concat(THE_AMOUNT_TO_PAY_ASTRID_IS_85_USD).concat(System.lineSeparator())
                        .concat(THE_AMOUNT_TO_PAY_JUANMA_IS_245_USD).concat(System.lineSeparator()));
    }

    @Test
    void bufferedReader() throws FileNotFoundException {
        assertThat(ACMEEmployeesPayment.bufferedReader().isPresent())
                .isFalse();
        try (BufferedReader bufferedReader
                     = ACMEEmployeesPayment.bufferedReader(args).get()) {
            bufferedReader.lines()
                    .forEach(this::isTheExpectedLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void isTheExpectedLine(String line) {
        assertThat(line)
                .isEqualTo(linesExpected.poll());
    }
}
