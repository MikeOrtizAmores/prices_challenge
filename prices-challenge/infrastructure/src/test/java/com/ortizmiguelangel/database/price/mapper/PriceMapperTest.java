package com.ortizmiguelangel.database.price.mapper;

import com.ortizmiguelangel.database.price.JpaProductPriceQueryDaoTestFixtures;
import com.ortizmiguelangel.model.ProductPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PriceMapperTest {

    private PriceMapper priceMapper;

    @BeforeEach
    public void setUp() {
        this.priceMapper = new PriceMapperImpl();
    }

    @Test
    @DisplayName("Test map price repository mapper OK")
    public void testMapPriceRepositoryMapperOK() {

        ProductPrice mappedProductPrice = priceMapper.priceEntityToProductPrice(JpaProductPriceQueryDaoTestFixtures.RESPONSE_PRODUCT_1);
        assertThat(mappedProductPrice).isEqualTo(JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_1);
    }
}
