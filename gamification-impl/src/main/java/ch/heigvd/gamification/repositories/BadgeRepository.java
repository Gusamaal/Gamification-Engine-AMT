package ch.heigvd.gamification.repositories;

import ch.heigvd.gamification.api.model.Badge;
import ch.heigvd.gamification.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BadgeRepository extends  PagingAndSortingRepository<BadgeEntity,Long>, CrudRepository<BadgeEntity, Long> {
    Iterable<BadgeEntity> findByApplicationEntity_ApiKey(String applicationEntity_apiKey, Pageable p);
}
