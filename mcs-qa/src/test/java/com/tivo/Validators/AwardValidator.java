package com.tivo.Validators;

import io.restassured.response.Response;
import org.bson.Document;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by rjaiswal on 5/18/2017.
 */
public class AwardValidator {

    public static void awardCommonValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("awards")).size() > 0 && ((List<Document>) doc.get("awards")).size() < 11) {
            size = ((List<Document>) doc.get("awards")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("awards")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].ref.id").toString(), ((List<Document>) doc.get("awards")).get(i).get("id").toString());
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].association.name"), ((List<Document>) doc.get("awards")).get(i).get("association", Document.class).get("name"));
                if(((List<Document>) doc.get("awards")).get(i).get("t_in") != null) {
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("awards["+ i +"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].in").toString().toLowerCase(),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("t_locale").toString().toLowerCase());
                            softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].category"),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("category"));
                        }
                    }
                }
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].year").toString(), ((List<Document>) doc.get("awards")).get(i).get("year").toString());
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].status").toString(), ((List<Document>) doc.get("awards")).get(i).get("status").toString());
                if(((List<Document>) doc.get("awards")).get(i).get("recipients") != null) {
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("awards")).get(i).get("recipients")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("t_default").toString().equalsIgnoreCase(resp.jsonPath().get("awards["+ i +"].recipients["+ j +"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].recipients["+ j +"].in").toString().toLowerCase(),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("t_default").toString().toLowerCase());
                            if(((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person") != null) {
                                if (((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("id") != null) {
                                    softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("id").toString());
                                    softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.name"), ((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("name"));
                                    if(((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.alts.first"), ((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts",Document.class).get("first"));
                                        softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.alts.last"), ((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts",Document.class).get("last"));
                                    }
                                    if(((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("image") != null){
                                        if(((List<Document>)((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.image.ref.id").toString(),((List<Document>)((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id").toString());
                                            softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.image.zoom"),((List<Document>)((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("zoom"));
                                            softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].recipients[" + j + "].person.image.type"),((List<Document>)((List<Document>) ((List<Document>) doc.get("awards")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("type"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void awardPersonValidator(Response resp, Document doc) {
        int size = 0;
        if (((List<Document>) doc.get("awards")).size() > 0 && ((List<Document>) doc.get("awards")).size() < 11) {
            size = ((List<Document>) doc.get("awards")).size();
        } else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("awards")).size() > 0) {
            for (int i = 0; i < size; i++) {
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].ref.id").toString(), ((List<Document>) doc.get("awards")).get(i).get("id").toString());
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].association.name"), ((List<Document>) doc.get("awards")).get(i).get("association", Document.class).get("name"));
                if(((List<Document>) doc.get("awards")).get(i).get("t_in") != null) {
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("awards["+ i +"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].in").toString().toLowerCase(),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("t_locale").toString().toLowerCase());
                            softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].category"),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("t_in")).get(j).get("category"));
                        }
                    }
                }
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].year").toString(), ((List<Document>) doc.get("awards")).get(i).get("year").toString());
                softAssert.assertEquals(resp.jsonPath().get("awards[" + i + "].status").toString(), ((List<Document>) doc.get("awards")).get(i).get("status").toString());
                if(((List<Document>) doc.get("awards")).get(i).get("content") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].ref.id").toString(),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("id").toString());
                    softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].type"),((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("type"));
                    if(((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in") != null){
                        for(int j=0; j < ((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).size(); j++) {
                            if(((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("awards["+ i +"].content[0].in").toString())){
                                softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].in").toString().toLowerCase(),((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("t_locale").toString().toLowerCase());
                                softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].title"),((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("title"));
                                if(((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("image") != null){
                                    if(((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("image", Document.class).get("id") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].image.ref.id").toString(),((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("image",Document.class).get("id").toString());
                                        softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].image.zoom"),((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("image",Document.class).get("zoom"));
                                        softAssert.assertEquals(resp.jsonPath().get("awards["+ i +"].content[0].image.type"),((List<Document>)((List<Document>)((List<Document>) doc.get("awards")).get(i).get("content")).get(0).get("t_in")).get(j).get("image",Document.class).get("type"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void awardValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("t_in")).size() > 0) {
            for (int i = 0; i < ((List<Document>) doc.get("t_in")).size(); i++) {
                if (((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("in").toString())) {
                    softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(), ((List<Document>) doc.get("t_in")).get(i).get("t_locale").toString().toLowerCase());
                    softAssert.assertEquals(resp.jsonPath().get("category"), ((List<Document>) doc.get("t_in")).get(i).get("category"));
                }
            }
        }
        if (doc.get("association") != null) {
            softAssert.assertEquals(resp.jsonPath().get("association.name"), doc.get("association", Document.class).get("name"));
        }
        softAssert.assertEquals(resp.jsonPath().get("year").toString(), doc.get("year").toString());
        softAssert.assertEquals(resp.jsonPath().getList("mediums"), doc.get("mediums"));
        if (doc.get("winners") != null) {
            for (int i = 0; i < ((List<Document>) doc.get("winners")).size(); i++) {
                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].in").toString().toLowerCase(), ((List<Document>) doc.get("winners")).get(i).get("t_default").toString().toLowerCase());
                if(((List<Document>) doc.get("winners")).get(i).get("content") != null) {
                    if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).size() > 0) {
                        for (int j = 0; j < ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).size(); j++) {
                            softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("id").toString());
                            softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].type").toString(), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("type"));
                            if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in") != null) {
                                for (int k = 0; k < ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).size(); k++) {
                                    if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("winners[" + i + "].content[" + j + "].in").toString())) {
                                        softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].in").toString().toLowerCase(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("t_locale").toString().toLowerCase());
                                        softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].title"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("title"));
                                        if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("image") != null) {
                                            if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("id") != null) {
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("id").toString());
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].image.zoom"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("zoom"));
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].content[" + j + "].image.type"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("type"));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(((List<Document>) doc.get("winners")).get(i).get("recipients") != null) {
                    if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).size() > 0) {
                        for (int j = 0; j < ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).size(); j++) {
                            if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("t_default").toString().equalsIgnoreCase(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].in").toString())) {
                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].in").toString().toLowerCase(), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("t_default").toString().toLowerCase());
                                if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person") != null) {
                                    if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("id") != null) {
                                        softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("id").toString());
                                        softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.name"), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("name"));
                                        if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts") != null) {
                                            softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.alts.first"), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts", Document.class).get("first"));
                                            softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.alts.last"), ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts", Document.class).get("last"));
                                        }
                                        if (((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("image") != null) {
                                            if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id") != null) {
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id").toString());
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.image.zoom"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("zoom"));
                                                softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].recipients[" + j + "].person.image.type"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("winners")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("type"));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (((List<Document>) doc.get("winners")).get(i).get("details") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].details").toString(), ((List<Document>) doc.get("winners")).get(i).get("details"));
                }
                if (((List<Document>) doc.get("winners")).get(i).get("network") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("winners[" + i + "].network").toString(), ((List<Document>) doc.get("winners")).get(i).get("network"));
                }
            }
        }
        if(doc.get("nominees") != null) {
            for (int i = 0; i < ((List<Document>) doc.get("nominees")).size(); i++) {
                softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].in").toString().toLowerCase(), ((List<Document>) doc.get("nominees")).get(i).get("t_default").toString().toLowerCase());
                if (((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).size() > 0) {
                    for (int j = 0; j < ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).size(); j++) {
                        softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("id").toString());
                        softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].type").toString(), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("type"));
                        if (((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in") != null) {
                            for (int k = 0; k < ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).size(); k++) {
                                if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("t_locale").toString().equalsIgnoreCase(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].in").toString())) {
                                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].in").toString().toLowerCase(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("t_locale").toString().toLowerCase());
                                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].title"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("title"));
                                    if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("image") != null) {
                                        if (((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("id") != null) {
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].image.ref.id").toString(), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("id").toString());
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].image.zoom"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("zoom"));
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].content[" + j + "].image.type"), ((List<Document>) ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("content")).get(j).get("t_in")).get(k).get("image", Document.class).get("type"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).size() > 0) {
                    for(int j=0; j < ((List<Document>)((List<Document>) doc.get("nominees")).get(i).get("recipients")).size(); j++){
                        if(((List<Document>)((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("t_default").toString().equalsIgnoreCase(resp.jsonPath().get("nominees["+ i +"].recipients["+ j +"].in").toString())){
                            softAssert.assertEquals(resp.jsonPath().get("nominees["+ i +"].recipients["+ j +"].in").toString().toLowerCase(),((List<Document>)((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("t_default").toString().toLowerCase());
                            if(((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person") != null) {
                                if (((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("id") != null) {
                                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("id").toString());
                                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.name"), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("name"));
                                    if(((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts") != null){
                                        softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.alts.first"), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts",Document.class).get("first"));
                                        softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.alts.last"), ((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("alts",Document.class).get("last"));
                                    }
                                    if(((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("image") != null){
                                        if(((List<Document>)((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id") != null){
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.image.ref.id").toString(),((List<Document>)((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("id").toString());
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.image.zoom"),((List<Document>)((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("zoom"));
                                            softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].recipients[" + j + "].person.image.type"),((List<Document>)((List<Document>) ((List<Document>) doc.get("nominees")).get(i).get("recipients")).get(j).get("person", Document.class).get("image")).get(0).get("type"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(((List<Document>) doc.get("nominees")).get(i).get("details") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].details").toString(), ((List<Document>) doc.get("nominees")).get(i).get("details"));
                }
                if(((List<Document>) doc.get("nominees")).get(i).get("details") != null) {
                    softAssert.assertEquals(resp.jsonPath().get("nominees[" + i + "].details").toString(), ((List<Document>) doc.get("nominees")).get(i).get("details"));
                }
            }
        }
    }
}
