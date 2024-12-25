package org.max.home.accuweather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.home.accuweather.location.Country;
import org.max.home.accuweather.location.Location;

import static io.restassured.RestAssured.given;

public class GetLocationByIPAddressTest extends AccuweatherAbstractTest {
    /*IP Address Search
    Returns information about a specific location, by IP Address.
    */
    @Test
    void getCountryIdTest() {
        Country country = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/ipaddress")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Location.class).getCountry();

        Assertions.assertEquals("RU", country.getId());
    }


}
