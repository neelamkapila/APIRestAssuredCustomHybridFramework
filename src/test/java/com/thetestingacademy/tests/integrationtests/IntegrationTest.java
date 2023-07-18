package com.thetestingacademy.tests.integrationtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.payload.Booking;
import com.thetestingacademy.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationTest extends BaseTest {
    String token;

    @Test(groups="integration")
    @Owner("Neelam Kapila | Straive")
    @Description("Verify if the booking is created successfully")
    public void testCreateBooking(ITestContext iTestContext) throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_DELETE_BOOKING_URL);
        response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = jsonPath.from(response.asString());
        String bookingId = jsonPath.getString("bookingid");
        validatableResponse.statusCode(200);
        assertThat(bookingId).isNotNull().isNotBlank();

        // Use Listeners for passing bookingid to update method
        iTestContext.setAttribute("bookingid",jsonPath.getString("bookingid"));
    }

    @Test(groups="integration",dependsOnMethods = "testCreateBooking")
    @Owner("Neelam Kapila | Straive")
    @Description("Verify if the booking is updated successfully")
    public void testUpdateBooking(ITestContext iTestContext) throws JsonProcessingException {
        token = GetToken();

        String bookingId = (String)iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_DELETE_BOOKING_URL+"/"+bookingId);
        response = RestAssured.given()
                .spec(requestSpecification)
                .cookie("token",token)
                .when()
                .body(payloadManager.updatePayload()).put();
        validatableResponse = response.then().log().all();

        jsonPath = jsonPath.from(response.asString());
        String firstname = jsonPath.getString("firstname");
        validatableResponse.statusCode(200);
        assertThat(firstname).isNotNull().isNotBlank();
    }

    @Test(groups="integration",dependsOnMethods = "testUpdateBooking")
    @Owner("Neelam Kapila | Straive")
    @Description("Verify if the booking is deleted successfully")
    public void testDeleteBooking(ITestContext iTestContext){

        String bookingId = (String)iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_DELETE_BOOKING_URL+"/"+bookingId);
        validatableResponse = RestAssured.given()
                .spec(requestSpecification)
                .auth()
                .preemptive().basic("admin","password123")
                // OR .cookie("token",token)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}
