package com.ortizmiguelangel.database.price.repository;

import com.ortizmiguelangel.database.price.model.PriceEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface ProductPriceRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    @Query("SELECT p from PriceEntity p where :productId = p.productId and :brandId = p.brandId and :requestTime between p.startDate and p.endDate")
    Flux<PriceEntity> findByProductIdAndBrandIdAndRequestTimeBetweenStartDateAndEndDate(@Param("productId") Long productId, @Param("brandId") Integer brandId, @Param("requestTime") LocalDateTime requestTime);
}
