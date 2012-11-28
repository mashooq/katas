package romannum;

public class RomanNumeralConverter {
    enum NumberToRoman {
        ONE(1, "I"), TWO(2, "II"), THREE(3, "III");

        private Integer number ;
        private String romanNumeral;

        NumberToRoman(final Integer number, final String romanNumeral) {
            this.number = number;
            this.romanNumeral = romanNumeral;
        }
    }
    public static String convertNumerToRomanNumeral(final Integer number) {
        for(NumberToRoman numberToRoman : NumberToRoman.values()) {
            if(numberToRoman.number == number) {
                return numberToRoman.romanNumeral;
            }
        }

        return null;
    }
}
