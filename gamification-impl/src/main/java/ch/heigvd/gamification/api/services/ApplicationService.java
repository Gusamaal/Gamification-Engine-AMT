package ch.heigvd.gamification.api.services;

import ch.heigvd.gamification.entities.ApplicationEntity;
import ch.heigvd.gamification.repositories.ApplicationRepository;
import ch.heigvd.gamification.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationService {
    final private ApplicationRepository applicationRepository;

    public void create(ApplicationEntity newApplicationEntity) {
        applicationRepository.save(newApplicationEntity);
    }

    public ApplicationEntity findByApiKey(String X_API_KEY) {
        return applicationRepository.findByApiKey(X_API_KEY.toString());
    }
}
