package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.spec.helpers.Environment;

public class PointscaleSteps {

    private Environment environment;
    private DefaultApi api;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private UserSteps userSteps;


    // I might need to call the steps in a cascad way: call applicationSteps from Usersteps

    public PointscaleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, UserSteps userSteps) {
        this.environment = environment;
        this.api = environment.getApi();
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.userSteps = userSteps;
    }
}
