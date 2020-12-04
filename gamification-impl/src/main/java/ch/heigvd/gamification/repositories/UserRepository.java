package ch.heigvd.gamification.repositories;

import ch.heigvd.gamification.entities.BadgeEntity;
import ch.heigvd.gamification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long>, CrudRepository<UserEntity, Long> {
    Iterable<UserEntity> findByApplicationEntity_ApiKey(String applicationEntity_apiKey, Pageable p);
    Optional<UserEntity> findByApplicationEntity_ApiKeyAndUsername(String applicationEntity_apiKey, String username);
    Optional<UserEntity> findByApplicationEntity_ApiKeyAndId(String applicationEntity_apiKey, Long id);
}
