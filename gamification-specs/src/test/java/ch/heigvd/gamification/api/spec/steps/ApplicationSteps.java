package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.ApiKey;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.spec.helpers.Environment;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ApplicationSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;

    private Application application;
    private UUID apiKey;
    private Application lastReceivedApplication;


    public ApplicationSteps(Environment environment, BasicSteps basicSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps=basicSteps;
    }

    public UUID getApiKey() {
        return apiKey;
    }

    @Given("I have an application payload")
    public void i_have_an_application_payload() {
        application = new ch.heigvd.gamification.api.dto.Application()
                .name("testApp");
    }

    @When("^I POST the application payload to the /applications endpoint$")
    public void i_POST_the_application_payload_to_the_applications_endpoint() {
        try {
            basicSteps.processApiResponse(api.createApplicationWithHttpInfo(application));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @Given("I have a correct API key")
    public void i_have_a_correct_API_key(){
        apiKey = ((ApiKey) basicSteps.getlastApiResponse().getData()).getKey();
    }

    @When("^I send a GET to the /applications endpoint with an API Key")
    public void i_send_a_GET_to_the_applications_endpoint_with_an_API_key() {
        try {
            basicSteps.processApiResponse(api.getApplicationWithHttpInfo(apiKey));
            lastReceivedApplication = (Application) basicSteps.getlastApiResponse().getData();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @Given("I have a random API Key")
    public void i_have_a_random_API_key() {
        apiKey = UUID.randomUUID();
    }

    @And("I receive a payload that corresponds to the application payload")
    public void i_receive_a_payload_that_corresponds_to_the_application_payload(){
        assertEquals(application.getName(), lastReceivedApplication.getName());
    }
}
