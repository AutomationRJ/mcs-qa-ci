package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.EpisodeValidator;
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
 * Created by rjaiswal on 5/10/2017.
 */
public class EpisodeAPI {

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
    public void validateEpisode_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        Document findQuery = coll.find(and(exists("series.t_in.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisode_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        Document findQuery = coll.find(and(exists("series.t_in.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisode_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateEpisode_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAired_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeAiredCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeAiredValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeAired_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_AIRED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAired_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeAired_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeRatings_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeRatingCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeRatingsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeRatings_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_RATING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeRatings_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeRatings_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeReleases_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeReleasesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeReleasesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeReleases_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_RELEASES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeReleases_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeReleases_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("episodeId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_EPISODE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateEpisodeSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "episodeId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_EPISODE_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("episodeId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateEpisodeDelta_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        EpisodeValidator.episodeDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validateEpisodeDelta_304_response() {
        MongoCollection<Document> coll = db.getCollection(map.get("EpisodeCollection"));
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_EPISODE_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validateEpisodeDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateEpisodeDelta_400_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_EPISODE_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
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
