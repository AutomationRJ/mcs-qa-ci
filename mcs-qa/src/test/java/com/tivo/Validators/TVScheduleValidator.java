package com.tivo.Validators;

import io.restassured.response.Response;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.asserts.SoftAssert;

import javax.print.Doc;
import java.util.List;

/**
 * Created by rjaiswal on 5/23/2017.
 */
public class TVScheduleValidator {

    public static void serviceValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("name"),doc.get("bestName"));
        softAssert.assertEquals(resp.jsonPath().get("city"),doc.get("city"));
        softAssert.assertEquals(resp.jsonPath().get("state"),doc.get("state"));
        softAssert.assertEquals(resp.jsonPath().get("system"),doc.get("system"));
        softAssert.assertEquals(resp.jsonPath().get("country"),doc.get("country"));
        softAssert.assertEquals(resp.jsonPath().get("serviceType"),doc.get("serviceType"));
        if(doc.get("msoId") != null){
            softAssert.assertEquals(resp.jsonPath().get("msoId").toString(),doc.get("msoId").toString());
        }
        if(doc.get("headendId") != null){
            softAssert.assertEquals(resp.jsonPath().get("headendId").toString(),doc.get("headendId").toString());
        }
        softAssert.assertEquals(resp.jsonPath().get("zone.zone"),doc.get("zone", Document.class).get("tzName"));
        if(doc.get("zone", Document.class).get("zoneWindows", Document.class).get("dcoCurStartDate") != null) {
            softAssert.assertEquals(resp.jsonPath().get("zone.zoneWindows.start").toString(), new DateTime(doc.get("zone", Document.class).get("zoneWindows", Document.class).get("dcoCurStartDate")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("zone", Document.class).get("zoneWindows", Document.class).get("dcoCurEndDate") != null) {
            softAssert.assertEquals(resp.jsonPath().get("zone.zoneWindows.end").toString(), new DateTime(doc.get("zone", Document.class).get("zoneWindows", Document.class).get("dcoCurEndDate")).toDateTime(DateTimeZone.UTC).toString());
        }
        if(doc.get("zone", Document.class).get("zoneWindows", Document.class).get("gmtOffsetValue") != null) {
            softAssert.assertEquals(resp.jsonPath().get("zone.zoneWindows.offset").toString(), doc.get("zone", Document.class).get("zoneWindows", Document.class).get("gmtOffsetValue").toString());
        }
        /*if(doc.get("f_offering") != null) {
            softAssert.assertEquals(resp.jsonPath().get("offering"), doc.get("f_offering"));
        }*/
        softAssert.assertAll();
    }

    public static void serviceChannelsValidator(Response resp, Document doc){
        int size = 0;
        if(((List<Document>) doc.get("channels")).size() > 0 && ((List<Document>) doc.get("channels")).size() <11){
            size = ((List<Document>) doc.get("channels")).size();
        }else {
            size = 10;
        }
        DateTime todayDate = new DateTime(DateTimeZone.UTC);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        if(((List<Document>)doc.get("channels")).size() > 0 ){
           for(int i=0,j=0; i < size; i++,j++){
               if(((List<Document>) doc.get("channels")).get(i).get("windows") != null) {
                   if (todayDate.isAfter(new DateTime(((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(0).get("SourceEndDate")).toDateTime(DateTimeZone.UTC))) {
                       j--;
                       continue;
                   }
                   if (((List<Document>) doc.get("channels")).get(i).get("CluChannelNumber").toString().equalsIgnoreCase(resp.jsonPath().get("channels[" + j + "].channel").toString())) {
                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].channel").toString(), ((List<Document>) doc.get("channels")).get(i).get("CluChannelNumber").toString());
                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].type"), ((List<Document>) doc.get("channels")).get(i).get("CluChannelTypeName"));
                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].code"), ((List<Document>) doc.get("channels")).get(i).get("ChannelDeviceCodeDesc"));
                       if (((List<Document>) doc.get("channels")).get(i).get("windows") != null) {
                           for (int k = 0; k < ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).size(); k++) {
                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].start").toString(), new DateTime(((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("SourceEffectiveDate")).toDateTime(DateTimeZone.UTC).toString());
                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].end").toString(), new DateTime(((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("SourceEndDate")).toDateTime(DateTimeZone.UTC).toString());
                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].on"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("SourceEffectiveTimeOn"));
                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].off"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("SourceEffectiveTimeOff"));
                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].days"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("days"));
                               if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source") != null) {
                                   if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("SourceId") != null) {
                                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("SourceId").toString());
                                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.in").toString().toLowerCase(), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("t_default").toString().toLowerCase());
                                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.type"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("type"));
                                       softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.name"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("TvsourceDisplayName"));
                                       if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("alts") != null) {
                                           softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.alts.full"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("alts", Document.class).get("longName"));
                                           softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.alts.source"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("alts", Document.class).get("sourceName"));
                                       }
                                       if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("logo") != null) {
                                           if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("logo", Document.class).get("id") != null) {
                                               softAssert.assertEquals(resp.jsonPath().get("channels[" + j + "].windows[" + k + "].source.logo.ref.id").toString(), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("source", Document.class).get("logo", Document.class).get("id").toString());
                                           }
                                       }
                                   }
                               }
                               if (((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("sourceGenres") != null) {
                                   softAssert.assertEquals(resp.jsonPath().getList("channels[" + j + "].windows[" + k + "].sourceGenres"), ((List<Document>) ((List<Document>) doc.get("channels")).get(i).get("windows")).get(k).get("sourceGenres"));
                               }
                           }
                       }
                   }
               }
           }
        }
        softAssert.assertAll();
    }

    public static void sourceValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("primaryLang").toString().toLowerCase());
        if(doc.get("type") != null){
            softAssert.assertEquals(resp.jsonPath().get("type"),doc.get("type"));
        }
        if (doc.get("fccchannel") != null) {
            softAssert.assertEquals(resp.jsonPath().get("fccchannel").toString(),doc.get("fccchannel").toString());
        }
        if (doc.get("digitalFccchannel") != null) {
            softAssert.assertEquals(resp.jsonPath().get("digitalChannelNumber").toString(),doc.get("digitalFccchannel").toString());
        }
        if(doc.get("alts") != null) {
            softAssert.assertEquals(resp.jsonPath().get("alts.full"), doc.get("alts", Document.class).get("longName"));
            softAssert.assertEquals(resp.jsonPath().get("alts.source"), doc.get("alts", Document.class).get("sourceName"));
        }
        if(doc.get("logo") != null) {
            softAssert.assertEquals(resp.jsonPath().get("logo.ref.id").toString(), doc.get("logo", Document.class).get("id").toString());
        }
        if(doc.get("callSign") != null){
            softAssert.assertEquals(resp.jsonPath().get("call"),doc.get("callSign"));
        }
        if(doc.get("sourceGenres") != null){
            softAssert.assertEquals(resp.jsonPath().getList("genres"),doc.get("sourceGenres"));
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
        if(doc.get("region") != null){
            softAssert.assertEquals(resp.jsonPath().get("region"),doc.get("region"));
        }
        if(doc.get("ratingType") != null){
            softAssert.assertEquals(resp.jsonPath().get("ratingType.tv").toString().toLowerCase(),doc.get("ratingType",Document.class).get("tv").toString().toLowerCase());
            softAssert.assertEquals(resp.jsonPath().get("ratingType.movie").toString().toLowerCase(),doc.get("ratingType",Document.class).get("movie").toString().toLowerCase());
        }
        if(doc.get("is3D") != null){
            softAssert.assertEquals(resp.jsonPath().get("is3D"),doc.get("is3D"));
        }
        if(doc.get("isDigital") != null){
            softAssert.assertEquals(resp.jsonPath().get("isDigital"),doc.get("isDigital"));
        }
        if(doc.get("isHdTV") != null){
            softAssert.assertEquals(resp.jsonPath().get("isHd"),doc.get("isHdTV"));
        }
        if(doc.get("isSap") != null){
            softAssert.assertEquals(resp.jsonPath().get("isSap"),doc.get("isSap"));
        }
        if(doc.get("isPsipvctActive") != null){
            softAssert.assertEquals(resp.jsonPath().get("isVirtual"),doc.get("isPsipvctActive"));
        }
        if(doc.get("isStet") != null){
            softAssert.assertEquals(resp.jsonPath().get("isStet"),doc.get("isStet"));
        }
        if (doc.get("affiliateSource1Id") != null) {
            softAssert.assertEquals(resp.jsonPath().get("affiliateSource1Id").toString(),doc.get("affiliateSource1Id").toString());
        }
        if (doc.get("affiliateSource2Id") != null) {
            softAssert.assertEquals(resp.jsonPath().get("affiliateSource2Id").toString(),doc.get("affiliateSource2Id").toString());
        }
        if (doc.get("affiliateSource3Id") != null) {
            softAssert.assertEquals(resp.jsonPath().get("affiliateSource3Id").toString(),doc.get("affiliateSource3Id").toString());
        }
        if (doc.get("dma") != null) {
            if (doc.get("dma", Document.class).get("code") != null) {
                softAssert.assertEquals(resp.jsonPath().get("dma.code").toString(), doc.get("dma", Document.class).get("code").toString());
            }
            if (doc.get("dma", Document.class).get("name") != null) {
                softAssert.assertEquals(resp.jsonPath().get("dma.name"), doc.get("dma", Document.class).get("name"));
            }
            if (doc.get("dma", Document.class).get("rank") != null) {
                softAssert.assertEquals(resp.jsonPath().get("dma.rank").toString(), doc.get("dma", Document.class).get("rank").toString());
            }
        }
        if(doc.get("zone") != null) {
            softAssert.assertEquals(resp.jsonPath().get("zone.zone"), doc.get("zone", Document.class).get("tzName"));
        }
        softAssert.assertAll();
    }

    public static void airingValidator(Response resp, Document doc){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        softAssert.assertEquals(resp.jsonPath().get("type"),doc.get("ProgramTypeName"));
        softAssert.assertEquals(resp.jsonPath().get("title"),doc.get("MasterTitle"));
        if(doc.get("alts") != null){
            if(doc.get("alts", Document.class).get("short15") != null){
                softAssert.assertEquals(resp.jsonPath().get("alts.short15"),doc.get("alts", Document.class).get("short15"));
            }
            if(doc.get("alts", Document.class).get("short30") != null){
                softAssert.assertEquals(resp.jsonPath().get("alts.short30"),doc.get("alts", Document.class).get("short30"));
            }
            if(doc.get("alts", Document.class).get("short8") != null){
                softAssert.assertEquals(resp.jsonPath().get("alts.short8"),doc.get("alts", Document.class).get("short8"));
            }
            if(doc.get("alts", Document.class).get("short50") != null){
                softAssert.assertEquals(resp.jsonPath().get("alts.short50"),doc.get("alts", Document.class).get("short50"));
            }
        }
        if(doc.get("content") != null) {
            softAssert.assertEquals(resp.jsonPath().get("content.ref.id").toString(), doc.get("content", Document.class).get("id").toString());
            softAssert.assertEquals(resp.jsonPath().get("content.title"), doc.get("content", Document.class).get("title"));
            if(doc.get("content", Document.class).get("synonyms") != null){
                for(int i = 0; i < ((List<Document>)doc.get("content", Document.class).get("synonyms")).size() ; i++){
                    if(((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rv20") != null){
                        softAssert.assertEquals(resp.jsonPath().get("content.synonyms[" + i + "].rv20").toString(),((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rv20").toString());
                    }
                    if(((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rv11") != null){
                        softAssert.assertEquals(resp.jsonPath().get("content.synonyms[" + i + "].rv11").toString(),((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rv11").toString());
                    }
                    if(((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rvgroup") != null){
                        softAssert.assertEquals(resp.jsonPath().get("content.synonyms[" + i + "].rvgroup").toString(),((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("rvgroup").toString());
                    }
                    if(((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("eidr") != null){
                        softAssert.assertEquals(resp.jsonPath().get("content.synonyms[" + i + "].eidr").toString(),((List<Document>)doc.get("content", Document.class).get("synonyms")).get(i).get("eidr").toString());
                    }
                }
            }
        }
        softAssert.assertEquals(resp.jsonPath().get("source.ref.id").toString(),doc.get("source", Document.class).get("id").toString());
        softAssert.assertEquals(resp.jsonPath().get("source.name"),doc.get("source", Document.class).get("name"));
        softAssert.assertEquals(resp.jsonPath().get("start").toString(),new DateTime(doc.get("AirDateTime")).toDateTime(DateTimeZone.UTC).toString());
        softAssert.assertEquals(resp.jsonPath().get("end").toString(),new DateTime(doc.get("EndAirDateTime")).toDateTime(DateTimeZone.UTC).toString());
        softAssert.assertEquals(resp.jsonPath().get("duration").toString(),doc.get("ScheduledDuration").toString());

        if(doc.get("Ratings") != null){
            if(doc.get("Ratings",Document.class).get("tvRatings") != null){
                softAssert.assertEquals(resp.jsonPath().get("ratings.tv.in").toString().toLowerCase(),doc.get("Ratings",Document.class).get("tvRatings", Document.class).get("t_default").toString().toLowerCase());
                softAssert.assertEquals(resp.jsonPath().get("ratings.tv.type"),doc.get("Ratings",Document.class).get("tvRatings", Document.class).get("RatingSystemName"));
                softAssert.assertEquals(resp.jsonPath().get("ratings.tv.rating"),doc.get("Ratings",Document.class).get("tvRatings", Document.class).get("RatingValue"));
            }
            if(doc.get("Ratings",Document.class).get("MovieRatings") != null){
                for(int i=0; i < ((List<Document>)doc.get("Ratings",Document.class).get("MovieRatings")).size(); i++){
                    softAssert.assertEquals(resp.jsonPath().get("ratings.movie["+ i + "].in").toString().toLowerCase(),((List<Document>) doc.get("Ratings",Document.class).get("MovieRatings")).get(i).get("t_default").toString().toLowerCase());
                    softAssert.assertEquals(resp.jsonPath().get("ratings.movie["+ i + "].type"),((List<Document>) doc.get("Ratings",Document.class).get("MovieRatings")).get(i).get("RatingSystemName"));
                    softAssert.assertEquals(resp.jsonPath().get("ratings.movie["+ i + "].rating"),((List<Document>) doc.get("Ratings",Document.class).get("MovieRatings")).get(i).get("RatingValue"));
                }
            }
        }
        if(doc.get("subTitle") != null){
            softAssert.assertEquals(resp.jsonPath().get("subtitle").toString().toLowerCase(),doc.get("subTitle").toString().toLowerCase());
        }
        softAssert.assertAll();
    }

    public static void airingCastValidator(Response resp,Document doc){
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() < 11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        if(((List<Document>) doc.get("credits")).size() > 0 ){
            for(int i = 0; i < size; i++){
                if(((List<Document>) doc.get("credits")).get(i).get("isCast") != null){
                    if(((List<Document>) doc.get("credits")).get(i).get("isCast").equals(true)){
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"),((List<Document>) doc.get("credits")).get(i).get("type"));
                        softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].isCast"),((List<Document>) doc.get("credits")).get(i).get("isCast"));
                        if(((List<Document>) doc.get("credits")).get(i).get("role") != null){
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].role"),((List<Document>) doc.get("credits")).get(i).get("role"));
                        }
                        if(((List<Document>) doc.get("credits")).get(i).get("person") != null){
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
    }

    public static void airingCrewValidator(Response resp,Document doc){
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() <11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(),doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        if(((List<Document>) doc.get("credits")).size() > 0 ){
            for(int i = 0; i < size; i++){
                    if(((List<Document>) doc.get("credits")).get(i).get("isCast") == null || ((List<Document>) doc.get("credits")).get(i).get("isCast").equals(false)){
                        if(((List<Document>) doc.get("credits")).get(i).get("type") !=null) {
                            softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].type"), ((List<Document>) doc.get("credits")).get(i).get("type"));
                            if (((List<Document>) doc.get("credits")).get(i).get("type").toString().equalsIgnoreCase("company")) {
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.id"), ((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("id"));
                                softAssert.assertEquals(resp.jsonPath().get("credits[" + i + "].organization.name"), ((List<Document>) doc.get("credits")).get(i).get("organization", Document.class).get("name"));
                            }
                        }
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

    public static void airingCreditsValidator(Response resp, Document doc) {
        int size = 0;
        if(((List<Document>) doc.get("credits")).size() > 0 && ((List<Document>) doc.get("credits")).size() <11){
            size = ((List<Document>) doc.get("credits")).size();
        }else {
            size = 10;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        softAssert.assertEquals(resp.jsonPath().get("in").toString().toLowerCase(),doc.get("t_default").toString().toLowerCase());
        if (((List<Document>) doc.get("credits")).size() > 0) {
            for (int i = 0; i < size; i++) {
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

    public static void airingSynopsesValidator(Response resp, Document doc) {
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
                softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].in").toString().toLowerCase(),((List<Document>) doc.get("synopses")).get(i).get("t_default").toString().toLowerCase());
                softAssert.assertEquals(resp.jsonPath().get("synopses[" + i + "].synopsis"),((List<Document>) doc.get("synopses")).get(i).get("synopsis"));
                if(((List<Document>)doc.get("synopses")).get(i).get("cuts") != null){
                    softAssert.assertEquals(resp.jsonPath().getList("synopses[" + i + "].cuts"),((List<Document>) doc.get("synopses")).get(i).get("cuts"));
                }
            }
        }
    }

    public static void airingSynopsesBestValidator(Response resp, Document doc) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resp.jsonPath().get("ref.id").toString(), doc.get("_id").toString());
        if (((List<Document>) doc.get("synopses")).size() > 0) {
            for (int i = 0; i < ((List<Document>) doc.get("synopses")).size(); i++) {
                softAssert.assertEquals(resp.jsonPath().get("synopsis.length"), ((List<Document>) doc.get("synopses")).get(0).get("length"));
                softAssert.assertEquals(resp.jsonPath().get("synopsis.in").toString().toLowerCase(),((List<Document>) doc.get("synopses")).get(0).get("t_default").toString().toLowerCase());
                softAssert.assertEquals(resp.jsonPath().get("synopsis.synopsis"),((List<Document>) doc.get("synopses")).get(0).get("synopsis"));
                if(((List<Document>)doc.get("synopses")).get(i).get("cuts") != null){
                    softAssert.assertEquals(resp.jsonPath().getList("synopsis.cuts"),((List<Document>) doc.get("synopses")).get(0).get("cuts"));
                }
            }
        }
    }
}
