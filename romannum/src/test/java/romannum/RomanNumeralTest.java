package romannum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static romannum.RomanNumeral.*;

@RunWith(value = Parameterized.class)
public class RomanNumeralTest {
    private int number;
    private String romanNumeral;

    public RomanNumeralTest(final int number, final String romanNumeral) {
        this.number = number;
        this.romanNumeral = romanNumeral;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> numberToRomanNumeralMappings() {
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
    public void convertToRomanNumeral() {
        assertThat(convertToRoman(number).toString(), is(romanNumeral));
    }

}
