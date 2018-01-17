package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.MovieValidator;
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
 * Created by rjaiswal on 4/27/2017.
 */
public class MovieAPI {

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
    public void validateMovie_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCollection"));
        Document findQuery = coll.find(and(exists("d_movieType",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovie_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovie_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovie_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

//    @Test(groups = "Regression")
//    public void validateMovie401_response(){
//        Properties prop = Util.loadProperties();
//        MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
//        Document findQuery = coll.find(exists("d_movieType",true)).first();
//        String id = findQuery.get("_id").toString();
//        RequestSpecification requestSpecification = given().pathParam("movieId",id);
//        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_LOOKUP_PATH_PARAM, HttpStatus.SC_UNAUTHORIZED);
//        System.out.println(response);
//    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieAired_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieAiredCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903604296)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieAiredValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieAired_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieAiredCollection"));
        Document findQuery = coll.find(and(exists("aired.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_AIRED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieAired_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieAired400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","").log().all();
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_AIRED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieCast_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903604296)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id).pathParam("cast", true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieCastValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieCast_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.0.isCast",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"true",EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieCast_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber())).pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieCast_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","").pathParam("cast",true);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieCrew_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903604296)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieCrewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieCrew_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(eq("credits.isCast",false),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        String pathParam2 = "cast";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,pathParam2,id,"false",EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieCrew_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber())).pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieCrew_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","").pathParam("cast",false);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CAST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieCredits_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCreditsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieCreditsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieCredits_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCreditsCollection"));
        Document findQuery = coll.find(and(exists("credits.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_CREDITS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieCredits_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieCredits_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_CREDITS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieRatings_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieRatingsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieRatingsCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RATINGS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieRatingsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieRatings_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieRatingsCollection"));
        Document findQuery = coll.find(and(exists("RatingCollection.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_RATINGS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieRatings_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RATINGS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieRatings_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RATINGS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieRelated_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieRelatedCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("related.relation", "related"),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieRelatedValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieRelated_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieRelatedCollection"));
        Document findQuery = coll.find(and(exists("related",true),eq("related.relation", "related"),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_RELATED_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieRelated_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieRelated_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELATED_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieReleases_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieReleasesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieReleasesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieReleases_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieReleasesCollection"));
        Document findQuery = coll.find(and(exists("releases.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_RELEASES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieReleases_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieReleases_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_RELEASES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieReview_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieReviewCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieReviewCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_REVIEW_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieReviewValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieReview_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieReviewCollection"));
        Document findQuery = coll.find(and(exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_REVIEW_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieReview_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_REVIEW_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieReview_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_REVIEW_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieSynopsesCollection_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieSynopsesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieSynopsesCollection_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_SYNOPSES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieSynopsesCollection_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieSynopsesCollection_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieSynopsesBest_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieSynopsesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieSynopsesBestValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieSynopsesBest_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieSynopsesCollection"));
        Document findQuery = coll.find(and(exists("synopses.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_SYNOPSES_BEST_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validateMovieSynopsesBest_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieSynopsesBest_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_SYNOPSES_BEST_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieTheatricals_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieTheatricalsCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903607312)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("movieId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieTheatricalsValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateMovieTheatricals_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("MovieTheatricalsCollection"));
        Document findQuery = coll.find(and(exists("theatricals.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "movieId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_MOVIE_THEATRICALS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieTheatricals_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieTheatricals_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("movieId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_THEATRICALS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validateMovieDelta_200_response() throws Exception{
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        MovieValidator.movieDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validateMovieDelta_304_response() {
        MongoCollection<Document> coll = db.getCollection(map.get("MovieCollection"));
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_MOVIE_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validateMovieDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateMovieDelta_400_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_MOVIE_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
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
