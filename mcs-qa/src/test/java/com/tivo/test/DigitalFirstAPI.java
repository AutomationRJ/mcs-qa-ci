package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.*;
import com.tivo.common.*;
import com.tivo.utility.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.bson.Document;
import org.json.JSONException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.mongodb.client.model.Filters.*;

public class DigitalFirstAPI {

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

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebsource_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("WebSourceCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("webSourceID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.websourceValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsource_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsource_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebsource_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "webSourceID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSOURCE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"}, enabled = false)
    public void validateBrowseWebsource_200_response() throws Exception{
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("browseBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        //Document findQuery = coll.find(exists("synopses.2",true)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        //String id = findQuery.get("_id").toString();
        //requestSpecification.pathParam("countryCode","US");
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_BROWSE_PATH_PARAM, HttpStatus.SC_OK);
        afterTest();
        setup();
        //TVScheduleValidator.airingSynopsesBestValidator(response,findQuery);

    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateBrowseWebsource_304_response() throws Exception {
        EtagVerfication.browseAPIeTagVerification(EndPoint.GET_WEBSOURCE_BROWSE_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebsourceSeries_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("WebSourceSeriesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceSeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false),exists("series", true))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("webSourceID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.websourceSeriesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebsourceSeries_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceSeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false),exists("series", true))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "webSourceID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSOURCE_SERIES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceSeries_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceSeries_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebsourceVideos_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("WebSourceVideosCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceVideosCollection"));
        Document findQuery = coll.find(and(exists("_del",false),exists("videos", true))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("webSourceID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_VIDEOS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebsourceVideos_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceVideosCollection"));
        Document findQuery = coll.find(and(exists("_del",false),exists("videos", true))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "webSourceID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSOURCE_VIDEOS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceVideos_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_VIDEOS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceVideos_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_VIDEOS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClip_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.clipValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClip_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClip_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClip_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"}, enabled = false)
    public void validateClipCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateClipCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"}, enabled = false)
    public void validateClipCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_CREW_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateClipCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_CREW_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CREW_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CREW_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClipCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.clipCreditValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClipCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClipRelated_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipRelatedCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),exists("_del",false),gt("_v",version))).sort(Sorts.descending("_ts")).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.clipRelatedValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClipRelated_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),exists("_del",false))).sort(Sorts.descending("_ts")).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_RELATED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipRelated_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipRelated_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClipReleases_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipReleasesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClipReleases_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_RELEASES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipReleases_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipReleases_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClipSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClipSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateClipSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ClipSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ClipSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("clipID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CLIP_SYNOPSES_COLLECTION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //DigitalFirstValidator.websourceVideosValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateClipSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ClipSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "clipID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CLIP_SYNOPSES_COLLECTION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_SYNOPSES_COLLECTION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateClipSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("clipID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CLIP_SYNOPSES_COLLECTION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebSeries_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gt("_v",version),eq("isweb", true))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebSeries_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false),eq("isweb", true))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSERIES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebSeries_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebSeries_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateSeriesEpisodes_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesEpisodesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesEpisodesCollection"));
        Document findQuery = coll.find(and(exists("episodes",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.seriesEpisodesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesEpisodes_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesEpisodesCollection"));
        Document findQuery = coll.find(and(exists("episodes",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_EPISODES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateSeriesEpisodes_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateSeriesEpisodes_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_EPISODES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateSeriesRelated_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesRelatedCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("isweb",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.seriesRelatedValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesRelated_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("isweb",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_RELATED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateSeriesRelated_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateSeriesRelated_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebEpisode_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        Document findQuery = coll.find(and(eq("isweb",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBEPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebEpisode_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        Document findQuery = coll.find(and(eq("isweb",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBEPISODE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebEpisode_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBEPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebEpisode_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBEPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateEpisodeRelated_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeRelatedCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("isweb",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBEPISODE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        DigitalFirstValidator.episodeRelatedValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeRelated_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("isweb",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBEPISODE_RELATED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateEpisodeRelated_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBEPISODE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateEpisodeRelated_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBEPISODE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebSeason_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonCollection"));
        Document findQuery = coll.find(and(eq("isweb",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seasonId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeasonValidator.seasonValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebSeason_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeasonCollection"));
        Document findQuery = coll.find(and(eq("isweb",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seasonId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSEASON_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebSeason_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebSeason_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seasonId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSEASON_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebPerson_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCollection"));
        Document findQuery = coll.find(and(exists("f_digitalFirst",true),exists("_del",false),gt("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBPERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebPerson_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCollection"));
        Document findQuery = coll.find(and(exists("f_digitalFirst",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBPERSON_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebPerson_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBPERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebPerson_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBPERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity", "DigitalFirst"})
    public void validatePersonWebography_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonWebographyCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonWebographyCollection"));
        Document findQuery = coll.find(and(exists("webography",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_WEBOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //PersonValidator.personFilmographyValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonWebography_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonWebographyCollection"));
        Document findQuery = coll.find(and(exists("webography",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_WEBOGRAPHY_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validatePersonWebography_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_WEBOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validatePersonWebography_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_WEBOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity", "DigitalFirst"})
    public void validateWebsourcePopular_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonWebographyCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourcePopularCollection"));
        Document findQuery = coll.find(and(exists("popular",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("webSourceID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_POPULAR_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //PersonValidator.personFilmographyValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebsourcePopular_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourcePopularCollection"));
        Document findQuery = coll.find(and(exists("popular",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "webSourceID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSOURCE_POPULAR_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourcePopular_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_POPULAR_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourcePopular_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_POPULAR_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity","DigitalFirst"})
    public void validateWebsourceRecent_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonWebographyCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceRecentCollection"));
        Document findQuery = coll.find(and(exists("recent",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("webSourceID",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_WEBSOURCE_RECENT_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //PersonValidator.personFilmographyValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateWebsourceRecent_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("WebSourceRecentCollection"));
        Document findQuery = coll.find(and(exists("recent",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "webSourceID";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_WEBSOURCE_RECENT_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceRecent_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_RECENT_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "DigitalFirst"})
    public void validateWebsourceRecent_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("webSourceID","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_WEBSOURCE_RECENT_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
