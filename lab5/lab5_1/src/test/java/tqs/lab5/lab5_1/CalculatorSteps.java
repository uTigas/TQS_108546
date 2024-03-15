package tqs.lab5.lab5_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {


    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} to {int}")
    public void multiply(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I mod {int} to {int}")
    public void invalid(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("mod");
    }

    @Then("the result is {}")
    public void the_result_is(String expected) {
        Object value = calc.value();
        assertEquals(expected, value);
    }

}