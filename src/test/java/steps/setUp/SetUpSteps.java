package steps.setUp;

import annotations.LazyAutowired;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.LoginPage;
import runner.TestContext;
import steps.BaseSteps;

public class SetUpSteps extends BaseSteps {

    private static final Logger logger = LoggerFactory.getLogger(SetUpSteps.class);

    @LazyAutowired
    LoginPage loginPage;

    public SetUpSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("User unlock the cellphone")
    public void unlocksCellphone() {
        System.out.println("LLEGA POR ACAAAAAAAAAAAAAAAAAAAA SET UP STEPS!!");
        logger.info("LLEGA POR ACAAAAAAAAAAAAAAAAAAAA");
    }

    @Given("User tap Chrome button")
    public void tapChromeButton() {
        loginPage.tapNotifications();
    }
//
//    @When("User type {string} in the searcher")
//    public void search(String elementToSearch) {
//        //TODO
//    }
//
//    @Then("User see a list of results")
//    public void getResultList() {
//        //TODO
//    }

}
