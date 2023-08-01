package com.ortizmiguelangel.database.price;

import com.ortizmiguelangel.database.price.mapper.PriceMapper;
import com.ortizmiguelangel.database.price.model.PriceEntity;
import com.ortizmiguelangel.database.price.repository.ProductPriceRepository;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.out.ProductPriceQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class JpaProductPriceQueryDao implements ProductPriceQueryDao {

    private final ProductPriceRepository productPriceRepository;

    private final PriceMapper priceMapper;

    @Transactional
    @Override
    public Flux<ProductPrice> getProductPricesOnDate(Long productId, Integer brandId, Long currentDateMilis) {
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentDateMilis), ZoneOffset.UTC);
        Flux<PriceEntity> allMatchingPrices = productPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, currentDateTime, currentDateTime);
        return allMatchingPrices
                .map(priceMapper::priceEntityToProductPrice);
    }
}
