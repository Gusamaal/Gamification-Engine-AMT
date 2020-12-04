package ch.heigvd.gamification.api.endpoints;


import ch.heigvd.gamification.api.UsersApi;
import ch.heigvd.gamification.api.model.Application;
import ch.heigvd.gamification.api.model.Badge;
import ch.heigvd.gamification.api.model.User;
import ch.heigvd.gamification.api.services.UserService;
import ch.heigvd.gamification.entities.ApplicationEntity;
import ch.heigvd.gamification.entities.UserEntity;

import ch.heigvd.gamification.repositories.ApplicationRepository;
import ch.heigvd.gamification.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ch.heigvd.gamification.api.util.UserUtils.*;
import static ch.heigvd.gamification.api.util.BadgeUtils.*;


@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserService userService;


    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUser(UUID X_API_KEY, @Valid User user) {
        UserEntity newUserEntity = toUserEntity(user);
        newUserEntity = userService.createUser(newUserEntity, X_API_KEY.toString());

        Long id = newUserEntity.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "" ,required=true) @RequestHeader(value="X-API-KEY", required=true) UUID X_API_KEY,@ApiParam(value = "The number of items to skip before starting to collect the result set.") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "The number of items to return.") @Valid @RequestParam(value = "limit", required = false) Integer limit) {

        List<User> users = new ArrayList<>();

        userService.findByApplicationEntity_ApiKey(X_API_KEY.toString(), offset, limit)
                .forEach(userEntity -> users.add(toUser(userEntity)));

        return ResponseEntity.ok(users);
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userService.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUser(Integer id, UUID X_API_KEY) {
        UserEntity existingUserEntity = userService
                .findByApplicationEntity_ApiKeyAndId(X_API_KEY.toString(),Long.valueOf(id));

        return ResponseEntity.ok(toUser(existingUserEntity));
    }

    @Override
    public ResponseEntity<List<Badge>> getUsersBadges(@ApiParam(value = "",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true) @RequestHeader(value="X-API-KEY", required=true) UUID X_API_KEY,@ApiParam(value = "The number of items to skip before starting to collect the result set.") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "The number of items to return.") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        List<Badge> badges = new ArrayList<>();
        UserEntity existingUserEntity = userService
                .findByApplicationEntity_ApiKeyAndId(X_API_KEY.toString(),Long.valueOf(id));

        existingUserEntity.getBadgeEntitys().forEach(badgeEntity -> badges.add(toBadge(badgeEntity)));
        return ResponseEntity.ok(badges);
    }

}
