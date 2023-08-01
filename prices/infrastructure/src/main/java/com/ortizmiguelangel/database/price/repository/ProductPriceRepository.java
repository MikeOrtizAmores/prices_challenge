package com.ortizmiguelangel.database.price.repository;

import com.ortizmiguelangel.database.price.model.PriceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface ProductPriceRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    Flux<PriceEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Integer brandId, LocalDateTime requestTime, LocalDateTime sameRequestTime);
}
