package com.ortizmiguelangel.model;

public record ProductPrice(Long id, Long productId, Integer brandId, Float price, Currency currency,
                           Integer priority, Long startDateMillis, Long endDateMillis) {
}
