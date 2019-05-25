package ec.com.jmgorduez.ACMEEmployeesPayment;

import ec.com.jmgorduez.ACMEEmployeesPayment.ACMEEmployeesPayment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.ACMEEmployeesPayment.dataGenerator.TestDataGenerator.*;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.MESSAGE_ILLEGAL_FORMAT;
import static ec.com.jmgorduez.ACMEEmployeesPayment.utils.Constants.MESSAGE_INVALID_FILE;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;

class ACMEEmployeesPaymentTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private final String[] args = new String[]{INPUT_FILE_NAME};
    private Queue<String> linesExpected;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        linesExpected = of(RENE_MO_10_00_12_00_TU_10_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00,
                ASTRID_MO_10_00_12_00_TH_12_00_14_00_SU_20_00_21_00,
                JUANMA_MO_09_00_12_00_TU_09_00_12_00_TH_01_00_03_00_SA_14_00_18_00_SU_20_00_21_00,
                EMPTY_STRING)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
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
    void mainProcessingACompactPaySheetFile() {
        ACMEEmployeesPayment.main(new String[]{INPUT_FILE_NAME_COMPACT_PAYSHEET});
        assertThat(outContent.toString())
                .isEqualTo(THE_AMOUNT_TO_PAY_RENE_IS_300_USD.concat(System.lineSeparator()));
    }

    @Test
    void mainProcessingAEmptyFile() {
        ACMEEmployeesPayment.main(new String[]{INPUT_EMPTY_FILE_NAME});
        assertThat(outContent.toString())
                .isEqualTo(EMPTY_STRING);
    }

    @Test
    void mainProcessingAFileWithIllegalFormat() {
        ACMEEmployeesPayment.main(new String[]{INPUT_FILE_NAME_ILLEGAL_FORMAT});
        assertThat(errContent.toString())
                .isEqualTo(MESSAGE_ILLEGAL_FORMAT.concat(System.lineSeparator()));
    }

    @Test
    void mainProcessingAInvalidFile() {
        ACMEEmployeesPayment.main(new String[]{INVALID_INPUT_FILE_NAME});
        assertThat(errContent.toString())
                .isEqualTo(MESSAGE_INVALID_FILE.concat(System.lineSeparator()));
    }

    @Test
    void mainProcessingAEmptyFileName() {
        ACMEEmployeesPayment.main(new String[]{EMPTY_STRING});
        assertThat(errContent.toString())
                .isEqualTo(MESSAGE_INVALID_FILE.concat(System.lineSeparator()));
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
