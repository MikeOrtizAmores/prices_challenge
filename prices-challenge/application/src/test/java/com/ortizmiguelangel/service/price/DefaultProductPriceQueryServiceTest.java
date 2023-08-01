package com.ortizmiguelangel.service.price;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.exception.PriceNotFoundException;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.in.ProductPriceQueryService;
import com.ortizmiguelangel.port.out.ProductPriceQueryDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DefaultProductPriceQueryServiceTest {

    private ProductPriceQueryDao productPriceQueryDao;

    private ProductPriceQueryService productPriceQueryService;

    private Flux<ProductPrice> pricesRecoveredFromDao;

    private Mono<ProductPrice> expectedResultFromService;

    @BeforeEach
    public void setUp() {
        this.productPriceQueryDao = Mockito.mock(ProductPriceQueryDao.class);
        this.productPriceQueryService = new DefaultProductPriceQueryService(
                this.productPriceQueryDao
        );
    }

    @Test
    @Order(1)
    @DisplayName("Test query get product price service OK")
    void testGetProductPriceServiceOK() {
        setUpTestGetProductPriceServiceOK();
        Mockito.when(productPriceQueryDao.getProductPricesOnDate(
                        ArgumentMatchers.any(Long.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Long.class)))
                .thenReturn(this.pricesRecoveredFromDao);
        Mono<ProductPrice> monoServiceReturn = this.productPriceQueryService.getProductPriceByDate(new GetProductPriceOnDateQuery.Builder(1L, 10000L).build());
        StepVerifier.create(this.expectedResultFromService)
                .expectNext(Objects.requireNonNull(monoServiceReturn.block())) // block() to get the value from the Mono
                .expectComplete()
                .verify();
    }

    private void setUpTestGetProductPriceServiceOK() {
        this.pricesRecoveredFromDao = Flux.fromIterable(DefaultProductPriceQueryTestFixtures.LIST_OF_ONE_PRICE);
        this.expectedResultFromService = this.pricesRecoveredFromDao.next();
    }

    @Test
    @Order(2)
    @DisplayName("Test query get product price service multiple deterministic")
    void testGetProductPriceServiceMultipleDeterministic() {
        setUpTestGetProductPriceServiceMultipleDeterministic();
        Mockito.when(productPriceQueryDao.getProductPricesOnDate(
                        ArgumentMatchers.any(Long.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Long.class)))
                .thenReturn(this.pricesRecoveredFromDao);
        Mono<ProductPrice> monoServiceReturn = this.productPriceQueryService.getProductPriceByDate(new GetProductPriceOnDateQuery.Builder(1L, 10000L).build());
        StepVerifier.create(monoServiceReturn)
                .expectNext(Objects.requireNonNull(this.expectedResultFromService.block()))
                .expectComplete()
                .verify();
    }

    private void setUpTestGetProductPriceServiceMultipleDeterministic() {
        this.pricesRecoveredFromDao = Flux.fromIterable(DefaultProductPriceQueryTestFixtures.LIST_OF_DETERMINISTIC);
        this.expectedResultFromService = DefaultProductPriceQueryTestFixtures.EXPECTED_LIST_OF_DETERMINISTIC;
    }

    @Test
    @Order(3)
    @DisplayName("Test query get product price service multiple non deterministic by criticality")
    void testGetProductPriceServiceMultipleNonDeterministic() {
        setUpTestGetProductPriceServiceMultipleNonDeterministic();
        Mockito.when(productPriceQueryDao.getProductPricesOnDate(
                        ArgumentMatchers.any(Long.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Long.class)))
                .thenReturn(this.pricesRecoveredFromDao);
        Mono<ProductPrice> monoServiceReturn = this.productPriceQueryService.getProductPriceByDate(new GetProductPriceOnDateQuery.Builder(1L, 10000L).build());
        StepVerifier.create(monoServiceReturn)
                .expectNext(Objects.requireNonNull(this.expectedResultFromService.block()))
                .expectComplete()
                .verify();
    }

    private void setUpTestGetProductPriceServiceMultipleNonDeterministic() {
        this.pricesRecoveredFromDao = Flux.fromIterable(DefaultProductPriceQueryTestFixtures.LIST_OF_NON_DETERMINISTIC);
        this.expectedResultFromService = DefaultProductPriceQueryTestFixtures.EXPECTED_LIST_OF_NON_DETERMINISTIC;
    }

    @Test
    @Order(4)
    @DisplayName("Test query get product price service not found")
    void testGetProductPriceServiceNotFound() {
        setUpTestGetProductPriceServiceNotFound();
        Mockito.when(productPriceQueryDao.getProductPricesOnDate(
                        ArgumentMatchers.any(Long.class),
                        ArgumentMatchers.any(Integer.class),
                        ArgumentMatchers.any(Long.class)))
                .thenReturn(this.pricesRecoveredFromDao);
        Mono<ProductPrice> monoServiceReturn = this.productPriceQueryService.getProductPriceByDate(new GetProductPriceOnDateQuery.Builder(1L, 10000L).build());
        StepVerifier.create(monoServiceReturn)
                .expectError(PriceNotFoundException.class) // Expect an error of PriceNotFoundException type
                .verify();
    }

    private void setUpTestGetProductPriceServiceNotFound() {
        this.pricesRecoveredFromDao = Flux.fromIterable(DefaultProductPriceQueryTestFixtures.LIST_EMPTY);
    }
}
