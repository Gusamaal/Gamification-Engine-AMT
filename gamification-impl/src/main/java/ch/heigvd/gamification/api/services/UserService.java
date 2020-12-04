package ch.heigvd.gamification.api.services;

import ch.heigvd.gamification.entities.ApplicationEntity;
import ch.heigvd.gamification.entities.UserEntity;
import ch.heigvd.gamification.repositories.ApplicationRepository;
import ch.heigvd.gamification.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public UserEntity createUser(UserEntity newUserEntity, String X_API) {
        ApplicationEntity applicationEntity = applicationRepository.findByApiKey(X_API.toString());
        newUserEntity.setApplicationEntity(applicationEntity);
        userRepository.save(newUserEntity);
        return newUserEntity;
    }

    public Iterable<UserEntity> findByApplicationEntity_ApiKey(String toString) {
        return userRepository.findByApplicationEntity_ApiKey(toString);
    }

    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findByApplicationEntity_ApiKeyAndId(String toString, Long valueOf) {
        return userRepository.findByApplicationEntity_ApiKeyAndId(toString, valueOf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
