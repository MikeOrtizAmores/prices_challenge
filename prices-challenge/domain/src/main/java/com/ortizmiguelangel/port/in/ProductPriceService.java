package com.ortizmiguelangel.port.in;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.model.ProductPrice;
import reactor.core.publisher.Mono;

public interface ProductPriceService {

    Mono<ProductPrice> getProductPriceByDate(GetProductPriceOnDateQuery getProductPriceOnDateQuery);
}
