package ch.heigvd.gamification.api.endpoints;

import ch.heigvd.gamification.api.BadgesApi;
import ch.heigvd.gamification.api.services.ApplicationService;
import ch.heigvd.gamification.api.services.BadgeService;
import ch.heigvd.gamification.entities.ApplicationEntity;
import ch.heigvd.gamification.entities.BadgeEntity;
import ch.heigvd.gamification.repositories.ApplicationRepository;
import ch.heigvd.gamification.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ch.heigvd.gamification.api.model.Badge;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ch.heigvd.gamification.api.util.BadgeUtils.*;


@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeService badgeService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createBadge(UUID X_API_KEY, @Valid Badge badge) {
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);

        badgeService.createBadge(newBadgeEntity, X_API_KEY);



        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createBadge(Badge badge){
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);

        badgeService.createBadge(newBadgeEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges(@ApiParam(value = "" ,required=true) @RequestHeader(value="X-API-KEY", required=true) UUID X_API_KEY, @ApiParam(value = "The number of items to skip before starting to collect the result set.") @Valid @RequestParam(value = "offset", required = false) Integer offset, @ApiParam(value = "The number of items to return.") @Valid @RequestParam(value = "limit", required = false) Integer limit) {

        List<Badge> badges = new ArrayList<>();


        badgeService.findByApplicationEntity_ApiKey(X_API_KEY.toString(),offset,limit)
                .forEach(badgeEntity -> badges.add(toBadge(badgeEntity)));

        return ResponseEntity.ok(badges);
    }


}
