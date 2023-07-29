package com.ortizmiguelangel.service.price;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.in.ProductPriceService;
import com.ortizmiguelangel.port.out.ProductPriceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DefaultProductPriceService implements ProductPriceService {

    private final ProductPriceDao productPriceDao;

    @Override
    public Mono<ProductPrice> getProductPriceByDate(GetProductPriceOnDateQuery getProductPriceOnDateQuery) {
        Flux<ProductPrice> productPriceFlux = productPriceDao.getProductPricesOnDate(
                getProductPriceOnDateQuery.getProductId(),
                getProductPriceOnDateQuery.getTenantId(),
                getProductPriceOnDateQuery.getDateInMilis());
        return productPriceFlux.reduce((price1, price2) -> price1.priority() > price2.priority() ? price1 : price2);
    }
}
