package com.ortizmiguelangel.database.price;

import com.ortizmiguelangel.database.price.mapper.PriceMapper;
import com.ortizmiguelangel.database.price.model.PriceEntity;
import com.ortizmiguelangel.database.price.repository.ProductPriceRepository;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.out.ProductPriceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class JpaProductPriceDao implements ProductPriceDao {

    private final ProductPriceRepository productPriceRepository;

    private final PriceMapper priceMapper;

    @Transactional
    @Override
    public Flux<ProductPrice> getProductPricesOnDate(Long productId, Integer brandId, Long currentDateMilis) {
        LocalDateTime currentDateTime = Instant.ofEpochMilli(currentDateMilis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Flux<PriceEntity> prices = productPriceRepository.findByProductIdAndBrandIdAndRequestTimeBetweenStartDateAndEndDate(productId, brandId, currentDateTime);
        return prices.map(priceMapper::priceEntityToProductPrice);
    }
}
