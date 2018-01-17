package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.MovieValidator;
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
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lte;

/**
 * Created by rjaiswal on 5/9/2017.
 */
public class SeriesAPI {

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
    public void validateSeries_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeries_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCollection"));
        Document findQuery = coll.find(and(exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeries_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeries_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateSeriesCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id).pathParam("cast", true);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesHistory_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesHistoryCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesHistoryCollection"));
        Document findQuery = coll.find(and(exists("history.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_HISTORY_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesHistoryValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesHistory_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesHistoryCollection"));
        Document findQuery = coll.find(and(exists("history.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_HISTORY_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesHistory_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_HISTORY_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesHistory_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_HISTORY_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesRatings_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesRatingsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesRatingsCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesRatingsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesRatings_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesRatingsCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_RATING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesRatings_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesRatings_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSeasons_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesSeasonsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSeasonsCollection"));
        Document findQuery = coll.find(and(exists("seasons.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("seriesId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SERIES_SEASONS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesSeasonsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSeriesSeasons_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesSeasonsCollection"));
        Document findQuery = coll.find(and(exists("seasons.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "seriesId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SERIES_SEASONS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateSeriesSeasons_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SEASONS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesSeasons_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("seriesId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_SEASONS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateSeriesDelta_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SeriesCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        SeriesValidator.seriesDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validateSeriesDelta_304_response() {
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_SERIES_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validateSeriesDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateSeriesDelta_400_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SERIES_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
