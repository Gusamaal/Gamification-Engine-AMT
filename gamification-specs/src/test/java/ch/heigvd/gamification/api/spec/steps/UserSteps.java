package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.Pointscale;
import ch.heigvd.gamification.api.dto.Reputation;
import ch.heigvd.gamification.api.dto.User;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;

    private User user;

    private User lastReceivedUser;
    private Reputation lastReceivedReputation;
    private int lastReceivedUserId;
    private Badge lastReceivedBadge;
    private Pointscale lastReceivedPointscale;
    private int lastReceivedPointscaleTotal;

    public  UserSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
    }

    @Given("I have a user payload")
    public void i_have_a_user_payload() {
        user = new ch.heigvd.gamification.api.dto.User()
                .username("Jean");
    }

    // we need to get the created user id
    @When("^I POST the user payload to the /users endpoint$")
    public void i_POST_the_user_payload_to_the_users_endpoint() {
        try {
            basicSteps.processApiResponse(api.createUserWithHttpInfo(applicationSteps.getApiKey(), user));
            lastReceivedUserId=basicSteps.getLastApiResponseLocationId();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /users endpoint$")
    public void i_send_a_GET_to_the_users_endpoint() {
        try {
            basicSteps.processApiResponse(api.getUsersWithHttpInfo(applicationSteps.getApiKey()));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /user endpoint$")
    public void i_send_a_GET_to_the_user_endpoint() {
        try {
            basicSteps.processApiResponse(api.getUserWithHttpInfo(lastReceivedUserId,applicationSteps.getApiKey()));
            lastReceivedUser = (User) basicSteps.getlastApiResponse().getData();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive a payload that correspond to the user payload")
    public void i_receive_a_payload_that_corresponds_to_the_user_payload() {
        // assertEquals(user.getUsername(), lastReceivedUser.getUsername());
        assertTrue(isPayloadUser(lastReceivedUser.getUsername()));
    }

    @When("I send a GET to the users-reputations endpoint")
    public void i_send_a_GET_to_the_users_reputations_endpoint() {
        try {
            basicSteps.processApiResponse(api.getReputationsWithHttpInfo(applicationSteps.getApiKey()));
            lastReceivedReputation = ((ArrayList<Reputation>) basicSteps.getlastApiResponse().getData()).get(0);
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    public Reputation getLastReceivedReputation(){
        return lastReceivedReputation;
    }

    public boolean isPayloadUser(String otherUserUsername){
        return user.getUsername().equals(otherUserUsername);
    }


    @When("I send a GET to the users-id-badges endpoint")
    public void i_send_a_GET_to_the_users_id_badges_endpoint() {
        try {
            basicSteps.processApiResponse(api.getUsersBadgesWithHttpInfo(lastReceivedUserId,applicationSteps.getApiKey()));
            lastReceivedBadge = ( (ArrayList<Badge>) basicSteps.getlastApiResponse().getData()).get(0);
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    public Badge getLastReceivedBadge() {
        return lastReceivedBadge;
    }

    public Pointscale getLastReceivedPointscale(){
        return lastReceivedPointscale;
    }

    @When("I send a GET to the users-id-pointscale endpoint")
    public void iSendAGETToTheUsersIdPointscaleEndpoint() {
        try {
            basicSteps.processApiResponse(api.getUsersPointScalesWithHttpInfo(lastReceivedUserId,applicationSteps.getApiKey()));
            lastReceivedPointscale = ( (ArrayList<Pointscale>) basicSteps.getlastApiResponse().getData()).get(0);
            lastReceivedPointscaleTotal = lastReceivedPointscale.getPointCounter();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive an unchanged pointscale total")
    public void iReceiveAnUnchangedPointscaleTotal() {
        assertEquals(0,lastReceivedPointscaleTotal);
    }
}
