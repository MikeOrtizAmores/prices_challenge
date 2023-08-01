package com.ortizmiguelangel.api.price.mapper;

import com.ortizmiguelangel.generatedapi.query.model.PriceDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceDTOQueryMapperTest {

    private PriceDTOQueryMapper priceDTOQueryMapper;

    @BeforeEach
    public void setUp() {
        this.priceDTOQueryMapper = new PriceDTOQueryMapperImpl();
    }

    @Test
    @Order(1)
    @DisplayName("Test map productprice to price DTO OK")
    public void testMapProductPriceToPriceDTOOK() {

        PriceDTO priceDTO = priceDTOQueryMapper.mapProductPriceToPriceDTO(PriceDTOQueryMapperTestFixture.UNMAPPED_PRODUCT_PRICE);

        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getProductId(), priceDTO.getProductId());
        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getBrandId(), priceDTO.getBrandId());
        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getPrice(), priceDTO.getPrice());
        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getCurrency(), priceDTO.getCurrency());
        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getStartDate(), priceDTO.getStartDate());
        assertEquals(PriceDTOQueryMapperTestFixture.EXPECTED_MAPPED_PRICE_DTO.getEndDate(), priceDTO.getEndDate());
    }

    @Test
    @Order(1)
    @DisplayName("Test map productprice to price DTO OK null input")
    public void testMapProductPriceToPriceDTOOKNullInput() {

        PriceDTO priceDTO = priceDTOQueryMapper.mapProductPriceToPriceDTO(null);
        assertNull(priceDTO);
    }
}
