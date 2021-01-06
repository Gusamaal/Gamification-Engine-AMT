package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasicSteps {

    private Environment environment;
    private DefaultApi api;

    // the four horsemen of the apocalypse
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    // A lost horsman
    private String lastReceivedLocationHeader;

    public BasicSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("there is a Gamification server")
    public void there_is_a_Gamification_server() {
        assertNotNull(api);
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int expectedStatusCode) {
        assertEquals(expectedStatusCode, lastStatusCode);
    }

    // this is never used
    @Then("I receive a {int} status code with a location header")
    public void iReceiveAStatusCodeWithALocationHeader(int arg0) {
    }

    /*@When("I send a GET to the URL in the location header")
    public void iSendAGETToTheURLInTheLocationHeader() {
        Integer id = Integer.parseInt(lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
        try {
            lastApiResponse = api.getUserWithHttpInfo(id);
            processApiResponse(lastApiResponse);
            lastReceivedUser = (User)lastApiResponse.getData();
        } catch (ApiException e) {
            processApiException(e);
        }
    }*/

    public void processApiResponse(ApiResponse apiResponse) {
        lastApiResponse = apiResponse;
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
        List<String> locationHeaderValues = (List<String>)lastApiResponse.getHeaders().get("Location");
        lastReceivedLocationHeader = locationHeaderValues != null ? locationHeaderValues.get(0) : null;
    }

    public void processApiException(ApiException apiException) {
        lastApiCallThrewException = true;
        lastApiResponse = null;
        lastApiException = apiException;
        lastStatusCode = lastApiException.getCode();
    }

    public ApiResponse getlastApiResponse(){
        return lastApiResponse;
    }

    public int getLastApiResponseLocationId(){
        String[] elements = lastReceivedLocationHeader.split("/");
        return Integer.parseInt(elements[elements.length-1]);
    }
}
