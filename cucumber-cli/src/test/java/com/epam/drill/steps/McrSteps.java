package com.epam.drill.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class McrSteps {

    @When("^user open (.*)")
    public void user_open_link(String link) {
        open(CommonSteps.getPetclinicUrl() + link);
    }

    @Then("^user should see page info (.*)")
    public void user_should_see_page_info(String header) {
        $x("//div[@class='ng-scope']").shouldHave(text(header));
    }

    @When("^user search owner by (.*)")
    public void user_search_owner_by(String text) {
        $x("//a[text()='Owners']").click();
        $x("//a[@href='#!/owners']").click();
        $x("//input[@placeholder='Search Filter']").sendKeys(text);
        $x("//input[@placeholder='Search Filter']").submit();
    }

    @Then("^he find owner (.*)")
    public void he_find_owner(String owner) {
        $x("//a[@href='#!/owners/details/2']").shouldHave(text(owner));
    }
}
