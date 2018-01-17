package com.tivo.common;

import com.tivo.utility.Util;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by rjaiswal on 4/28/2017.
 */
public class RequestSpec {

    private static Map<String,String> map;

    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    /*
    ***Sets ContentType***
    We should set content type as JSON or XML before starting the test
    */
    public static RequestSpecification setOAuth (String consumerKey,String consumerSecret){
        return given().auth().oauth(consumerKey,consumerSecret,"","", OAuthSignature.HEADER);
    }

    public static String getBaseURI() throws Exception{
        //noinspection unchecked
        map = Util.loadProperties();
        String baseURI = System.getProperty("BaseURI");
        if (baseURI == null) baseURI = map.get("baseURI");
        return baseURI;
    }


//    @BeforeSuite(alwaysRun = true)
//    public void setup(){
//        RestAssured.baseURI = loadProperties().getProperty("baseURI");
//        RestAssured.basePath = loadProperties().getProperty("basePath");
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        final MatcherConfig matcherConfig = RestAssured.config().getMatcherConfig().errorDescriptionType(MatcherConfig.ErrorDescriptionType.HAMCREST);
//        RestAssured.config = RestAssured.config().matcherConfig(matcherConfig);
//    }
//
//    public RequestSpecification getAuthRequestSpecification(){
//        String consumerKey = loadProperties().get("ConsumerKey").toString();
//        String consumerSecret = loadProperties().get("ConsumerSecret").toString();
//        return given().auth().oauth(consumerKey,consumerSecret,"","", OAuthSignature.HEADER);
//    }
}
