package ch.heigvd.gamification.api.services;

import ch.heigvd.gamification.entities.ApplicationEntity;
import ch.heigvd.gamification.entities.BadgeEntity;
import ch.heigvd.gamification.repositories.ApplicationRepository;
import ch.heigvd.gamification.repositories.BadgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BadgeService {


    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public void createBadge(BadgeEntity newBadgeEntity, UUID x_api_key) {
        ApplicationEntity applicationEntity = applicationRepository.findByApiKey(x_api_key.toString());

        newBadgeEntity.setApplicationEntity(applicationEntity);
        badgeRepository.save(newBadgeEntity);
    }

    public void createBadge(BadgeEntity newBadgeEntity) {
        badgeRepository.save(newBadgeEntity);
    }

    public Iterable<BadgeEntity> findByApplicationEntity_ApiKey(String X_API_KEY) {
        return badgeRepository.findByApplicationEntity_ApiKey(X_API_KEY.toString());
    }
}
