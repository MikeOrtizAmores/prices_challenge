package com.ortizmiguelangel.api.price.mapper;

import com.ortizmiguelangel.generatedapi.query.model.PriceDTO;
import com.ortizmiguelangel.model.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface PriceDTOQueryMapper {

    @Mapping(target = "startDate", source = "startDateMillis", qualifiedByName = "mapDateMillisToOffsetDateTime")
    @Mapping(target = "endDate", source = "endDateMillis", qualifiedByName = "mapDateMillisToOffsetDateTime")
    PriceDTO mapProductPriceToPriceDTO(ProductPrice productPrice);

    @Named("mapDateMillisToOffsetDateTime")
    default OffsetDateTime mapDateMillisToOffsetDateTime(Long dateInMillis) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(dateInMillis), ZoneOffset.UTC);
    }
}
