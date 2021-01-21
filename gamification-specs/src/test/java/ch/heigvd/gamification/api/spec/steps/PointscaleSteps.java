package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.Pointscale;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PointscaleSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private UserSteps userSteps;

    private Pointscale pointscale;


    // I might need to call the steps in a cascade way: call applicationSteps from Usersteps

    public PointscaleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, UserSteps userSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.userSteps = userSteps;
    }

    @Given("I have a pointscale payload")
    public void i_have_a_pointscale_payload() {
        pointscale = new ch.heigvd.gamification.api.dto.Pointscale()
                .label("pointsCounter");
    }

    @Given("I have a second pointscale payload")
    public void i_have_a_second_pointscale_payload() {
        pointscale = new ch.heigvd.gamification.api.dto.Pointscale()
                .label("secondCounter");
    }



    @When("^I POST the pointscale payload to the /pointscale endpoint$") // not plural, weird
    public void i_POST_the_pointscale_payload_to_the_pointscale_endpoint() {
        try {
            basicSteps.processApiResponse(api.createPointScalesWithHttpInfo(applicationSteps.getApiKey(), pointscale));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    public boolean isPayloadPointscale(Pointscale otherPointscale){
        return pointscale.getLabel().equals(otherPointscale.getLabel());
    }
}
