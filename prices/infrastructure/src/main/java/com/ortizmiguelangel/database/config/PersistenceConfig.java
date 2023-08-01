package com.ortizmiguelangel.database.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = {"com.ortizmiguelangel.database"})
@EntityScan("com.ortizmiguelangel.database")
public class PersistenceConfig {

}
