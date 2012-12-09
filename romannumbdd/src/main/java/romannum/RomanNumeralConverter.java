package romannum;

public class RomanNumeralConverter {
    enum NumberToRoman {
        THOUSAND(1000, "M"),
        NINE_HUNDRED(900, "CM"), FIVE_HUNDRED(500, "D"), FOUR_HUNDRED(400, "CD"), HUNDRED(100, "C"),
        NINETY(90, "XC"), FIFTY(50, "L"), FORTY(40, "XL"), TEN(10, "X"),
        NINE(9, "IX"), FIVE(5, "V"), FOUR(4, "IV"), ONE(1, "I");

        private Integer number;
        private String romanNumeral;

        NumberToRoman(final Integer number, final String romanNumeral) {
            this.number = number;
            this.romanNumeral = romanNumeral;
        }
    }

    public static String convertNumberToRomanNumeral(Integer number) {
        String numeral = "";
        while (number > 0) {
            for (NumberToRoman numberToRoman : NumberToRoman.values()) {
                if (numberToRoman.number <= number) {
                    numeral += numberToRoman.romanNumeral;
                    number -= numberToRoman.number;
                    break;
                }
            }
        }

        return numeral;
    }
}
