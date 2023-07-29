package com.ortizmiguelangel.database.price.mapper;

import com.ortizmiguelangel.database.price.model.PriceEntity;
import com.ortizmiguelangel.model.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "id", source = "priceList")
    @Mapping(target = "currency", source = "curr", defaultExpression = "java(Currency.EUR)")
    ProductPrice priceEntityToProductPrice(PriceEntity priceEntity);
}
