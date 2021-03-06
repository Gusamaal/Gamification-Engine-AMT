package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BadgeSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;

    private Badge badge;

    public BadgeSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
    }

    @Given("I have a badge payload")
    public void i_have_a_badge_payload() {
        badge = new ch.heigvd.gamification.api.dto.Badge()
                .name("goodBoiBadge")
                .color("red")
                .description("forGoodBoisOnly");
    }

    @When("^I POST the badge payload to the /badges endpoint$")
    public void i_POST_the_badge_payload_to_the_badges_endpoint() {
        try {
            basicSteps.processApiResponse(api.createBadgeWithHttpInfo(applicationSteps.getApiKey(), badge));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /badges endpoint$")
    public void i_send_a_GET_to_the_badges_endpoint() {
        try {
            basicSteps.processApiResponse(api.getBadgesWithHttpInfo(applicationSteps.getApiKey()));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }


    public boolean isPayloadBadge(Badge otherBadge){
        return badge.getName().equals(otherBadge.getName())
                && badge.getColor().equals(otherBadge.getColor())
                && badge.getDescription().equals(otherBadge.getDescription());
    }
}
