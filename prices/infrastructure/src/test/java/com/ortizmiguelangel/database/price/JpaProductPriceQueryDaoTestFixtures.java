package com.ortizmiguelangel.database.price;

import com.ortizmiguelangel.database.price.model.PriceEntity;
import com.ortizmiguelangel.model.Currency;
import com.ortizmiguelangel.model.ProductPrice;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class JpaProductPriceQueryDaoTestFixtures {

    public final static PriceEntity RESPONSE_PRODUCT_1 = PriceEntity.builder()
            .priceList(1L)
            .productId(1L)
            .brandId(1)
            .startDate(OffsetDateTime.now().minusDays(1).toLocalDateTime())
            .endDate(OffsetDateTime.now().toLocalDateTime())
            .price(50.23f)
            .curr("EUR")
            .priority(1).build();
    public final static ProductPrice MAPPED_PRODUCT_1 = new ProductPrice(
            RESPONSE_PRODUCT_1.getPriceList(),
            RESPONSE_PRODUCT_1.getProductId(),
            RESPONSE_PRODUCT_1.getBrandId(),
            RESPONSE_PRODUCT_1.getPrice(),
            Currency.valueOf(RESPONSE_PRODUCT_1.getCurr()),
            RESPONSE_PRODUCT_1.getPriority(),
            RESPONSE_PRODUCT_1.getStartDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
            RESPONSE_PRODUCT_1.getEndDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    public final static PriceEntity RESPONSE_PRODUCT_2 = PriceEntity.builder()
            .priceList(10L)
            .productId(1L)
            .brandId(1)
            .startDate(OffsetDateTime.now().minusDays(1).toLocalDateTime())
            .endDate(OffsetDateTime.now().toLocalDateTime())
            .price(50.23f)
            .curr("EUR")
            .priority(1).build();
    public final static ProductPrice MAPPED_PRODUCT_2 = new ProductPrice(
            RESPONSE_PRODUCT_2.getPriceList(),
            RESPONSE_PRODUCT_2.getProductId(),
            RESPONSE_PRODUCT_2.getBrandId(),
            RESPONSE_PRODUCT_2.getPrice(),
            Currency.valueOf(RESPONSE_PRODUCT_2.getCurr()),
            RESPONSE_PRODUCT_2.getPriority(),
            RESPONSE_PRODUCT_2.getStartDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
            RESPONSE_PRODUCT_2.getEndDate().toInstant(ZoneOffset.UTC).toEpochMilli());
}
