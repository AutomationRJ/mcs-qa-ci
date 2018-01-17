package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tivo.Validators.AwardValidator;
import com.tivo.Validators.ContentValidator;
import com.tivo.common.*;
import com.tivo.utility.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.bson.Document;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.xml.ws.Endpoint;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.gte;

/**
 * Created by rjaiswal on 5/18/2017.
 */
public class AwardAPI {

    private static Map<String,String> map;
    private static  String version;
    private static MongoDatabase db;

    @BeforeTest(alwaysRun = true)
    public void setup() throws Exception{
        map = Util.loadProperties();
        RequestSpec.setBaseURI(RequestSpec.getBaseURI());
        RequestSpec.setBasePath(map.get("LookupBasePath"));
        version = map.get("Version");
        db = new MongoConn().db(map.get("MongoHost"),map.get("MongoDatabase"));
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieAward_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieAwardCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieAwardCollection"));
        Document findQuery = coll.find(and(exists("awards",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_MOVIE_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        AwardValidator.awardCommonValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateMovieAward_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieAward_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieAward_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieAwardCollection"));
        Document findQuery = coll.find(and(exists("awards",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_AWARD_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateSeriesAward_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesAwardCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        AwardValidator.awardCommonValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateSeriesAward_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesAward_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesAward_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_AWARD_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherAward_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherAwardCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.1",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        AwardValidator.awardCommonValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateOtherAward_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherAward_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherAward_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.1",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_AWARD_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonAward_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonAwardCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.1",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        AwardValidator.awardPersonValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validatePersonAward_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonAward_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonAward_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonAwardCollection"));
        Document findQuery = coll.find(and(exists("awards.1",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_AWARD_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAward_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AwardCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AwardCollection"));
        Document findQuery = coll.find(and(exists("winners",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("awardId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        AwardValidator.awardValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateAward_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("awardId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAward_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("awardId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AWARD_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAward_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AwardCollection"));
        Document findQuery = coll.find(and(exists("winners",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "awardId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_AWARD_LOOKUP_PATH_PARAM,map);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
