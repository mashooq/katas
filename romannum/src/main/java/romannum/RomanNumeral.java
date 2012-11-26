package romannum;

public class RomanNumeral {
    public static String convertToRoman(int digit) {
        String romanNumeral = "";
        romanNumeral += constructRomanNumeral(digit / 1000 % 10, "M", "", "");
        romanNumeral += constructRomanNumeral(digit / 100 % 10, "C", "D", "M");
        romanNumeral += constructRomanNumeral(digit / 10 % 10, "X", "L", "C");
        romanNumeral += constructRomanNumeral(digit % 10, "I", "V", "X");
        return romanNumeral;
    }

    private static String constructRomanNumeral(int times, String one, String five, String ten) {
        switch (times) {
            case 1: return one;
            case 2: return one + one;
            case 3: return one + one + one;
            case 4: return one + five;
            case 5: return five;
            case 6: return five + one;
            case 7: return five + one + one;
            case 8: return five + one + one + one;
            case 9: return one + ten;
            default:
                return "";
        }
    }
}
