package com.tivo.Validators;

import io.restassured.response.Response;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.asserts.SoftAssert;

import javax.swing.event.DocumentEvent;
import java.util.List;

/**
 * Created by rjaiswal on 5/25/2017.
 */
public class SportsValidator {

    public static void gameValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        softAssert.assertEquals(resp.jsonPath().get("type"),doc.get("type"));
        if(doc.get("gender") != null){
            softAssert.assertEquals(resp.jsonPath().get("gender"),doc.get("gender"));
        }
        if(doc.get("note") != null){
            softAssert.assertEquals(resp.jsonPath().get("note"),doc.get("note"));
        }
        if(doc.get("url") != null){
            softAssert.assertEquals(resp.jsonPath().get("url"),doc.get("url"));
        }
        softAssert.assertEquals(resp.jsonPath().get("status"),doc.get("status"));
        if (doc.get("teams") != null){
            for(int i = 0; i < ((List<Document>)doc.get("teams")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].ref.id").toString(), ((List<Document>) doc.get("teams")).get(i).get("id").toString());
                softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].name"),((List<Document>)doc.get("teams")).get(i).get("name"));
                if(((List<Document>)doc.get("teams")).get(i).get("image") != null) {
                    if (((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("id") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].image.ref.id").toString(), ((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("id").toString());
                    }
                    if (((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("zoom") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].image.zoom"), ((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("zoom"));
                    }
                    if (((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("type") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].image.type"), ((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("type"));
                    }
                }
                if(((List<Document>)doc.get("teams")).get(i).get("school") != null){
                    softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].school.ref.id").toString(),((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].school.name"),((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("name"));
                    if(((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("image") != null){
                        if (((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("image", Document.class).get("id") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].school.image.ref.id").toString(), ((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("image", Document.class).get("id").toString());
                        }
                        if (((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("zoom") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].school.image.zoom"), ((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("image", Document.class).get("zoom"));
                        }
                        if (((List<Document>) doc.get("teams")).get(i).get("image", Document.class).get("type") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("teams[" + i + "].school.image.type"), ((List<Document>)doc.get("teams")).get(i).get("school", Document.class).get("image", Document.class).get("type"));
                        }
                    }
                }
                if(((List<Document>)doc.get("teams")).get(i).get("major") != null){
                    softAssert.assertEquals(resp.jsonPath().get("teams["+ i +"].major"),((List<Document>)doc.get("teams")).get(i).get("major"));
                }
                if(((List<Document>)doc.get("teams")).get(i).get("minor") != null){
                    softAssert.assertEquals(resp.jsonPath().get("teams["+ i +"].minor"),((List<Document>)doc.get("teams")).get(i).get("minor"));
                }
                if(((List<Document>)doc.get("teams")).get(i).get("isHome") != null){
                    softAssert.assertEquals(resp.jsonPath().get("teams["+ i +"].isHome"),((List<Document>)doc.get("teams")).get(i).get("isHome"));
                }
            }
        }
        if (doc.get("league") != null){
            softAssert.assertEquals(resp.jsonPath().get("league.ref.id").toString(),doc.get("league",Document.class).get("id").toString());
            softAssert.assertEquals(resp.jsonPath().get("league.name"),doc.get("league",Document.class).get("name"));
            if(doc.get("league",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.type"),doc.get("league",Document.class).get("type"));
            }
            if(doc.get("league",Document.class).get("image") != null){
                if(doc.get("league",Document.class).get("image",Document.class).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("league.image.ref.id").toString(),doc.get("league",Document.class).get("image",Document.class).get("id").toString());
                }
                if(doc.get("league",Document.class).get("image",Document.class).get("zoom") != null){
                    softAssert.assertEquals(resp.jsonPath().get("league.image.zoom"),doc.get("league",Document.class).get("image",Document.class).get("zoom"));
                }
                if(doc.get("league",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("league.image.type"),doc.get("league",Document.class).get("image",Document.class).get("type"));
                }
            }
        }
        if(doc.get("major") != null){
            softAssert.assertEquals(resp.jsonPath().get("major"),doc.get("major"));
        }
        if(doc.get("minor") != null){
            softAssert.assertEquals(resp.jsonPath().get("minor"),doc.get("minor"));
        }
        if(doc.get("start") != null){
            softAssert.assertEquals(resp.jsonPath().get("start").toString(),doc.get("start").toString());
        }
        if(doc.get("venue") != null){
            softAssert.assertEquals(resp.jsonPath().get("venue"),doc.get("venue"));
        }
        if(doc.get("city") != null){
            softAssert.assertEquals(resp.jsonPath().get("city"),doc.get("city"));
        }
        if(doc.get("state") != null){
            softAssert.assertEquals(resp.jsonPath().get("state"),doc.get("state"));
        }
        softAssert.assertEquals(resp.jsonPath().get("country"),doc.get("country"));
        if(doc.get("tv") != null){
            softAssert.assertEquals(resp.jsonPath().getList("tv"),doc.get("tv"));
        }
        if(doc.get("radio") != null){
            softAssert.assertEquals(resp.jsonPath().getList("radio"),doc.get("radio"));
        }
        softAssert.assertAll();
    }

    public static void leagueValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(doc.get("t_default") != null) {
            softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(), doc.get("t_default").toString().toLowerCase());
        }
        if(doc.get("type") != null) {
            softAssert.assertEquals(resp.jsonPath().get("type"), doc.get("type"));
        }
        if(doc.get("name") != null) {
            softAssert.assertEquals(resp.jsonPath().get("name"), doc.get("name"));
        }
        if(doc.get("subleague") !=null){
            for(int i=0; i < ((List<Document>)doc.get("subleague")).size(); i++){
                if(((List<Document>)doc.get("subleague")).get(i).get("name") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].name").toString(), ((List<Document>) doc.get("subleague")).get(i).get("name").toString());
                }
                if(((List<Document>)doc.get("subleague")).get(i).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].type").toString(), ((List<Document>) doc.get("subleague")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("subleague")).get(i).get("subleague") != null) {
                    for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).size(); j++){
                        if(((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("name").toString());
                        }
                        if(((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].type").toString(),((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("type").toString());
                        }
                        if(((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team") != null){
                            for (int k =0; k < ((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).size(); k++){
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id").toString());
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name").toString());
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image") != null){
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.zoom").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.type").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type").toString());
                                    }
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school") != null){
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("image") != null) {
                                        if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id") != null) {
                                            softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id").toString());
                                        }
                                        if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom") != null) {
                                            softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.zoom").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom").toString());
                                        }
                                        if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type") != null) {
                                            softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.type").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type").toString());
                                        }
                                    }
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].major").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major").toString());
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].minor").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor").toString());
                                }
                                if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster") != null){
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.season").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.role").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.isPlayer").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.number").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.status").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.remark").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark").toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(doc.get("image") != null){
            if(doc.get("image",Document.class).get("id") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), doc.get("image", Document.class).get("id").toString());
            }
            if(doc.get("image",Document.class).get("zoom") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.zoom"), doc.get("image", Document.class).get("zoom"));
            }
            if(doc.get("image",Document.class).get("type") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.type"), doc.get("image", Document.class).get("type"));
            }
        }
        if(doc.get("description") != null){
            softAssert.assertEquals(resp.jsonPath().get("description"), doc.get("description"));
        }
        if(doc.get("gender") != null){
            softAssert.assertEquals(resp.jsonPath().get("gender"), doc.get("gender"));
        }
        if(doc.get("affiliation") != null){
            softAssert.assertEquals(resp.jsonPath().get("affiliation"), doc.get("affiliation"));
        }
        if(doc.get("major") != null){
            softAssert.assertEquals(resp.jsonPath().get("major"), doc.get("major"));
        }
        if(doc.get("minor") != null){
            softAssert.assertEquals(resp.jsonPath().get("minor"), doc.get("minor"));
        }
        if(doc.get("h_twitters") != null){
            softAssert.assertEquals(resp.jsonPath().getList("twitters"),doc.get("h_twitters"));
        }
        softAssert.assertAll();
    }

    public static void schoolValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        softAssert.assertEquals(resp.jsonPath().get("name"),doc.get("name"));
        if(doc.get("image") != null){
            softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(),doc.get("image",Document.class).get("id").toString());
            softAssert.assertEquals(resp.jsonPath().get("image.zoom"),doc.get("image",Document.class).get("zoom"));
            softAssert.assertEquals(resp.jsonPath().get("image.type"),doc.get("image",Document.class).get("type"));
        }
        if(doc.get("city") != null){
            softAssert.assertEquals(resp.jsonPath().get("city"),doc.get("city"));
        }
        if(doc.get("state") != null){
            softAssert.assertEquals(resp.jsonPath().get("state"),doc.get("state"));
        }
        softAssert.assertEquals(resp.jsonPath().get("country"),doc.get("country"));
        softAssert.assertAll();
    }

    public static void teamValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("type") != null) {
            softAssert.assertEquals(resp.jsonPath().get("type").toString(), doc.get("type").toString());
        }
        if(doc.get("t_in") != null){
            for(int i = 0; i < ((List<Document>)doc.get("t_in")).size(); i++){
                if(((List<Document>)doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())){
                    if(((List<Document>)doc.get("t_in")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("name").toString(),((List<Document>)doc.get("t_in")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("t_in")).get(i).get("alts") != null){
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("nickname") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.nickname").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("nickname").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("representing") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.representing").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("representing").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("display") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.display").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("display").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short20") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.short20").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short20").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short4") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.short4").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short4").toString());
                        }
                    }
                    if(((List<Document>)doc.get("t_in")).get(i).get("alias") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("t_in")).get(i).get("alias")).size(); j++) {
                            if (((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("name") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("alias[" + j + "].name").toString(), ((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("name").toString());
                            }
                            if (((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("first") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("alias[" + j + "].first").toString(), ((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("first").toString());
                            }
                            if (((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("last") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("alias[" + j + "].last").toString(), ((List<Document>) ((List<Document>) doc.get("t_in")).get(i).get("alias")).get(j).get("last").toString());
                            }
                        }
                    }
                }
            }
        }
        if(doc.get("callSign") != null){
            softAssert.assertEquals(resp.jsonPath().get("call").toString(),doc.get("callSign").toString());
        }
        if(doc.get("category") != null){
            softAssert.assertEquals(resp.jsonPath().get("category").toString(),doc.get("category").toString());
        }
        if(doc.get("gender") != null){
            softAssert.assertEquals(resp.jsonPath().get("gender").toString(),doc.get("gender").toString());
        }
        if(doc.get("description") != null){
            softAssert.assertEquals(resp.jsonPath().get("description").toString(),doc.get("description").toString());
        }
        if(doc.get("formed") != null){
            softAssert.assertEquals(resp.jsonPath().get("formed").toString(),doc.get("formed").toString());
        }
        if(doc.get("image") != null){
            softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(),doc.get("image",Document.class).get("id").toString());
            if(doc.get("image",Document.class).get("zoom") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.zoom").toString(),doc.get("image",Document.class).get("zoom").toString());
            }
            if(doc.get("image",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.type").toString(),doc.get("image",Document.class).get("type").toString());
            }
        }
        if(doc.get("coach") != null){
            softAssert.assertEquals(resp.jsonPath().get("coach.ref.id").toString(),doc.get("coach",Document.class).get("id").toString());
            if(doc.get("coach",Document.class).get("name") != null){
                softAssert.assertEquals(resp.jsonPath().get("coach.name").toString(),doc.get("coach",Document.class).get("name").toString());
            }
            if(doc.get("coach",Document.class).get("image") != null){
                softAssert.assertEquals(resp.jsonPath().get("coach.image.ref.id").toString(),doc.get("coach",Document.class).get("image",Document.class).get("id").toString());
                if(doc.get("coach",Document.class).get("image",Document.class).get("zoom") != null){
                    softAssert.assertEquals(resp.jsonPath().get("coach.image.zoom").toString(),doc.get("coach",Document.class).get("image",Document.class).get("zoom").toString());
                }
                if(doc.get("coach",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("coach.image.type").toString(),doc.get("coach",Document.class).get("image",Document.class).get("type").toString());
                }
            }
        }
        if(doc.get("league") != null){
            softAssert.assertEquals(resp.jsonPath().get("league.ref.id").toString(),doc.get("league", Document.class).get("id").toString());
            if(doc.get("league", Document.class).get("name") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.name").toString(), doc.get("league", Document.class).get("name").toString());
            }
            if(doc.get("league", Document.class).get("major") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.major").toString(), doc.get("league", Document.class).get("major").toString());
            }
            if(doc.get("league", Document.class).get("minor") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.minor").toString(), doc.get("league", Document.class).get("minor").toString());
            }
            if(doc.get("league", Document.class).get("subleague") != null){
                for(int i = 0; i < ((List<Document>)doc.get("league", Document.class).get("subleague")).size(); i++){
                    if(((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].name").toString(),((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].type").toString(),((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("type").toString());
                    }
                    if(((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type") != null){
                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].type").toString(),((List<Document>)((List<Document>)doc.get("league", Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type").toString());
                            }
                        }
                    }
                }
            }
            if(doc.get("league",Document.class).get("image") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.image.ref.id").toString(),doc.get("league",Document.class).get("image",Document.class).get("id").toString());
                if(doc.get("league",Document.class).get("image",Document.class).get("zoom") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("league.image.zoom").toString(), doc.get("league", Document.class).get("image", Document.class).get("zoom").toString());
                }
                if(doc.get("league",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("league.image.type").toString(),doc.get("league",Document.class).get("image",Document.class).get("type").toString());
                }
            }
        }
        if(doc.get("major") != null){
            softAssert.assertEquals(resp.jsonPath().get("major"),doc.get("major"));
        }
        if(doc.get("minor") != null){
            softAssert.assertEquals(resp.jsonPath().get("minor"),doc.get("minor"));
        }
        if(doc.get("school") != null){
            softAssert.assertEquals(resp.jsonPath().get("school.ref.id").toString(),doc.get("school", Document.class).get("id").toString());
            softAssert.assertEquals(resp.jsonPath().get("school.name"), doc.get("school", Document.class).get("name"));
            if(doc.get("school",Document.class).get("image") != null){
                softAssert.assertEquals(resp.jsonPath().get("school.image.ref.id").toString(),doc.get("school",Document.class).get("image",Document.class).get("id").toString());
                if(doc.get("school",Document.class).get("image",Document.class).get("zoom") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("school.image.zoom"), doc.get("school", Document.class).get("image", Document.class).get("zoom"));
                }
                if(doc.get("school",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("school.image.type"),doc.get("school",Document.class).get("image",Document.class).get("type"));
                }
            }
        }
        if(doc.get("venue") != null){
            if(doc.get("venue",Document.class).get("stadium") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.stadium").toString(),doc.get("venue",Document.class).get("stadium").toString());
            }
            if(doc.get("venue",Document.class).get("aka") != null){
                softAssert.assertEquals(resp.jsonPath().getList("venue.aka"),doc.get("venue",Document.class).get("aka"));
            }
            if(doc.get("venue",Document.class).get("street") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.street").toString(),doc.get("venue",Document.class).get("street").toString());
            }
            if(doc.get("venue",Document.class).get("city") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.city").toString(),doc.get("venue",Document.class).get("city").toString());
            }
            if(doc.get("venue",Document.class).get("state") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.state").toString(),doc.get("venue",Document.class).get("state").toString());
            }
            if(doc.get("venue",Document.class).get("postalCode") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.postalCode").toString(),doc.get("venue",Document.class).get("postalCode").toString());
            }
            if(doc.get("venue",Document.class).get("country") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.country").toString(),doc.get("venue",Document.class).get("country").toString());
            }
            if(doc.get("venue",Document.class).get("continent") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.continent").toString(),doc.get("venue",Document.class).get("continent").toString());
            }
        }
        if(doc.get("city") != null){
            softAssert.assertEquals(resp.jsonPath().get("city"),doc.get("city"));
        }
        if(doc.get("state") != null){
            softAssert.assertEquals(resp.jsonPath().get("state"),doc.get("state"));
        }
        if(doc.get("country") != null){
            softAssert.assertEquals(resp.jsonPath().get("country"),doc.get("country"));
        }
        softAssert.assertAll();
    }

    public static void leagueCompetitionValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(doc.get("competition") != null){
            for(int i = 0; i < ((List<Document>)doc.get("competition")).size(); i++){
                if(((List<Document>)doc.get("competition")).get(i).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("id").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("t_default") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].in").toString().toLowerCase(),((List<Document>)doc.get("competitions")).get(i).get("t_default").toString().toLowerCase());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("title") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].title").toString(),((List<Document>)doc.get("competitions")).get(i).get("title").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("competition") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.subType").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].type").toString(),((List<Document>)doc.get("competitions")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonStartTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonEndTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("start") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].start").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("start")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("venue") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.stadium").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka") != null){
                        softAssert.assertEquals(resp.jsonPath().getList("competitions[" + i + "].venue.aka"),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka"));
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.street").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.city").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.state").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.postalCode").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.country").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.continent").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("latLong") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.latLong").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("latLong").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("result") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("value") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].result.value").toString(),((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("value").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("resulttype") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].result.resulttype").toString(),((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("resulttype").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("resultsubtype") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].result.resultsubtype").toString(),((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("resultsubtype").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("season") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].result.season").toString(),((List<Document>)doc.get("competitions")).get(i).get("result",Document.class).get("season").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("team") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("id").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.name").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("name").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image") != null){
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.zoom").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school") != null){
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.school.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school",Document.class).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.school.name").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school",Document.class).get("name").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school",Document.class).get("image") != null) {
                            if (((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("id") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.school.image.ref.id").toString(), ((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("id").toString());
                            }
                            if (((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("zoom") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.school.image.zoom").toString(), ((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("zoom").toString());
                            }
                            if (((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("type") != null) {
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.school.image.type").toString(), ((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("school", Document.class).get("image", Document.class).get("type").toString());
                            }
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("major") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.major").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("major").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("minor") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.minor").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("minor").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster") != null){
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("season") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.season").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("season").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("role") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.role").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("role").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("isPlayer") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.isPlayer").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("isPlayer").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("number") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.number").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("number").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("status") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.status").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("status").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("remark") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.remark").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("remark").toString());
                        }
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("participant") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("id") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("id").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image") != null){
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("id") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("id").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("status") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].status").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("status").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("remark") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].remark").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("remark").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].season").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("season").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("isHome") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].isHome").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("isHome").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("groupNumber") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].groupNumber").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("groupNumber").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result") != null){
                                for(int k = 0; k < ((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).size(); k++){
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("value") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("value").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("resulttype") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("resulttype").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("resultsubtype") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("resultsubtype").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("season") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("result")).get(k).get("season").toString());
                                    }
                                }
                            }
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("id") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("id").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image") != null){
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("status") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].status").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("status").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("remark") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].remark").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("remark").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].season").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("season").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("isHome") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].isHome").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("isHome").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("groupNumber") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].groupNumber").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("groupNumber").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result") != null){
                                for(int k = 0; k < ((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).size(); k++){
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype").toString());
                                    }
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season").toString());
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

    public static void competitionValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(doc.get("t_default") != null){
            softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        }
        if(doc.get("title") != null){
            softAssert.assertEquals(resp.jsonPath().get("title").toString(),doc.get("title").toString());
        }
        if(doc.get("competition") != null){
            if( doc.get("competition",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("competition.type").toString(),doc.get("competition",Document.class).get("type").toString());
            }
            if( doc.get("competition",Document.class).get("subtype") != null){
                softAssert.assertEquals(resp.jsonPath().get("competition.subtype").toString(),doc.get("competition",Document.class).get("subtype").toString());
            }
        }
        if(doc.get("type") != null){
            softAssert.assertEquals(resp.jsonPath().get("type").toString(),doc.get("type").toString());
        }
        if(doc.get("affiliation") != null){
            softAssert.assertEquals(resp.jsonPath().get("affiliation").toString(),doc.get("affiliation").toString());
        }
        if(doc.get("d_genres") != null){
            softAssert.assertEquals(resp.jsonPath().getList("genres"),doc.get("d_genres"));
        }
        if(doc.get("gender") != null){
            softAssert.assertEquals(resp.jsonPath().get("gender").toString(),doc.get("gender").toString());
        }
        if(doc.get("seasonstarttime") != null){
            softAssert.assertEquals(resp.jsonPath().get("seasonstarttime").toString(),new DateTime(doc.get("seasonstarttime")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("seasonendtime") != null){
            softAssert.assertEquals(resp.jsonPath().get("seasonendtime").toString(),new DateTime(doc.get("seasonendtime")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("url") != null){
            if( doc.get("url",Document.class).get("reference") != null){
                softAssert.assertEquals(resp.jsonPath().get("url.reference").toString(),doc.get("url",Document.class).get("reference").toString());
            }
            if( doc.get("url",Document.class).get("schedule") != null){
                softAssert.assertEquals(resp.jsonPath().get("url.schedule").toString(),doc.get("url",Document.class).get("schedule").toString());
            }
            if( doc.get("url",Document.class).get("score") != null){
                softAssert.assertEquals(resp.jsonPath().get("url.score").toString(),doc.get("url",Document.class).get("score").toString());
            }
            if( doc.get("url",Document.class).get("standing") != null){
                softAssert.assertEquals(resp.jsonPath().get("url.standing").toString(),doc.get("url",Document.class).get("standing").toString());
            }
        }
        if(doc.get("status") != null){
            softAssert.assertEquals(resp.jsonPath().get("status").toString(),doc.get("status").toString());
        }
        if(doc.get("channels") != null){
            softAssert.assertEquals(resp.jsonPath().getList("channels"),doc.get("channels"));
        }
        if(doc.get("radio") != null){
            softAssert.assertEquals(resp.jsonPath().getList("radio"),doc.get("radio"));
        }
        if(doc.get("start") != null){
            softAssert.assertEquals(resp.jsonPath().get("start").toString(),new DateTime(doc.get("start")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("timezone") != null){
            softAssert.assertEquals(resp.jsonPath().get("timezone").toString(),doc.get("timezone").toString());
        }
        if(doc.get("venue") != null){
            if(doc.get("venue",Document.class).get("stadium") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.stadium").toString(),doc.get("venue",Document.class).get("stadium").toString());
            }
            if(doc.get("venue",Document.class).get("aka") != null){
                softAssert.assertEquals(resp.jsonPath().getList("venue.aka"),doc.get("venue",Document.class).get("aka"));
            }
            if(doc.get("venue",Document.class).get("street") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.street").toString(),doc.get("venue",Document.class).get("street").toString());
            }
            if(doc.get("venue",Document.class).get("city") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.city").toString(),doc.get("venue",Document.class).get("city").toString());
            }
            if(doc.get("venue",Document.class).get("state") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.state").toString(),doc.get("venue",Document.class).get("state").toString());
            }
            if(doc.get("venue",Document.class).get("postalCode") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.postalCode").toString(),doc.get("venue",Document.class).get("postalCode").toString());
            }
            if(doc.get("venue",Document.class).get("country") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.country").toString(),doc.get("venue",Document.class).get("country").toString());
            }
            if(doc.get("venue",Document.class).get("continent") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.continent").toString(),doc.get("venue",Document.class).get("continent").toString());
            }
            if(doc.get("venue",Document.class).get("latLong") != null){
                softAssert.assertEquals(resp.jsonPath().get("venue.latLong").toString(),doc.get("venue",Document.class).get("latLong").toString());
            }
        }
        if(doc.get("rivalry") != null){
            softAssert.assertEquals(resp.jsonPath().get("rivalry").toString(),doc.get("rivalry").toString());
        }
        if(doc.get("league") != null){
            if(doc.get("league",Document.class).get("id") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.ref.id").toString(),doc.get("league",Document.class).get("id").toString());
            }
            if(doc.get("league",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.type").toString(),doc.get("league",Document.class).get("type").toString());
            }
            if(doc.get("league",Document.class).get("name") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.name").toString(),doc.get("league",Document.class).get("name").toString());
            }
            if(doc.get("league",Document.class).get("major") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.major").toString(),doc.get("league",Document.class).get("major").toString());
            }
            if(doc.get("league",Document.class).get("minor") != null){
                softAssert.assertEquals(resp.jsonPath().get("league.minor").toString(),doc.get("league",Document.class).get("minor").toString());
            }
            if(doc.get("league",Document.class).get("subleague") != null){
                for(int i=0; i < ((List<Document>)doc.get("league",Document.class).get("subleague")).size(); i++){
                    if(((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("name") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].name").toString(), ((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("type") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].type").toString(), ((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("type").toString());
                    }
                    if(((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague") != null) {
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type") != null){
                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].type").toString(),((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team") != null){
                                for (int k =0; k < ((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).size(); k++){
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image") != null){
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.zoom").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.type").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type").toString());
                                        }
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school") != null){
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("image") != null) {
                                            if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id") != null) {
                                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id").toString());
                                            }
                                            if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom") != null) {
                                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.zoom").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom").toString());
                                            }
                                            if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type") != null) {
                                                softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.type").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type").toString());
                                            }
                                        }
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].major").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].minor").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor").toString());
                                    }
                                    if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster") != null){
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.season").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.role").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.isPlayer").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.number").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.status").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.remark").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark").toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(doc.get("league",Document.class).get("image") != null){
                if(doc.get("league",Document.class).get("image",Document.class).get("id") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("league.image.ref.id").toString(), doc.get("league",Document.class).get("image",Document.class).get("id").toString());
                }
                if(doc.get("league",Document.class).get("image",Document.class).get("zoom") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("league.image.zoom"), doc.get("league",Document.class).get("image",Document.class).get("zoom"));
                }
                if(doc.get("league",Document.class).get("image",Document.class).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("league.image.type"), doc.get("league",Document.class).get("image",Document.class).get("type"));
                }
            }
        }
        if(doc.get("group") != null){
            for(int i = 0 ; i < ((List<Document>)doc.get("group")).size(); i++){
                if(((List<Document>)doc.get("group")).get(i).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].ref.id").toString(),((List<Document>)doc.get("group")).get(i).get("id").toString());
                }
                if(((List<Document>)doc.get("group")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].type").toString(),((List<Document>)doc.get("group")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("group")).get(i).get("name") != null){
                    softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].name").toString(),((List<Document>)doc.get("group")).get(i).get("name").toString());
                }
                if(((List<Document>)doc.get("group")).get(i).get("image") != null){
                    if(((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("id").toString());
                    }
                    if(((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].image.zoom").toString(),((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("group[" + i + "].image.type").toString(),((List<Document>)doc.get("group")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
            }
        }
        if(doc.get("organizationconcept") != null){
            for(int i = 0 ; i < ((List<Document>)doc.get("organizationconcept")).size(); i++){
                if(((List<Document>)doc.get("organizationconcept")).get(i).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("organizationconcept[" + i + "].ref.id").toString(),((List<Document>)doc.get("organizationconcept")).get(i).get("id").toString());
                }
                if(((List<Document>)doc.get("organizationconcept")).get(i).get("name") != null){
                    softAssert.assertEquals(resp.jsonPath().get("organizationconcept[" + i + "].name").toString(),((List<Document>)doc.get("organizationconcept")).get(i).get("name").toString());
                }
                if(((List<Document>)doc.get("organizationconcept")).get(i).get("image") != null){
                    if(((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("organizationconcept[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("id").toString());
                    }
                    if(((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("organizationconcept[" + i + "].image.zoom").toString(),((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("organizationconcept[" + i + "].image.type").toString(),((List<Document>)doc.get("organizationconcept")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
            }
        }
        if(doc.get("final") != null){
            if(doc.get("final",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("final.type").toString(),doc.get("final",Document.class).get("type").toString());
            }
            if(doc.get("final",Document.class).get("iswinner") != null){
                softAssert.assertEquals(resp.jsonPath().get("final.iswinner").toString(),doc.get("final",Document.class).get("iswinner").toString());
            }
        }
        if(doc.get("participant") != null){
            if(doc.get("participant",Document.class).get("teams") != null){
                for(int i=0; i < ((List<Document>)doc.get("participant",Document.class).get("teams")).size(); i++){
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("id").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].name").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image") != null){
                        if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.zoom").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.type").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("status") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].status").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("status").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("remark") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].remark").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("remark").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("season") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].season").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("season").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("isHome") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].isHome").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("isHome").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("groupNumber") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].groupNumber").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("groupNumber").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result") != null){
                        for(int k = 0; k < ((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).size(); k++){
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("value") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("value").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resulttype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resulttype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resultsubtype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resultsubtype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("season").toString());
                            }
                        }
                    }
                }
            }
            if(doc.get("participant",Document.class).get("players") != null){
                for(int j=0; j < ((List<Document>)doc.get("participant",Document.class).get("players")).size(); j++){
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("id").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].name").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("name").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image") != null){
                        if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].image.ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].image.zoom").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].image.type").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("status") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].status").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("status").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("remark") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].remark").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("remark").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("season") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].season").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("season").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("isHome") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].isHome").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("isHome").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("groupNumber") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].groupNumber").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("groupNumber").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result") != null){
                        for(int k = 0; k < ((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).size(); k++){
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + j + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season").toString());
                            }
                        }
                    }
                }
            }
        }
        if(doc.get("image") != null){
            if(doc.get("image",Document.class).get("id") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), doc.get("image",Document.class).get("id").toString());
            }
            if(doc.get("image",Document.class).get("zoom") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.zoom"), doc.get("image",Document.class).get("zoom"));
            }
            if(doc.get("image",Document.class).get("type") != null) {
                softAssert.assertEquals(resp.jsonPath().get("image.type"), doc.get("image",Document.class).get("type"));
            }
        }
        if(doc.get("content") != null){
            if(doc.get("content",Document.class).get("id") != null) {
                softAssert.assertEquals(resp.jsonPath().get("content.ref.id").toString(), doc.get("content",Document.class).get("id").toString());
            }
            if(doc.get("content",Document.class).get("type") != null) {
                softAssert.assertEquals(resp.jsonPath().get("content.type").toString(), doc.get("content",Document.class).get("type").toString());
            }
            if(doc.get("content",Document.class).get("t_default") != null) {
                softAssert.assertEquals(resp.jsonPath().get("content.in").toString(), doc.get("content",Document.class).get("t_default").toString());
            }
            if(doc.get("content",Document.class).get("title") != null) {
                softAssert.assertEquals(resp.jsonPath().get("content.title").toString(), doc.get("content",Document.class).get("title").toString());
            }
            if(doc.get("content",Document.class).get("image") != null) {
                if(doc.get("content",Document.class).get("image",Document.class).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("content.image.ref.id").toString(), doc.get("content",Document.class).get("image",Document.class).get("id").toString());
                }
                if(doc.get("content",Document.class).get("image",Document.class).get("zoom") != null){
                    softAssert.assertEquals(resp.jsonPath().get("content.image.zoom").toString(), doc.get("content",Document.class).get("image",Document.class).get("zoom").toString());
                }
                if(doc.get("content",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("content.image.type").toString(), doc.get("content",Document.class).get("image",Document.class).get("type").toString());
                }
            }
            if(doc.get("content",Document.class).get("year") != null) {
                softAssert.assertEquals(resp.jsonPath().get("content.year").toString(), doc.get("content",Document.class).get("year").toString());
            }
        }
        if(doc.get("parent") != null){
            if(doc.get("parent",Document.class).get("id") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.ref.id").toString(),doc.get("parent",Document.class).get("id").toString());
            }
            if(doc.get("parent",Document.class).get("t_default") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.in").toString().toLowerCase(),doc.get("parent",Document.class).get("t_default").toString().toLowerCase());
            }
            if(doc.get("parent",Document.class).get("title") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.title").toString(),doc.get("parent",Document.class).get("title").toString());
            }
            if(doc.get("parent",Document.class).get("competition") != null){
                if( doc.get("parent",Document.class).get("competition",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.competition.type").toString(),doc.get("parent",Document.class).get("competition",Document.class).get("type").toString());
                }
                if( doc.get("parent",Document.class).get("competition",Document.class).get("subtype") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.competition.subtype").toString(),doc.get("parent",Document.class).get("competition",Document.class).get("subtype").toString());
                }
            }
            if(doc.get("parent",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.type").toString(),doc.get("parent",Document.class).get("type").toString());
            }
            if(doc.get("parent",Document.class).get("affiliation") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.affiliation").toString(),doc.get("parent",Document.class).get("affiliation").toString());
            }
            if(doc.get("parent",Document.class).get("d_genres") != null){
                softAssert.assertEquals(resp.jsonPath().getList("parent.genres"),doc.get("parent",Document.class).get("d_genres"));
            }
            if(doc.get("parent",Document.class).get("gender") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.gender").toString(),doc.get("parent",Document.class).get("gender").toString());
            }
            if(doc.get("parent",Document.class).get("seasonstarttime") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.seasonstarttime").toString(),new DateTime(doc.get("parent",Document.class).get("seasonstarttime")).toDateTime(DateTimeZone.UTC).toString());
            }
            if(doc.get("parent",Document.class).get("seasonendtime") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.seasonendtime").toString(),new DateTime(doc.get("parent",Document.class).get("seasonendtime")).toDateTime(DateTimeZone.UTC).toString());
            }
            if(doc.get("parent",Document.class).get("url") != null){
                if( doc.get("parent",Document.class).get("url",Document.class).get("reference") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.url.reference").toString(),doc.get("parent",Document.class).get("url",Document.class).get("reference").toString());
                }
                if( doc.get("parent",Document.class).get("url",Document.class).get("schedule") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.url.schedule").toString(),doc.get("parent",Document.class).get("url",Document.class).get("schedule").toString());
                }
                if( doc.get("parent",Document.class).get("url",Document.class).get("score") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.url.score").toString(),doc.get("parent",Document.class).get("url",Document.class).get("score").toString());
                }
                if( doc.get("parent",Document.class).get("url",Document.class).get("standing") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.url.standing").toString(),doc.get("parent",Document.class).get("url",Document.class).get("standing").toString());
                }
            }
            if(doc.get("parent",Document.class).get("status") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.status").toString(),doc.get("parent",Document.class).get("status").toString());
            }
            if(doc.get("parent",Document.class).get("channels") != null){
                softAssert.assertEquals(resp.jsonPath().getList("parent.channels"),doc.get("parent",Document.class).get("channels"));
            }
            if(doc.get("parent",Document.class).get("radio") != null){
                softAssert.assertEquals(resp.jsonPath().getList("parent.radio"),doc.get("parent",Document.class).get("radio"));
            }
            if(doc.get("parent",Document.class).get("start") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.start").toString(),new DateTime(doc.get("parent",Document.class).get("start")).toDateTime(DateTimeZone.UTC).toString());
            }
            if(doc.get("parent",Document.class).get("timezone") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.timezone").toString(),doc.get("parent",Document.class).get("timezone").toString());
            }
            if(doc.get("parent",Document.class).get("venue") != null){
                if(doc.get("parent",Document.class).get("venue",Document.class).get("stadium") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.stadium").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("stadium").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("aka") != null){
                    softAssert.assertEquals(resp.jsonPath().getList("parent.venue.aka"),doc.get("parent",Document.class).get("venue",Document.class).get("aka"));
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("street") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.street").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("street").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("city") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.city").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("city").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("state") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.state").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("state").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("postalCode") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.postalCode").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("postalCode").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("country") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.country").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("country").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("continent") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.continent").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("continent").toString());
                }
                if(doc.get("parent",Document.class).get("venue",Document.class).get("latLong") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.venue.latLong").toString(),doc.get("parent",Document.class).get("venue",Document.class).get("latLong").toString());
                }
            }
            if(doc.get("parent",Document.class).get("rivalry") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.rivalry").toString(),doc.get("parent",Document.class).get("rivalry").toString());
            }
            if(doc.get("parent",Document.class).get("league") != null){
                if(doc.get("parent",Document.class).get("league",Document.class).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.league.ref.id").toString(),doc.get("parent",Document.class).get("league",Document.class).get("id").toString());
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.league.type").toString(),doc.get("parent",Document.class).get("league",Document.class).get("type").toString());
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("name") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.league.name").toString(),doc.get("parent",Document.class).get("league",Document.class).get("name").toString());
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("major") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.league.major").toString(),doc.get("parent",Document.class).get("league",Document.class).get("major").toString());
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("minor") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.league.minor").toString(),doc.get("parent",Document.class).get("league",Document.class).get("minor").toString());
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("subleague") != null){
                    for(int i=0; i < ((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).size(); i++){
                        if(((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("name") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].name").toString(), ((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("name").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("type") != null) {
                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].type").toString(), ((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("type").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague") != null) {
                            for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).size(); j++){
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("name").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].type").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("type").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team") != null){
                                    for (int k =0; k < ((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).size(); k++){
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("id").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("name").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image") != null){
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("id").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.zoom").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("zoom").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].image.type").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("image",Document.class).get("type").toString());
                                            }
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school") != null){
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.ref.id").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("id").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.name").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("name").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school",Document.class).get("image") != null) {
                                                if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("parent",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id") != null) {
                                                    softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("parent",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("id").toString());
                                                }
                                                if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom") != null) {
                                                    softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.zoom").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("parent",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("zoom").toString());
                                                }
                                                if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type") != null) {
                                                    softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].school.image.type").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("parent",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("school", Document.class).get("image", Document.class).get("type").toString());
                                                }
                                            }
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].major").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("major").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].minor").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("minor").toString());
                                        }
                                        if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster") != null){
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.season").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("season").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.role").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("role").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.isPlayer").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("isPlayer").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.number").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("number").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.status").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("status").toString());
                                            }
                                            if(((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("parent.league.subleague[" + i + "].subleague[" + j + "].team[" + k + "].roster.remark").toString(),((List< Document>)((List<Document>)((List<Document>)doc.get("parent",Document.class).get("league",Document.class).get("subleague")).get(i).get("subleague")).get(j).get("team")).get(k).get("roster",Document.class).get("remark").toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(doc.get("parent",Document.class).get("league",Document.class).get("image") != null){
                    if(doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("id") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("parent.league.image.ref.id").toString(), doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("id").toString());
                    }
                    if(doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("zoom") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("parent.league.image.zoom"), doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("zoom"));
                    }
                    if(doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("type") != null) {
                        softAssert.assertEquals(resp.jsonPath().get("parent.league.image.type"), doc.get("parent",Document.class).get("league",Document.class).get("image",Document.class).get("type"));
                    }
                }
            }
            if(doc.get("parent",Document.class).get("group") != null){
                for(int i = 0 ; i < ((List<Document>)doc.get("parent",Document.class).get("group")).size(); i++){
                    if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("id").toString());
                    }
                    if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].type").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("type").toString());
                    }
                    if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].name").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image") != null){
                        if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].image.zoom").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.group[" + i + "].image.type").toString(),((List<Document>)doc.get("parent",Document.class).get("group")).get(i).get("image",Document.class).get("type").toString());
                        }
                    }
                }
            }
            if(doc.get("parent",Document.class).get("organizationconcept") != null){
                for(int i = 0 ; i < ((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).size(); i++){
                    if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.organizationconcept[" + i + "].ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("id").toString());
                    }
                    if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.organizationconcept[" + i + "].name").toString(),((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image") != null){
                        if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.organizationconcept[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("id").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.organizationconcept[" + i + "].image.zoom").toString(),((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.organizationconcept[" + i + "].image.type").toString(),((List<Document>)doc.get("parent",Document.class).get("organizationconcept")).get(i).get("image",Document.class).get("type").toString());
                        }
                    }
                }
            }
            if(doc.get("parent",Document.class).get("final") != null){
                if(doc.get("parent",Document.class).get("final",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.final.type").toString(),doc.get("parent",Document.class).get("final",Document.class).get("type").toString());
                }
                if(doc.get("parent",Document.class).get("final",Document.class).get("iswinner") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.final.iswinner").toString(),doc.get("parent",Document.class).get("final",Document.class).get("iswinner").toString());
                }
            }
            if(doc.get("parent",Document.class).get("participant") != null){
                if(doc.get("parent",Document.class).get("participant",Document.class).get("teams") != null){
                    for(int i=0; i < ((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).size(); i++){
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("id").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].name").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("name").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image") != null){
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("id") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("id").toString());
                            }
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].image.zoom").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom").toString());
                            }
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].image.type").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type").toString());
                            }
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("status") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].status").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("status").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("remark") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].remark").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("remark").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("season") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].season").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("season").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("isHome") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].isHome").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("isHome").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("groupNumber") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].groupNumber").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("groupNumber").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result") != null){
                            for(int k = 0; k < ((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).size(); k++){
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("value") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("value").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resulttype") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resulttype").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resultsubtype") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("resultsubtype").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("season") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.teams[" + i + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("teams")).get(i).get("result")).get(k).get("season").toString());
                                }
                            }
                        }
                    }
                }
                if(doc.get("parent",Document.class).get("participant",Document.class).get("players") != null){
                    for(int j=0; j < ((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).size(); j++){
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("id") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("id").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].name").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("name").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image") != null){
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].image.ref.id").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                            }
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].image.zoom").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                            }
                            if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                                softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].image.type").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                            }
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("status") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].status").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("status").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("remark") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].remark").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("remark").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("season") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].season").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("season").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("isHome") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].isHome").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("isHome").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("groupNumber") != null){
                            softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].groupNumber").toString(),((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("groupNumber").toString());
                        }
                        if(((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result") != null){
                            for(int k = 0; k < ((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).size(); k++){
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].result[" + k + "].value").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("value").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].result[" + k + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resulttype").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].result[" + k + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("resultsubtype").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("parent.participant.players[" + j + "].result[" + k + "].season").toString(),((List<Document>)((List<Document>)doc.get("parent",Document.class).get("participant",Document.class).get("players")).get(j).get("result")).get(k).get("season").toString());
                                }
                            }
                        }
                    }
                }
            }
            if(doc.get("parent",Document.class).get("image") != null){
                if(doc.get("parent",Document.class).get("image",Document.class).get("id") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.image.ref.id").toString(), doc.get("parent",Document.class).get("image",Document.class).get("id").toString());
                }
                if(doc.get("parent",Document.class).get("image",Document.class).get("zoom") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.image.zoom"), doc.get("parent",Document.class).get("image",Document.class).get("zoom"));
                }
                if(doc.get("parent",Document.class).get("image",Document.class).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.image.type"), doc.get("parent",Document.class).get("image",Document.class).get("type"));
                }
            }
            if(doc.get("parent",Document.class).get("content") != null){
                if(doc.get("parent",Document.class).get("content",Document.class).get("id") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.content.ref.id").toString(), doc.get("parent",Document.class).get("content",Document.class).get("id").toString());
                }
                if(doc.get("parent",Document.class).get("content",Document.class).get("type") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.content.type").toString(), doc.get("parent",Document.class).get("content",Document.class).get("type").toString());
                }
                if(doc.get("parent",Document.class).get("content",Document.class).get("t_default") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.content.in").toString(), doc.get("parent",Document.class).get("content",Document.class).get("t_default").toString());
                }
                if(doc.get("parent",Document.class).get("content",Document.class).get("title") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.content.title").toString(), doc.get("parent",Document.class).get("content",Document.class).get("title").toString());
                }
                if(doc.get("parent",Document.class).get("content",Document.class).get("image") != null) {
                    if(doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("id") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.content.image.ref.id").toString(), doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("id").toString());
                    }
                    if(doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.content.image.zoom").toString(), doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("zoom").toString());
                    }
                    if(doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("parent.content.image.type").toString(), doc.get("parent",Document.class).get("content",Document.class).get("image",Document.class).get("type").toString());
                    }
                }
                if(doc.get("parent",Document.class).get("content",Document.class).get("year") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("parent.content.year").toString(), doc.get("parent",Document.class).get("content",Document.class).get("year").toString());
                }
            }
        }
        if(doc.get("result") != null){
            for(int i = 0; i < ((List<Document>)doc.get("result")).size(); i++){
                if(((List<Document>)doc.get("result")).get(i).get("value") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].value").toString(),((List<Document>)doc.get("result")).get(i).get("value").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("resulttype") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].resulttype").toString(),((List<Document>)doc.get("result")).get(i).get("resulttype").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("resultsubtype") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].resultsubtype").toString(),((List<Document>)doc.get("result")).get(i).get("resultsubtype").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("season") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].season").toString(),((List<Document>)doc.get("result")).get(i).get("season").toString());
                }
            }
        }
    }

    /*public static void competitionChildrenValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(doc.get("children") != null){
            for(int i = 0; i < ((List<Document>)doc.get("children")).size(); i++ ){
                if(((List<Document>)doc.get("children")).get(i).get("id") != null){
                    softAssert.assertEquals(resp.jsonPath().get("children[" + i + "].ref.id").toString(),((List<Document>)doc.get("children")).get(i).get("id").toString());
                }

            }
        }
    }*/

    public static void organizationConceptValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("type") != null){
            softAssert.assertEquals(resp.jsonPath().get("type").toString(),doc.get("type").toString());
        }
        if(doc.get("t_in") != null){
            for(int i  = 0; i < ((List<Document>)doc.get("t_in")).size();i++){
                if(((List<Document>)doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())){
                    softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),((List<Document>)doc.get("t_in")).get(i).get("t_locale").toString().toLowerCase());
                    if(((List<Document>)doc.get("t_in")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("name").toString(),((List<Document>)doc.get("t_in")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("t_in")).get(i).get("alts") != null){
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("nickname") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.nickname").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("nickname").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("representing") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.representing").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("representing").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("display") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.display").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("display").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short20") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.short20").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short20").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short4") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alts.short4").toString(),((List<Document>)doc.get("t_in")).get(i).get("alts",Document.class).get("short4").toString());
                        }
                    }
                    if(((List<Document>)doc.get("t_in")).get(i).get("alias") != null){
                        if(((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("name") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alias.name").toString(),((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("name").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("first") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alias.first").toString(),((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("first").toString());
                        }
                        if(((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("last") != null){
                            softAssert.assertEquals(resp.jsonPath().get("alias.last").toString(),((List<Document>)doc.get("t_in")).get(i).get("alias",Document.class).get("last").toString());
                        }
                    }
                }
            }
        }
        if(doc.get("gender") != null){
            softAssert.assertEquals(resp.jsonPath().get("gender").toString(),doc.get("gender").toString());
        }
        if(doc.get("description") != null){
            softAssert.assertEquals(resp.jsonPath().get("description").toString(),doc.get("description").toString());
        }
        if(doc.get("image") != null){
            softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(),doc.get("image",Document.class).get("id").toString());
            if(doc.get("image",Document.class).get("zoom") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.zoom").toString(),doc.get("image",Document.class).get("zoom").toString());
            }
            if(doc.get("image",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.type").toString(),doc.get("image",Document.class).get("type").toString());
            }
        }
    }

    public static void organizationConceptCompetitionValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("competitions") != null){
            for(int i = 0 ; i < ((List<Document>)doc.get("competitions")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("id").toString());
                if(((List<Document>)doc.get("competitions")).get(i).get("t_default") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].in").toString().toLowerCase(),((List<Document>)doc.get("competitions")).get(i).get("t_default").toString().toLowerCase());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("title") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].title").toString(),((List<Document>)doc.get("competitions")).get(i).get("title").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("competition") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].type").toString(),((List<Document>)doc.get("competitions")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonStartTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonEndTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("start") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].start").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("start")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("venue") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.stadium").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka") != null){
                        softAssert.assertEquals(resp.jsonPath().getList("competitions[" + i + "].venue.aka"),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka"));
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.street").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.city").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.state").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.postalCode").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.country").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.continent").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("image") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.zoom").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("participant") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in") != null){
                                for(int k = 0; k < ((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).size(); k++){
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].in").toString())){
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("name") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].name").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("name").toString());
                                        }
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts") != null){
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("nickname") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alts.nickname").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("nickname").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("representing") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alts.representing").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("representing").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("display") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alts.display").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("display").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("short20") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alts.short20").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("short20").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("short4") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alts.short4").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alts",Document.class).get("short4").toString());
                                            }
                                        }
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias") != null){
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("name") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alias.name").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("name").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("first") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alias.first").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("first").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("last") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].alias.last").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("t_in")).get(k).get("alias",Document.class).get("last").toString());
                                            }
                                        }
                                    }
                                }
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in") != null){
                                for(int k = 0; k < ((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).size(); k++){
                                    if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].in").toString())){
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("name") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].name").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("name").toString());
                                        }
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts") != null){
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("first") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alts.first").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("first").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("last") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alts.last").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("last").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("birth") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alts.birth").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alts",Document.class).get("birth").toString());
                                            }
                                        }
                                        if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias") != null){
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("name") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alias.name").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("name").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("first") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alias.first").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("first").toString());
                                            }
                                            if(((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("last") != null){
                                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].alias.last").toString(),((List<Document>)((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("t_in")).get(k).get("alias",Document.class).get("last").toString());
                                            }
                                        }
                                    }
                                }
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void groupValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("name") != null){
            softAssert.assertEquals(resp.jsonPath().get("name").toString(), doc.get("name").toString());
        }
        if(doc.get("type") != null){
            softAssert.assertEquals(resp.jsonPath().get("type").toString(), doc.get("type").toString());
        }
        if(doc.get("info") != null){
            softAssert.assertEquals(resp.jsonPath().get("info").toString(), doc.get("info").toString());
        }
        if(doc.get("image") != null){
            softAssert.assertEquals(resp.jsonPath().get("image.ref.id").toString(), doc.get("image",Document.class).get("id").toString());
            if(doc.get("image",Document.class).get("zoom") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.zoom").toString(),doc.get("image",Document.class).get("zoom").toString());
            }
            if(doc.get("image",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("image.type").toString(),doc.get("image",Document.class).get("type").toString());
            }
        }
        if(doc.get("parent") != null){
            softAssert.assertEquals(resp.jsonPath().get("parent.ref.id").toString(),doc.get("parent",Document.class).get("id").toString());
            if(doc.get("parent",Document.class).get("type") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.type").toString(),doc.get("parent",Document.class).get("type").toString());
            }
            if(doc.get("parent",Document.class).get("name") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.name").toString(),doc.get("parent",Document.class).get("name").toString());
            }
            if(doc.get("parent",Document.class).get("image") != null){
                softAssert.assertEquals(resp.jsonPath().get("parent.image.ref.id").toString(),doc.get("parent",Document.class).get("image",Document.class).get("id").toString());
                if(doc.get("parent",Document.class).get("image",Document.class).get("zoom") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.image.zoom").toString(),doc.get("parent",Document.class).get("image",Document.class).get("zoom").toString());
                }
                if(doc.get("parent",Document.class).get("image",Document.class).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("parent.image.type").toString(),doc.get("parent",Document.class).get("image",Document.class).get("type").toString());
                }
            }
        }
        if(doc.get("child") != null){
            for(int i = 0; i < ((List<Document>)doc.get("child")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].ref.id").toString(),((List<Document>)doc.get("child")).get(i).get("id").toString());
                if(((List<Document>)doc.get("child")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].type").toString(),((List<Document>)doc.get("child")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("child")).get(i).get("name") != null){
                    softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].name").toString(),((List<Document>)doc.get("child")).get(i).get("name").toString());
                }
                if(((List<Document>)doc.get("child")).get(i).get("image") != null){
                    softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("child")).get(i).get("image",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("child")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].image.zoom").toString(),((List<Document>)doc.get("child")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("child")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("child[" + i + "].image.type").toString(),((List<Document>)doc.get("child")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
            }
        }
        if(doc.get("competition") != null){
            for(int i = 0; i < ((List<Document>)doc.get("competition")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].ref.id").toString(),((List<Document>)doc.get("competition")).get(i).get("id").toString());
                if(((List<Document>)doc.get("competition")).get(i).get("t_default") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].in").toString().toLowerCase(),((List<Document>)doc.get("competition")).get(i).get("t_default").toString().toLowerCase());
                }
                if(((List<Document>)doc.get("competition")).get(i).get("title") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].title").toString(),((List<Document>)doc.get("competition")).get(i).get("title").toString());
                }
                if(((List<Document>)doc.get("competition")).get(i).get("competition") != null){
                    if(((List<Document>)doc.get("competition")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].competition.type").toString(),((List<Document>)doc.get("competition")).get(i).get("competition",Document.class).get("type").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("competition",Document.class).get("subtype") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].competition.subtype").toString(),((List<Document>)doc.get("competition")).get(i).get("competition",Document.class).get("subtype").toString());
                    }
                }
                if(((List<Document>)doc.get("competition")).get(i).get("seasonstarttime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].seasonstarttime").toString(),new DateTime(((List<Document>)doc.get("competition")).get(i).get("seasonstarttime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competition")).get(i).get("seasonendtime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].seasonendtime").toString(),new DateTime(((List<Document>)doc.get("competition")).get(i).get("seasonendtime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competition")).get(i).get("start") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].start").toString(),new DateTime(((List<Document>)doc.get("competition")).get(i).get("start")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competition")).get(i).get("venue") != null){
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("stadium") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.stadium").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("stadium").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("aka") != null){
                        softAssert.assertEquals(resp.jsonPath().getList("competition[" + i + "].venue.aka"),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("aka"));
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("street") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.street").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("street").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("city") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.city").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("city").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("state") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.state").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("state").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("postalCode") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.postalCode").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("postalCode").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("country") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.country").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("country").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("continent") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].venue.continent").toString(),((List<Document>)doc.get("competition")).get(i).get("venue",Document.class).get("continent").toString());
                    }
                }
                if(((List<Document>)doc.get("competition")).get(i).get("image") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("competition")).get(i).get("image",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("competition")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].image.zoom").toString(),((List<Document>)doc.get("competition")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].image.type").toString(),((List<Document>)doc.get("competition")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
                if(((List<Document>)doc.get("competition")).get(i).get("participant") != null){
                    if(((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.teams[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.teams[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.teams[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.teams[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.teams[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                    if(((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.players[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.players[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.players[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.players[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competition[" + i + "].participant.players[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competition")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                }

            }
        }
        if(doc.get("participant") != null){
            if(doc.get("participant",Document.class).get("teams") != null){
                for(int i = 0; i < ((List<Document>)doc.get("participant",Document.class).get("teams")).size(); i++){
                    softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("id").toString());
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].name").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("id").toString());
                        if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.zoom").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].image.type").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("season") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].season").toString(),((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("season").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("value") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + j + "].value").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("value").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("resulttype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + j + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("resulttype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("resultsubtype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + j + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("resultsubtype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.teams[" + i + "].result[" + j + "].season").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("teams")).get(i).get("result")).get(j).get("season").toString());
                            }
                        }
                    }
                }
            }
            if(doc.get("participant",Document.class).get("players") != null){
                for(int i = 0; i < ((List<Document>)doc.get("participant",Document.class).get("players")).size(); i++){
                    softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("id").toString());
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].name").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("name").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image",Document.class).get("id").toString());
                        if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].image.zoom").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].image.type").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("season") != null){
                        softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].season").toString(),((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("season").toString());
                    }
                    if(((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result") != null){
                        for(int j = 0; j < ((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).size(); j++){
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("value") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].result[" + j + "].value").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("value").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("resulttype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].result[" + j + "].resulttype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("resulttype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("resultsubtype") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].result[" + j + "].resultsubtype").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("resultsubtype").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("season") != null){
                                softAssert.assertEquals(resp.jsonPath().get("participant.players[" + i + "].result[" + j + "].season").toString(),((List<Document>)((List<Document>)doc.get("participant",Document.class).get("players")).get(i).get("result")).get(j).get("season").toString());
                            }
                        }
                    }
                }
            }
        }
        if(doc.get("result") != null){
            for(int i = 0; i < ((List<Document>)doc.get("result")).size(); i++){
                if(((List<Document>)doc.get("result")).get(i).get("value") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].value").toString(),((List<Document>)doc.get("result")).get(i).get("value").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("resulttype") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].resulttype").toString(),((List<Document>)doc.get("result")).get(i).get("resulttype").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("resultsubtype") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].resultsubtype").toString(),((List<Document>)doc.get("result")).get(i).get("resultsubtype").toString());
                }
                if(((List<Document>)doc.get("result")).get(i).get("season") != null){
                    softAssert.assertEquals(resp.jsonPath().get("result[" + i + "].season").toString(),((List<Document>)doc.get("result")).get(i).get("season").toString());
                }
            }
        }
    }

    public static void personCompetitionValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("t_default") != null){
            softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        }
        if(doc.get("competitions") != null){
            for(int i = 0; i < ((List<Document>)doc.get("competitions")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("id").toString());
                if(((List<Document>)doc.get("competitions")).get(i).get("t_default") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].in").toString().toLowerCase(),((List<Document>)doc.get("competitions")).get(i).get("t_default").toString().toLowerCase());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("title") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].title").toString(),((List<Document>)doc.get("competitions")).get(i).get("title").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("competition") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.subType").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].type").toString(),((List<Document>)doc.get("competitions")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonStartTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonEndTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("start") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].start").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("start")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("venue") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.stadium").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka") != null){
                        softAssert.assertEquals(resp.jsonPath().getList("competitions[" + i + "].venue.aka"),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka"));
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.street").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.city").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.state").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.postalCode").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.country").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.continent").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("team") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.name").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("name").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("id").toString());
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.zoom").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.image.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("image",Document.class).get("type").toString());
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster") != null){
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("season") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.season").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("season").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("role") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.role").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("role").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("isPlayer") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.isPlayer").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("isPlayer").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("number") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.number").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("number").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("status") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.status").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("status").toString());
                        }
                        if(((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("remark") != null){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].team.roster.remark").toString(),((List<Document>)doc.get("competitions")).get(i).get("team",Document.class).get("roster",Document.class).get("remark").toString());
                        }
                    }
                }
            }
        }
    }

    public static void teamCompetitionValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("id").toString());
        if(doc.get("t_default") != null){
            softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        }
        if(doc.get("competitions") != null){
            for(int i = 0; i < ((List<Document>)doc.get("competitions")).size(); i++){
                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("id").toString());
                if(((List<Document>)doc.get("competitions")).get(i).get("t_default") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].in").toString().toLowerCase(),((List<Document>)doc.get("competitions")).get(i).get("t_default").toString().toLowerCase());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("title") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].title").toString(),((List<Document>)doc.get("competitions")).get(i).get("title").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("competition") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("type").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].competition.subType").toString(),((List<Document>)doc.get("competitions")).get(i).get("competition",Document.class).get("subType").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("type") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].type").toString(),((List<Document>)doc.get("competitions")).get(i).get("type").toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonStartTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonStartTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].seasonEndTime").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("seasonEndTime")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("start") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].start").toString(),new DateTime(((List<Document>)doc.get("competitions")).get(i).get("start")).toDateTime(DateTimeZone.UTC).toString());
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("venue") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.stadium").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("stadium").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka") != null){
                        softAssert.assertEquals(resp.jsonPath().getList("competitions[" + i + "].venue.aka"),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("aka"));
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.street").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("street").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.city").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("city").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.state").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("state").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.postalCode").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("postalCode").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.country").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("country").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].venue.continent").toString(),((List<Document>)doc.get("competitions")).get(i).get("venue",Document.class).get("continent").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("image") != null){
                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.ref.id").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("zoom") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.zoom").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("zoom").toString());
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("type") != null){
                        softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].image.type").toString(),((List<Document>)doc.get("competitions")).get(i).get("image",Document.class).get("type").toString());
                    }
                }
                if(((List<Document>)doc.get("competitions")).get(i).get("participant") != null){
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.teams[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("teams")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                    if(((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).size(); j++){
                            softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("id").toString());
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("name") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].name").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("name").toString());
                            }
                            if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image") != null){
                                softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.ref.id").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("id").toString());
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.zoom").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("zoom").toString());
                                }
                                if(((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type") != null){
                                    softAssert.assertEquals(resp.jsonPath().get("competitions[" + i + "].participant.players[" + j + "].image.type").toString(),((List<Document>)((List<Document>)doc.get("competitions")).get(i).get("participant",Document.class).get("players")).get(j).get("image",Document.class).get("type").toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void teamRosterValidator(Response resp, Document doc) {
        int size = 0;
        if(((List<Document>) doc.get("roster")).size() > 0 && ((List<Document>) doc.get("roster")).size() <11){
            size = ((List<Document>) doc.get("roster")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("id").toString());
        if(doc.get("roster") != null){
            for(int i = 0; i < size; i++){
                if(((List<Document>)doc.get("roster")).get(i).get("season") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].season").toString(),((List<Document>)doc.get("roster")).get(i).get("season").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("role") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].role").toString(),((List<Document>)doc.get("roster")).get(i).get("role").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("isPlayer") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].isPlayer").toString(),((List<Document>)doc.get("roster")).get(i).get("isPlayer").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("number") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].number").toString(),((List<Document>)doc.get("roster")).get(i).get("number").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("status") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].status").toString(),((List<Document>)doc.get("roster")).get(i).get("status").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("remark") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].remark").toString(),((List<Document>)doc.get("roster")).get(i).get("remark").toString());
                }
                if(((List<Document>)doc.get("roster")).get(i).get("person") != null){
                    softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].person.ref.id").toString(),((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("id").toString());
                    if(((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("name") != null){
                        softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].person.name").toString(),((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("name").toString());
                    }
                    if(((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image") != null){
                        softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].person.image.ref.id").toString(),((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image",Document.class).get("id").toString());
                        if(((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image",Document.class).get("zoom") != null){
                            softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].person.image.zoom").toString(),((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image",Document.class).get("zoom").toString());
                        }
                        if(((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image",Document.class).get("type") != null){
                            softAssert.assertEquals(resp.jsonPath().get("roster[" + i + "].person.image.type").toString(),((List<Document>)doc.get("roster")).get(i).get("person",Document.class).get("image",Document.class).get("type").toString());
                        }
                    }
                }
            }
        }
    }
}
