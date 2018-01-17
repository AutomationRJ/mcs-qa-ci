package com.tivo.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;

/**
 * Created by rjaiswal on 5/2/2017.
 */
public class ResponseSpec {

    public Response getResponse(RequestSpecification requestSpecification, String endpoint, int status){
        Response response = requestSpecification.get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),status);
        softAssert.assertAll();
        response.then().log().all();
        return response;
    }
}
