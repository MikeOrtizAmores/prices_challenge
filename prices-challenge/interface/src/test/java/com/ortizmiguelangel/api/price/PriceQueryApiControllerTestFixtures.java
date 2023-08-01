package com.ortizmiguelangel.api.price;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.model.Currency;
import com.ortizmiguelangel.model.ProductPrice;

public class PriceQueryApiControllerTestFixtures {

    public final static GetProductPriceOnDateQuery GET_PRODUCT_PRICE_ON_DATE_QUERY =
            new GetProductPriceOnDateQuery.Builder(1L, 1000L).build();

    public final static ProductPrice PRODUCT_PRICE_OK = new ProductPrice(10L, 20L, 1, 30.52f, Currency.EUR, 1, System.currentTimeMillis() - 3600, System.currentTimeMillis());
}
