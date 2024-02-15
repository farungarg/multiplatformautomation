package org.test.stepDefinations.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.test.drivers.DriverFactory;
import org.test.pages.api.RegresAPIPage;
import org.test.pages.web.ControlgroupPage;
import org.test.pages.web.DroppablePage;
import org.test.pages.web.SelectablePage;
import org.test.stepDefinations.web.JQueryWebSteps;
import org.test.utilities.CommonHelper;
import org.test.utilities.ExtentTestManager;
import org.test.utilities.PropertyHandler;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ReqResAPISteps extends CommonHelper {

    public static final Logger log = Logger.getLogger(ReqResAPISteps.class.getName());

    Scenario scenario;
    RegresAPIPage regresAPIPage;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        child = ExtentTestManager.startTest(scenario.getName(), "");
        regresAPIPage = new RegresAPIPage();
    }

    @Given("Get the users details at page {string}")
    public void get_the_users_details_at_page(String pageNumber) {
        try {
            regresAPIPage.getUsers(pageNumber);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    @When("Status Code is {int}")
    public void status_code_is(Integer statusCode) {
        try {
            regresAPIPage.verifyStatusCodeOfGetUser(statusCode);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    @Then("Verify the value of {string} for {string} is {string}")
    public void verify_the_value_of_for_is(String field, String id, String value) {
        try {
            regresAPIPage.verifyUser(field, id, value);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    @Given("Create user with {string} and {string}")
    public void create_user_with_and(String name, String job) {
    regresAPIPage.createUser(name,job);
    }
    @Then("Verify that id is generated")
    public void verify_that_id_is_generated() {
       regresAPIPage.verifyID();
    }
    @Then("Response matches its JSON Schema")
    public void response_matches_its_json_schema() {
       regresAPIPage.validateJSONSchemaForPostUser();
    }


    @After
    public void after() {
      ExtentTestManager.endTest();
    }
}