package com.tivo.Validators;

import com.mongodb.client.MongoCursor;
import io.restassured.response.Response;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by rjaiswal on 5/16/2017.
 */
public class PersonValidator {

    public static void personValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"),doc.get("f_digitalFirst"));
        }*/
        for(int i = 0; i < ((List<Document>) doc.get("t_in")).size(); i++){
            if(((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())) {
                softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(), ((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().toLowerCase());
                softAssert.assertEquals(resp.jsonPath().get("name"), ((List<Document>) doc.get("t_in")).get(i).get("name"));
                if (((List<Document>) doc.get("t_in")).get(i).get("alts") != null) {
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("first") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.first"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("first"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("last") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.last"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("last"));
                    }
                    if (((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("birth") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("alts.birth"), ((List<Document>) doc.get("t_in")).get(i).get("alts", Document.class).get("birth"));
                    }
                }
                if (((List<Document>) doc.get("t_in")).get(i).get("birthplace") != null){
                    softAssert.assertEquals(resp.jsonPath().get("birthplace"), ((List<Document>) doc.get("t_in")).get(i).get("birthplace"));
                }
                if (((List<Document>) doc.get("t_in")).get(i).get("highSchool") != null){
                    softAssert.assertEquals(resp.jsonPath().get("highSchool"), ((List<Document>) doc.get("t_in")).get(i).get("highSchool"));
                }
            }
        }
        if( doc.get("image") != null) {
            for (int i = 0; i < ((List<Document>) doc.get("image")).size(); i++) {
                if (((List<Document>) doc.get("image")).get(i).get("id").toString().equalsIgnoreCase(resp.jsonPath().get("image.ref.id").toString())) {
                    softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), ((List<Document>) doc.get("image")).get(i).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("image.zoom"), ((List<Document>) doc.get("image")).get(i).get("zoom"));
                    softAssert.assertEquals(resp.jsonPath().get("image.type"), ((List<Document>) doc.get("image")).get(i).get("type"));
                }
            }
        }
        if(doc.get("gender") != null) {
            softAssert.assertEquals(resp.jsonPath().get("gender"), doc.get("gender"));
        }
        if(doc.get("isCelebrity") != null) {
            softAssert.assertEquals(resp.jsonPath().get("isCelebrity"), doc.get("isCelebrity"));
        }
        if(doc.get("dob") != null) {
            softAssert.assertEquals(resp.jsonPath().get("dob").toString(), new DateTime(doc.get("dob")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("homepage") != null) {
            softAssert.assertEquals(resp.jsonPath().get("homepage"), doc.get("homepage"));
        }
        if(doc.get("h_facebooks") != null){
            for(int i=0;i < ((List<Document>) doc.get("h_facebooks")).size();i++){
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].facebookId").toString(),((List<Document>) doc.get("h_facebooks")).get(i).get("facebookId").toString());
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].type"),((List<Document>) doc.get("h_facebooks")).get(i).get("type"));
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].uri"),((List<Document>) doc.get("h_facebooks")).get(i).get("uri"));
            }
        }
        if(doc.get("h_twitters") != null){
            softAssert.assertEquals(resp.jsonPath().getList("twitters"),doc.get("h_twitters"));
        }
        if(doc.get("h_web") != null){
            if(doc.get("h_web",Document.class).get("featured") != null){
                for(int i = 0; i < ((List<Document>)doc.get("h_web",Document.class).get("featured")).size(); i++){
                    if(((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("web.featured[" + i + "].type"),((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("type"));
                    }
                    if(((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("content") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("content")).size() ; j++){
                            softAssert.assertEquals(resp.jsonPath().get("web.featured["+ i +"].content["+ j +"].ref.id"),((List<Document>)((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("content")).get(j).get("id"));
                            if(((List<Document>)((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("content")).get(j).get("type") != null){
                                softAssert.assertEquals(resp.jsonPath().get("web.featured["+ i +"].content["+ j +"].type"),((List<Document>)((List<Document>)doc.get("h_web",Document.class).get("featured")).get(i).get("content")).get(j).get("type"));
                            }
                        }
                    }
                }
            }
        }
        softAssert.assertAll();
    }

    public static void nameValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("name"),doc.get("name"));
    }

    public static void personBioValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("bios")).size() > 0) {
            for (int i = 0; i < ((List<Document>) doc.get("bios")).size(); i++) {
                softAssert.assertEquals(resp.jsonPath().get("bios[" + i + "].type"), ((List<Document>) doc.get("bios")).get(i).get("type"));
                if(((List<Document>) doc.get("bios")).get(i).get("t_in") !=null){
                    for(int j=0; j < ((List<Document>)(((List<Document>) doc.get("bios")).get(i).get("t_in"))).size(); j++){
                        if(((List<Document>)(((List<Document>) doc.get("bios")).get(i).get("t_in"))).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("bios["+ i +"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("bios["+ i +"].in").toString().toLowerCase(),((List<Document>)(((List<Document>) doc.get("bios")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase());
                            softAssert.assertEquals(resp.jsonPath().get("bios["+ i +"].bio"),((List<Document>)(((List<Document>) doc.get("bios")).get(i).get("t_in"))).get(j).get("bio"));
                        }
                    }
                }
            }
        }
    }

    public static void personFilmographyValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("filmography")).size() > 0 && ((List<Document>) doc.get("filmography")).size() < 11) {
            size = ((List<Document>) doc.get("filmography")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("filmography")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].type"), ((List<Document>) doc.get("filmography")).get(i).get("type"));
                if(((List<Document>) doc.get("filmography")).get(i).get("isCast") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].isCast"), ((List<Document>) doc.get("filmography")).get(i).get("isCast"));
                }
                if(((List<Document>) doc.get("filmography")).get(i).get("roles") != null) {
                    softAssert.assertEquals(resp.jsonPath().getList("filmography[" + i + "].roles"), ((List<Document>) doc.get("filmography")).get(i).get("roles"));
                }
                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.ref.id").toString(), ((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("id").toString());
                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.type"), ((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("type"));
                if(((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in") != null){
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("filmography["+ i + "].content.in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("filmography["+ i +"].content.in").toString().toLowerCase(),((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("t_locale").toString().toLowerCase());
                            softAssert.assertEquals(resp.jsonPath().get("filmography["+ i +"].content.title"),((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("title"));
                            if(((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("image",Document.class).get("id") != null){
                                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.image.ref.id").toString(), ((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("image",Document.class).get("id").toString());
                                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.image.zoom"), ((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("image",Document.class).get("zoom"));
                                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.image.type"), ((List<Document>)((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("t_in")).get(j).get("image",Document.class).get("type"));
                            }
                            if(((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("year") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("filmography[" + i + "].content.year").toString(), ((List<Document>) doc.get("filmography")).get(i).get("content", Document.class).get("year").toString());
                            }
                        }
                    }
                }

            }
        }
    }

    public static void personRelativesValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("relatives")).size() > 0 && ((List<Document>) doc.get("relatives")).size() < 11) {
            size = ((List<Document>) doc.get("relatives")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("relatives")).size() > 0) {
            for (int i = 0; i < size; i++) {
                if(((List<Document>) doc.get("relatives")).get(i).get("name") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].name"), ((List<Document>) doc.get("relatives")).get(i).get("name"));
                }
                if(((List<Document>) doc.get("relatives")).get(i).get("note") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].note"), ((List<Document>) doc.get("relatives")).get(i).get("note"));
                }
                if(((List<Document>) doc.get("relatives")).get(i).get("person") != null){
                    if(((List<Document>) doc.get("relatives")).get(i).get("person",Document.class).get("id") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].person.ref.id").toString(), ((List<Document>) doc.get("relatives")).get(i).get("person", Document.class).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].person.name"), ((List<Document>) doc.get("relatives")).get(i).get("person", Document.class).get("name"));
                        if (((List<Document>) doc.get("relatives")).get(i).get("person", Document.class).get("alts") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].person.alts.first"), ((List<Document>) doc.get("relatives")).get(i).get("person", Document.class).get("alts", Document.class).get("first"));
                            softAssert.assertEquals(resp.jsonPath().get("relatives[" + i + "].person.alts.last"), ((List<Document>) doc.get("relatives")).get(i).get("person", Document.class).get("alts", Document.class).get("last"));
                        }
                    }
                }
            }
        }
    }

    public static void personDeltaValidator(Response resp, MongoCursor<Document> cursor){
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
