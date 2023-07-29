package com.ortizmiguelangel.api;

import com.ortizmiguelangel.api.model.PriceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@AllArgsConstructor
public class PriceApiController implements ProductApi {

    @Override
    public Mono<ResponseEntity<PriceDTO>> findPrice(Long productId, BigDecimal brandId, OffsetDateTime date, final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"endDate\" : \"2000-01-23T04:56:07.000+00:00\", \"price\" : 0.14658129, \"product_id\" : 1, \"currency\" : \"currency\", \"startDate\" : \"2000-01-23T04:56:07.000+00:00\", \"brand_id\" : 1 }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());
    }
}
