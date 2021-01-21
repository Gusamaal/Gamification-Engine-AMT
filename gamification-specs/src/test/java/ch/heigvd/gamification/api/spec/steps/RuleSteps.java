package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Rule;
import ch.heigvd.gamification.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RuleSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private UserSteps userSteps;

    private Rule rule;


    // I might need to call the steps in a cascade way: call applicationSteps from Usersteps

    public RuleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, UserSteps userSteps) { // TODO add all steps ?
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.userSteps = userSteps;
    }

    @Given("I have a badge rule payload")
    public void i_have_a_badge_rule_payload() {
        rule = new ch.heigvd.gamification.api.dto.Rule()
                ._if(new ch.heigvd.gamification.api.dto.RuleIf()
                .eventType("badgeAction"))
        .then(new ch.heigvd.gamification.api.dto.RuleThen()
                .action(new ch.heigvd.gamification.api.dto.RuleThenAction()
                        .name("addBadge")
                        .target("red") // targeted badge
                        .amount(0) // no points when badgeAction
                )
        ); //this is pure insanity
    }

    @Given("I have a point rule payload")
    public void i_have_a_point_rule_payload() {
        rule = new ch.heigvd.gamification.api.dto.Rule()
                ._if(new ch.heigvd.gamification.api.dto.RuleIf()
                        .eventType("pointAction"))
                .then(new ch.heigvd.gamification.api.dto.RuleThen()
                        .action(new ch.heigvd.gamification.api.dto.RuleThenAction()
                                .name("addPoint")
                                .target("pointsCounter") // targeted pointscale
                                .amount(1)
                        )
                ); //this is still pure insanity
    }

    // we need to get the created rule id
    @When("^I POST the rule payload to the /rules endpoint$")
    public void i_POST_the_rule_payload_to_the_rules_endpoint() {
        try {
            basicSteps.processApiResponse(api.createRuleWithHttpInfo(applicationSteps.getApiKey(), rule));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /rules endpoint$")
    public void i_send_a_GET_to_the_rules_endpoint() {
        try {
            basicSteps.processApiResponse(api.getRulesWithHttpInfo(applicationSteps.getApiKey()));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }
}
