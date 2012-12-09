package romannum.steps;

import org.jbehave.core.annotations.*;
import romannum.RomanNumeralConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumberToRomanNumeralSteps {
    private Integer number;
    private String calculatedRomanNumeral;

    @Given("a <Number>")
    public void givenANumberAndItsCorrespondingRomanNumeral(@Named("Number") Integer number) {
        this.number = number;
    }

    @When("I convert")
    public void whenIConvertFromNumberToRomanNumeral() {
        this.calculatedRomanNumeral = RomanNumeralConverter.convertNumberToRomanNumeral(number);
    }

    @Then("the <Roman Numeral> is provided")
    public void thenTheCorrespondingRomanNumeralIsProvided(@Named("Roman Numeral") String expectedRomanNumeral) {
        assertThat(calculatedRomanNumeral, is(expectedRomanNumeral));
    }

}
