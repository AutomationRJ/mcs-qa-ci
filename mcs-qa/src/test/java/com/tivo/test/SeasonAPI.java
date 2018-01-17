package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.SeasonValidator;
import com.tivo.Validators.SeriesValidator;
import com.tivo.common.*;
import com.tivo.utility.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.bson.Document;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by rjaiswal on 5/9/2017.
 */
public class SeasonAPI {

    private static Map<String,String> map;
    private static int limit=100;
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
    public void validateSeason_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonCollection"));
        Document findQuery = coll.find(and(gt("episodes",6),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seasonId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeason_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonCollection"));
        Document findQuery = coll.find(and(gt("episodes",6),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seasonId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SEASON_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeason_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeason_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateSeasonEpisodes_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonEpisodesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonEpisodesCollection"));
        Document findQuery = coll.find(and(exists("episodes.6", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seasonId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SEASON_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonEpisodesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeasonEpisodes_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonEpisodesCollection"));
        Document findQuery = coll.find(and(exists("episodes.6", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seasonId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SEASON_EPISODES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeasonEpisodes_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeasonEpisodes_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seasonId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SEASON_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeasonSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seasonId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SEASON_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seasonId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SEASON_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeasonSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seasonId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SEASON_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeasonSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateSeasonDelta_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validateSeasonDelta_304_response() {
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_SEASON_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validateSeasonDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeasonDelta_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SEASON_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
