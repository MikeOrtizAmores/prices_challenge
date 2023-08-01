package com.ortizmiguelangel.integration.steps;

import com.ortizmiguelangel.integration.CucumberSpringContextConfig;
import com.ortizmiguelangel.model.ProductPrice;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CucumberContextConfiguration
public class PricesSteps extends CucumberSpringContextConfig {

    @LocalServerPort
    private int port;

    private OffsetDateTime currentDate;

    private ProductPrice productPriceResponse;

    @Given("the current date is {string}")
    public void setCurrentDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        currentDate = localDateTime.atOffset(ZoneOffset.UTC);
    }

    @When("a user makes a request for product {long} and brand {int}")
    public void makeProductPriceRequest(Long productId, Integer brandId) {
        String url = String.format("http://localhost:%d/price/product/%d?date=%s&brandId=%d", port, productId, currentDate, brandId);
        WebClient webClient = WebClient.create(); // Create WebClient with dynamically assigned port
        productPriceResponse = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ProductPrice.class)
                .block();
    }

    @Then("the system should return the product price information with price value {float}")
    public void verifyProductPrice(Float expectedPrice) {
        assertNotNull(productPriceResponse);
        assertEquals(expectedPrice, productPriceResponse.price(), 0.001);
    }
}
