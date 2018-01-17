package com.tivo.Validators;

import com.mongodb.client.MongoCursor;
import io.restassured.response.Response;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by rjaiswal on 5/11/2017.
 */
public class OtherValidator {

    public static void otherValidator(Response resp, Document doc){
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
                if(((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(),((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("image.zoom"),((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("zoom"));
                    softAssert.assertEquals(resp.jsonPath().get("image.type"),((List<Document>) doc.get("t_in")).get(i).get("image", Document.class).get("type"));
                }
            }
        }
        if((doc.get("releaseYear")) != null) {
            softAssert.assertEquals(resp.jsonPath().get("year").toString(), doc.get("releaseYear").toString());
        }
        if((doc.get("runtime")) != null) {
            softAssert.assertEquals(resp.jsonPath().get("runtime").toString(),doc.get("runtime").toString());
        }
        softAssert.assertEquals(resp.jsonPath().getList("spoken"),doc.get("spoken"));
        softAssert.assertEquals(resp.jsonPath().getList("countries"),doc.get("countries"));
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
        if((doc.get("h_facebooks")) != null){
            for(int i=0;i < ((List<Document>) doc.get("h_facebooks")).size();i++){
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].facebookId").toString(),((List<Document>) doc.get("h_facebooks")).get(i).get("facebookId").toString());
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].type"),((List<Document>) doc.get("h_facebooks")).get(i).get("type"));
                softAssert.assertEquals(resp.jsonPath().get("facebooks["+ i + "].uri"),((List<Document>) doc.get("h_facebooks")).get(i).get("uri"));
            }
        }
        softAssert.assertAll();
    }

    public static void otherAiredValidator(Response resp, Document doc){
        int size = 0;
        if(((List<Document>) doc.get("aired")).size() > 0 && ((List<Document>) doc.get("aired")).size() <11){
            size = ((List<Document>) doc.get("aired")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(((List<Document>) doc.get("aired")).size() > 0){
            for(int i = 0; i < size; i++){
                softAssert.assertEquals(resp.jsonPath().get("aired["+ i + "].country"),((List<Document>) doc.get("aired")).get(i).get("country"));
                softAssert.assertEquals(resp.jsonPath().get("aired["+ i + "].date").toString(),new DateTime(((List<Document>) doc.get("aired")).get(i).get("date")).toDateTime(DateTimeZone.UTC).toString());
            }
        }
        softAssert.assertAll();
    }

    public static void otherCastValidator(Response resp,Document doc){
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
                        for(int j=0; j < ((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).size(); j++){
                            if(((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("credits[" + i + "].in").toString())){
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in").toString().toLowerCase(),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase());
                                //softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].role"),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("role"));
                            }
                        }
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"),((List<Document>) doc.get("credits")).get(i).get("type"));
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

    public static void otherCrewValidator(Response resp,Document doc){
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
                        for(int j=0; j < ((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).size(); j++){
                            if(((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("credits[" + i + "].in").toString())){
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in").toString().toLowerCase(),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase());
                                //softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].role"),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("role"));
                            }
                        }
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
                }else if (((List<Document>) doc.get("credits")).get(i).get("type").toString().equalsIgnoreCase("company")){
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.id"),((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("id"));
                    softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.name"),((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("name"));
                }
            }
        }
    }

    public static void otherCreditsValidator(Response resp, Document doc) {
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
                if(((List<Document>) doc.get("credits")).get(i).get("t_in") != null){
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("credits")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("credits[" + i + "].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].in").toString().toLowerCase(),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("t_locale").toString().toLowerCase());
                            if(((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("role") != null){
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].role"),((List<Document>) (((List<Document>) doc.get("credits")).get(i).get("t_in"))).get(j).get("role"));
                            }
                        }
                    }
                }

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

    public static void otherRatingsValidator(Response resp, Document doc) {
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

    public static void otherRelatedValidator(Response resp, Document doc) {
        int size = 0;
        int respSize = 0;
        if(((List<Document>) doc.get("related")).size() > 0 && ((List<Document>) doc.get("related")).size() <11){
            size = ((List<Document>) doc.get("related")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("related")).size() > 0) {
            for (int i = 0; i < size; i++) {
                if (((List<Document>) doc.get("related")).get(i).get("relation").toString().equalsIgnoreCase("related")) {
                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].relation"), ((List<Document>) doc.get("related")).get(i).get("relation"));
                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.ref.id").toString(), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.type"), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("type"));
                    if (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in") != null) {
                        for (int j = 0; j < ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).size(); j++) {
                            if (((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("related[" + respSize + "].content.in").toString())) {
                                softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.in"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("t_locale"));
                                softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.title"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("title"));
                                if (((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image") != null) {
                                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.ref.id").toString(), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("id").toString());
                                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.zoom"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("zoom"));
                                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.type"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("type"));
                                }
                            }
                        }
                    }
                    softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.year").toString(), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year").toString());
                }
                respSize++;
            }
        }
    }

    public static void otherReleasesValidator(Response resp, Document doc) {
        int size = 0;
        if(((List<Document>) doc.get("releases")).size() > 0 && ((List<Document>) doc.get("releases")).size() <11){
            size = ((List<Document>) doc.get("releases")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("releases")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].ref.id").toString(), ((List<Document>) doc.get("releases")).get(i).get("id").toString());
                for(int j=0; j < ((List<Document>)((List<Document>) doc.get("releases")).get(i).get("t_in")).size(); j++) {
                    if (((List<Document>) ((List<Document>) doc.get("releases")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("releases[" + i + "].in").toString())) {
                        softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].in"), ((List<Document>) (((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("t_locale"));
                        softAssert.assertEquals(resp.jsonPath().getList("releases[" + i + "].market"), ((List<Document>)(((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("f_market"));
                        softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].title"), ((List<Document>)(((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("title"));
                        if(((List<Document>) ((List<Document>) doc.get("releases")).get(i).get("t_in")).get(j).get("image") != null) {
                            if (((List<Document>) ((List<Document>) doc.get("releases")).get(i).get("t_in")).get(j).get("image", Document.class).get("id") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.ref.id").toString(), ((List<Document>) (((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image", Document.class).get("id").toString());
                                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.zoom"), ((List<Document>) (((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image", Document.class).get("zoom"));
                                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.type"), ((List<Document>) (((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image", Document.class).get("type"));
                            }
                        }
                    }
                }
                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].year").toString(),  ((List<Document>) doc.get("releases")).get(i).get("year").toString());
            }
        }
    }

    public static void otherSynopsesValidator(Response resp, Document doc) {
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
                if(((List<Document>) doc.get("synopses")).get(i).get("t_in") != null){
                    for(int j = 0; j < ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).size(); j++){
                        if(((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("synopses[" + i + "].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].in"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("t_locale"));
                            softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].synopsis"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("synopsis"));
                            if (((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("cuts") != null) {
                                softAssert.assertEquals(resp.jsonPath().getList("synopses[" + i + "].cuts"), ((List<Document>) (((List<Document>) doc.get("synopses")).get(i).get("t_in"))).get(j).get("cuts"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void otherSynopsesBestValidator(Response resp, Document doc) {
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

    public static void otherTheatricalsValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("theatricals")).size() > 0 && ((List<Document>) doc.get("theatricals")).size() < 11) {
            size = ((List<Document>) doc.get("theatricals")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in"), doc.get("t_default").toString());
        if (((List<Document>) doc.get("theatricals")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("theatricals[" + i + "].country"), ((List<Document>) doc.get("theatricals")).get(i).get("country"));
                if(((List<Document>) doc.get("theatricals")).get(i).get("releases") != null){
                    for (int j = 0; j < ((List<Document>)((List<Document>) doc.get("theatricals")).get(i).get("releases")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("theatricals")).get(i).get("releases")).get(j).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("theatricals[" + i + "].releases[" + j + "].type"),(((List<Document>)((List<Document>) doc.get("theatricals")).get(i).get("releases")).get(j).get("type")));
                        }
                        softAssert.assertEquals(resp.jsonPath().get("theatricals[" + i + "].releases[" + j + "].date").toString(), new DateTime(((List<Document>)((List<Document>) doc.get("theatricals")).get(i).get("releases")).get(j).get("date")).toDateTime(DateTimeZone.UTC).toString());
                    }
                }
            }
        }
    }

    public static void otherDeltaValidator(Response resp, MongoCursor<Document> cursor){
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
