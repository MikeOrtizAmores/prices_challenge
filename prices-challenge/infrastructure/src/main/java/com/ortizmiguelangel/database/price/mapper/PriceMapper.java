package com.ortizmiguelangel.database.price.mapper;

import com.ortizmiguelangel.database.price.model.PriceEntity;
import com.ortizmiguelangel.model.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "id", source = "priceList")
    @Mapping(target = "currency", source = "curr", defaultExpression = "java(Currency.EUR)")
    @Mapping(target = "startDateMillis", source = "startDate", qualifiedByName = "mapLocalDateTimeToMilliseconds")
    @Mapping(target = "endDateMillis", source = "endDate", qualifiedByName = "mapLocalDateTimeToMilliseconds")
    ProductPrice priceEntityToProductPrice(PriceEntity priceEntity);

    @Named("mapLocalDateTimeToMilliseconds")
    default Long mapLocalDateTimeToMilliseconds(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
