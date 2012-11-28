package romannum;

public class RomanNumeralConverter {
    public static String convertNumerToRomanNumeral(final Integer number) {
        if (number == 1)
            return "I";
        else
            return "II";
    }
}
