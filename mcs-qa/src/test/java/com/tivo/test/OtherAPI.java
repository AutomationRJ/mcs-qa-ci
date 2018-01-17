package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.EpisodeValidator;
import com.tivo.Validators.OtherValidator;
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
 * Created by rjaiswal on 5/11/2017.
 */
public class OtherAPI {

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
    public void validateOther_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOther_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOther_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOther_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateOtherAired_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherAiredCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherAiredValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherAired_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_AIRED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherAired_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherAired_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherRatings_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherRatingCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherRatingsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherRatings_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_RATING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherRatings_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherRatings_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString());
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherRelated_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherRelatedCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherRelatedCollection"));
        Document findQuery = coll.find(and(exists("related.2",true),elemMatch("related", eq("relation", "related")),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherRelatedValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherRelated_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherRelatedCollection"));
        Document findQuery = coll.find(and(exists("related.2",true),elemMatch("related", eq("relation", "related")),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_RELATED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherRelated_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherRelated_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherReleases_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherReleasesColletion"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherReleasesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherReleases_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_RELEASES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherReleases_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherReleases_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherTheatricals_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherTheatricalsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("otherId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_OTHER_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherTheatricalsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOtherTheatricals_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OtherTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "otherId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_OTHER_THEATRICALS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateOtherTheatricals_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherTheatricals_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("otherId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateOtherDelta_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        OtherValidator.otherDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validateOtherDelta_304_response() {
        MongoCollection<Document> coll = db.getCollection(map.get("OtherCollection"));
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_OTHER_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validateOtherDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateOtherDelta_400_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_OTHER_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
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
