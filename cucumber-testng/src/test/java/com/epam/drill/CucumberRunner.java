package com.epam.drill;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "cucumber-testng/src/test/resources/com.epam.drill")
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
