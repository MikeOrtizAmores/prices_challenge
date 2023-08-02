package com.ortizmiguelangel.service.price;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.exception.PriceNotFoundException;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.in.ProductPriceQueryService;
import com.ortizmiguelangel.port.out.ProductPriceQueryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class DefaultProductPriceQueryService implements ProductPriceQueryService {

    private final ProductPriceQueryDao productPriceQueryDao;

    @Override
    public Mono<ProductPrice> getProductPriceByDate(GetProductPriceOnDateQuery getProductPriceOnDateQuery) {
        return productPriceQueryDao.getProductPricesOnDate(
                        getProductPriceOnDateQuery.getProductId(),
                        getProductPriceOnDateQuery.getTenantId(),
                        getProductPriceOnDateQuery.getDateInMillis())
                .switchIfEmpty(Mono.error(new PriceNotFoundException(
                        "Price for product " + getProductPriceOnDateQuery.getProductId() +
                                " of brand " + getProductPriceOnDateQuery.getTenantId() +
                                " and at date " + OffsetDateTime.ofInstant(Instant.ofEpochMilli(getProductPriceOnDateQuery.getDateInMillis()), ZoneOffset.UTC) +
                                " could not be found")))
                .reduce(this::getProductPriceWithHigherPriority);
    }

    private ProductPrice getProductPriceWithHigherPriority(ProductPrice price1, ProductPrice price2) {
        if (price1.priority() > price2.priority()) {
            return price1;
        } else if (price1.priority().equals(price2.priority())) {
            return price1.price() > price2.price() ? price1 : price2;
        } else {
            return price2;
        }
    }
}
