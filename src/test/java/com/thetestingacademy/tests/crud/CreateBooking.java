package com.thetestingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateBooking extends BaseTest {

    @Owner("Neelam Kapila")
    @Description("Verify Create Booking is working fine")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"p0","Sanity"})
    public void testCreateBooking() throws JsonProcessingException {

        // 1. Make a response with the payload
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_DELETE_BOOKING_URL);
        response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .body(payloadManager.createPayload()).post();

        // 2. Validate the response
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        System.out.println("BookingID = " + jsonPath.getString("bookingid"));
        String bookingID = jsonPath.getString("bookingid");
        assertThat(bookingID).isNotNull().isNotBlank();

//        this.token = (String)((ValidatableResponse)response.then()).extract().path("token", new String[0]);
//        System.out.println("Token -> " + this.token);
//        validatableResponse.body("token", Matchers.notNullValue(), new Object[0]);
//        validatableResponse.body("token", Matchers.hasLength(15), new Object[0]);
//        validatableResponse.body("token.length()", Matchers.is(15), new Object[0]);
    }
}
