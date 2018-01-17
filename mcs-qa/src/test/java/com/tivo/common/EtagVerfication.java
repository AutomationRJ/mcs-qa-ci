package com.tivo.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class EtagVerfication {

    public static void lookupAPIeTagVerfication(String pathParam, String id, String endpoint,Map<String,String> map){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam(pathParam, id);
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        Response response2 = requestSpecification.header(map.get("etagHeader"), eTag).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        softAssert.assertAll();
    }

    public static void lookupAPIeTagVerfication(String pathParam, String pathParam2, String id,String pathParam2Value, String endpoint,Map<String,String> map){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam(pathParam, id).pathParam(pathParam2,pathParam2Value);
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        Response response2 = requestSpecification.header(map.get("etagHeader"), eTag).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        softAssert.assertAll();
    }

    public static void lookupAPIeTagVerfication(String pathParam, String pathParam1, String pathParam2, String pathParamValue,String pathParam1Value, String pathParam2Value, String endpoint,Map<String,String> map){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam(pathParam, pathParamValue).pathParam(pathParam1,pathParam1Value).pathParam(pathParam2,pathParam2Value);
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        Response response2 = requestSpecification.header(map.get("etagHeader"), eTag).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        softAssert.assertAll();
    }

    public static void browseAPIeTagVerification(String endpoint,Map<String,String> map) throws Exception {
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        Response response2 = requestSpecification.header(map.get("etagHeader"), eTag).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("LookupBasePath"));
        softAssert.assertAll();
    }

    public static void deltaAPIeTagVerification(String pathParam, String pathParam2, String id,String pathParam2Value, String endpoint,Map<String,String> map){
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam(pathParam, id).pathParam(pathParam2,pathParam2Value);
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        RequestSpecification requestSpecification1 = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification1.pathParam(pathParam, id).pathParam(pathParam2,pathParam2Value);
        Response response2 = requestSpecification1.header(map.get("etagHeader"), eTag).urlEncodingEnabled(false).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("LookupBasePath"));
        softAssert.assertAll();
    }

    public static void browseAPIeTagVerification(String pathParam, String pathParamValue, String endpoint,Map<String,String> map){
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam(pathParam, pathParamValue);
        Response response = new ResponseSpec().getResponse(requestSpecification, endpoint, HttpStatus.SC_OK);
        String eTag = response.header("etag");
        RequestSpecification requestSpecification1 = request.setOAuth(map.get("ConsumerKey").toString(), map.get("ConsumerSecret").toString()).log().all();
        requestSpecification1.pathParam(pathParam, pathParamValue);
        Response response2 = requestSpecification1.header(map.get("etagHeader"), eTag).urlEncodingEnabled(false).get(endpoint);
        SoftAssert softAssert = new SoftAssert();
        if (response2.header("etag").toString().equalsIgnoreCase(eTag)) {

            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
        } else {
            softAssert.assertEquals(response2.getStatusCode(), HttpStatus.SC_OK);
        }
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("LookupBasePath"));
        softAssert.assertAll();
    }
}
