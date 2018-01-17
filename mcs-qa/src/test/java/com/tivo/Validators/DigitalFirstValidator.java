package com.tivo.Validators;

import io.restassured.response.Response;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class DigitalFirstValidator {

    public static void websourceValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"), doc.get("f_digitalFirst"));
        }*/
        if(doc.get("name") != null){
            softAssert.assertEquals(resp.jsonPath().get("name").toString(),doc.get("name").toString());
        }
        if(doc.get("url") != null){
            softAssert.assertEquals(resp.jsonPath().get("url").toString(),doc.get("url").toString());
        }
        if(doc.get("featured") != null){
            for(int i = 0; i < ((List<Document>)doc.get("featured")).size(); i++){
                if(((List<Document>)doc.get("featured")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].type"),((List<Document>)doc.get("featured")).get(i).get("type"));
                }
                if(((List<Document>)doc.get("featured")).get(i).get("content") != null){
                    for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).size(); j++){
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].ref.id"),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("id"));
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].type"),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("type"));
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("isweb") != null){
                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].isWeb"),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("isweb"));
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("t_in") != null){
                            for(int k = 0; k < ((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("t_in")).size(); k++){
                                if(((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("featured["+i+"].content["+j+"].in").toString())){
                                    softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].title"),((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("t_in")).get(k).get("title"));
                                }
                            }
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution") != null){
                            for(int k = 0; k < ((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).size(); k++){
                                if(((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("name") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].distribution["+k+"].name"),((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("name"));
                                }
                                if(((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("url") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].distribution["+k+"].url"),((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("url"));
                                }
                                if(((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform") != null){
                                    for(int l=0; l < ((List<Document>)((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform")).size() ; l++){
                                        if(((List<Document>)((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform")).get(l).get("platform") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].distribution["+k+"].platform["+l+"].platform"),((List<Document>)((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform")).get(l).get("platform"));
                                        }
                                        if(((List<Document>)((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform")).get(l).get("url") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].distribution["+k+"].platform["+l+"].url"),((List<Document>)((List<Document>) ((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("distribution")).get(k).get("platform")).get(l).get("url"));
                                        }
                                    }
                                }
                                if(((List<Document>)(((List<Document>)(((List<Document>)doc.get("featured")).get(i).get("content"))).get(j).get("distribution"))).get(k).get("quality") != null){
                                    softAssert.assertEquals(resp.jsonPath().getList("featured["+i+"].content["+j+"].distribution["+k+"].quality"),((List<Document>)(((List<Document>)(((List<Document>)doc.get("featured")).get(i).get("content"))).get(j).get("distribution"))).get(k).get("quality"));
                                }
                            }
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social") != null){
                            if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("likes") != null){
                                softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].social.likes").toString(),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("likes").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("dislikes") != null){
                                softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].social.dislikes").toString(),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("dislikes").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("views") != null){
                                softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].social.views").toString(),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("views").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("followers") != null){
                                softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].social.followers").toString(),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("followers").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("shares") != null){
                                softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].social.shares").toString(),((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("social", Document.class).get("shares").toString());
                            }
                        }
                        if(((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses") != null){
                            for(int k=0; k < ((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).size(); k++){
                                if(((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("length") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].synopses["+k+"].length"),((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("length"));
                                }
                                if(((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("t_in") != null){
                                    for(int l=0; l < ((List<Document>)((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("t_in")).size(); l++){
                                        if(((List<Document>)((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("t_in")).get(l).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("featured["+i+"].content["+j+"].synopses["+k+"].in").toString())){
                                            softAssert.assertEquals(resp.jsonPath().get("featured["+i+"].content["+j+"].synopses["+k+"].synopsis"),((List<Document>)((List<Document>)((List<Document>)((List<Document>)doc.get("featured")).get(i).get("content")).get(j).get("synopses")).get(k).get("t_in")).get(l).get("synopsis"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        softAssert.assertAll();
    }

    public static void websourceSeriesValidator(Response resp, Document doc){
        int size = 0;
        if(((List<Document>) doc.get("series")).size() > 0 && ((List<Document>) doc.get("series")).size() <11){
            size = ((List<Document>) doc.get("series")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"), doc.get("f_digitalFirst"));
        }*/
        if (((List<Document>) doc.get("series")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("series[" + i + "].ref.id"), ((List<Document>) doc.get("series")).get(i).get("id"));
                if (((List<Document>) doc.get("series")).get(i).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("series[" + i + "].type"), ((List<Document>) doc.get("series")).get(i).get("type"));
                }
                if (((List<Document>) doc.get("series")).get(i).get("isweb") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("series[" + i + "].isWeb"), ((List<Document>) doc.get("series")).get(i).get("isweb"));
                }
                if (((List<Document>) doc.get("series")).get(i).get("t_in") != null) {
                    for(int j=0 ; j < ((List<Document>)((List<Document>) doc.get("series")).get(i).get("t_in")).size() ; j++){
                        if(((List<Document>)((List<Document>) doc.get("series")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("series["+i+"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("series["+i+"].in"),((List<Document>)((List<Document>) doc.get("series")).get(i).get("t_in")).get(j).get("t_locale"));
                            //softAssert.assertEquals(resp.jsonPath().getList("series["+i+"].market"),((List<Document>)((List<Document>) doc.get("series")).get(i).get("t_in")).get(j).get("f_market"));
                            softAssert.assertEquals(resp.jsonPath().get("series["+i+"].title"),((List<Document>)((List<Document>) doc.get("series")).get(i).get("t_in")).get(j).get("title"));
                        }
                    }
                }
                if(((List<Document>) doc.get("series")).get(i).get("synopses") != null){
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).size(); j++){
                        softAssert.assertEquals(resp.jsonPath().get("series["+i+"].synopses["+j+"].length"),((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("length"));
                        if(((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("t_in") != null){
                            for(int k=0; k < ((List<Document>)((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("t_in")).size() ; k++){
                                if(((List<Document>)((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("series["+i+"].synopses["+j+"].in"))){
                                    softAssert.assertEquals(resp.jsonPath().get("series["+i+"].synopses["+j+"].in").toString().toLowerCase(),((List<Document>)((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("t_locale").toString().toLowerCase());
                                    softAssert.assertEquals(resp.jsonPath().get("series["+i+"].synopses["+j+"].synopsis").toString().toLowerCase(),((List<Document>)((List<Document>)((List<Document>) doc.get("series")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("synopsis").toString().toLowerCase());
                                }
                            }
                        }
                    }
                }
            }
        }
        softAssert.assertAll();
    }

    public static void websourceVideosValidator(Response resp, Document doc){
        int size = 0;
        if(((List<Document>) doc.get("videos")).size() > 0 && ((List<Document>) doc.get("videos")).size() <11){
            size = ((List<Document>) doc.get("videos")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"), doc.get("f_digitalFirst"));
        }*/
        if (((List<Document>) doc.get("videos")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("videos[" + i + "].ref.id"), ((List<Document>) doc.get("videos")).get(i).get("id"));
                if (((List<Document>) doc.get("videos")).get(i).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("videos[" + i + "].type"), ((List<Document>) doc.get("videos")).get(i).get("type"));
                }
                if (((List<Document>) doc.get("videos")).get(i).get("isweb") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("videos[" + i + "].isWeb"), ((List<Document>) doc.get("videos")).get(i).get("isweb"));
                }
                if (((List<Document>) doc.get("videos")).get(i).get("t_in") != null) {
                    for(int j=0 ; j < ((List<Document>)((List<Document>) doc.get("videos")).get(i).get("t_in")).size() ; j++){
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("videos["+i+"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].in"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("t_in")).get(j).get("t_locale"));
                            //softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].market"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("t_in")).get(j).get("f_market"));
                            softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].title"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("t_in")).get(j).get("title"));
                        }
                    }
                }
                if (((List<Document>) doc.get("videos")).get(i).get("distribution") != null){
                    for(int j=0 ; j < ((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].distribution["+j+"].name"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("name"));
                        }
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("url") != null){
                            softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].distribution["+j+"].url"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("url"));
                        }
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("platform") != null){
                            for(int k=0; k < ((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("platform")).size() ; k++){
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].distribution["+j+"].platform["+k+"].platform"),((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("platform")).get(k).get("platform"));
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].distribution["+j+"].platform["+k+"].url"),((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("platform")).get(k).get("url"));
                            }
                        }
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("quality") != null){
                            softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].distribution["+j+"].quality"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("quality"));
                        }
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social") != null){
                            if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("likes") != null){
                                softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].distribution["+j+"].social.likes"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("likes"));
                            }
                            if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("dislikes") != null){
                                softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].distribution["+j+"].social.dislikes"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("dislikes"));
                            }
                            if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("views") != null){
                                softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].distribution["+j+"].social.views"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("views"));
                            }
                            if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("shares") != null){
                                softAssert.assertEquals(resp.jsonPath().getList("videos["+i+"].distribution["+j+"].social.shares"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("distribution")).get(j).get("social",Document.class).get("shares"));
                            }
                        }
                    }
                }
                if(((List<Document>) doc.get("videos")).get(i).get("series") != null){
                    softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].series.ref.id"),((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("id"));
                    if(((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("t_in") != null){
                        for(int j =0 ; j < ((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("t_in")).size(); j++){
                            if(((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("videos["+i+"].series.in"))){
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].series.in").toString(),((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("t_in")).get(j).get("t_locale").toString());
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].series.title").toString(),((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("series",Document.class).get("t_in")).get(j).get("title").toString());
                            }
                        }
                    }
                }
                if (((List<Document>) doc.get("videos")).get(i).get("withinSeries") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("videos[" + i + "].withinSeries"), ((List<Document>) doc.get("videos")).get(i).get("withinSeries"));
                }
                if(((List<Document>) doc.get("videos")).get(i).get("season") != null){
                    softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].season.ref.id"),((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("id"));
                    if(((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("t_in") != null){
                        for(int j =0 ; j < ((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("t_in")).size(); j++){
                            if(((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("videos["+i+"].season.in"))){
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].season.in").toString(),((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("t_in")).get(j).get("t_locale").toString());
                                softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].season.title").toString(),((List<Document>) ((List<Document>) doc.get("videos")).get(i).get("season",Document.class).get("t_in")).get(j).get("title").toString());
                            }
                        }
                    }
                }
                if (((List<Document>) doc.get("videos")).get(i).get("withinSeason") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("videos[" + i + "].withinSeason"), ((List<Document>) doc.get("videos")).get(i).get("withinSeason"));
                }
                if(((List<Document>) doc.get("videos")).get(i).get("synopses") != null){
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).size(); j++){
                        softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].synopses["+j+"].length"),((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("length"));
                        if(((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("t_in") != null){
                            for(int k=0; k < ((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("t_in")).size() ; k++){
                                if(((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("videos["+i+"].synopses["+j+"].in"))){
                                    softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].synopses["+j+"].in").toString().toLowerCase(),((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("t_locale").toString().toLowerCase());
                                    softAssert.assertEquals(resp.jsonPath().get("videos["+i+"].synopses["+j+"].synopsis").toString().toLowerCase(),((List<Document>)((List<Document>)((List<Document>) doc.get("videos")).get(i).get("synopses")).get(j).get("t_in")).get(k).get("synopsis").toString().toLowerCase());
                                }
                            }
                        }
                    }
                }
            }
        }
        softAssert.assertAll();
    }

    public static void seriesEpisodesValidator(Response resp, Document doc){
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
                            if(((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).get(j).get("f_market") != null){
                                softAssert.assertEquals(resp.jsonPath().getList("episodes[" + i + "].market"),((List<Document>) (((List<Document>) doc.get("episodes")).get(i).get("t_in"))).get(j).get("f_market"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void seriesRelatedValidator(Response resp, Document doc) {
        int size = 0;
        int respSize = 0;
        if(((List<Document>) doc.get("related")).size() > 0 && ((List<Document>) doc.get("related")).size() <11){
            size = ((List<Document>) doc.get("related")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"),doc.get("f_digitalFirst"));
        }
        if(doc.get("isweb") != null){
            softAssert.assertEquals(resp.jsonPath().get("isWeb"),doc.get("isweb"));
        }
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
                    if(((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.year").toString(), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year").toString());
                    }
                }
                respSize++;
            }
        }
    }

    public static void episodeRelatedValidator(Response resp, Document doc) {
        int size = 0;
        int respSize = 0;
        if(((List<Document>) doc.get("related")).size() > 0 && ((List<Document>) doc.get("related")).size() <11){
            size = ((List<Document>) doc.get("related")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"),doc.get("f_digitalFirst"));
        }
        if(doc.get("isweb") != null){
            softAssert.assertEquals(resp.jsonPath().get("isWeb"),doc.get("isweb"));
        }
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
                                    if (((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("id") != null) {
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.ref.id").toString(), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("id").toString());
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.zoom"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("zoom"));
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.type"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("type"));
                                    }
                                }
                            }
                        }
                    }
                    if(((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.year").toString(), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year").toString());
                    }
                }
                respSize++;
            }
        }
    }

    public static void clipValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        /*if(doc.get("f_digitalFirst") != null){
            softAssert.assertEquals(resp.jsonPath().getList("dfsource"), doc.get("f_digitalFirst"));
        }*/
        if(doc.get("t_in") != null){
            for(int i=0; i < ((List<Document>)doc.get("t_in")).size(); i++){
                if(((List<Document>)doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())){
                    softAssert.assertEquals(resp.jsonPath().get("title"),((List<Document>)doc.get("t_in")).get(i).get("title"));
                }
            }
        }
        if(doc.get("websource") != null){
            softAssert.assertEquals(resp.jsonPath().get("websource.ref.id"),doc.get("websource", Document.class).get("id"));
            softAssert.assertEquals(resp.jsonPath().get("websource.name"),doc.get("websource", Document.class).get("name"));
        }
        if(doc.get("distribution") != null) {
            for (int i = 0; i < ((List<Document>)doc.get("distribution")).size(); i++) {
                if (((List<Document>)doc.get("distribution")).get(i).get("name") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("distribution[" + i + "].name"), ((List<Document>)doc.get("distribution")).get(i).get("name"));
                }
                if (((List<Document>)doc.get("distribution")).get(i).get("url") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("distribution[" + i + "].url"), ((List<Document>)doc.get("distribution")).get(i).get("url"));
                }
                if (((List<Document>)doc.get("distribution")).get(i).get("platform") != null) {
                    for (int j = 0; j < ((List<Document>)((List<Document>)doc.get("distribution")).get(i).get("platform")).size(); j++) {
                        if (((List<Document>)((List<Document>)doc.get("distribution")).get(i).get("platform")).get(j).get("platform") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("distribution[" + i + "].platform[" + j + "].platform"), ((List<Document>)((List<Document>)doc.get("distribution")).get(i).get("platform")).get(j).get("platform"));
                        }
                        if (((List<Document>)((List<Document>)doc.get("distribution")).get(i).get("platform")).get(j).get("url") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("distribution[" + i + "].platform[" + j + "].url"), ((List<Document>)((List<Document>)doc.get("distribution")).get(i).get("platform")).get(j).get("url"));
                        }
                    }
                }
                if (((List<Document>)doc.get("distribution")).get(i).get("quality") != null) {
                    softAssert.assertEquals(resp.jsonPath().getList("distribution[" + i + "].quality"), ((List<Document>)doc.get("distribution")).get(i).get("quality"));
                }
            }
        }
        if(doc.get("social") != null){
            if(doc.get("social", Document.class).get("likes") != null){
                softAssert.assertEquals(resp.jsonPath().get("social.likes").toString(),doc.get("social", Document.class).get("likes").toString());
            }
            if(doc.get("social", Document.class).get("dislikes") != null){
                softAssert.assertEquals(resp.jsonPath().get("social.dislikes").toString(),doc.get("social", Document.class).get("dislikes").toString());
            }
            if(doc.get("social", Document.class).get("views") != null){
                softAssert.assertEquals(resp.jsonPath().get("social.views").toString(),doc.get("social", Document.class).get("views").toString());
            }
            if(doc.get("social", Document.class).get("followers") != null){
                softAssert.assertEquals(resp.jsonPath().get("social.followers").toString(),doc.get("social", Document.class).get("followers").toString());
            }
            if(doc.get("social", Document.class).get("shares") != null){
                softAssert.assertEquals(resp.jsonPath().get("social.shares").toString(),doc.get("social", Document.class).get("shares").toString());
            }
        }
        if(doc.get("year") != null){
            softAssert.assertEquals(resp.jsonPath().get("year"), doc.get("year"));
        }
        if(doc.get("releaseDate") != null){
            softAssert.assertEquals(resp.jsonPath().get("releaseDate").toString(), new DateTime(doc.get("releaseDate")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("runtime") != null){
            softAssert.assertEquals(resp.jsonPath().get("runtime"), doc.get("runtime"));
        }
        if(doc.get("spoken") != null){
            softAssert.assertEquals(resp.jsonPath().getList("spoken"), doc.get("spoken"));
        }
        if(doc.get("countries") != null){
            softAssert.assertEquals(resp.jsonPath().getList("countries"), doc.get("countries"));
        }
        if(doc.get("keywords") != null){
            softAssert.assertEquals(resp.jsonPath().getList("keywords"),doc.get("keywords"));
        }
        softAssert.assertAll();
    }

    public static void clipCreditValidator(Response resp, Document doc) {
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
                    if(((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("name") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.name"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("name"));
                    }
                    if (((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts") != null) {
                        if(((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("first") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.first"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("first"));
                        }
                        if(((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("last") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].person.alts.last"), ((List<Document>) doc.get("credits")).get(i).get("person", Document.class).get("alts", Document.class).get("last"));
                        }
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

    public static void clipRelatedValidator(Response resp, Document doc) {
        int size = 0;
        int respSize = 0;
        if(((List<Document>) doc.get("related")).size() > 0 && ((List<Document>) doc.get("related")).size() <11){
            size = ((List<Document>) doc.get("related")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if(doc.get("isweb") != null){
            softAssert.assertEquals(resp.jsonPath().get("isWeb"),doc.get("isweb"));
        }
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
                                    if (((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("id") != null) {
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.ref.id").toString(), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("id").toString());
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.zoom"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("zoom"));
                                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.image.type"), ((List<Document>) (((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("t_in"))).get(j).get("image", Document.class).get("type"));
                                    }
                                }
                            }
                        }
                    }
                    if(((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("related[" + respSize + "].content.year").toString(), ((List<Document>) doc.get("related")).get(i).get("content", Document.class).get("year").toString());
                    }
                }
                respSize++;
            }
        }
    }

    public static void clipReleasesValidator(Response resp, Document doc) {
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
                        if(((List<Document>) ((List<Document>) doc.get("releases")).get(i).get("t_in")).get(j).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.ref.id").toString(), ((List<Document>)(((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image",Document.class).get("id").toString());
                            softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.zoom"), ((List<Document>)(((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image",Document.class).get("zoom"));
                            softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].image.type"), ((List<Document>)(((List<Document>) doc.get("releases")).get(i).get("t_in"))).get(j).get("image",Document.class).get("type"));
                        }
                    }
                }
                softAssert.assertEquals(resp.jsonPath().get("releases[" + i + "].year").toString(),  ((List<Document>) doc.get("releases")).get(i).get("year").toString());
            }
        }
    }
}
