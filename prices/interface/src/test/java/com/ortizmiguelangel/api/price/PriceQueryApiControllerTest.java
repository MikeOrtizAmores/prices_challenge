package com.ortizmiguelangel.api.price;

import com.ortizmiguelangel.BootstrapTest;
import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.api.price.mapper.PriceDTOQueryMapper;
import com.ortizmiguelangel.exception.PriceNotFoundException;
import com.ortizmiguelangel.exception.code.ExceptionCode;
import com.ortizmiguelangel.generatedapi.query.model.ErrorDTO;
import com.ortizmiguelangel.generatedapi.query.model.PriceDTO;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.in.ProductPriceQueryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@WebFluxTest({PriceQueryApiController.class, PriceDTOQueryMapper.class})
@ContextConfiguration(classes = BootstrapTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceQueryApiControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductPriceQueryService productPriceQueryService;

    private OffsetDateTime dateTimeNow;

    @BeforeEach
    void initTests() {
        dateTimeNow = OffsetDateTime.now(ZoneOffset.UTC);
    }

    @Test
    @Order(1)
    @DisplayName("Test query get product price endpoint OK")
    void testGetProductPriceEndpointOK() {
        ProductPrice recoveredPrice = PriceQueryApiControllerTestFixtures.PRODUCT_PRICE_OK;
        Mono<ProductPrice> recoveredPriceMono = Mono.just(recoveredPrice);
        Mockito.when(productPriceQueryService.getProductPriceByDate(ArgumentMatchers.any(GetProductPriceOnDateQuery.class)))
                .thenReturn(recoveredPriceMono);
        webTestClient.get().uri("/price/product/1?date=" + dateTimeNow + "&brandId=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PriceDTO.class)
                .value(priceDTO -> {
                    assertThat(priceDTO.getProductId()).isEqualTo(recoveredPrice.productId());
                    assertThat(priceDTO.getPrice()).isEqualTo(recoveredPrice.price());
                    assertThat(priceDTO.getBrandId()).isEqualTo(recoveredPrice.brandId());
                    assertThat(priceDTO.getCurrency()).isEqualTo(String.valueOf(recoveredPrice.currency()));
                    assertThat(priceDTO.getStartDate()).isEqualTo(OffsetDateTime.ofInstant(Instant.ofEpochMilli(recoveredPrice.startDateMillis()), ZoneOffset.UTC));
                    assertThat(priceDTO.getEndDate()).isEqualTo(OffsetDateTime.ofInstant(Instant.ofEpochMilli(recoveredPrice.endDateMillis()), ZoneOffset.UTC));
                });
    }

    @Test
    @Order(2)
    @DisplayName("Test query get product price endpoint not found")
    void testGetProductPriceEndpointPriceNotFound() {
        String exceptionMessage = "Not found test";
        Mockito.when(productPriceQueryService.getProductPriceByDate(ArgumentMatchers.any(GetProductPriceOnDateQuery.class)))
                .thenReturn(Mono.error(new PriceNotFoundException(exceptionMessage)));
        webTestClient.get().uri("/price/product/1?date=" + dateTimeNow + "&brandId=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorDTO.class)
                .value(errorDTO -> {
                    assertThat(errorDTO.getCode()).isEqualTo(ExceptionCode.EXCEPTION_CODE_PRICE_NOT_FOUND);
                    assertThat(errorDTO.getMessage()).isEqualTo(exceptionMessage);
                });
    }
}
