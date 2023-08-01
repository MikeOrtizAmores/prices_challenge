package com.ortizmiguelangel.api.price.mapper;

import com.ortizmiguelangel.generatedapi.query.model.PriceDTO;
import com.ortizmiguelangel.model.Currency;
import com.ortizmiguelangel.model.ProductPrice;

import java.time.OffsetDateTime;

public class PriceDTOQueryMapperTestFixture {

    public final static ProductPrice UNMAPPED_PRODUCT_PRICE = new ProductPrice(
            100L,
            999L,
            2,
            90.43f,
            Currency.USD,
            3,
            OffsetDateTime.parse("2020-06-10T10:00:00Z").toInstant().toEpochMilli(),
            OffsetDateTime.parse("2020-06-15T10:00:00Z").toInstant().toEpochMilli()
    );
    public final static PriceDTO EXPECTED_MAPPED_PRICE_DTO = new PriceDTO(
            90.43f,
            "USD"
    )
            .brandId(2)
            .productId(999L)
            .startDate(OffsetDateTime.parse("2020-06-10T10:00:00Z"))
            .endDate(OffsetDateTime.parse("2020-06-15T10:00:00Z"));
}
