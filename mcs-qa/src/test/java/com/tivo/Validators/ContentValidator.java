package com.tivo.Validators;

import io.restassured.response.Response;
import org.bson.Document;
import org.testng.asserts.SoftAssert;
import java.util.List;

/**
 * Created by rjaiswal on 5/16/2017.
 */
public class ContentValidator {

    public static void contentValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        for(int i = 0; i < ((List<Document>) doc.get("t_in")).size(); i++){
            if(((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())) {
                /*softAssert.assertEquals(resp.jsonPath().getList("market"), ((List<Document>) doc.get("t_in")).get(i).get("f_market"));*/
                softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(), ((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().toLowerCase());
                softAssert.assertEquals(resp.jsonPath().get("title"), ((List<Document>) doc.get("t_in")).get(i).get("title"));
                if (((List<Document>) doc.get("t_in")).get(i).get("alts") != null) {
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short15") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.short15"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short15"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short30") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.short30"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short30"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short8") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.short8"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short8"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short50") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.short50"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("short50"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("alias") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.alias"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("alias"));
                    }
                }
                if(((List<Document>) doc.get("t_in")).get(i).get("image") != null) {
                    if (((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("image.zoom"), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("zoom"));
                        softAssert.assertEquals(resp.jsonPath().get("image.type"), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("type"));
                    }
                }
            }
        }
        if(doc.get("synonyms") != null){
            for(int i = 0; i < ((List<Document>)doc.get("synonyms")).size(); i++){
                if(((List<Document>)doc.get("synonyms")).get(i).get("rv20") != null){
                    softAssert.assertEquals(resp.jsonPath().get("synonyms[" + i + "].rv20").toString(),((List<Document>)doc.get("synonyms")).get(i).get("rv20").toString());
                }
                if(((List<Document>)doc.get("synonyms")).get(i).get("rv11") != null){
                    softAssert.assertEquals(resp.jsonPath().get("synonyms[" + i + "].rv11").toString(),((List<Document>)doc.get("synonyms")).get(i).get("rv11").toString());
                }
                if(((List<Document>)doc.get("synonyms")).get(i).get("rvgroup") != null){
                    softAssert.assertEquals(resp.jsonPath().get("synonyms[" + i + "].rvgroup").toString(),((List<Document>)doc.get("synonyms")).get(i).get("rvgroup").toString());
                }
                if(((List<Document>)doc.get("synonyms")).get(i).get("eidr") != null){
                    softAssert.assertEquals(resp.jsonPath().get("synonyms[" + i + "].eidr").toString(),((List<Document>)doc.get("synonyms")).get(i).get("eidr").toString());
                }
            }
        }
        if((doc.get("type")) != null) {
            softAssert.assertEquals(resp.jsonPath().get("type"), doc.get("type"));
        }
        softAssert.assertAll();
    }
}
