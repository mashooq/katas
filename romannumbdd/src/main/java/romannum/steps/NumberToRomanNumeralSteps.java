package romannum.steps;

import org.jbehave.core.annotations.*;
import romannum.RomanNumeralConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumberToRomanNumeralSteps {
    private Integer number;
    private String expectedRomanNumeral;
    private String calculatedRomanNumeral;

    @Given("a <Number> and it's corresponding <Roman Numeral>")
    public void givenANumberAndItsCorrespondingRomanNumeral(@Named("Number") Integer number,
                                                            @Named("Roman Numeral") String romanNumeral) {
        this.number = number;
        this.expectedRomanNumeral = romanNumeral;
    }

    @When("I convert from Number to Roman Numeral")
    public void whenIConvertFromNumberToRomanNumeral() {
        this.calculatedRomanNumeral = RomanNumeralConverter.convertNumerToRomanNumeral(number);
    }

    @Then("the corresponding Roman Numeral is provided")
    public void thenTheCorrespondingRomanNumeralIsProvided() {
        assertThat(calculatedRomanNumeral, is(expectedRomanNumeral));
    }

}
