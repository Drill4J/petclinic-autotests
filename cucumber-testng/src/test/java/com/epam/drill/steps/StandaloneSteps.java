package com.epam.drill.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StandaloneSteps {

    @When("^user click to tab (.*)")
    public void user_click_to_tab_home_page(String tab) {
        String xpathElement = String.format("//a[@title='%s']", tab);
        $x(xpathElement).click();
    }

    @Then("^user should see header (.*)")
    public void user_should_see_header_welcome(String header) {
        $x("//h2").shouldHave(text(header));
    }

    @And("^type last name (.*)")
    public void type_last_name(String lastName) {
        $(By.id("lastName")).sendKeys(lastName);
    }

    @And("^click button (.*)")
    public void click_button(String button) {
        String xpathElement = String.format("//button[@type='%s']", button);
        $x("//button[@type='submit']").click();
    }

    @Then("^user should see result (.*)")
    public void user_should_see_result(String result) {
        $x("//a[@href='/owners/2']").shouldHave(text(result));
    }

}
