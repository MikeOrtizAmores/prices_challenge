package com.ortizmiguelangel.database.price.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    @Id
    @Column(name = "PRICE_LIST")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceList;

    @Column(name = "PRODUCT_ID", updatable = false, nullable = false)
    private Long productId;

    @Column(name = "BRAND_ID", updatable = false, nullable = false)
    private Integer brandId;

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "CURR", nullable = false)
    @Length(min = 3, max = 3, message = "Currency must have exactly 3 characters")
    private String curr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntity priceEntity = (PriceEntity) o;
        return Objects.equals(priceList, priceEntity.priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceList);
    }
}
