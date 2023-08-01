package com.ortizmiguelangel.initialize;

import com.ortizmiguelangel.database.price.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("dev|int")
public class DbInitialize implements CommandLineRunner {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Initialized database:");
        log.info("---------------------");
        productPriceRepository.findAll().doOnNext(priceEntity -> {
            log.info(priceEntity.toString());
        }).blockLast(Duration.ofSeconds(10));
    }
}
