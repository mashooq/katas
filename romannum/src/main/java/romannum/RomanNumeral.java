package romannum;

public class RomanNumeral {
    public static String convertToRoman(int digit) {
        String romanNumeral = "";
        romanNumeral += getTens(digit / 10 % 10);
        romanNumeral += getOnes(digit % 10);
        return romanNumeral;
    }

    private static String getOnes(final int times) {
        switch (times) {
            case 0: return "";
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            default:
                return "";

        }
    }

    private static String getTens(final int times) {
        switch (times) {
            case 1: return "X";
            case 2: return "XX";
            case 3: return "XXX";
            case 4: return "LX";
            case 5: return "L";
            case 6: return "LX";
            case 7: return "LXX";
            case 8: return "LXXX";
            case 9: return "XC";
            default:
                 return "";

        }
    }
}
