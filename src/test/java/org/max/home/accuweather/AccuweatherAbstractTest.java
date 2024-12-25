package org.max.home.accuweather;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.max.home.accuweather.location.Location;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public abstract class AccuweatherAbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String locationKey;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/test/resources/accuweather.properties");
        prop.load(configFile);
        apiKey = prop.getProperty("apikey");
        baseUrl = prop.getProperty("base_url");

        locationKey = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/ipaddress/")
                .then()
                .extract()
                .body().as(Location.class).getKey();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getLocationKey() { return locationKey; }

}
