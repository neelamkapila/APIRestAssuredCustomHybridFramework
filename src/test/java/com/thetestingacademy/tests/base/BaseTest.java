package com.thetestingacademy.tests.base;

import com.thetestingacademy.actions.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.module.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    // Inheritance Single - Father to child
    // Father - Base Test
    // Children - CreateBooking

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    String token;

    @BeforeMethod
    public void setUpConfig(){
        //Set Base URI, Headers
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder() // class same as RequestSpecification - typecast (RequestSpecification)
                .setBaseUri(APIConstants.BASE_URL)//BASE_URI is Static so directly called via class
                .addHeader("Content-Type","application/json")
                .build().log().all();
    }

    @Test
    public String GetToken() {
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.CREATE_TOKEN_AUTH_URL);
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");
    }

    @BeforeMethod
    public void tearDown(){
        System.out.println("End!!");
    }
}
