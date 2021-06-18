package com.epam.drill.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.open;

public class CommonSteps {

    private static String petclinicUrl;

    public static String getPetclinicUrl() {
        return petclinicUrl;
    }

    public static void setPetclinicUrl(String petclinicUrl) {
        CommonSteps.petclinicUrl = petclinicUrl;
    }

    @Given("^main page of Petclinic is opened in (.*)")
    public void main_page_of_petclinic_is_opened_in(String browser) {
        setPetclinicUrl(System.getProperty("petclinicUrl", "http://localhost:8087"));
        Configuration.browser = browser;
        Configuration.remote = "http://ecse005002a0.epam.com:4444/wd/hub";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        open(petclinicUrl);
    }

    @Then("failed assertion")
    public void failedAssertion() {
        Assert.fail();
    }

}
