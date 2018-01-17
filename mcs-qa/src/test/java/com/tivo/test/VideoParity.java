package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.TVScheduleValidator;
import com.tivo.Validators.VideoParityValidator;
import com.tivo.common.MongoConn;
import com.tivo.utility.Util;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by rjaiswal on 6/8/2017.
 */

public class VideoParity {

    private static Map<String,String> map;
    private static String version = "2.8.0";
    private static Long timeStamp = 1515609000000L;

    @BeforeTest(alwaysRun = true)
    public void setup() throws Exception{
        map = Util.loadProperties();
    }

    @Test(groups = "Regression", enabled=false)
    public void validateSourceTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SourceCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SourceCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            VideoParityValidator.sourceTransformValidator(findQuery,devQuery,softAssert);
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validatePersonTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("PersonCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> prodCursor = prodColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields(Projections.include("_id"))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(prodCursor.hasNext()){
            Document prod = prodCursor.next();
            idArray.put(prod.getLong("_id"));
        }
        prodCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        /*Document devQuery = devColl.find(eq("_id", 882550748)).first();
        Document findQuery = prodColl.find(eq("_id", 882550748)).first();
        VideoParityValidator.personTransformValidator(findQuery, devQuery, softAssert);*/
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            //Document devQuery = devColl.find(eq("_id", 20820462)).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            //Document findQuery = prodColl.find(eq("_id", 20820462)).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.personTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        //MongoCursor<Document> devCursor = devColl.find(and(eq("_v",version),exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).iterator();
        MongoCursor<Document> prodCursor = prodColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields(Projections.include("_id"))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(prodCursor.hasNext()){
            Document prod = prodCursor.next();
            idArray.put(prod.getLong("_id"));
        }
        prodCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateEpisodeTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("EpisodeCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        //MongoCursor<Document> devCursor = devColl.find(and(eq("_v",version),exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).iterator();
        MongoCursor<Document> prodCursor = prodColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields(Projections.include("_id"))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(prodCursor.hasNext()){
            Document prod = prodCursor.next();
            idArray.put(prod.getLong("_id"));
        }
        prodCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.episodeTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeasonTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeasonCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        //MongoCursor<Document> devCursor = devColl.find(and(eq("_v",version),exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).iterator();
        MongoCursor<Document> prodCursor = prodColl.find(and(eq("_ts",timeStamp),exists("_del",false))).projection(Projections.fields(Projections.include("_id"))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(prodCursor.hasNext()){
            Document prod = prodCursor.next();
            idArray.put(prod.getLong("_id"));
        }
        prodCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seasonTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateOtherTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("OtherCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        //MongoCursor<Document> devCursor = devColl.find(and(eq("_v",version),exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).iterator();
        MongoCursor<Document> prodCursor = prodColl.find(and(eq("_ts",timeStamp),exists("_del",false))).projection(Projections.fields(Projections.include("_id"))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(prodCursor.hasNext()){
            Document prod = prodCursor.next();
            idArray.put(prod.getLong("_id"));
        }
        prodCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.otherTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateDvbTripletsTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("DvbTripletsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("DvbTripletsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.dvbTripletsTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateServiceTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ServiceCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("ServiceCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.serviceTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateMovieRelatedTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieRelatedCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("MovieRelatedCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.movieRelatedTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesAwardTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesAwardCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesAwardCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesAwardTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesCreditsTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesCreditsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesCreditsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesCreditsTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesHistoryTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesHistoryCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesHistoryCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesHistoryTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesRatingTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesRatingsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesRatingsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesRatingTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesSeasonsTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesSeasonsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesSeasonsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesSeasonsTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeriesSynopsesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeriesSynopsesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeriesSynopsesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesSynopsesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeasonEpisodesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonEpisodesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeasonEpisodesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seriesSeasonsTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateSeasonSynopsesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SeasonSynopsesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("SeasonSynopsesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.seasonSynopsesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateEpisodeCreditsTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeCreditsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("EpisodeCreditsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.episodeCreditsTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateEpisodeRatingTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeRatingCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("EpisodeRatingCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.episodeRatingTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateEpisodeReleasesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeReleasesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("EpisodeReleasesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.episodeReleasesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateEpisodeSynopsesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("EpisodeSynopsesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("EpisodeSynopsesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.episodeSynopsesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateOtherRelatedTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherRelatedCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("OtherRelatedCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.otherRelatedTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateContentTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ContentCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("ContentCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.contentTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateContentImagesTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ContentImagesCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("ContentImagesCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.contentImagesTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateOtherRatingTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("OtherRatingCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("OtherRatingCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.otherRatingTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateMovieRatingTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("MovieRatingsCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("MovieRatingsCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(gt("_ts",timeStamp),exists("_del",false))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.ascending("_id")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < idArray.length(); i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.movieRatingTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }

    @Test(groups = "Regression", enabled = false)
    public void validateAiringTransform() throws JSONException {
        JSONArray idArray = new JSONArray();
        MongoCollection<Document> devColl = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        MongoCollection<Document> prodColl = new MongoConn().mongoJDBC(map.get("MongoProdHost"),map.get("MongoDatabase"),map.get("AiringCollection"));
        //db.getCollection('movie_related').find({"related":{$exists : true}},{"-id":1}).limit(1000)
        //MongoCursor<Document> devCursor = devColl.find(exists("related",true)).projection(Projections.fields((Projections.include("_id")))).limit(1000).iterator();
        MongoCursor<Document> devCursor = devColl.find(and(exists("_del",false),gt("_ts",timeStamp))).projection(Projections.fields((Projections.include("_id")))).sort(Sorts.descending("_ts")).limit(100000).iterator();
        while(devCursor.hasNext()){
            Document dev = devCursor.next();
            idArray.put(dev.getLong("_id"));
        }
        devCursor.close();
        SoftAssert softAssert = new SoftAssert();
        VideoParityValidator.initCount();
        for(int i = 0; i < 25000; i++) {
            Long id = null;
            //Document dev = devCursor.next();
            //id = Long.parseLong(dev.get("_id").toString()) ;
            Document devQuery = devColl.find(eq("_id", idArray.getLong(i))).first();
            Document findQuery = prodColl.find(eq("_id", idArray.getLong(i))).first();
            System.out.println(idArray.getLong(i));
            try {
                VideoParityValidator.airingTransformValidator(findQuery, devQuery, softAssert);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }
}
