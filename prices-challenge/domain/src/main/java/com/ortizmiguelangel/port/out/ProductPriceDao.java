package com.ortizmiguelangel.port.out;

import com.ortizmiguelangel.model.ProductPrice;
import reactor.core.publisher.Flux;

public interface ProductPriceDao {

    Flux<ProductPrice> getProductPricesOnDate(Long productId, Integer brandId, Long currentDateMilis);
}
