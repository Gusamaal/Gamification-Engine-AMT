package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Event;
import ch.heigvd.gamification.api.dto.EventEventparams;
import ch.heigvd.gamification.api.dto.Reputation;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private BadgeSteps badgeSteps;
    private UserSteps userSteps;
    private PointscaleSteps pointscaleSteps;
    private RuleSteps ruleSteps;

    Event badgeEvent;
    EventEventparams badgeEventparams;

    Event pointscaleEvent;
    EventEventparams pointscaleEventparams;

    // I might need to call the steps in a cascade way
    public EventSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps , UserSteps userSteps, BadgeSteps badgeSteps, PointscaleSteps pointscaleSteps, RuleSteps ruleSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.userSteps = userSteps;
        this.badgeSteps = badgeSteps;
        this.pointscaleSteps = pointscaleSteps;
        this.ruleSteps = ruleSteps;
    }

    @Given("I have a badge event payload")
    public void i_have_an_event_payload() {
        badgeEventparams = new ch.heigvd.gamification.api.dto.EventEventparams()
                .username("Jean");
        badgeEvent = new ch.heigvd.gamification.api.dto.Event()
                .eventType("badgeAction")
                .eventparams(badgeEventparams)
                .timestamp((long)0);
    }

    @Given("I have a poinscale event payload")
    public void i_have_a_pointscale_event_payload() {
        pointscaleEventparams = new ch.heigvd.gamification.api.dto.EventEventparams()
                .username("Jean");
        pointscaleEvent = new ch.heigvd.gamification.api.dto.Event()
                .eventType("chaussette")
                .eventparams(pointscaleEventparams)
                .timestamp((long)0);
    }

    @When("^I POST the badge event payload to the /events endpoint$")
    public void i_POST_the_badge_event_payload_to_the_events_endpoint() {
        try {
            basicSteps.processApiResponse(api.processEventWithHttpInfo(applicationSteps.getApiKey(), badgeEvent));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I POST the pointscale event payload to the /events endpoint$")
    public void i_POST_the_pointscale_event_payload_to_the_events_endpoint() {
        try {
            basicSteps.processApiResponse(api.processEventWithHttpInfo(applicationSteps.getApiKey(), pointscaleEvent));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive a payload that correspond to all payloads")
    public void iReceiveAPayloadThatCorrespondToAllPayloads() {
        Reputation tmpReputation = userSteps.getLastReceivedReputation();
        assertTrue(userSteps.isPayloadUser(tmpReputation.getUsername()));
        assertTrue(badgeSteps.isPayloadBadge(tmpReputation.getBadges().get(0)));


        // add the rest
    }

    @And("I receive a badge that corresponds to the payload")
    public void iReceiveABadgeThatCorrespondsToThePayload() {
        assertTrue(badgeSteps.isPayloadBadge(userSteps.getLastReceivedBadge()));
    }

    @And("I receive a pointscale that corresponds to the payload")
    public void iReceiveAPointscaleThatCorrespondsToThePayload() {
        assertTrue(pointscaleSteps.isPayloadPointscale(userSteps.getLastReceivedPointscale()));
    }
}
