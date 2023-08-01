package com.ortizmiguelangel.service.price;

import com.ortizmiguelangel.model.Currency;
import com.ortizmiguelangel.model.ProductPrice;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

public class DefaultProductPriceQueryTestFixtures {

    public static final List<ProductPrice> LIST_OF_ONE_PRICE = List.of(
            new ProductPrice(10L, 20L, 1, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis())
    );

    public static final List<ProductPrice> LIST_OF_DETERMINISTIC = List.of(
            new ProductPrice(10L, 20L, 1, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(11L, 20L, 3, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(12L, 20L, 4, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(13L, 20L, 2, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis())
    );
    public static final Mono<ProductPrice> EXPECTED_LIST_OF_DETERMINISTIC = Mono.just(LIST_OF_DETERMINISTIC.get(3));

    public static final List<ProductPrice> LIST_OF_NON_DETERMINISTIC = List.of(
            new ProductPrice(10L, 20L, 4, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(11L, 20L, 4, 39.81f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(12L, 20L, 4, 35.12f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis()),
            new ProductPrice(13L, 20L, 4, 31.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis())
    );
    public static final Mono<ProductPrice> EXPECTED_LIST_OF_NON_DETERMINISTIC = Mono.just(LIST_OF_NON_DETERMINISTIC.get(1));

    public static final List<ProductPrice> LIST_EMPTY = Collections.emptyList();
}
