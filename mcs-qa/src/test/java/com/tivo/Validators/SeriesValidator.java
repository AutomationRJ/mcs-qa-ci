package com.tivo.Validators;

import com.mongodb.client.MongoCursor;
import io.restassured.response.Response;
import org.bson.Document;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by rjaiswal on 5/9/2017.
 */
public class SeriesValidator {

    public static void seriesValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"),doc.get("f_digitalFirst"));
        }
        if(doc.get("f_market") != null) {
            softAssert.assertEquals(resp.jsonPath().getList("market"), doc.get("f_market"));
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
        if(doc.get("spoken") != null) {
            softAssert.assertEquals(resp.jsonPath().getList("spoken"), doc.get("spoken"));
        }
        if(doc.get("countries") != null) {
            softAssert.assertEquals(resp.jsonPath().getList("countries"), doc.get("countries"));
        }
        if(doc.get("keywords") != null){
            softAssert.assertEquals(resp.jsonPath().getList("keywords"),doc.get("keywords"));
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
        if(doc.get("websource") != null){
            softAssert.assertEquals(resp.jsonPath().get("websource.ref.id"),doc.get("websource", Document.class).get("id"));
            softAssert.assertEquals(resp.jsonPath().get("websource.name"),doc.get("websource", Document.class).get("name"));
        }
        softAssert.assertAll();
    }

    public static void seriesCastValidator(Response resp,Document doc){
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() <11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(((List<Document>) doc.get("credits")).size() > 0 ){
            for(int i = 0; i < size; i++){
                if(((List<Document>) doc.get("credits")).get(i).get("isCast") != null){
                    if(((List<Document>) doc.get("credits")).get(i).get("isCast").equals(true)){
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in"),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(0).get("t_locale"));
                        if(((List<Document>) doc.get("credits")).get(i).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"),((List<Document>) doc.get("credits")).get(i).get("type"));
                        }
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].isCast"),((List<Document>) doc.get("credits")).get(i).get("isCast"));
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.ref.id").toString(),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.name"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("name"));
                        if(((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts") != null){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.first"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts",Document.class).get("first"));
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.last"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts",Document.class).get("last"));
                        }
                        if((((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")) != null) {
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("id").toString());
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.zoom"), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("zoom"));
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.type"), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("type"));
                        }
                    }
                }
            }
        }
    }

    public static void seriesCrewValidator(Response resp,Document doc){
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() <11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(((List<Document>) doc.get("credits")).size() > 0 ){
            for(int i = 0; i < size; i++){
                if(((List<Document>) doc.get("credits")).get(i).get("isCast") != null){
                    if(((List<Document>) doc.get("credits")).get(i).get("isCast").equals(false)){
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in"),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(0).get("t_locale"));
                        if(((List<Document>) doc.get("credits")).get(i).get("type") !=null){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"),((List<Document>) doc.get("credits")).get(i).get("type"));
                        }
                        if(((List<Document>) doc.get("credits")).get(i).get("type") !=null && ((List<Document>) doc.get("credits")).get(i).get("type").toString().equalsIgnoreCase("company")){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.id"),((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("id"));
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.name"),((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("name"));
                        }
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].isCast"),((List<Document>) doc.get("credits")).get(i).get("isCast"));
                        if(((List<Document>) doc.get("credits")).get(i).get("person") != null){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.ref.id").toString(),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("id").toString());
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.name"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("name"));
                            if(((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts") != null){
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.first"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts",Document.class).get("first"));
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.last"),((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts",Document.class).get("last"));
                            }
                            if((((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")) != null){
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.ref.id").toString(),((List<Document>)((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("id").toString());
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.zoom"),((List<Document>)((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("zoom"));
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.type"),((List<Document>)((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("type"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void seriesCreditsValidator(Response resp, Document doc) {
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() <11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("credits")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in"), ((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(0).get("t_locale"));
                if (((List<Document>) doc.get("credits")).get(i).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"), ((List<Document>) doc.get("credits")).get(i).get("type"));
                }
                if (((List<Document>) doc.get("credits")).get(i).get("isCast") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].isCast"), ((List<Document>) doc.get("credits")).get(i).get("isCast"));
                }
                if (((List<Document>) doc.get("credits")).get(i).get("person") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.ref.id").toString(), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.name"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("name"));
                    if (((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.first"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("first"));
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.last"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("last"));
                    }
                    if ((((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")) != null) {
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.zoom"), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("zoom"));
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.image.type"), ((List<Document>) ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("image")).get(0).get("type"));
                    }
                }
                if (((List<Document>) doc.get("credits")).get(i).get("organization") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.id"), ((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("id"));
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.name"), ((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("name"));
                }
            }
        }
    }

    public static void seriesHistoryValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("history")).size() > 0 && ((List<Document>) doc.get("history")).size() < 11) {
            size = ((List<Document>) doc.get("history")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("history")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("history[" + i + "].start"),  ((List<Document>) doc.get("history")).get(i).get("start"));
                if(((List<Document>) doc.get("history")).get(i).get("end") != null){
                    softAssert.assertEquals(resp.jsonPath().get("history[" + i + "].end"),  ((List<Document>) doc.get("history")).get(i).get("end"));
                }
                if(((List<Document>) doc.get("history")).get(i).get("source") != null){
                    for(int j = 0; j < ((List<Document>)(((List<Document>) doc.get("history")).get(i).get("source"))).size(); j++){
                        softAssert.assertEquals(resp.jsonPath().get("history["+ i +"].source["+ j + "].ref.id").toString(),((List<Document>)(((List<Document>) doc.get("history")).get(i).get("source"))).get(j).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("history["+ i +"].source["+ j + "].name"),((List<Document>)(((List<Document>) doc.get("history")).get(i).get("source"))).get(j).get("name"));
                    }
                }
            }
        }
    }

    public static void seriesRatingsValidator(Response resp, Document doc) {
        int size = 0;
        if(((List<Document>) doc.get("RatingCollection")).size() > 0 && ((List<Document>) doc.get("RatingCollection")).size() <11){
            size = ((List<Document>) doc.get("RatingCollection")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("RatingCollection")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("ratings[" + i + "].in"), ((List<Document>) doc.get("RatingCollection")).get(i).get("t_default"));
                softAssert.assertEquals(resp.jsonPath().get("ratings[" + i + "].type"), ((List<Document>) doc.get("RatingCollection")).get(i).get("RatingSystemName"));
                softAssert.assertEquals(resp.jsonPath().get("ratings[" + i + "].rating"), ((List<Document>) doc.get("RatingCollection")).get(i).get("RatingValue"));
                softAssert.assertEquals(resp.jsonPath().getList("countries"),((List<Document>) doc.get("RatingCollection")).get(i).get("countries"));
            }
        }
    }

    public static void seriesSynopsesValidator(Response resp, Document doc) {
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

    public static void seriesSynopsesBestValidator(Response resp, Document doc) {
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

    public static void seriesSeasonsValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("seasons")).size() > 0 && ((List<Document>) doc.get("seasons")).size() < 11) {
            size = ((List<Document>) doc.get("seasons")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("seasons")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].ref.id").toString(), ((List<Document>) doc.get("seasons")).get(i).get("id").toString());
                softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].in"), ((List<Document>) (((List<Document>) doc.get("seasons")).get(i).get("t_in"))).get(0).get("t_locale"));
                softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].title"), ((List<Document>) (((List<Document>) doc.get("seasons")).get(i).get("t_in"))).get(0).get("title"));
                softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].start").toString(), ((List<Document>) doc.get("seasons")).get(i).get("start").toString());
                if(((List<Document>) doc.get("seasons")).get(i).get("end") != null){
                    softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].end").toString(), ((List<Document>) doc.get("seasons")).get(i).get("end").toString());
                }
                softAssert.assertEquals(resp.jsonPath().get("seasons[" + i + "].number").toString(), ((List<Document>) doc.get("seasons")).get(i).get("number").toString());
            }
        }
    }

    public static void seriesDeltaValidator(Response resp, MongoCursor<Document> cursor){
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
