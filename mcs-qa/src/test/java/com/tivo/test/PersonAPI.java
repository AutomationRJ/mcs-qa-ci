package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.tivo.Validators.OtherValidator;
import com.tivo.Validators.PersonValidator;
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

/**
 * Created by rjaiswal on 5/16/2017.
 */
public class PersonAPI {

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
    public void validatePerson_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePerson_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCollection"));
        Document findQuery = coll.find(and(exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePerson_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePerson_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validatePersonName_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonNameCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonNameCollection"));
        Document findQuery = coll.find(and(exists("r_person",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("nameId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_NAME_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.nameValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonName_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonNameCollection"));
        Document findQuery = coll.find(and(exists("r_person",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "nameId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_NAME_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonName_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("nameId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_NAME_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonName_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("nameId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_NAME_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonBio_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonBiosCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonBiosCollection"));
        Document findQuery = coll.find(and(exists("bios.1",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_BIOS_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personBioValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonBio_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonBiosCollection"));
        Document findQuery = coll.find(and(exists("bios.1",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_BIOS_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonBio_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_BIOS_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonBio_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_BIOS_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonFilmography_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonFilmographyCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonFilmographyCollection"));
        Document findQuery = coll.find(and(exists("filmography.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_FILMOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personFilmographyValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonFilmography_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonFilmographyCollection"));
        Document findQuery = coll.find(and(exists("filmography.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_FILMOGRAPHY_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonFilmography_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_FILMOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonFilmography_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_FILMOGRAPHY_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonRelatives_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonRelativesCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonRelativesCollection"));
        Document findQuery = coll.find(and(exists("relatives.3",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_RELATIVES_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personRelativesValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validatePersonRelatives_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonRelativesCollection"));
        Document findQuery = coll.find(and(exists("relatives.3",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_RELATIVES_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = "Regression")
    public void validatePersonRelatives_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_RELATIVES_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonRelatives_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_RELATIVES_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Sanity"})
    public void validatePersonDelta_200_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("PersonCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCollection"));
        MongoCursor<Document> cursor = coll.find(and(gt("_ts", Util.from()),lte("_ts",Util.to()),gt("_id",0),exists("_del",false))).projection(Projections.fields((Projections.include("_id","_v","id","r_movie","_pub")))).sort(Sorts.ascending("_id")).limit(limit).iterator();
        requestSpecification.pathParam("date",Util.currentDate()).pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_DELTA_PATH_PARAM, HttpStatus.SC_OK);
        PersonValidator.personDeltaValidator(response,cursor);
        afterTest();
        setup();
        //System.out.println(response);
    }

    /*@Test(groups = {"Regression", "Etag"})
    public void validatePersonDelta_304_response() {
        String pathParam = "date";
        String pathParam2 = "size";
        EtagVerfication.deltaAPIeTagVerification(pathParam,pathParam2,Util.currentDate(),"10",EndPoint.GET_PERSON_DELTA_PATH_PARAM,map);
    }*/

    @Test(groups = "Regression")
    public void validatePersonDelta_404_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","3000-01-01").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_DELTA_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        afterTest();
        setup();
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validatePersonDelta_400_response() throws Exception{
        RequestSpec request = new RequestSpec();
        RequestSpec.resetBasePath();
        RequestSpec.setBasePath(map.get("deltaBasePath"));
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("date","").pathParam("size", limit);
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_DELTA_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
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
