package org.max.home.accuweather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetOneHourOfHourlyForecastsTest extends AccuweatherAbstractTest {
    @Test
    void GetLinkWeather() {
        String link = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/hourly/1hour/" + getLocationKey())
                .path("Link[0]");
        Assertions.assertNotNull(link);
        Assertions.assertNotEquals("", link);
    }
}
