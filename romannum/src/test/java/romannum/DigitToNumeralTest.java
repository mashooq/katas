package romannum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class DigitToNumeralTest {

    private int digit;
    private String romanNumeral;

    public DigitToNumeralTest(final int digit, final String romanNumeral) {
        this.digit = digit;
        this.romanNumeral = romanNumeral;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> digitToRomanMappings() {
        Object[][] mappings = new Object[][] {
                {1,"I"},
                {10, "X"},
                {7, "VII"},
                {9, "IX"},
                {11, "XI"},
                {40, "XL"},
                {50, "L"},
                {60, "LX"},
                {90, "XC"},
                {369, "CCCLXIX"},
                {448, "CDXLVIII"},
                {1999, "MCMXCIX"}
             };
        return Arrays.asList(mappings);
    }


    @Test
    public void convertToRoman() {
        assertThat(RomanNumeral.convertToRoman(digit).toString(), is(romanNumeral));
    }

}
