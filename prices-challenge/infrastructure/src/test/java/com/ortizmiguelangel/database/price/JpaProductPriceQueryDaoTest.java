package com.ortizmiguelangel.database.price;

import com.ortizmiguelangel.database.price.mapper.PriceMapper;
import com.ortizmiguelangel.database.price.repository.ProductPriceRepository;
import com.ortizmiguelangel.model.ProductPrice;
import com.ortizmiguelangel.port.out.ProductPriceQueryDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JpaProductPriceQueryDaoTest {

    private ProductPriceRepository productPriceRepository;

    private PriceMapper priceMapper;

    private ProductPriceQueryDao productPriceQueryDao;

    @BeforeEach
    public void setUp() {
        this.productPriceRepository = Mockito.mock(ProductPriceRepository.class);
        this.priceMapper = Mockito.mock(PriceMapper.class);
        this.productPriceQueryDao = new JpaProductPriceQueryDao(
                this.productPriceRepository,
                this.priceMapper
        );
    }

    @Test
    @Order(1)
    @DisplayName("Test query get product price DAO OK")
    public void testGetProductPriceDaoOK() {

        Mockito.when(productPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
                .thenReturn(Flux.just(
                        JpaProductPriceQueryDaoTestFixtures.RESPONSE_PRODUCT_1,
                        JpaProductPriceQueryDaoTestFixtures.RESPONSE_PRODUCT_2));

        Mockito.when(priceMapper.priceEntityToProductPrice(JpaProductPriceQueryDaoTestFixtures.RESPONSE_PRODUCT_1)).thenReturn(JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_1);
        Mockito.when(priceMapper.priceEntityToProductPrice(JpaProductPriceQueryDaoTestFixtures.RESPONSE_PRODUCT_2)).thenReturn(JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_2);

        Flux<ProductPrice> resultFlux = productPriceQueryDao.getProductPricesOnDate(10L, 1, System.currentTimeMillis());

        List<ProductPrice> expectedProductPrices = List.of(JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_1, JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_2);
        assertThat(resultFlux.collectList().block()).isEqualTo(expectedProductPrices);
    }

    @Test
    @Order(2)
    @DisplayName("Test query get product price DAO OK but empty")
    public void testGetProductPriceDaoOKEmpty() {

        Mockito.when(productPriceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
                .thenReturn(Flux.empty());

        Mockito.when(priceMapper.priceEntityToProductPrice(ArgumentMatchers.any())).thenReturn(JpaProductPriceQueryDaoTestFixtures.MAPPED_PRODUCT_1);

        Flux<ProductPrice> resultFlux = productPriceQueryDao.getProductPricesOnDate(10L, 1, System.currentTimeMillis());

        StepVerifier.create(resultFlux)
                .expectNextCount(0)
                .verifyComplete();
    }
}
