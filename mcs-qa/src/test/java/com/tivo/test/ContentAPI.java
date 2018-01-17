package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.tivo.Validators.ContentValidator;
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

/**
 * Created by rjaiswal on 5/16/2017.
 */
public class ContentAPI {

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
    public void validateContent_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("ContentCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("ContentCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("contentId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_CONTENT_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        ContentValidator.contentValidator(response,findQuery);
    }

    @Test(groups = "Regression")
    public void validateContent_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("contentId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CONTENT_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = "Regression")
    public void validateContent_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("contentId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_CONTENT_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateContent_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("ContentCollection"));
        Document findQuery = coll.find(and(exists("t_in.2",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "contentId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_CONTENT_LOOKUP_PATH_PARAM,map);
    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
