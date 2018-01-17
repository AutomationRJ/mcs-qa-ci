package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.tivo.Validators.TVScheduleValidator;
import com.tivo.common.*;
import com.tivo.utility.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.bson.BSON;
import org.bson.Document;
import org.bson.Transformer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by rjaiswal on 5/23/2017.
 */
public class TVScheduleAPI {

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
    public void validateService_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceCollection"));
        Document findQuery = coll.find(and(exists("name.1",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("serviceId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.serviceValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateService_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceCollection"));
        Document findQuery = coll.find(and(exists("name.1",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "serviceId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERVICE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateService_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateService_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateServiceChannels_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceChannelsCollection"));
        Document findQuery = coll.find(and(exists("channels.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("serviceId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_CHANNELS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.serviceChannelsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateServiceChannels_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceChannelsCollection"));
        Document findQuery = coll.find(and(exists("channels.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "serviceId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERVICE_CHANNELS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateServiceChannels_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_CHANNELS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateServiceChannels_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_CHANNELS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSource_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SourceCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SourceCollection"));
        Document findQuery = coll.find(and(exists("digitalFccchannel",true),exists("affiliateSource1Id",true),exists("affiliateSource2Id",true),exists("affiliateSource3Id", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("sourceId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.sourceValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSource_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SourceCollection"));
        Document findQuery = coll.find(and(exists("digitalFccchannel",true),exists("affiliateSource1Id",true),exists("affiliateSource2Id",true),exists("affiliateSource3Id", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "sourceId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SOURCE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSource_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("sourceId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSource_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("sourceId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiring_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiring_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        Document findQuery = coll.find(and(exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_AIRING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiring_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiring_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiringCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiringCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("credits.2.isCast",false),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiringCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("credits.2.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiringCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiringCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_AIRING_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiringCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiringSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_AIRING_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("airingId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_AIRING_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateAiringSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "airingId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_AIRING_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateAiringSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("airingId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_AIRING_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateBrowseService_200_response() throws Exception{
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        //Document findQuery = coll.find(exists("synopses.2",true)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        //String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("countryCode","IN");
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_BROWSE_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
        afterTest();
        setup();
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateBrowseService_304_response() throws Exception {
        String pathParam = "countryCode";
        EtagVerfication.browseAPIeTagVerification(pathParam,"IN",EndPoint.GET_SERVICE_BROWSE_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateBrowseService_404_response() throws Exception{
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        //Document findQuery = coll.find(exists("synopses.2",true)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        //String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("countryCode","ABC");
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_BROWSE_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateBrowseService_400_response() throws Exception{
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        //Document findQuery = coll.find(exists("synopses.2",true)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        //String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("countryCode","");
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_BROWSE_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
        afterTest();
        setup();
    }

    @Test(groups = "Regression")
    public void validateServiceSchedule_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceChannelsCollection"));
        Document findQuery = coll.find(and(exists("channels.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("serviceId",id).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERVICE_SCHEDULE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateServiceSchedule_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ServiceChannelsCollection"));
        Document findQuery = coll.find(and(exists("channels.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,Util.currentDate(),EndPoint.GET_SERVICE_SCHEDULE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateServiceSchedule_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_SCHEDULE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateServiceSchedule_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERVICE_SCHEDULE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSourceAiring_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SourceCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SourceCollection"));
        Document findQuery = coll.find(and(exists("digitalFccchannel",true),exists("affiliateSource1Id",true),exists("affiliateSource2Id",true),exists("affiliateSource3Id", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("sourceId",id).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SOURCE_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSourceAiring_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SourceCollection"));
        Document findQuery = coll.find(and(exists("digitalFccchannel",true),exists("affiliateSource1Id",true),exists("affiliateSource2Id",true),exists("affiliateSource3Id", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "sourceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,Util.currentDate(),EndPoint.GET_SOURCE_AIRING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSourceAiring_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("sourceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SOURCE_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSourceAiring_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("sourceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SOURCE_AIRING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieAppearances_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        //Document findQuery = coll.find(and(eq("ProgramTypeName","movie"),gt("EndAirDateTime", dt),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","movie"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
//        System.out.println(findQuery.get("_id"));
//        String id = findQuery.get("content", Document.class).get("id").toString();
//        Long sourceId = findQuery.get("source",Document.class).getLong("id");
        //MongoCollection<Document> serviceChannelColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
//        MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));
//        Document findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
//        String serviceId = findServiceId.get("_id").toString();
        requestSpecification.pathParam("movieId",id).pathParam("serviceId",serviceId).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_MOVIE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieAppearances_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","movie"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        String pathParam = "movieId";
        String pathParam1 = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam1,pathParam2,id,serviceId,Util.currentDate(),EndPoint.GET_MOVIE_APPEARANCES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieAppearances_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber())).pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieAppearances_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","").pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesAppearances_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        //Document findQuery = coll.find(and(eq("ProgramTypeName","series"),gt("AirDateTime", dt),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","series"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false),gte("_v",version))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        //String id = findQuery.get("content", Document.class).get("id").toString();
        //Long sourceId = findQuery.get("source",Document.class).getLong("id");
        //MongoCollection<Document> serviceChannelColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        //MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));
        //Document findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
        //String serviceId = findServiceId.get("_id").toString();
        requestSpecification.pathParam("seriesId",id).pathParam("serviceId",serviceId).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateSeriesAppearances_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","series"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        String pathParam = "seriesId";
        String pathParam1 = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam1,pathParam2,id,serviceId,Util.currentDate(),EndPoint.GET_SERIES_APPEARANCES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesAppearances_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber())).pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesAppearances_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","").pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAppearances_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        //Document findQuery = coll.find(and(eq("ProgramTypeName","episode"),gt("EndAirDateTime", dt),exists("_del",false))).first();
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","episode"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        //String id = findQuery.get("content", Document.class).get("id").toString();
        //Long sourceId = findQuery.get("source",Document.class).getLong("id");
        //MongoCollection<Document> serviceChannelColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        //MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));
        //Document findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
        //String serviceId = findServiceId.get("_id").toString();
        requestSpecification.pathParam("episodeId",id).pathParam("serviceId",serviceId).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeAppearances_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","episode"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        String pathParam = "episodeId";
        String pathParam1 = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam1,pathParam2,id,serviceId,Util.currentDate(),EndPoint.GET_EPISODE_APPEARANCES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAppearances_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber())).pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAppearances_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","").pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression", enabled = false)
    public void validateOtherAppearances_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        //Document findQuery = coll.find(and(eq("ProgramTypeName","other"),gt("AirDateTime", dt),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","other"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        //String id = findQuery.get("content", Document.class).get("id").toString();
        //Long sourceId = findQuery.get("source",Document.class).getLong("id");
        //MongoCollection<Document> serviceChannelColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        //MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));
        //Document findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
        //String serviceId = findServiceId.get("_id").toString();
        requestSpecification.pathParam("otherId",id).pathParam("serviceId",serviceId).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateOtherAppearances_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        MongoCursor<Document> cursor = coll.find(and(eq("ProgramTypeName","other"),gt("AirDateTime", dt),lt("AirDateTime", dt.plusDays(14)),exists("_del",false))).iterator();
        Document findServiceId = null;
        String serviceId = "";
        String id = "";
        while (cursor.hasNext()){
            Document dev = cursor.next();
            Long sourceId = dev.get("source",Document.class).getLong("id");
            MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));

            findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
            if(findServiceId != null)
            {
                serviceId = findServiceId.get("_id").toString();
                id = dev.get("content", Document.class).get("id").toString();;
                break;
            }
        }
        String pathParam = "otherId";
        String pathParam1 = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam1,pathParam2,id,serviceId,Util.currentDate(),EndPoint.GET_OTHER_APPEARANCES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherAppearances_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber())).pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherAppearances_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","").pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonAppearances_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        Document findQuery = coll.find(and(exists("credits.person",true),gt("h_airDateTime", dt),lt("h_airDateTime", dt.plusDays(14)),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = ((List<Document>)(findQuery.get("credits"))).get(0).get("person", Document.class).get("id").toString();
        //Long sourceId = findQuery.get("source",Document.class).getLong("id");
        //MongoCollection<Document> serviceChannelColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceChannelsCollection"));
        //MongoCollection<Document> serviceChannelColl = db.getCollection(map.get("ServiceChannelsCollection"));
        //Document findServiceId = serviceChannelColl.find(eq("channels.windows.source.r_source",sourceId)).projection(Projections.fields((Projections.include("channels.windows.source.r_source")))).first();
        //String serviceId = findServiceId.get("_id").toString();
        requestSpecification.pathParam("personId",id).pathParam("serviceId","4068867320").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonAppearances_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("AiringCreditsCollection"));
        DateTime dt = new DateTime(DateTimeZone.UTC).withTime(0,0,0,0);
        Document findQuery = coll.find(and(exists("credits.person",true),gt("h_airDateTime", dt),lt("h_airDateTime", dt.plusDays(14)),exists("_del",false))).first();
        String id = ((List<Document>)(findQuery.get("credits"))).get(0).get("person", Document.class).get("id").toString();
        String pathParam = "personId";
        String pathParam1 = "serviceId";
        String pathParam2 = "date";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam1,pathParam2,id,"4068867320",Util.currentDate(),EndPoint.GET_PERSON_APPEARANCES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonAppearances_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber())).pathParam("serviceId",Long.toString(Util.randomNumber())).pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonAppearances_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","").pathParam("serviceId","").pathParam("date",Util.currentDate());
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_APPEARANCES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
