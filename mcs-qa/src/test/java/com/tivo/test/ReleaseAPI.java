package com.tivo.test;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import com.tivo.Validators.ReleaseValidator;
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
 * Created by rjaiswal on 5/12/2017.
 */
public class ReleaseAPI {

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
    public void validateRelease_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateRelease_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateRelease_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateRelease_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateReleaseAired_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseAiredCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseAiredValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseAired_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_AIRED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseAired_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseAired_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.2.isCast",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.2.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("credits.4.isCast",false),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.4",true),exists("credits.4.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseRatings_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseRatingCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseRatingsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseRatings_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseRatingCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_RATING_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseRatings_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseRatings_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_RATING_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseTheatricals_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ReleaseTheatricalsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("releaseId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_RELEASE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ReleaseValidator.releaseTheatricalsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateReleaseTheatricals_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ReleaseTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "releaseId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_RELEASE_THEATRICALS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateReleaseTheatricals_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateReleaseTheatricals_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("releaseId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_RELEASE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
