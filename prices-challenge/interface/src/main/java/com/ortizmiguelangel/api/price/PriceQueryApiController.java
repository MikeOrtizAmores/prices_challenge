package com.ortizmiguelangel.api.price;

import com.ortizmiguelangel.action.query.GetProductPriceOnDateQuery;
import com.ortizmiguelangel.api.price.mapper.PriceDTOQueryMapper;
import com.ortizmiguelangel.generatedapi.query.PriceQueryApi;
import com.ortizmiguelangel.generatedapi.query.model.PriceDTO;
import com.ortizmiguelangel.port.in.ProductPriceQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "price-query", description = "To query everything related to the product price")
public class PriceQueryApiController implements PriceQueryApi {

    private final ProductPriceQueryService productPriceQueryService;

    private final PriceDTOQueryMapper priceDTOQueryMapper;

    @Override
    public Mono<ResponseEntity<PriceDTO>> findPrice(Long productId, OffsetDateTime date, Integer brandId, final ServerWebExchange exchange) {
        return productPriceQueryService.getProductPriceByDate(getGetProductPriceOnDateQuery(productId, date, brandId))
                .map(productPrice -> new ResponseEntity<>(priceDTOQueryMapper.mapProductPriceToPriceDTO(productPrice), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private GetProductPriceOnDateQuery getGetProductPriceOnDateQuery(Long productId, OffsetDateTime date, Integer brandId) {
        GetProductPriceOnDateQuery.Builder queryBuilder = new GetProductPriceOnDateQuery.Builder(productId, date.toInstant().toEpochMilli());
        if (Objects.nonNull(brandId)) {
            queryBuilder.setTenantId(brandId).build();
        }
        return queryBuilder.build();
    }
}
