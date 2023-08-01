package com.ortizmiguelangel.action.query;

public class GetProductPriceOnDateQuery {

    private final Integer tenantId;

    private final Long productId;

    private final Long dateInMillis;

    private GetProductPriceOnDateQuery(Builder builder) {
        this.tenantId = builder.tenantId;
        this.productId = builder.productId;
        this.dateInMillis = builder.dateInMillis;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getDateInMillis() {
        return dateInMillis;
    }

    public static class Builder {

        private Integer tenantId = 1;

        private final Long productId;

        private final Long dateInMillis;

        public Builder(Long productId, Long dateInMillis) {
            this.productId = productId;
            this.dateInMillis = dateInMillis;
        }

        public Builder setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public GetProductPriceOnDateQuery build() {
            return new GetProductPriceOnDateQuery(this);
        }
    }
}
