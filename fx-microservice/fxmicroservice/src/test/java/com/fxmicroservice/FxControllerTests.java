package com.fxmicroservice;

import com.fxmicroservice.model.FxRate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FxMicroserviceApplication.class)
@TestPropertySource(value = {"classpath:application.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FxControllerTests {

    private final String fxBaseUrl = "http://localhost:8080/api/v1/fx/";

    private final String GET = "GET";
    private final String PUT = "PUT";

    private final String SUCCESS = "SUCCESS";
    private final String FAILURE = "FAILURE";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testGetFxRate() {
        String methodName = "testGetFxRate: ";
        String fromGbpToUsd = "from/GBP/to/USD";
        String fxRateUrl = fxBaseUrl + fromGbpToUsd;

        String successMessage = "has returned results.";
        String failureMessage = "has returned no results.";

        FxRate fxRate = testRestTemplate.getForObject(fxRateUrl, FxRate.class);

        Assert.assertNotNull(constructMessage(methodName, FAILURE, GET, fxRateUrl, failureMessage), fxRate);
        System.out.println(constructMessage(methodName, SUCCESS, GET, fxRateUrl, successMessage));
    }

    @Test
    public void testGetFxRateByFromAndTo() {
        String methodName = "testGetFxRateByFromAndTo: ";
        String fromGbpToUsd = "from/GBP/to/USD";
        String fxRateUrl = fxBaseUrl + fromGbpToUsd;

        String failureMessage = methodName + "DOES NOT match expectations.";
        String successMessage = methodName + "DOES match expectations.";

        FxRate fxRate = testRestTemplate.getForObject(fxRateUrl, FxRate.class);

        FxRate fxRateComparison = new FxRate(101, "GBP", "USD", 1.29);
        Assert.assertEquals(constructMessage(methodName, FAILURE, GET, fxRateUrl, failureMessage), fxRate, fxRateComparison);
        System.out.println(constructMessage(methodName, SUCCESS, GET, fxRateUrl, successMessage));
    }

    @Test
    public void testAddFxRate() {
        String methodName = "testAddFxRate: ";
        String rateFromUsdtoGbp = "id/102/from/USD/to/GBP/rate/0.775";

        String putFxRateUrl = fxBaseUrl + rateFromUsdtoGbp;

        String failureMessage1 = "was not successful. No response from target URL.";
        String failureMessage2 = "was not successful. Error code received.";
        String successMessage = "was successful. A new object has been added to database.";

        ResponseEntity<String> response = testRestTemplate.exchange(putFxRateUrl, HttpMethod.PUT, null, String.class);

        Assert.assertNotNull(constructMessage(methodName, FAILURE, PUT, putFxRateUrl, failureMessage1), response);
        Assert.assertEquals(constructMessage(methodName, FAILURE, PUT, putFxRateUrl, failureMessage2), 200, response.getStatusCode().value());
        System.out.println(constructMessage(methodName, SUCCESS, PUT, putFxRateUrl, successMessage));
    }

    @Test
    public void testAddFxRateValue() {
        String methodName = "testAddFxRateValue: ";
        String fromUsdToGbp = "from/USD/to/GBP";
        String rateFromUsdToGbp = "id/102/from/USD/to/GBP/rate/0.775";

        String putFxRateUrl = fxBaseUrl + rateFromUsdToGbp;
        String getFxRateUrl = fxBaseUrl + fromUsdToGbp;

        String failureMessage = "DOES NOT match expectations.";
        String successMessage = "DOES match expectations.";

        FxRate fxRateToAdd = new FxRate(102, "USD", "GBP", 0.775);

        testRestTemplate.put(putFxRateUrl, null);
        FxRate addedFxRate = testRestTemplate.getForObject(getFxRateUrl, FxRate.class);

        Assert.assertEquals(constructMessage(methodName, FAILURE, PUT, putFxRateUrl, failureMessage), fxRateToAdd, addedFxRate);
        System.out.println(constructMessage(methodName, SUCCESS, PUT, putFxRateUrl, successMessage));
    }

    @Test
    public void testAddNegativeValue() {
        String methodName = "testAddNegativeValue: ";
        String rateFromAtoB = "id/103/from/A/to/B/rate/-25";

        String putFxRateUrl = fxBaseUrl + rateFromAtoB;

        String successMessage = "was completed but unsuccessful. We should not be able to add an FxRate Object " +
                                "with negative exchange rate to the database.";
        String failureMessage1 = "was not completed and a error code was received.";
        String failureMessage2 = "was completed and successful. New object has been added to database despite negative value.";

        ResponseEntity<String> response = testRestTemplate.exchange(putFxRateUrl, HttpMethod.PUT, null, String.class);

        Assert.assertNotNull(constructMessage(methodName, FAILURE, PUT, putFxRateUrl, failureMessage1), response);
        Assert.assertEquals(constructMessage(methodName, FAILURE, PUT, putFxRateUrl, failureMessage2), 200, response.getStatusCode().value());
        System.out.println(constructMessage(methodName, SUCCESS, PUT, putFxRateUrl, successMessage));
    }

    public String constructMessage(String methodName, String outcome, String mapping,
                                   String targetUrl, String extra) {
        return methodName + ": " + outcome + ": " + mapping + " mapping to URL: " + targetUrl + " " + extra;
    }
}
