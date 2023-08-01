package com.ortizmiguelangel.port.in;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.model.ProductPrice;
import reactor.core.publisher.Mono;

public interface ProductPriceQueryService {

    Mono<ProductPrice> getProductPriceByDate(GetProductPriceOnDateQuery getProductPriceOnDateQuery);
}
