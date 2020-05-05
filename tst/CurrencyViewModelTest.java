import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.Data;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CurrencyViewModelTest {
    @Data
    public static class TestCase {
        final double amount;
        final Locale locale;
        final String expectedOutput;
    }

    public static List<CurrencyViewModelTest.TestCase> simpleTestCase() {
        return Arrays.asList(
                new CurrencyViewModelTest.TestCase(5.0, Locale.US, "$5"),
                new CurrencyViewModelTest.TestCase(5.00000001, Locale.US, "$5"),
                new CurrencyViewModelTest.TestCase(5.2, Locale.US, "$5.20"),
                new CurrencyViewModelTest.TestCase(5.818290382423413523534535, Locale. US, "$5.82"),
                new CurrencyViewModelTest.TestCase(123456789.7, new Locale("en", "IN"), "₹ 12,34,56,789.70"),
                new CurrencyViewModelTest.TestCase(123456789.0, new Locale("en", "IN"), "₹ 12,34,56,789"),
                new CurrencyViewModelTest.TestCase(500.0, Locale.JAPAN, "￥500"), // Minimal unit of JPY is ￥1
                new CurrencyViewModelTest.TestCase(123006.0, Locale.GERMANY, "123.006 €")
        );
    }

    @Test
    @Parameters(method = "simpleTestCase")
    public void test(CurrencyViewModelTest.TestCase testCase) {
        CurrencyViewModel model = new CurrencyViewModel(testCase.amount, testCase.locale);
        assertEquals(testCase.expectedOutput, model.getAmountWithSymbols());
    }
}
