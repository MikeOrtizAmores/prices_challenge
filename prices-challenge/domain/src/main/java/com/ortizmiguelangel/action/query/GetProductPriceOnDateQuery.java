package com.ortizmiguelangel.action.query;

public class GetProductPriceOnDateQuery {

    private Integer tenantId;

    private Long productId;

    private Long dateInMilis;

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDateInMilis() {
        return dateInMilis;
    }

    public void setDateInMilis(Long dateInMilis) {
        this.dateInMilis = dateInMilis;
    }
}
