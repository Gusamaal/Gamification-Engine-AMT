package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.spec.helpers.Environment;

public class RuleSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private UserSteps userSteps;


    // I might need to call the steps in a cascad way: call applicationSteps from Usersteps

    public RuleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, UserSteps userSteps) { // TODO add all steps ?
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.userSteps = userSteps;
    }
}
