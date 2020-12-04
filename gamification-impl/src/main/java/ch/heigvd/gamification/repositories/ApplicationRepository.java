package ch.heigvd.gamification.repositories;

import ch.heigvd.gamification.api.model.Application;
import ch.heigvd.gamification.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity,Long>, CrudRepository<ApplicationEntity,Long> {
    ApplicationEntity findByApiKey(String apiKey, Pageable p);
    ApplicationEntity findByApiKey(String apiKey);
}
