package com.tivo.Validators;

import com.mongodb.client.MongoCursor;
import io.restassured.response.Response;
import org.bson.Document;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by rjaiswal on 5/9/2017.
 */
public class SeasonValidator {

    public static void seasonValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"),doc.get("f_digitalFirst"));
        }
        if(doc.get("f_market") != null){
            softAssert.assertEquals(resp.jsonPath().getList("market"),doc.get("f_market"));
        }*/
        for(int i = 0; i < ((List<Document>) doc.get("t_in")).size(); i++){
            softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().toLowerCase());
            softAssert.assertEquals(resp.jsonPath().get("title"),((List<Document>) doc.get("t_in")).get(i).get("title"));
            if(((List<Document>) doc.get("t_in")).get(i).get("alts") != null){
                softAssert.assertEquals(resp.jsonPath().get("alts.short15"),((List<Document>) doc.get("t_in")).get(i).get("alts",Document.class).get("short15"));
                softAssert.assertEquals(resp.jsonPath().get("alts.short30"),((List<Document>) doc.get("t_in")).get(i).get("alts",Document.class).get("short30"));
                softAssert.assertEquals(resp.jsonPath().get("alts.short8"),((List<Document>) doc.get("t_in")).get(i).get("alts",Document.class).get("short8"));
                softAssert.assertEquals(resp.jsonPath().get("alts.short50"),((List<Document>) doc.get("t_in")).get(i).get("alts",Document.class).get("short50"));
            }
            if(((List<Document>) doc.get("t_in")).get(i).get("image") != null) {
                if (((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("image.zoom"), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("zoom"));
                    softAssert.assertEquals(resp.jsonPath().get("image.type"), ((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("type"));
                }
            }
        }
        if(doc.get("websource") != null){
            softAssert.assertEquals(resp.jsonPath().get("websource.ref.id"),doc.get("websource",Document.class).get("id"));
            softAssert.assertEquals(resp.jsonPath().get("websource.name"),doc.get("websource",Document.class).get("name"));
        }
        if(doc.get("number") != null) {
            softAssert.assertEquals(resp.jsonPath().get("number").toString(), doc.get("number").toString());
        }
        if(doc.get("episodes") != null) {
            softAssert.assertEquals(resp.jsonPath().get("episodes").toString(), doc.get("episodes").toString());
        }
        if(doc.get("series") != null) {
            softAssert.assertEquals(resp.jsonPath().get("series.ref.id").toString(), doc.get("series", Document.class).get("id").toString());
            if ((doc.get("series", Document.class).get("t_in")) != null) {
                for (int i = 0; i < ((List<Document>) doc.get("series", Document.class).get("t_in")).size(); i++) {
                    if (((List<Document>) doc.get("series", Document.class).get("t_in")).get(i).get("t_locale").toString().toLowerCase().equals(resp.jsonPath().get("in").toString().toLowerCase())) {
                        softAssert.assertEquals(resp.jsonPath().get("series.in").toString().toLowerCase(), ((List<Document>) doc.get("series", Document.class).get("t_in")).get(i).get("t_locale").toString().toLowerCase());
                        //softAssert.assertEquals(resp.jsonPath().getList("series.market"), ((List<Document>) doc.get("series", Document.class).get("t_in")).get(i).get("f_market"));
                        softAssert.assertEquals(resp.jsonPath().get("series.title"), ((List<Document>) doc.get("series", Document.class).get("t_in")).get(i).get("title"));
                    }
                }
            }
        }
        if(doc.get("network") != null) {
            softAssert.assertEquals(resp.jsonPath().get("network"), doc.get("network"));
        }
        if(doc.get("start") != null) {
            softAssert.assertEquals(resp.jsonPath().get("start").toString(), doc.get("start").toString());
        }
        if(doc.get("end") != null){
            softAssert.assertEquals(resp.jsonPath().get("end").toString(),doc.get("end").toString());
        }
        if(doc.get("spoken") != null) {
            softAssert.assertEquals(resp.jsonPath().getList("spoken"), doc.get("spoken"));
        }
        if(doc.get("countries") != null) {
            softAssert.assertEquals(resp.jsonPath().getList("countries"), doc.get("countries"));
        }
        if(doc.get("smarttags") != null){
            softAssert.assertEquals(resp.jsonPath().getList("keywords"),doc.get("smarttags"));
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
        if(doc.get("h_facebooks") != null){
            for(int i=0;i < ((List<Document>) doc.get("h_facebooks")).size();i++){
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].facebookId").toString(),((List<Document>) doc.get("h_facebooks")).get(i).get("facebookId").toString());
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].type"),((List<Document>) doc.get("h_facebooks")).get(i).get("type"));
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].uri"),((List<Document>) doc.get("h_facebooks")).get(i).get("uri"));
            }
        }
        softAssert.assertAll();
    }

    public static void seasonEpisodesValidator(Response resp, Document doc){
        int size = 0;
        if(((List<Document>) doc.get("episodes")).size() > 0 && ((List<Document>) doc.get("episodes")).size() <11){
            size = ((List<Document>) doc.get("episodes")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(((List<Document>) doc.get("episodes")).size() > 0 ){
            for(int i = 0; i < size; i++){
                softAssert.assertEquals(resp.jsonPath().get("episodes["+ i + "].ref.id").toString(),((List<Document>) doc.get("episodes")).get(i).get("id").toString());
                if(((List<Document>) doc.get("episodes")).get(i).get("t_in") != null) {
                    for (int j = 0; j < ((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).size(); j++) {
                        if (((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase().equals(resp.jsonPath().get("episodes[" + i + "].in").toString().toLowerCase())) {
                            softAssert.assertEquals(resp.jsonPath().get("episodes[" + i + "].in").toString().toLowerCase(), ((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase());
                            softAssert.assertEquals(resp.jsonPath().get("episodes[" + i + "].title"), ((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).get(j).get("title"));
                        }
                    }
                }
            }
        }
    }

    public static void seasonSynopsesValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("synopses")).size() > 0 && ((List<Document>) doc.get("synopses")).size() < 11) {
            size = ((List<Document>) doc.get("synopses")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("synopses")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].length"), ((List<Document>) doc.get("synopses")).get(i).get("length"));
                softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].in"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(0).get("t_locale"));
                softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].synopsis"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(0).get("synopsis"));
                if (((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(0).get("cuts") != null) {
                    softAssert.assertEquals(resp.jsonPath().getList("synopses[" + i + "].cuts"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(0).get("cuts"));
                }
            }
        }
    }

    public static void seasonSynopsesBestValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("synopses")).size() > 0 && ((List<Document>) doc.get("synopses")).size() < 11) {
            size = ((List<Document>) doc.get("synopses")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("synopses")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("synopsis.length"), ((List<Document>) doc.get("synopses")).get(i).get("length"));
                if(((List<Document>) doc.get("synopses")).get(i).get("t_in") != null){
                    for(int j = 0; j < ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).size(); j++){
                        if(((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("synopsis.in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("synopsis.in"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("t_locale"));
                            softAssert.assertEquals(resp.jsonPath().get("synopsis.synopsis"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("synopsis"));
                            if (((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("cuts") != null) {
                                softAssert.assertEquals(resp.jsonPath().getList("synopsis.cuts"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("cuts"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void seasonDeltaValidator(Response resp, MongoCursor<Document> cursor){
        int i = 0;
        SoftAssert softAssert = new SoftAssert();
        try{
            while (cursor.hasNext()){
                softAssert.assertEquals(resp.jsonPath().get("deltas["+ Integer.toString(i) +"].id").toString(),cursor.next().get("_id").toString());
                i++;
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
    }
}
