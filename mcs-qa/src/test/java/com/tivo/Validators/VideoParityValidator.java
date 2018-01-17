package com.tivo.Validators;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.util.*;


/**
 * Created by rjaiswal on 6/8/2017.
 */
public class VideoParityValidator {

    private static int i = 0;
    private static final String OUTPUT_FOLDER = new File("").getAbsolutePath() + "\\test-output\\";
    private static final String FILE_NAME = "MovieDataDifference";
    private static int k = 0;

    public static void initCount() {
        i = 0;
    }

    public static int getCount() {
        return i;
    }

    /*public static void transformValidator(Document prod, Document dev) throws JSONException {
        Set<String> set = new HashSet<>();
        set.add("_v");
        set.add("_pub");
        set.add("_ts");
        //set.add("t_in");
        *//*set.add("synonyms");
        set.add("f_market");*//*
        dev.keySet().removeAll(set);
        prod.keySet().removeAll(set);
        Gson gson = new Gson();
        String devJson = gson.toJson(dev);
        String prodJson = gson.toJson(prod);
        JSONCompareResult result = JSONCompare.compareJSON(prodJson, devJson, JSONCompareMode.LENIENT);
        if(result.failed()){
            i++;
            System.out.println(prod.get("_id"));
            System.out.println(result.getMessage());
        }

    }*/

    public static void sourceTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                softAssert.assertEquals(dev.get("_id"), prod.get("_id"), "Failed for _id : " + dev.get("_id").toString());
            }
            if (prod.get("id") != null) {
                softAssert.assertEquals(dev.get("id"), prod.get("id"), "Failed for id : " + dev.get("_id").toString());
            }
            if (prod.get("r_source") != null) {
                softAssert.assertEquals(dev.get("r_source"), prod.get("r_source"), "Failed for r_source : " + dev.get("_id").toString());
            }
            if (prod.get("r_source_logos") != null) {
                softAssert.assertEquals(dev.get("r_source_logos"), prod.get("r_source_logos"), "Failed for r_source_logos : " + dev.get("_id").toString());
            }
            if (prod.get("primaryLang") != null) {
                softAssert.assertEquals(dev.get("primaryLang"), prod.get("primaryLang"), "Failed for primaryLang : " + dev.get("_id").toString());
            }
            if (prod.get("type") != null) {
                softAssert.assertEquals(dev.get("type"), prod.get("type"), "Failed for type : " + dev.get("_id").toString());
            }
            if (prod.get("displayName") != null) {
                softAssert.assertEquals(dev.get("displayName"), prod.get("displayName"), "Failed for displayName : " + dev.get("_id").toString());
            }
            if (prod.get("fccchannel") != null) {
                softAssert.assertEquals(dev.get("fccchannel"), prod.get("fccchannel"), "Failed for fccchannel : " + dev.get("_id").toString());
            }
            if (prod.get("digitalFccchannel") != null) {
                softAssert.assertEquals(dev.get("digitalFccchannel"), prod.get("digitalFccchannel"), "Failed for digitalFccchannel : " + dev.get("_id").toString());
            }
            if (prod.get("alts") != null) {
                if (prod.get("alts", Document.class).get("longName") != null) {
                    softAssert.assertEquals(dev.get("alts", Document.class).get("longName"), prod.get("alts", Document.class).get("longName"), "Failed for longName : " + dev.get("_id").toString());
                }
                if (prod.get("alts", Document.class).get("sourceName") != null) {
                    softAssert.assertEquals(dev.get("alts", Document.class).get("sourceName"), prod.get("alts", Document.class).get("sourceName"), "Failed for sourceName : " + dev.get("_id").toString());
                }
            }
            /*if (prod.get("logo") != null) {
                if (prod.get("logo", Document.class).get("id") != null) {
                    softAssert.assertEquals(dev.get("logo", Document.class).get("id"), prod.get("logo", Document.class).get("id"), "Failed for logo.id : " + dev.get("_id").toString());
                }
                if (prod.get("logo", Document.class).get("l_media_logo") != null) {
                    softAssert.assertEquals(dev.get("logo", Document.class).get("l_media_logo"), prod.get("logo", Document.class).get("l_media_logo"), "Failed for logo.l_media_logo : " + dev.get("_id").toString());
                }
            }*/
            if (prod.get("callSign") != null) {
                softAssert.assertEquals(dev.get("callSign"), prod.get("callSign"), "Failed for callSign : " + dev.get("_id").toString());
            }
            if (prod.get("sourceGenres") != null && dev.get("sourceGenres") != null) {
                List<Document> prodList = (List<Document>) prod.get("sourceGenres");
                List<Document> dList = (List<Document>) dev.get("sourceGenres");
                softAssert.assertTrue(dList.containsAll(prodList), "Failed sourceGenres");
            }
            if (prod.get("city") != null) {
                softAssert.assertEquals(dev.get("city"), prod.get("city"), "Failed for city : " + dev.get("_id").toString());
            }
            if (prod.get("state") != null) {
                softAssert.assertEquals(dev.get("state"), prod.get("state"), "Failed for state : " + dev.get("_id").toString());
            }
            if (prod.get("country") != null) {
                softAssert.assertEquals(dev.get("country"), prod.get("country"), "Failed for country : " + dev.get("_id").toString());
            }
            if (prod.get("region") != null) {
                softAssert.assertEquals(dev.get("region"), prod.get("region"), "Failed for region : " + dev.get("_id").toString());
            }
            if (prod.get("ratingType") != null) {
                if (prod.get("ratingType", Document.class).get("tv") != null) {
                    softAssert.assertEquals(dev.get("ratingType", Document.class).get("tv"), prod.get("ratingType", Document.class).get("tv"), "Failed for ratingType.tv : " + dev.get("_id").toString());
                }
                if (prod.get("ratingType", Document.class).get("movie") != null) {
                    softAssert.assertEquals(dev.get("ratingType", Document.class).get("movie"), prod.get("ratingType", Document.class).get("movie"), "Failed for ratingType.movie : " + dev.get("_id").toString());
                }
            }
            if (prod.get("is3D") != null) {
                softAssert.assertEquals(dev.get("is3D"), prod.get("is3D"), "Failed for is3D : " + dev.get("_id").toString());
            }
            if (prod.get("isDigital") != null) {
                softAssert.assertEquals(dev.get("isDigital"), prod.get("isDigital"), "Failed for isDigital : " + dev.get("_id").toString());
            }
            if (prod.get("Hdtv_Yn") != null) {
                softAssert.assertEquals(dev.get("Hdtv_Yn"), prod.get("Hdtv_Yn"), "Failed for Hdtv_Yn : " + dev.get("_id").toString());
            }
            if (prod.get("isHdTV") != null) {
                softAssert.assertEquals(dev.get("isHdTV"), prod.get("isHdTV"), "Failed for isHdTV : " + dev.get("_id").toString());
            }
            if (prod.get("isSap") != null) {
                softAssert.assertEquals(dev.get("isSap"), prod.get("isSap"), "Failed for isSap : " + dev.get("_id").toString());
            }
            if (prod.get("isPsipvctActive") != null) {
                softAssert.assertEquals(dev.get("isPsipvctActive"), prod.get("isPsipvctActive"), "Failed for isPsipvctActive : " + dev.get("_id").toString());
            }
            if (prod.get("affiliateSource1Id") != null) {
                softAssert.assertEquals(dev.get("affiliateSource1Id"), prod.get("affiliateSource1Id"), "Failed for affiliateSource1Id : " + dev.get("_id").toString());
            }
            if (prod.get("affiliateSource2Id") != null) {
                softAssert.assertEquals(dev.get("affiliateSource2Id"), prod.get("affiliateSource2Id"), "Failed for affiliateSource2Id : " + dev.get("_id").toString());
            }
            if (prod.get("affiliateSource3Id") != null) {
                softAssert.assertEquals(dev.get("affiliateSource3Id"), prod.get("affiliateSource3Id"), "Failed for affiliateSource3Id : " + dev.get("_id").toString());
            }
            if (prod.get("isStet") != null) {
                softAssert.assertEquals(dev.get("isStet"), prod.get("isStet"), "Failed for isStet : " + dev.get("_id").toString());
            }
            if (prod.get("dma") != null) {
                if (prod.get("dma", Document.class).get("code") != null) {
                    softAssert.assertEquals(dev.get("dma", Document.class).get("code"), prod.get("dma", Document.class).get("code"), "Failed for dma.code : " + dev.get("_id").toString());
                }
                if (prod.get("dma", Document.class).get("name") != null) {
                    softAssert.assertEquals(dev.get("dma", Document.class).get("name"), prod.get("dma", Document.class).get("name"), "Failed for dma.name : " + dev.get("_id").toString());
                }
                if (prod.get("dma", Document.class).get("rank") != null) {
                    softAssert.assertEquals(dev.get("dma", Document.class).get("rank"), prod.get("dma", Document.class).get("rank"), "Failed for dma.rank : " + dev.get("_id").toString());
                }
            }
            if (prod.get("zone") != null) {
                if (prod.get("zone", Document.class).get("tzName") != null) {
                    softAssert.assertEquals(dev.get("zone", Document.class).get("tzName"), prod.get("zone", Document.class).get("tzName"), "Failed for zone.tzName : " + dev.get("_id").toString());
                }
                if (prod.get("zone", Document.class).get("zoneWindows") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).size(); i++) {
                        if (((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurStartDate") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurStartDate"), ((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurStartDate"), "Failed for zone.zoneWindows.dcoCurStartDate : " + dev.get("_id").toString());
                        }
                        if (((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurEndDate") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurEndDate"), ((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("dcoCurEndDate"), "Failed for zone.zoneWindows.dcoCurEndDate : " + dev.get("_id").toString());
                        }
                        if (((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("gmtOffsetValue") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("zone", Document.class).get("zoneWindows"))).get(i).get("gmtOffsetValue"), ((List<Document>) (prod.get("zone", Document.class).get("zoneWindows"))).get(i).get("gmtOffsetValue"), "Failed for zone.zoneWindows.gmtOffsetValue : " + dev.get("_id").toString());
                        }
                    }
                }
            }
            if (prod.get("c_source_logos") != null) {
                softAssert.assertTrue(dev.getLong("c_source_logos") >= prod.getLong("c_source_logos"), "Failed for c_source_logos : " + dev.get("_id").toString());
            }
            if (prod.get("synonyms") != null) {
                for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                    if (((List<Document>) (prod.get("synonyms"))).get(i).get("source") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("synonyms"))).get(i).get("source"), ((List<Document>) (prod.get("synonyms"))).get(i).get("source"), "Failed for synonyms.source : " + dev.get("_id").toString());
                    }
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void personTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null ");
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }

            }
            if (prod.get("r_person") != null) {
                if (dev.get("r_person") != null) {
                    softAssert.assertEquals(dev.get("r_person").toString(), prod.get("r_person").toString(), "Failed for r_person : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person") != null, "Failed because Dev.r_person is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_person_appearances") != null) {
                if (dev.get("r_person_appearances") != null) {
                    softAssert.assertEquals(dev.get("r_person_appearances").toString(), prod.get("r_person_appearances").toString(), "Failed for r_person_appearances : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person_appearances") != null, "Failed because Dev.r_person_appearances is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_person_awards") != null) {
                if (dev.get("r_person_awards") != null) {
                    softAssert.assertEquals(dev.get("r_person_awards").toString(), prod.get("r_person_awards").toString(), "Failed for r_person_awards : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person_awards") != null, "Failed because Dev.r_person_awards is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_person_bios") != null) {
                if (dev.get("r_person_bios") != null) {
                    softAssert.assertEquals(dev.get("r_person_bios").toString(), prod.get("r_person_bios").toString(), "Failed for r_person_bios : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person_bios") != null, "Failed because Dev.r_person_bios is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_person_filmography") != null) {
                if (dev.get("r_person_filmography") != null) {
                    softAssert.assertEquals(dev.get("r_person_filmography").toString(), prod.get("r_person_filmography").toString(), "Failed for r_person_filmography : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person_filmography") != null, "Failed because Dev.r_person_filmography is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_person_relatives") != null) {
                if (dev.get("r_person_relatives") != null) {
                    softAssert.assertEquals(dev.get("r_person_relatives").toString(), prod.get("r_person_relatives").toString(), "Failed for r_person_relatives : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_person_relatives") != null, "Failed because Dev.r_person_relatives is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_name") != null) {
                if (dev.get("r_name") != null) {
                    softAssert.assertEquals(dev.get("r_name").toString(), prod.get("r_name").toString(), "Failed for r_name : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_name") != null, "Failed because Dev.r_name is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_name_images") != null) {
                if (dev.get("r_name_images") != null) {
                    softAssert.assertEquals(dev.get("r_name_images").toString(), prod.get("r_name_images").toString(), "Failed for r_name_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_name_images") != null, "Failed because Dev.r_name_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        prodTinLocale.add(((List<Document>) (prod.get("t_in"))).get(i).getString("t_locale"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        devTinLocale.add(((List<Document>) (dev.get("t_in"))).get(i).getString("t_locale"));
                    }
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
                /*for(int i = 0 ; i < ((List<Document>)(prod.get("t_in"))).size(); i++){
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("t_locale") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("t_locale"), ((List<Document>) (prod.get("t_in"))).get(i).get("t_locale"), "Failed for t_in." + i + ".t_locale : " + dev.get("_id").toString());
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("name") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("name"), ((List<Document>) (prod.get("t_in"))).get(i).get("name"), "Failed for t_in." + i + ".name : " + dev.get("_id").toString());
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("alts") != null) {
                        if(((List<Document>)(prod.get("t_in"))).get(i).get("alts",Document.class).get("first") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("alts", Document.class).get("first"), ((List<Document>) (prod.get("t_in"))).get(i).get("alts", Document.class).get("first"), "Failed for t_in." + i + ".alts.first : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("t_in"))).get(i).get("alts",Document.class).get("last") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("alts", Document.class).get("last"), ((List<Document>) (prod.get("t_in"))).get(i).get("alts", Document.class).get("last"), "Failed for t_in." + i + ".alts.last : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("t_in"))).get(i).get("alts",Document.class).get("birth") != null) {
                            softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("alts", Document.class).get("birth"), ((List<Document>) (prod.get("t_in"))).get(i).get("alts", Document.class).get("birth"), "Failed for t_in." + i + ".alts.birth : " + dev.get("_id").toString());
                        }
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("alias") != null) {
                        for (int j = 0; j < ((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).size(); j++) {
                            if (((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("name") != null) {
                                softAssert.assertEquals(((List<Document>) ((List<Document>) (dev.get("t_in"))).get(i).get("alias")).get(j).get("name"), ((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("name"), "Failed for t_in." + i + ".alias." + j + ".name : " + dev.get("_id").toString());
                            }
                            if (((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("first") != null) {
                                softAssert.assertEquals(((List<Document>) ((List<Document>) (dev.get("t_in"))).get(i).get("alias")).get(j).get("first"), ((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("first"), "Failed for t_in." + i + ".alias." + j + ".first : " + dev.get("_id").toString());
                            }
                            if (((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("last") != null) {
                                softAssert.assertEquals(((List<Document>) ((List<Document>) (dev.get("t_in"))).get(i).get("alias")).get(j).get("last"), ((List<Document>) ((List<Document>) (prod.get("t_in"))).get(i).get("alias")).get(j).get("last"), "Failed for t_in." + i + ".alias." + j + ".last : " + dev.get("_id").toString());
                            }
                        }
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("birthplace") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("birthplace"), ((List<Document>) (prod.get("t_in"))).get(i).get("birthplace"), "Failed for t_in." + i + ".birthplace : " + dev.get("_id").toString());
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("highSchool") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("highSchool"), ((List<Document>) (prod.get("t_in"))).get(i).get("highSchool"), "Failed for t_in." + i + ".highSchool : " + dev.get("_id").toString());
                    }
                    if(((List<Document>)(prod.get("t_in"))).get(i).get("college") != null) {
                        softAssert.assertEquals(((List<Document>) (dev.get("t_in"))).get(i).get("college"), ((List<Document>) (prod.get("t_in"))).get(i).get("college"), "Failed for t_in." + i + ".college : " + dev.get("_id").toString());
                    }
                }*/
                if (prod.get("p_alias") != null) {
                    if (dev.get("p_alias") != null) {
                        ArrayList<String> prodPalias = new ArrayList<>();
                        ArrayList<String> devPalias = new ArrayList<>();
                        for (int i = 0; i < ((List<Document>) (prod.get("p_alias"))).size(); i++) {
                            prodPalias.add(((List<Document>) (prod.get("p_alias"))).get(i).getString("name"));
                        }
                        for (int i = 0; i < ((List<Document>) (dev.get("p_alias"))).size(); i++) {
                            devPalias.add(((List<Document>) (dev.get("p_alias"))).get(i).getString("name"));
                        }
                        softAssert.assertTrue(devPalias.containsAll(prodPalias), "Failed for p_alias : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("p_alias") != null, "Failed because Dev.p_alias is null " + dev.get("_id").toString());
                    }
                    /*for(int i=0; i < ((List<Document>)(prod.get("p_alias"))).size(); i++){
                        if(((List<Document>)(prod.get("p_alias"))).get(i).get("name") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("p_alias"))).get(i).get("name"),((List<Document>)(prod.get("p_alias"))).get(i).get("name"),"Failed for p_alias." + i + ".name : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("p_alias"))).get(i).get("first") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("p_alias"))).get(i).get("first"),((List<Document>)(prod.get("p_alias"))).get(i).get("first"),"Failed for p_alias." + i + ".first : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("p_alias"))).get(i).get("last") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("p_alias"))).get(i).get("last"),((List<Document>)(prod.get("p_alias"))).get(i).get("last"),"Failed for p_alias." + i + ".last : " + dev.get("_id").toString());
                        }
                    }*/
                }
                if (prod.get("image") != null) {
                    if (dev.get("image") != null) {
                        ArrayList<Long> prodImage = new ArrayList<>();
                        ArrayList<Long> devImage = new ArrayList<>();
                        for (int i = 0; i < ((List<Document>) (prod.get("image"))).size(); i++) {
                            prodImage.add(((List<Document>) (prod.get("image"))).get(i).getLong("id"));
                        }
                        for (int i = 0; i < ((List<Document>) (dev.get("image"))).size(); i++) {
                            devImage.add(((List<Document>) (dev.get("image"))).get(i).getLong("id"));
                        }
                        softAssert.assertTrue(devImage.containsAll(prodImage), "Failed for Image : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("image") != null, "Failed because Dev.image is null " + dev.get("_id").toString());
                    }
                    /*for(int i=0; i < ((List<Document>)(prod.get("image"))).size(); i++){
                        if(((List<Document>)(prod.get("image"))).get(i).get("id") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("image"))).get(i).get("id"),((List<Document>)(prod.get("image"))).get(i).get("id"),"Failed for image." + i + ".id : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("image"))).get(i).get("l_media_image") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("image"))).get(i).get("l_media_image"),((List<Document>)(prod.get("image"))).get(i).get("l_media_image"),"Failed for image." + i + ".l_media_image : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("image"))).get(i).get("zoom") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("image"))).get(i).get("zoom"),((List<Document>)(prod.get("image"))).get(i).get("zoom"),"Failed for image." + i + ".zoom : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("image"))).get(i).get("type") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("image"))).get(i).get("type"),((List<Document>)(prod.get("image"))).get(i).get("type"),"Failed for image." + i + ".type : " + dev.get("_id").toString());
                        }
                    }*/
                }
                if (prod.get("gender") != null) {
                    if (dev.get("gender") != null) {
                        softAssert.assertEquals(dev.get("gender"), prod.get("gender"), "Failed for gender : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("gender") != null, "Failed because Dev.gender is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("isCelebrity") != null) {
                    if (dev.get("isCelebrity") != null) {
                        softAssert.assertEquals(dev.get("isCelebrity"), prod.get("isCelebrity"), "Failed for isCelebrity : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("isCelebrity") != null, "Failed because Dev.isCelebrity is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("d_professions") != null) {
                    if (dev.get("d_professions") != null) {
                        ArrayList<Long> prodProfessions = new ArrayList<>();
                        ArrayList<Long> devProfessions = new ArrayList<>();
                        for (int i = 0; i < ((List<Document>) (prod.get("d_professions"))).size(); i++) {
                            prodProfessions.add(((List<Document>) (prod.get("d_professions"))).get(i).getLong("id"));
                        }
                        for (int i = 0; i < ((List<Document>) (dev.get("d_professions"))).size(); i++) {
                            devProfessions.add(((List<Document>) (dev.get("d_professions"))).get(i).getLong("id"));
                        }
                        softAssert.assertTrue(devProfessions.containsAll(prodProfessions), "Failed for d_professions : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("d_professions") != null, "Failed because Dev.d_professions is null " + dev.get("_id").toString());
                    }
                    /*for(int i=0; i < ((List<Document>)(prod.get("d_professions"))).size(); i++){
                        if(((List<Document>)(prod.get("d_professions"))).get(i).get("id") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("d_professions"))).get(i).get("id"),((List<Document>)(prod.get("d_professions"))).get(i).get("id"),"Failed for d_professions." + i + ".id : " + dev.get("_id").toString());
                        }
                    }*/
                }
                if (prod.get("dob") != null) {
                    if (dev.get("dob") != null) {
                        softAssert.assertEquals(dev.get("dob"), prod.get("dob"), "Failed for dob : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("dob") != null, "Failed because Dev.dob is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("dod") != null) {
                    if (dev.get("dod") != null) {
                        softAssert.assertEquals(dev.get("dod"), prod.get("dod"), "Failed for dod : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("dod") != null, "Failed because Dev.dod is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("d_zodiac") != null) {
                    if (dev.get("d_zodiac") != null) {
                        softAssert.assertEquals(dev.get("d_zodiac", Document.class).get("id"), prod.get("d_zodiac", Document.class).get("id"), "Failed for d_zodiac.id : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("d_zodiac") != null, "Failed because Dev.d_zodiac is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("p_birthplace") != null) {
                    if (dev.get("p_birthplace") != null) {
                        softAssert.assertEquals(dev.get("p_birthplace"), prod.get("p_birthplace"), "Failed for p_birthplace : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("p_birthplace") != null, "Failed because Dev.p_birthplace is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("p_highSchool") != null) {
                    if (dev.get("p_highSchool") != null) {
                        softAssert.assertEquals(dev.get("p_highSchool"), prod.get("p_highSchool"), "Failed for p_highSchool : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("p_highSchool") != null, "Failed because Dev.p_highSchool is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("p_college") != null) {
                    if (dev.get("p_college") != null) {
                        softAssert.assertEquals(dev.get("p_college"), prod.get("p_college"), "Failed for p_college : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("p_college") != null, "Failed because Dev.p_college is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("homepage") != null) {
                    if (dev.get("homepage") != null) {
                        softAssert.assertEquals(dev.get("homepage"), prod.get("homepage"), "Failed for homepage : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("homepage") != null, "Failed because Dev.homepage is null " + dev.get("_id").toString());
                    }
                }
                if (prod.get("h_facebooks") != null) {
                    if (dev.get("h_facebooks") != null) {
                        ArrayList<Long> prodFacebookId = new ArrayList<>();
                        ArrayList<Long> devFacebookId = new ArrayList<>();
                        for (int i = 0; i < ((List<Document>) (prod.get("h_facebooks"))).size(); i++) {
                            prodFacebookId.add(((List<Document>) (prod.get("h_facebooks"))).get(i).getLong("facebookId"));
                        }
                        for (int i = 0; i < ((List<Document>) (dev.get("h_facebooks"))).size(); i++) {
                            devFacebookId.add(((List<Document>) (dev.get("h_facebooks"))).get(i).getLong("facebookId"));
                        }
                        softAssert.assertTrue(devFacebookId.containsAll(prodFacebookId), "Failed for h_facebooks : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("h_facebooks") != null, "Failed because Dev.h_facebooks is null " + dev.get("_id").toString());
                    }
                    /*for(int i=0; i < ((List<Document>)(prod.get("h_facebooks"))).size(); i++){
                        if(((List<Document>)(prod.get("h_facebooks"))).get(i).get("facebookId") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("h_facebooks"))).get(i).get("facebookId"),((List<Document>)(prod.get("h_facebooks"))).get(i).get("facebookId"),"Failed for h_facebooks." + i + ".facebookId : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("h_facebooks"))).get(i).get("type") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("h_facebooks"))).get(i).get("type"),((List<Document>)(prod.get("h_facebooks"))).get(i).get("type"),"Failed for h_facebooks." + i + ".type : " + dev.get("_id").toString());
                        }
                        if(((List<Document>)(prod.get("h_facebooks"))).get(i).get("uri") != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("h_facebooks"))).get(i).get("uri"),((List<Document>)(prod.get("h_facebooks"))).get(i).get("uri"),"Failed for h_facebooks." + i + ".uri : " + dev.get("_id").toString());
                        }
                    }*/
                }
                if (prod.get("h_twitters") != null) {
                    if (dev.get("h_twitters") != null) {
                        List<Document> prodList = (List<Document>) prod.get("h_twitters");
                        List<Document> dList = (List<Document>) dev.get("h_twitters");
                        softAssert.assertTrue(dList.containsAll(prodList), "Failed for h_twitters : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("h_twitters") != null, "Failed because Dev.h_twitters is null " + dev.get("_id").toString());
                    }
                    /*for(int i=0; i < ((List<Document>)(prod.get("h_twitters"))).size(); i++){
                        if(((List<Document>)(prod.get("h_twitters"))).get(i) != null){
                            softAssert.assertEquals(((List<Document>)(dev.get("h_twitters"))).get(i),((List<Document>)(prod.get("h_twitters"))).get(i),"Failed for h_twitters :" + i + dev.get("_id").toString());
                        }
                    }*/
                }
                if (prod.get("synonyms") != null) {
                    if (dev.get("synonyms") != null) {
                        ArrayList<Long> prodSynonymsId = new ArrayList<>();
                        ArrayList<Long> devSynonymsId = new ArrayList<>();
                        for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                            prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("rv20"));
                        }
                        for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                            devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("rv20"));
                        }
                        softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                    }
                }
                i++;
                System.out.println(i);
            }
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_awards") != null) {
                if (dev.get("r_series_awards") != null) {
                    softAssert.assertEquals(dev.get("r_series_awards").toString(), prod.get("r_series_awards").toString(), "Failed for r_series_awards : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_awards") != null, "Failed because Dev.r_series_awards is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_credits") != null) {
                if (dev.get("r_series_credits") != null) {
                    softAssert.assertEquals(dev.get("r_series_credits").toString(), prod.get("r_series_credits").toString(), "Failed for r_series_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_credits") != null, "Failed because Dev.r_series_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_history") != null) {
                if (dev.get("r_series_history") != null) {
                    softAssert.assertEquals(dev.get("r_series_history").toString(), prod.get("r_series_history").toString(), "Failed for r_series_history : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_history") != null, "Failed because Dev.r_series_history is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_ratings") != null) {
                if (dev.get("r_series_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_series_ratings").toString(), prod.get("r_series_ratings").toString(), "Failed for r_series_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_ratings") != null, "Failed because Dev.r_series_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_seasons") != null) {
                if (dev.get("r_series_seasons") != null) {
                    softAssert.assertEquals(dev.get("r_series_seasons").toString(), prod.get("r_series_seasons").toString(), "Failed for r_series_seasons : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_seasons") != null, "Failed because Dev.r_series_seasons is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_synopses") != null) {
                if (dev.get("r_series_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_series_synopses").toString(), prod.get("r_series_synopses").toString(), "Failed for r_series_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_synopses") != null, "Failed because Dev.r_series_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_market") != null) {
                if (dev.get("f_market") != null) {
                    List<Document> prodFmarket = (List<Document>) (prod.get("f_market"));
                    List<Document> devFmarket = (List<Document>) (dev.get("f_market"));
                    softAssert.assertTrue(devFmarket.containsAll(prodFmarket), "Failed for f_market : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_market") != null, "Failed because Dev.f_market is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        prodTinLocale.add(((List<Document>) (prod.get("t_in"))).get(i).getString("t_locale"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        devTinLocale.add(((List<Document>) (dev.get("t_in"))).get(i).getString("t_locale"));
                    }
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("p_title") != null) {
                if (dev.get("p_title") != null) {
                    softAssert.assertEquals(dev.get("p_title").toString(), prod.get("p_title").toString(), "Failed for p_title : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("p_title") != null, "Failed because Dev.p_title is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("networks") != null) {
                if (dev.get("networks") != null) {
                    ArrayList<String> prodNetworkName = new ArrayList<>();
                    ArrayList<String> devNetworkName = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("networks"))).size(); i++) {
                        prodNetworkName.add(((List<Document>) (prod.get("networks"))).get(i).getString("name"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("networks"))).size(); i++) {
                        devNetworkName.add(((List<Document>) (dev.get("networks"))).get(i).getString("name"));
                    }
                    softAssert.assertTrue(devNetworkName.containsAll(prodNetworkName), "Failed for Network.name : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("networks") != null, "Failed because Dev.networks is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("isSyndicated") != null) {
                if (dev.get("isSyndicated") != null) {
                    softAssert.assertEquals(dev.get("isSyndicated"), prod.get("isSyndicated"), "Failed for isSyndicated : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("isSyndicated") != null, "Failed because Dev.isSyndicated is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("isMini") != null) {
                if (dev.get("isMini") != null) {
                    softAssert.assertEquals(dev.get("isMini"), prod.get("isMini"), "Failed for isMini : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("isMini") != null, "Failed because Dev.isMini is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("spoken") != null) {
                if (dev.get("spoken") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("spoken"));
                    List<Document> devSpoken = (List<Document>) (dev.get("spoken"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for spoken : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("spoken") != null, "Failed because Dev.spoken is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("countries") != null) {
                if (dev.get("countries") != null) {
                    List<Document> prodCountries = (List<Document>) (prod.get("countries"));
                    List<Document> devCountries = (List<Document>) (dev.get("countries"));
                    softAssert.assertTrue(devCountries.containsAll(prodCountries), "Failed for countries : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("countries") != null, "Failed because Dev.countries is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_category") != null) {
                if (dev.get("d_category") != null) {
                    if(prod.get("d_category", Document.class).get("id") != null){
                        if(dev.get("d_category", Document.class).get("id") != null){
                            softAssert.assertEquals(dev.get("d_category", Document.class).get("id").toString(), prod.get("d_category", Document.class).get("id").toString(), "Failed for d_category.id : " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("d_category") != null, "Failed because Dev.d_category is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_genres") != null) {
                if (dev.get("d_genres") != null) {
                    ArrayList<Long> prodGenres = new ArrayList<>();
                    ArrayList<Long> devGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_genres"))).size(); i++) {
                        prodGenres.add(((List<Document>) (prod.get("d_genres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_genres"))).size(); i++) {
                        devGenres.add(((List<Document>) (dev.get("d_genres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devGenres.containsAll(prodGenres), "Failed for d_genres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_genres") != null, "Failed because Dev.d_genres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_subGenres") != null) {
                if (dev.get("d_subGenres") != null) {
                    ArrayList<Long> prodSubGenres = new ArrayList<>();
                    ArrayList<Long> devSubGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_subGenres"))).size(); i++) {
                        prodSubGenres.add(((List<Document>) (prod.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_subGenres"))).size(); i++) {
                        devSubGenres.add(((List<Document>) (dev.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devSubGenres.containsAll(prodSubGenres), "Failed for d_subGenres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_subGenres") != null, "Failed because Dev.d_subGenres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_moods") != null) {
                if (dev.get("dh_moods") != null) {
                    ArrayList<Long> prodDhMooods = new ArrayList<>();
                    ArrayList<Long> devDhMooods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_moods"))).size(); i++) {
                        prodDhMooods.add(((List<Document>) (prod.get("dh_moods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_moods"))).size(); i++) {
                        devDhMooods.add(((List<Document>) (dev.get("dh_moods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhMooods.containsAll(prodDhMooods), "Failed for dh_moods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_moods") != null, "Failed because Dev.dh_moods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_tones") != null) {
                if (dev.get("dh_tones") != null) {
                    ArrayList<Long> prodDhTones = new ArrayList<>();
                    ArrayList<Long> devDhTones = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_tones"))).size(); i++) {
                        prodDhTones.add(((List<Document>) (prod.get("dh_tones"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_tones"))).size(); i++) {
                        devDhTones.add(((List<Document>) (dev.get("dh_tones"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhTones.containsAll(prodDhTones), "Failed for dh_tones.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_tones") != null, "Failed because Dev.dh_tones is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_themes") != null) {
                if (dev.get("dh_themes") != null) {
                    ArrayList<Long> prodDhThemes = new ArrayList<>();
                    ArrayList<Long> devDhThemes = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_themes"))).size(); i++) {
                        prodDhThemes.add(((List<Document>) (prod.get("dh_themes"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_themes"))).size(); i++) {
                        devDhThemes.add(((List<Document>) (dev.get("dh_themes"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhThemes.containsAll(prodDhThemes), "Failed for dh_themes.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_themes") != null, "Failed because Dev.dh_themes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_keywords") != null) {
                if (dev.get("dh_keywords") != null) {
                    ArrayList<Long> prodDhKeywords = new ArrayList<>();
                    ArrayList<Long> devDhKeywords = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_keywords"))).size(); i++) {
                        prodDhKeywords.add(((List<Document>) (prod.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_keywords"))).size(); i++) {
                        devDhKeywords.add(((List<Document>) (dev.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhKeywords.containsAll(prodDhKeywords), "Failed for dh_keywords.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_keywords") != null, "Failed because Dev.dh_keywords is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_artisticStyles") != null) {
                if (dev.get("dh_artisticStyles") != null) {
                    ArrayList<Long> prodDhArtStyle = new ArrayList<>();
                    ArrayList<Long> devDhArtStyle = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_artisticStyles"))).size(); i++) {
                        prodDhArtStyle.add(((List<Document>) (prod.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_artisticStyles"))).size(); i++) {
                        devDhArtStyle.add(((List<Document>) (dev.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhArtStyle.containsAll(prodDhArtStyle), "Failed for dh_artisticStyles.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_artisticStyles") != null, "Failed because Dev.dh_artisticStyles is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_audiences") != null) {
                if (dev.get("dh_audiences") != null) {
                    ArrayList<Long> prodDhAudiences = new ArrayList<>();
                    ArrayList<Long> devDhAudiences = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_audiences"))).size(); i++) {
                        prodDhAudiences.add(((List<Document>) (prod.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_audiences"))).size(); i++) {
                        devDhAudiences.add(((List<Document>) (dev.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhAudiences.containsAll(prodDhAudiences), "Failed for dh_audiences.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_audiences") != null, "Failed because Dev.dh_audiences is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_periods") != null) {
                if (dev.get("dh_periods") != null) {
                    ArrayList<Long> prodDhPeriods = new ArrayList<>();
                    ArrayList<Long> devDhPeriods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_periods"))).size(); i++) {
                        prodDhPeriods.add(((List<Document>) (prod.get("dh_periods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_periods"))).size(); i++) {
                        devDhPeriods.add(((List<Document>) (dev.get("dh_periods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhPeriods.containsAll(prodDhPeriods), "Failed for dh_periods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_periods") != null, "Failed because Dev.dh_periods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_characters") != null) {
                if (dev.get("dh_characters") != null) {
                    ArrayList<Long> prodDhCharacters = new ArrayList<>();
                    ArrayList<Long> devDhCharacters = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_characters"))).size(); i++) {
                        prodDhCharacters.add(((List<Document>) (prod.get("dh_characters"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_characters"))).size(); i++) {
                        devDhCharacters.add(((List<Document>) (dev.get("dh_characters"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhCharacters.containsAll(prodDhCharacters), "Failed for dh_characters.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_characters") != null, "Failed because Dev.dh_characters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_ratingFlags") != null) {
                if (dev.get("dh_ratingFlags") != null) {
                    ArrayList<Long> prodDhRatingFlag = new ArrayList<>();
                    ArrayList<Long> devDhRatingFlag = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_ratingFlags"))).size(); i++) {
                        prodDhRatingFlag.add(((List<Document>) (prod.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_ratingFlags"))).size(); i++) {
                        devDhRatingFlag.add(((List<Document>) (dev.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhRatingFlag.containsAll(prodDhRatingFlag), "Failed for dh_ratingFlags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_ratingFlags") != null, "Failed because Dev.dh_ratingFlags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_flags") != null) {
                if (dev.get("dh_flags") != null) {
                    ArrayList<Long> prodDhFlags = new ArrayList<>();
                    ArrayList<Long> devDhFlags = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_flags"))).size(); i++) {
                        prodDhFlags.add(((List<Document>) (prod.get("dh_flags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_flags"))).size(); i++) {
                        devDhFlags.add(((List<Document>) (dev.get("dh_flags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhFlags.containsAll(prodDhFlags), "Failed for dh_flags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_flags") != null, "Failed because Dev.dh_flags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_facebooks") != null) {
                if (dev.get("h_facebooks") != null) {
                    ArrayList<Long> prodFacebookId = new ArrayList<>();
                    ArrayList<Long> devFacebookId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("h_facebooks"))).size(); i++) {
                        prodFacebookId.add(((List<Document>) (prod.get("h_facebooks"))).get(i).getLong("facebookId"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("h_facebooks"))).size(); i++) {
                        devFacebookId.add(((List<Document>) (dev.get("h_facebooks"))).get(i).getLong("facebookId"));
                    }
                    softAssert.assertTrue(devFacebookId.containsAll(prodFacebookId), "Failed for h_facebooks : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_facebooks") != null, "Failed because Dev.h_facebooks is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_twitters") != null) {
                if (dev.get("h_twitters") != null) {
                    List<Document> prodList = (List<Document>) prod.get("h_twitters");
                    List<Document> dList = (List<Document>) dev.get("h_twitters");
                    softAssert.assertTrue(dList.containsAll(prodList), "Failed for h_twitters : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_twitters") != null, "Failed because Dev.h_twitters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<Long> prodSynonymsId = new ArrayList<>();
                    ArrayList<Long> devSynonymsId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                        prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                        devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
            System.out.println(dev.get("_id").toString());
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void episodeTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }

            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_aired") != null) {
                if (dev.get("r_episode_aired") != null) {
                    softAssert.assertEquals(dev.get("r_episode_aired").toString(), prod.get("r_episode_aired").toString(), "Failed for r_episode_aired : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_aired") != null, "Failed because Dev.r_episode_aired is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_credits") != null) {
                if (dev.get("r_episode_credits") != null) {
                    softAssert.assertEquals(dev.get("r_episode_credits").toString(), prod.get("r_episode_credits").toString(), "Failed for r_episode_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_credits") != null, "Failed because Dev.r_episode_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_ratings") != null) {
                if (dev.get("r_episode_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_episode_ratings").toString(), prod.get("r_episode_ratings").toString(), "Failed for r_episode_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_ratings") != null, "Failed because Dev.r_episode_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_releases") != null) {
                if (dev.get("r_episode_releases") != null) {
                    softAssert.assertEquals(dev.get("r_episode_releases").toString(), prod.get("r_episode_releases").toString(), "Failed for r_episode_releases : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_releases") != null, "Failed because Dev.r_episode_releases is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_synopses") != null) {
                if (dev.get("r_episode_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_episode_synopses").toString(), prod.get("r_episode_synopses").toString(), "Failed for r_episode_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_synopses") != null, "Failed because Dev.r_episode_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_market") != null) {
                if (dev.get("f_market") != null) {
                    List<Document> prodFmarket = (List<Document>) (prod.get("f_market"));
                    List<Document> devFmarket = (List<Document>) (dev.get("f_market"));
                    softAssert.assertTrue(devFmarket.containsAll(prodFmarket), "Failed for f_market : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_market") != null, "Failed because Dev.f_market is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        prodTinLocale.add(((List<Document>) (prod.get("t_in"))).get(i).getString("t_locale"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        devTinLocale.add(((List<Document>) (dev.get("t_in"))).get(i).getString("t_locale"));
                    }
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("series") != null) {
                if (dev.get("series") != null) {
                    if(prod.get("series",Document.class).get("id") != null) {
                        if (dev.get("series", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("series", Document.class).get("id").toString(), prod.get("series", Document.class).get("id").toString(), "Failed for series.id : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("series",Document.class).get("id") != null, "Failed because Dev.series.id is null " + dev.get("_id").toString());
                        }
                    }else {
                        softAssert.assertTrue(prod.get("series",Document.class).get("id") != null, "Failed because Prod.series.id is null " + prod.get("_id").toString());
                    }
                } else {
                    softAssert.assertTrue(dev.get("series") != null, "Failed because Dev.series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("withinSeries") != null) {
                if (dev.get("withinSeries") != null) {
                    softAssert.assertEquals(dev.get("withinSeries"), prod.get("withinSeries"), "Failed for withinSeries : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("withinSeries") != null, "Failed because Dev.withinSeries is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("season") != null) {
                if (dev.get("season") != null) {
                    if(prod.get("season",Document.class).get("id") != null) {
                        if (dev.get("season", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("season", Document.class).get("id").toString(), prod.get("season", Document.class).get("id").toString(), "Failed for season.id : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("season",Document.class).get("id") != null, "Failed because Dev.season.id is null " + dev.get("_id").toString());
                        }
                    }else {
                        softAssert.assertTrue(prod.get("season",Document.class).get("id") != null, "Failed because Dev.season.id is null " + dev.get("_id").toString());
                    }
                    if(prod.get("season",Document.class).get("number") != null){
                        if(dev.get("season",Document.class).get("number") != null){
                            softAssert.assertEquals(dev.get("season", Document.class).get("number").toString(), prod.get("season", Document.class).get("number").toString(), "Failed for season.number : " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("season",Document.class).get("episodes") != null){
                        if(dev.get("season",Document.class).get("episodes") != null){
                            softAssert.assertEquals(dev.get("season", Document.class).get("episodes").toString(), prod.get("season", Document.class).get("episodes").toString(), "Failed for season.episodes : " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("season") != null, "Failed because Dev.season is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("withinSeason") != null) {
                if (dev.get("withinSeason") != null) {
                    softAssert.assertEquals(dev.get("withinSeason"), prod.get("withinSeason"), "Failed for withinSeason : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("withinSeason") != null, "Failed because Dev.withinSeason is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("releaseYear") != null) {
                if (dev.get("releaseYear") != null) {
                    softAssert.assertEquals(dev.get("releaseYear"), prod.get("releaseYear"), "Failed for releaseYear : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("releaseYear") != null, "Failed because Dev.releaseYear is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("duration") != null) {
                if (dev.get("duration") != null) {
                    softAssert.assertEquals(dev.get("duration"), prod.get("duration"), "Failed for duration : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("duration") != null, "Failed because Dev.duration is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("spoken") != null) {
                if (dev.get("spoken") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("spoken"));
                    List<Document> devSpoken = (List<Document>) (dev.get("spoken"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for spoken : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("spoken") != null, "Failed because Dev.spoken is null " + dev.get("_id").toString());
                }
            }

            if (prod.get("countries") != null) {
                if (dev.get("countries") != null) {
                    List<Document> prodCountries = (List<Document>) (prod.get("countries"));
                    List<Document> devCountries = (List<Document>) (dev.get("countries"));
                    softAssert.assertTrue(devCountries.containsAll(prodCountries), "Failed for countries : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("countries") != null, "Failed because Dev.countries is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_category") != null) {
                if (dev.get("d_category") != null) {
                    if(prod.get("d_category", Document.class).get("id") != null) {
                        if (dev.get("d_category", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("d_category", Document.class).get("id").toString(), prod.get("d_category", Document.class).get("id").toString(), "Failed for d_category.id : " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("d_category") != null, "Failed because Dev.d_category is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_genres") != null) {
                if (dev.get("d_genres") != null) {
                    ArrayList<Long> prodGenres = new ArrayList<>();
                    ArrayList<Long> devGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_genres"))).size(); i++) {
                        prodGenres.add(((List<Document>) (prod.get("d_genres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_genres"))).size(); i++) {
                        devGenres.add(((List<Document>) (dev.get("d_genres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devGenres.containsAll(prodGenres), "Failed for d_genres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_genres") != null, "Failed because Dev.d_genres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_subGenres") != null) {
                if (dev.get("d_subGenres") != null) {
                    ArrayList<Long> prodSubGenres = new ArrayList<>();
                    ArrayList<Long> devSubGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_subGenres"))).size(); i++) {
                        prodSubGenres.add(((List<Document>) (prod.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_subGenres"))).size(); i++) {
                        devSubGenres.add(((List<Document>) (dev.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devSubGenres.containsAll(prodSubGenres), "Failed for d_subGenres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_subGenres") != null, "Failed because Dev.d_subGenres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_moods") != null) {
                if (dev.get("dh_moods") != null) {
                    ArrayList<Long> prodDhMooods = new ArrayList<>();
                    ArrayList<Long> devDhMooods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_moods"))).size(); i++) {
                        prodDhMooods.add(((List<Document>) (prod.get("dh_moods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_moods"))).size(); i++) {
                        devDhMooods.add(((List<Document>) (dev.get("dh_moods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhMooods.containsAll(prodDhMooods), "Failed for dh_moods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_moods") != null, "Failed because Dev.dh_moods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_tones") != null) {
                if (dev.get("dh_tones") != null) {
                    ArrayList<Long> prodDhTones = new ArrayList<>();
                    ArrayList<Long> devDhTones = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_tones"))).size(); i++) {
                        prodDhTones.add(((List<Document>) (prod.get("dh_tones"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_tones"))).size(); i++) {
                        devDhTones.add(((List<Document>) (dev.get("dh_tones"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhTones.containsAll(prodDhTones), "Failed for dh_tones.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_tones") != null, "Failed because Dev.dh_tones is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_themes") != null) {
                if (dev.get("dh_themes") != null) {
                    ArrayList<Long> prodDhThemes = new ArrayList<>();
                    ArrayList<Long> devDhThemes = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_themes"))).size(); i++) {
                        prodDhThemes.add(((List<Document>) (prod.get("dh_themes"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_themes"))).size(); i++) {
                        devDhThemes.add(((List<Document>) (dev.get("dh_themes"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhThemes.containsAll(prodDhThemes), "Failed for dh_themes.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_themes") != null, "Failed because Dev.dh_themes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_keywords") != null) {
                if (dev.get("dh_keywords") != null) {
                    ArrayList<Long> prodDhKeywords = new ArrayList<>();
                    ArrayList<Long> devDhKeywords = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_keywords"))).size(); i++) {
                        prodDhKeywords.add(((List<Document>) (prod.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_keywords"))).size(); i++) {
                        devDhKeywords.add(((List<Document>) (dev.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhKeywords.containsAll(prodDhKeywords), "Failed for dh_keywords.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_keywords") != null, "Failed because Dev.dh_keywords is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_artisticStyles") != null) {
                if (dev.get("dh_artisticStyles") != null) {
                    ArrayList<Long> prodDhArtStyle = new ArrayList<>();
                    ArrayList<Long> devDhArtStyle = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_artisticStyles"))).size(); i++) {
                        prodDhArtStyle.add(((List<Document>) (prod.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_artisticStyles"))).size(); i++) {
                        devDhArtStyle.add(((List<Document>) (dev.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhArtStyle.containsAll(prodDhArtStyle), "Failed for dh_artisticStyles.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_artisticStyles") != null, "Failed because Dev.dh_artisticStyles is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_audiences") != null) {
                if (dev.get("dh_audiences") != null) {
                    ArrayList<Long> prodDhAudiences = new ArrayList<>();
                    ArrayList<Long> devDhAudiences = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_audiences"))).size(); i++) {
                        prodDhAudiences.add(((List<Document>) (prod.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_audiences"))).size(); i++) {
                        devDhAudiences.add(((List<Document>) (dev.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhAudiences.containsAll(prodDhAudiences), "Failed for dh_audiences.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_audiences") != null, "Failed because Dev.dh_audiences is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_periods") != null) {
                if (dev.get("dh_periods") != null) {
                    ArrayList<Long> prodDhPeriods = new ArrayList<>();
                    ArrayList<Long> devDhPeriods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_periods"))).size(); i++) {
                        prodDhPeriods.add(((List<Document>) (prod.get("dh_periods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_periods"))).size(); i++) {
                        devDhPeriods.add(((List<Document>) (dev.get("dh_periods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhPeriods.containsAll(prodDhPeriods), "Failed for dh_periods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_periods") != null, "Failed because Dev.dh_periods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_characters") != null) {
                if (dev.get("dh_characters") != null) {
                    ArrayList<Long> prodDhCharacters = new ArrayList<>();
                    ArrayList<Long> devDhCharacters = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_characters"))).size(); i++) {
                        prodDhCharacters.add(((List<Document>) (prod.get("dh_characters"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_characters"))).size(); i++) {
                        devDhCharacters.add(((List<Document>) (dev.get("dh_characters"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhCharacters.containsAll(prodDhCharacters), "Failed for dh_characters.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_characters") != null, "Failed because Dev.dh_characters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_ratingFlags") != null) {
                if (dev.get("dh_ratingFlags") != null) {
                    ArrayList<Long> prodDhRatingFlag = new ArrayList<>();
                    ArrayList<Long> devDhRatingFlag = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_ratingFlags"))).size(); i++) {
                        prodDhRatingFlag.add(((List<Document>) (prod.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_ratingFlags"))).size(); i++) {
                        devDhRatingFlag.add(((List<Document>) (dev.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhRatingFlag.containsAll(prodDhRatingFlag), "Failed for dh_ratingFlags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_ratingFlags") != null, "Failed because Dev.dh_ratingFlags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_flags") != null) {
                if (dev.get("dh_flags") != null) {
                    ArrayList<Long> prodDhFlags = new ArrayList<>();
                    ArrayList<Long> devDhFlags = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_flags"))).size(); i++) {
                        prodDhFlags.add(((List<Document>) (prod.get("dh_flags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_flags"))).size(); i++) {
                        devDhFlags.add(((List<Document>) (dev.get("dh_flags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhFlags.containsAll(prodDhFlags), "Failed for dh_flags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_flags") != null, "Failed because Dev.dh_flags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("c_episode_credits") != null) {
                if (dev.get("c_episode_credits") != null) {
                    softAssert.assertEquals(dev.get("c_episode_credits"), prod.get("c_episode_credits"), "Failed for c_episode_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("c_episode_credits") != null, "Failed because Dev.c_episode_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("c_episode_ratings") != null) {
                if (dev.get("c_episode_ratings") != null) {
                    softAssert.assertEquals(dev.get("c_episode_ratings"), prod.get("c_episode_ratings"), "Failed for c_episode_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("c_episode_ratings") != null, "Failed because Dev.c_episode_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("c_episode_synopses") != null) {
                if (dev.get("c_episode_synopses") != null) {
                    softAssert.assertEquals(dev.get("c_episode_synopses"), prod.get("c_episode_synopses"), "Failed for c_episode_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("c_episode_synopses") != null, "Failed because Dev.c_episode_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<Long> prodSynonymsId = new ArrayList<>();
                    ArrayList<Long> devSynonymsId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                        prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                        devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }

    }

    public static void seasonTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season") != null) {
                if (dev.get("r_season") != null) {
                    softAssert.assertEquals(dev.get("r_season").toString(), prod.get("r_season").toString(), "Failed for r_season : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season") != null, "Failed because Dev.r_season is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season_episodes") != null) {
                if (dev.get("r_season_episodes") != null) {
                    softAssert.assertEquals(dev.get("r_season_episodes").toString(), prod.get("r_season_episodes").toString(), "Failed for r_season_episodes : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season_episodes") != null, "Failed because Dev.r_season_episodes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season_synopses") != null) {
                if (dev.get("r_season_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_season_synopses").toString(), prod.get("r_season_synopses").toString(), "Failed for r_season_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season_synopses") != null, "Failed because Dev.r_season_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_market") != null) {
                if (dev.get("f_market") != null) {
                    List<Document> prodFmarket = (List<Document>) (prod.get("f_market"));
                    List<Document> devFmarket = (List<Document>) (dev.get("f_market"));
                    softAssert.assertTrue(devFmarket.containsAll(prodFmarket), "Failed for f_market : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_market") != null, "Failed because Dev.f_market is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        prodTinLocale.add(((List<Document>) (prod.get("t_in"))).get(i).getString("t_locale"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        devTinLocale.add(((List<Document>) (dev.get("t_in"))).get(i).getString("t_locale"));
                    }
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("p_title") != null) {
                if (dev.get("p_title") != null) {
                    softAssert.assertEquals(dev.get("p_title").toString(), prod.get("p_title").toString(), "Failed for p_title : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("p_title") != null, "Failed because Dev.p_title is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("number") != null) {
                if (dev.get("number") != null) {
                    softAssert.assertEquals(dev.get("number").toString(), prod.get("number").toString(), "Failed for number : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("number") != null, "Failed because Dev.number is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("episodes") != null) {
                if (dev.get("episodes") != null) {
                    softAssert.assertEquals(dev.get("episodes").toString(), prod.get("episodes").toString(), "Failed for episodes : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("episodes") != null, "Failed because Dev.episodes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("series") != null) {
                if (dev.get("series") != null) {
                    if(prod.get("series",Document.class).get("id") != null) {
                        if (dev.get("series", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("series", Document.class).get("id").toString(), prod.get("series", Document.class).get("id").toString(), "Failed for series.id : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("series",Document.class).get("id") != null, "Failed because Dev.series.id is null " + dev.get("_id").toString());
                        }
                    }else {
                        softAssert.assertTrue(prod.get("series",Document.class).get("id") != null, "Failed because Prod.series.id is null " + prod.get("_id").toString());
                    }
                } else {
                    softAssert.assertTrue(dev.get("series") != null, "Failed because Dev.series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("network") != null) {
                if (dev.get("network") != null) {
                    softAssert.assertEquals(dev.get("network").toString(), prod.get("network").toString(), "Failed for network : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("network") != null, "Failed because Dev.network is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("start") != null) {
                if (dev.get("start") != null) {
                    softAssert.assertEquals(dev.get("start").toString(), prod.get("start").toString(), "Failed for start : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("start") != null, "Failed because Dev.start is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("end") != null) {
                if (dev.get("end") != null) {
                    softAssert.assertEquals(dev.get("end").toString(), prod.get("end").toString(), "Failed for end : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("end") != null, "Failed because Dev.end is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("spoken") != null) {
                if (dev.get("spoken") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("spoken"));
                    List<Document> devSpoken = (List<Document>) (dev.get("spoken"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for spoken : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("spoken") != null, "Failed because Dev.spoken is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("countries") != null) {
                if (dev.get("countries") != null) {
                    List<Document> prodCountries = (List<Document>) (prod.get("countries"));
                    List<Document> devCountries = (List<Document>) (dev.get("countries"));
                    softAssert.assertTrue(devCountries.containsAll(prodCountries), "Failed for countries : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("countries") != null, "Failed because Dev.countries is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_category") != null) {
                if (dev.get("d_category") != null) {
                    if(prod.get("d_category", Document.class).get("id") != null) {
                        if (dev.get("d_category", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("d_category", Document.class).get("id").toString(), prod.get("d_category", Document.class).get("id").toString(), "Failed for d_category.id : " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("d_category") != null, "Failed because Dev.d_category is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_genres") != null) {
                if (dev.get("d_genres") != null) {
                    ArrayList<Long> prodGenres = new ArrayList<>();
                    ArrayList<Long> devGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_genres"))).size(); i++) {
                        prodGenres.add(((List<Document>) (prod.get("d_genres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_genres"))).size(); i++) {
                        devGenres.add(((List<Document>) (dev.get("d_genres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devGenres.containsAll(prodGenres), "Failed for d_genres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_genres") != null, "Failed because Dev.d_genres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_subGenres") != null) {
                if (dev.get("d_subGenres") != null) {
                    ArrayList<Long> prodSubGenres = new ArrayList<>();
                    ArrayList<Long> devSubGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_subGenres"))).size(); i++) {
                        prodSubGenres.add(((List<Document>) (prod.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_subGenres"))).size(); i++) {
                        devSubGenres.add(((List<Document>) (dev.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devSubGenres.containsAll(prodSubGenres), "Failed for d_subGenres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_subGenres") != null, "Failed because Dev.d_subGenres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_moods") != null) {
                if (dev.get("dh_moods") != null) {
                    ArrayList<Long> prodDhMooods = new ArrayList<>();
                    ArrayList<Long> devDhMooods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_moods"))).size(); i++) {
                        prodDhMooods.add(((List<Document>) (prod.get("dh_moods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_moods"))).size(); i++) {
                        devDhMooods.add(((List<Document>) (dev.get("dh_moods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhMooods.containsAll(prodDhMooods), "Failed for dh_moods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_moods") != null, "Failed because Dev.dh_moods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_tones") != null) {
                if (dev.get("dh_tones") != null) {
                    ArrayList<Long> prodDhTones = new ArrayList<>();
                    ArrayList<Long> devDhTones = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_tones"))).size(); i++) {
                        prodDhTones.add(((List<Document>) (prod.get("dh_tones"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_tones"))).size(); i++) {
                        devDhTones.add(((List<Document>) (dev.get("dh_tones"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhTones.containsAll(prodDhTones), "Failed for dh_tones.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_tones") != null, "Failed because Dev.dh_tones is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_themes") != null) {
                if (dev.get("dh_themes") != null) {
                    ArrayList<Long> prodDhThemes = new ArrayList<>();
                    ArrayList<Long> devDhThemes = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_themes"))).size(); i++) {
                        prodDhThemes.add(((List<Document>) (prod.get("dh_themes"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_themes"))).size(); i++) {
                        devDhThemes.add(((List<Document>) (dev.get("dh_themes"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhThemes.containsAll(prodDhThemes), "Failed for dh_themes.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_themes") != null, "Failed because Dev.dh_themes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_keywords") != null) {
                if (dev.get("dh_keywords") != null) {
                    ArrayList<Long> prodDhKeywords = new ArrayList<>();
                    ArrayList<Long> devDhKeywords = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_keywords"))).size(); i++) {
                        prodDhKeywords.add(((List<Document>) (prod.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_keywords"))).size(); i++) {
                        devDhKeywords.add(((List<Document>) (dev.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhKeywords.containsAll(prodDhKeywords), "Failed for dh_keywords.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_keywords") != null, "Failed because Dev.dh_keywords is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_artisticStyles") != null) {
                if (dev.get("dh_artisticStyles") != null) {
                    ArrayList<Long> prodDhArtStyle = new ArrayList<>();
                    ArrayList<Long> devDhArtStyle = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_artisticStyles"))).size(); i++) {
                        prodDhArtStyle.add(((List<Document>) (prod.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_artisticStyles"))).size(); i++) {
                        devDhArtStyle.add(((List<Document>) (dev.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhArtStyle.containsAll(prodDhArtStyle), "Failed for dh_artisticStyles.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_artisticStyles") != null, "Failed because Dev.dh_artisticStyles is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_audiences") != null) {
                if (dev.get("dh_audiences") != null) {
                    ArrayList<Long> prodDhAudiences = new ArrayList<>();
                    ArrayList<Long> devDhAudiences = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_audiences"))).size(); i++) {
                        prodDhAudiences.add(((List<Document>) (prod.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_audiences"))).size(); i++) {
                        devDhAudiences.add(((List<Document>) (dev.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhAudiences.containsAll(prodDhAudiences), "Failed for dh_audiences.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_audiences") != null, "Failed because Dev.dh_audiences is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_periods") != null) {
                if (dev.get("dh_periods") != null) {
                    ArrayList<Long> prodDhPeriods = new ArrayList<>();
                    ArrayList<Long> devDhPeriods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_periods"))).size(); i++) {
                        prodDhPeriods.add(((List<Document>) (prod.get("dh_periods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_periods"))).size(); i++) {
                        devDhPeriods.add(((List<Document>) (dev.get("dh_periods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhPeriods.containsAll(prodDhPeriods), "Failed for dh_periods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_periods") != null, "Failed because Dev.dh_periods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_characters") != null) {
                if (dev.get("dh_characters") != null) {
                    ArrayList<Long> prodDhCharacters = new ArrayList<>();
                    ArrayList<Long> devDhCharacters = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_characters"))).size(); i++) {
                        prodDhCharacters.add(((List<Document>) (prod.get("dh_characters"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_characters"))).size(); i++) {
                        devDhCharacters.add(((List<Document>) (dev.get("dh_characters"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhCharacters.containsAll(prodDhCharacters), "Failed for dh_characters.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_characters") != null, "Failed because Dev.dh_characters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_ratingFlags") != null) {
                if (dev.get("dh_ratingFlags") != null) {
                    ArrayList<Long> prodDhRatingFlag = new ArrayList<>();
                    ArrayList<Long> devDhRatingFlag = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_ratingFlags"))).size(); i++) {
                        prodDhRatingFlag.add(((List<Document>) (prod.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_ratingFlags"))).size(); i++) {
                        devDhRatingFlag.add(((List<Document>) (dev.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhRatingFlag.containsAll(prodDhRatingFlag), "Failed for dh_ratingFlags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_ratingFlags") != null, "Failed because Dev.dh_ratingFlags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<Long> prodSynonymsId = new ArrayList<>();
                    ArrayList<Long> devSynonymsId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                        prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                        devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void otherTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other") != null) {
                if (dev.get("r_other") != null) {
                    softAssert.assertEquals(dev.get("r_other").toString(), prod.get("r_other").toString(), "Failed for r_other : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other") != null, "Failed because Dev.r_other is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_aired") != null) {
                if (dev.get("r_other_aired") != null) {
                    softAssert.assertEquals(dev.get("r_other_aired").toString(), prod.get("r_other_aired").toString(), "Failed for r_other_aired : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_aired") != null, "Failed because Dev.r_other_aired is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_awards") != null) {
                if (dev.get("r_other_awards") != null) {
                    softAssert.assertEquals(dev.get("r_other_awards").toString(), prod.get("r_other_awards").toString(), "Failed for r_other_awards : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_awards") != null, "Failed because Dev.r_other_awards is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_credits") != null) {
                if (dev.get("r_other_credits") != null) {
                    softAssert.assertEquals(dev.get("r_other_credits").toString(), prod.get("r_other_credits").toString(), "Failed for r_other_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_credits") != null, "Failed because Dev.r_other_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_ratings") != null) {
                if (dev.get("r_other_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_other_ratings").toString(), prod.get("r_other_ratings").toString(), "Failed for r_other_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_ratings") != null, "Failed because Dev.r_other_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_related") != null) {
                if (dev.get("r_other_related") != null) {
                    softAssert.assertEquals(dev.get("r_other_related").toString(), prod.get("r_other_related").toString(), "Failed for r_other_related : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_related") != null, "Failed because Dev.r_other_related is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_releases") != null) {
                if (dev.get("r_other_releases") != null) {
                    softAssert.assertEquals(dev.get("r_other_releases").toString(), prod.get("r_other_releases").toString(), "Failed for r_other_releases : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_releases") != null, "Failed because Dev.r_other_releases is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_synopses") != null) {
                if (dev.get("r_other_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_other_synopses").toString(), prod.get("r_other_synopses").toString(), "Failed for r_other_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_synopses") != null, "Failed because Dev.r_other_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_theatricals") != null) {
                if (dev.get("r_other_theatricals") != null) {
                    softAssert.assertEquals(dev.get("r_other_theatricals").toString(), prod.get("r_other_theatricals").toString(), "Failed for r_other_theatricals : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_theatricals") != null, "Failed because Dev.r_other_theatricals is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_market") != null) {
                if (dev.get("f_market") != null) {
                    List<Document> prodFmarket = (List<Document>) (prod.get("f_market"));
                    List<Document> devFmarket = (List<Document>) (dev.get("f_market"));
                    softAssert.assertTrue(devFmarket.containsAll(prodFmarket), "Failed for f_market : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_market") != null, "Failed because Dev.f_market is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        prodTinLocale.add(((List<Document>) (prod.get("t_in"))).get(i).getString("t_locale"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        devTinLocale.add(((List<Document>) (dev.get("t_in"))).get(i).getString("t_locale"));
                    }
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("p_title") != null) {
                if (dev.get("p_title") != null) {
                    softAssert.assertEquals(dev.get("p_title").toString(), prod.get("p_title").toString(), "Failed for p_title : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("p_title") != null, "Failed because Dev.p_title is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("year") != null) {
                if (dev.get("year") != null) {
                    softAssert.assertEquals(dev.get("year").toString(), prod.get("year").toString(), "Failed for year : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("year") != null, "Failed because Dev.year is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("runtime") != null) {
                if (dev.get("runtime") != null) {
                    softAssert.assertEquals(dev.get("runtime").toString(), prod.get("runtime").toString(), "Failed for runtime : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("runtime") != null, "Failed because Dev.runtime is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("spoken") != null) {
                if (dev.get("spoken") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("spoken"));
                    List<Document> devSpoken = (List<Document>) (dev.get("spoken"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for spoken : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("spoken") != null, "Failed because Dev.spoken is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("countries") != null) {
                if (dev.get("countries") != null) {
                    List<Document> prodCountries = (List<Document>) (prod.get("countries"));
                    List<Document> devCountries = (List<Document>) (dev.get("countries"));
                    softAssert.assertTrue(devCountries.containsAll(prodCountries), "Failed for countries : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("countries") != null, "Failed because Dev.countries is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_category") != null) {
                if (dev.get("d_category") != null) {
                    if(prod.get("d_category", Document.class).get("id") != null) {
                        if (dev.get("d_category", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("d_category", Document.class).get("id").toString(), prod.get("d_category", Document.class).get("id").toString(), "Failed for d_category.id : " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("d_category") != null, "Failed because Dev.d_category is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_genres") != null) {
                if (dev.get("d_genres") != null) {
                    ArrayList<Long> prodGenres = new ArrayList<>();
                    ArrayList<Long> devGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_genres"))).size(); i++) {
                        prodGenres.add(((List<Document>) (prod.get("d_genres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_genres"))).size(); i++) {
                        devGenres.add(((List<Document>) (dev.get("d_genres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devGenres.containsAll(prodGenres), "Failed for d_genres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_genres") != null, "Failed because Dev.d_genres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_subGenres") != null) {
                if (dev.get("d_subGenres") != null) {
                    ArrayList<Long> prodSubGenres = new ArrayList<>();
                    ArrayList<Long> devSubGenres = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("d_subGenres"))).size(); i++) {
                        prodSubGenres.add(((List<Document>) (prod.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("d_subGenres"))).size(); i++) {
                        devSubGenres.add(((List<Document>) (dev.get("d_subGenres"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devSubGenres.containsAll(prodSubGenres), "Failed for d_subGenres.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_subGenres") != null, "Failed because Dev.d_subGenres is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_moods") != null) {
                if (dev.get("dh_moods") != null) {
                    ArrayList<Long> prodDhMooods = new ArrayList<>();
                    ArrayList<Long> devDhMooods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_moods"))).size(); i++) {
                        prodDhMooods.add(((List<Document>) (prod.get("dh_moods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_moods"))).size(); i++) {
                        devDhMooods.add(((List<Document>) (dev.get("dh_moods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhMooods.containsAll(prodDhMooods), "Failed for dh_moods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_moods") != null, "Failed because Dev.dh_moods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_tones") != null) {
                if (dev.get("dh_tones") != null) {
                    ArrayList<Long> prodDhTones = new ArrayList<>();
                    ArrayList<Long> devDhTones = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_tones"))).size(); i++) {
                        prodDhTones.add(((List<Document>) (prod.get("dh_tones"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_tones"))).size(); i++) {
                        devDhTones.add(((List<Document>) (dev.get("dh_tones"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhTones.containsAll(prodDhTones), "Failed for dh_tones.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_tones") != null, "Failed because Dev.dh_tones is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_themes") != null) {
                if (dev.get("dh_themes") != null) {
                    ArrayList<Long> prodDhThemes = new ArrayList<>();
                    ArrayList<Long> devDhThemes = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_themes"))).size(); i++) {
                        prodDhThemes.add(((List<Document>) (prod.get("dh_themes"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_themes"))).size(); i++) {
                        devDhThemes.add(((List<Document>) (dev.get("dh_themes"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhThemes.containsAll(prodDhThemes), "Failed for dh_themes.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_themes") != null, "Failed because Dev.dh_themes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_keywords") != null) {
                if (dev.get("dh_keywords") != null) {
                    ArrayList<Long> prodDhKeywords = new ArrayList<>();
                    ArrayList<Long> devDhKeywords = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_keywords"))).size(); i++) {
                        prodDhKeywords.add(((List<Document>) (prod.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_keywords"))).size(); i++) {
                        devDhKeywords.add(((List<Document>) (dev.get("dh_keywords"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhKeywords.containsAll(prodDhKeywords), "Failed for dh_keywords.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_keywords") != null, "Failed because Dev.dh_keywords is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_artisticStyles") != null) {
                if (dev.get("dh_artisticStyles") != null) {
                    ArrayList<Long> prodDhArtStyle = new ArrayList<>();
                    ArrayList<Long> devDhArtStyle = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_artisticStyles"))).size(); i++) {
                        prodDhArtStyle.add(((List<Document>) (prod.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_artisticStyles"))).size(); i++) {
                        devDhArtStyle.add(((List<Document>) (dev.get("dh_artisticStyles"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhArtStyle.containsAll(prodDhArtStyle), "Failed for dh_artisticStyles.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_artisticStyles") != null, "Failed because Dev.dh_artisticStyles is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_audiences") != null) {
                if (dev.get("dh_audiences") != null) {
                    ArrayList<Long> prodDhAudiences = new ArrayList<>();
                    ArrayList<Long> devDhAudiences = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_audiences"))).size(); i++) {
                        prodDhAudiences.add(((List<Document>) (prod.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_audiences"))).size(); i++) {
                        devDhAudiences.add(((List<Document>) (dev.get("dh_audiences"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhAudiences.containsAll(prodDhAudiences), "Failed for dh_audiences.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_audiences") != null, "Failed because Dev.dh_audiences is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_periods") != null) {
                if (dev.get("dh_periods") != null) {
                    ArrayList<Long> prodDhPeriods = new ArrayList<>();
                    ArrayList<Long> devDhPeriods = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_periods"))).size(); i++) {
                        prodDhPeriods.add(((List<Document>) (prod.get("dh_periods"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_periods"))).size(); i++) {
                        devDhPeriods.add(((List<Document>) (dev.get("dh_periods"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhPeriods.containsAll(prodDhPeriods), "Failed for dh_periods.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_periods") != null, "Failed because Dev.dh_periods is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_characters") != null) {
                if (dev.get("dh_characters") != null) {
                    ArrayList<Long> prodDhCharacters = new ArrayList<>();
                    ArrayList<Long> devDhCharacters = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_characters"))).size(); i++) {
                        prodDhCharacters.add(((List<Document>) (prod.get("dh_characters"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_characters"))).size(); i++) {
                        devDhCharacters.add(((List<Document>) (dev.get("dh_characters"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhCharacters.containsAll(prodDhCharacters), "Failed for dh_characters.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_characters") != null, "Failed because Dev.dh_characters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_ratingFlags") != null) {
                if (dev.get("dh_ratingFlags") != null) {
                    ArrayList<Long> prodDhRatingFlag = new ArrayList<>();
                    ArrayList<Long> devDhRatingFlag = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_ratingFlags"))).size(); i++) {
                        prodDhRatingFlag.add(((List<Document>) (prod.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_ratingFlags"))).size(); i++) {
                        devDhRatingFlag.add(((List<Document>) (dev.get("dh_ratingFlags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhRatingFlag.containsAll(prodDhRatingFlag), "Failed for dh_ratingFlags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_ratingFlags") != null, "Failed because Dev.dh_ratingFlags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("dh_flags") != null) {
                if (dev.get("dh_flags") != null) {
                    ArrayList<Long> prodDhFlags = new ArrayList<>();
                    ArrayList<Long> devDhFlags = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("dh_flags"))).size(); i++) {
                        prodDhFlags.add(((List<Document>) (prod.get("dh_flags"))).get(i).getLong("id"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("dh_flags"))).size(); i++) {
                        devDhFlags.add(((List<Document>) (dev.get("dh_flags"))).get(i).getLong("id"));
                    }
                    softAssert.assertTrue(devDhFlags.containsAll(prodDhFlags), "Failed for dh_flags.id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("dh_flags") != null, "Failed because Dev.dh_flags is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_rottenTomatoes") != null) {
                if (dev.get("h_rottenTomatoes") != null) {
                    if(prod.get("h_rottenTomatoes", Document.class).get("critic") != null) {
                        if (dev.get("h_rottenTomatoes", Document.class).get("critic") != null) {
                            if(prod.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("count") != null) {
                                softAssert.assertEquals(dev.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("count").toString(), prod.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("count").toString(), "Failed for h_rottenTomatoes.critic.count : " + dev.get("_id").toString());
                            }
                            if(prod.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("uri") != null) {
                                softAssert.assertEquals(dev.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("uri").toString(), prod.get("h_rottenTomatoes", Document.class).get("critic",Document.class).get("uri").toString(), "Failed for h_rottenTomatoes.critic.uri : " + dev.get("_id").toString());
                            }
                        }
                    }
                    if(prod.get("h_rottenTomatoes", Document.class).get("fan") != null) {
                        if (dev.get("h_rottenTomatoes", Document.class).get("fan") != null) {
                            if(prod.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("count") != null) {
                                softAssert.assertEquals(dev.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("count").toString(), prod.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("count").toString(), "Failed for h_rottenTomatoes.fan.count : " + dev.get("_id").toString());
                            }
                            if(prod.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("uri") != null) {
                                softAssert.assertEquals(dev.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("uri").toString(), prod.get("h_rottenTomatoes", Document.class).get("fan",Document.class).get("uri").toString(), "Failed for h_rottenTomatoes.fan.uri : " + dev.get("_id").toString());
                            }
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("h_rottenTomatoes") != null, "Failed because Dev.h_rottenTomatoes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_facebooks") != null) {
                if (dev.get("h_facebooks") != null) {
                    ArrayList<Long> prodFacebookId = new ArrayList<>();
                    ArrayList<Long> devFacebookId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("h_facebooks"))).size(); i++) {
                        prodFacebookId.add(((List<Document>) (prod.get("h_facebooks"))).get(i).getLong("facebookId"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("h_facebooks"))).size(); i++) {
                        devFacebookId.add(((List<Document>) (dev.get("h_facebooks"))).get(i).getLong("facebookId"));
                    }
                    softAssert.assertTrue(devFacebookId.containsAll(prodFacebookId), "Failed for h_facebooks : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_facebooks") != null, "Failed because Dev.h_facebooks is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_twitters") != null) {
                if (dev.get("h_twitters") != null) {
                    List<Document> prodList = (List<Document>) prod.get("h_twitters");
                    List<Document> dList = (List<Document>) dev.get("h_twitters");
                    softAssert.assertTrue(dList.containsAll(prodList), "Failed for h_twitters : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_twitters") != null, "Failed because Dev.h_twitters is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<Long> prodSynonymsId = new ArrayList<>();
                    ArrayList<Long> devSynonymsId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                        prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                        devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("rv20"));
                    }
                    softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void dvbTripletsTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("country") != null) {
                if (dev.get("country") != null) {
                    softAssert.assertEquals(dev.get("country").toString(), prod.get("country").toString(), "Failed for country : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("country") != null, "Failed because Dev.country is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_postalCode") != null) {
                if (dev.get("h_postalCode") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("h_postalCode"));
                    List<Document> devSpoken = (List<Document>) (dev.get("h_postalCode"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for h_postalCode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_postalCode") != null, "Failed because Dev.h_postalCode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("triplets") != null) {
                if (dev.get("triplets") != null) {
                    ArrayList<Long> prodFacebookId = new ArrayList<>();
                    ArrayList<Long> devFacebookId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("triplets"))).size(); i++) {
                        if(((List<Document>) (prod.get("triplets"))).get(i).get("sourceId") != null) {
                            prodFacebookId.add(((List<Document>) (prod.get("triplets"))).get(i).getLong("sourceId"));
                        }
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("triplets"))).size(); i++) {
                        if(((List<Document>) (dev.get("triplets"))).get(i).get("sourceId") != null) {
                            devFacebookId.add(((List<Document>) (dev.get("triplets"))).get(i).getLong("sourceId"));
                        }
                    }
                    softAssert.assertTrue(devFacebookId.containsAll(prodFacebookId), "Failed for triplets.sourceId : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("triplets") != null, "Failed because Dev.triplets is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        }else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void serviceTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_service") != null) {
                if (dev.get("r_service") != null) {
                    softAssert.assertEquals(dev.get("r_service").toString(), prod.get("r_service").toString(), "Failed for r_service : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_service") != null, "Failed because Dev.r_service is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_service_channels") != null) {
                if (dev.get("r_service_channels") != null) {
                    softAssert.assertEquals(dev.get("r_service_channels").toString(), prod.get("r_service_channels").toString(), "Failed for r_service_channels : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_service_channels") != null, "Failed because Dev.r_service_channels is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_service_schedules") != null) {
                if (dev.get("r_service_schedules") != null) {
                    softAssert.assertEquals(dev.get("r_service_schedules").toString(), prod.get("r_service_schedules").toString(), "Failed for r_service_schedules : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_service_schedules") != null, "Failed because Dev.r_service_schedules is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("name") != null) {
                if (dev.get("name") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("name"));
                    List<Document> devSpoken = (List<Document>) (dev.get("name"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for name : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("name") != null, "Failed because Dev.name is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("bestName") != null) {
                if (dev.get("bestName") != null) {
                    softAssert.assertEquals(dev.get("bestName").toString(), prod.get("bestName").toString(), "Failed for bestName : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("bestName") != null, "Failed because Dev.bestName is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("city") != null) {
                if (dev.get("city") != null) {
                    softAssert.assertEquals(dev.get("city").toString(), prod.get("city").toString(), "Failed for city : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("city") != null, "Failed because Dev.city is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("state") != null) {
                if (dev.get("state") != null) {
                    softAssert.assertEquals(dev.get("state").toString(), prod.get("state").toString(), "Failed for state : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("state") != null, "Failed because Dev.state is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("system") != null) {
                if (dev.get("system") != null) {
                    softAssert.assertEquals(dev.get("system").toString(), prod.get("system").toString(), "Failed for system : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("system") != null, "Failed because Dev.system is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("h_postalCode") != null) {
                if (dev.get("h_postalCode") != null) {
                    List<Document> prodSpoken = (List<Document>) (prod.get("h_postalCode"));
                    List<Document> devSpoken = (List<Document>) (dev.get("h_postalCode"));
                    softAssert.assertTrue(devSpoken.containsAll(prodSpoken), "Failed for h_postalCode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("h_postalCode") != null, "Failed because Dev.h_postalCode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("country") != null) {
                if (dev.get("country") != null) {
                    softAssert.assertEquals(dev.get("country").toString(), prod.get("country").toString(), "Failed for country : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("country") != null, "Failed because Dev.country is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("serviceType") != null) {
                if (dev.get("serviceType") != null) {
                    softAssert.assertEquals(dev.get("serviceType").toString(), prod.get("serviceType").toString(), "Failed for serviceType : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("serviceType") != null, "Failed because Dev.serviceType is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("msoId") != null) {
                if (dev.get("msoId") != null) {
                    softAssert.assertEquals(dev.get("msoId").toString(), prod.get("msoId").toString(), "Failed for msoId : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("msoId") != null, "Failed because Dev.msoId is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("headendId") != null) {
                if (dev.get("headendId") != null) {
                    softAssert.assertEquals(dev.get("headendId").toString(), prod.get("headendId").toString(), "Failed for headendId : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("headendId") != null, "Failed because Dev.headendId is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("zone") != null){
                if(dev.get("zone") != null){
                    if(prod.get("zone",Document.class).get("tzName") != null){
                        if(dev.get("zone",Document.class).get("tzName") != null){
                            softAssert.assertEquals(dev.get("zone",Document.class).get("tzName").toString(),prod.get("zone",Document.class).get("tzName").toString(),"Failed for zone.tzName : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("zone",Document.class).get("tzName") != null, "Failed because Dev.zone.tzName is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("zone") != null, "Failed because Dev.zone is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_offering") != null) {
                if (dev.get("f_offering") != null) {
                    softAssert.assertEquals(dev.get("f_offering").toString(), prod.get("f_offering").toString(), "Failed for f_offering : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_offering") != null, "Failed because Dev.f_offering is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<Long> prodSynonymsId = new ArrayList<>();
                    ArrayList<Long> devSynonymsId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synonyms"))).size(); i++) {
                        if(((List<Document>) (prod.get("synonyms"))).get(i).getLong("service") != null) {
                            prodSynonymsId.add(((List<Document>) (prod.get("synonyms"))).get(i).getLong("service"));
                        }
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("synonyms"))).size(); i++) {
                        if(((List<Document>) (dev.get("synonyms"))).get(i).getLong("service") != null) {
                            devSynonymsId.add(((List<Document>) (dev.get("synonyms"))).get(i).getLong("service"));
                        }
                    }
                    softAssert.assertTrue(devSynonymsId.containsAll(prodSynonymsId), "Failed for synonyms : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        }else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void movieRelatedTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie_related") != null) {
                if (dev.get("r_movie_related") != null) {
                    softAssert.assertEquals(dev.get("r_movie_related").toString(), prod.get("r_movie_related").toString(), "Failed for r_movie_related : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie_related") != null, "Failed because Dev.r_movie_related is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie") != null) {
                if (dev.get("r_movie") != null) {
                    softAssert.assertEquals(dev.get("r_movie").toString(), prod.get("r_movie").toString(), "Failed for r_movie : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie") != null, "Failed because Dev.r_movie is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("related") != null) {
                ArrayList<String> prodContentId = new ArrayList<>();
                ArrayList<String> prodContentTLocale = new ArrayList<>();
                if (dev.get("related") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("related"))).size(); i++) {
                        String contentID = "";
                        String tLocale = "";
                        String title = "";
                        String finalString = "";
                        if(((List<Document>) (prod.get("related"))).get(i).get("content") != null){
                            if(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("id") != null){
                                contentID = ((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("id").toString();
                                prodContentId.add(contentID);
                                if(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in") != null){
                                    for(int j=0; j < ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).size(); j++){
                                        if(((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale") != null){
                                            tLocale = ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale").toString();
                                        }
                                        if(((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title") != null){
                                            title = ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title").toString();
                                        }
                                        finalString = StringUtils.trimToEmpty(tLocale + title);
                                        if(finalString.isEmpty()) {
                                            continue;
                                        }
                                        prodContentTLocale.add(finalString + contentID);
                                    }
                                }
                            }
                        }
                    }

                    ArrayList<String> devContentId = new ArrayList<>();
                    ArrayList<String> devContentTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("related"))).size(); i++) {
                        String contentID = "";
                        String tLocale = "";
                        String title = "";
                        String finalString = "";
                        if(((List<Document>) (dev.get("related"))).get(i).get("content") != null){
                            if(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("id") != null){
                                contentID = ((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("id").toString();
                                devContentId.add(contentID);
                                if(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in") != null){
                                    for(int j=0; j < ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).size(); j++){
                                        if(((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale") != null){
                                            tLocale = ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale").toString();
                                        }
                                        if(((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title") != null){
                                            title = ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title").toString();
                                        }
                                        finalString = StringUtils.trimToEmpty(tLocale + title);
                                        if(finalString.isEmpty()) {
                                            continue;
                                        }
                                        devContentTLocale.add(finalString + contentID);
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(prodContentTLocale);
                    Collections.sort(devContentTLocale);

                    if(devContentId.containsAll(prodContentId)){
                        int i = 0;
                        int j = 0;
                        for(;i < prodContentTLocale.size();){
                            int temp = j;
                            for(; j <= devContentTLocale.size();){
                                if(j< devContentTLocale.size() && prodContentTLocale.get(i).equalsIgnoreCase(devContentTLocale.get(temp))){
                                    j++;
                                    break;
                                }else {
                                    temp++;
                                    if(temp >= devContentTLocale.size()){
                                        softAssert.assertTrue(false, dev.get("_id").toString() + " Production Dev.synopses.length + t_locale + synopsis not present " + prodContentTLocale.get(i));
                                        break;
                                    }
                                }
                            }
                            i++;
                        }
                    }else{
                        softAssert.assertTrue(devContentId.containsAll(prodContentId), "Failed because Dev.related.content.id " + dev.get("_id").toString());
                    }
                } else {
                    softAssert.assertTrue(dev.get("related") != null, "Failed because Dev.related is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesAwardTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_awards") != null) {
                if (dev.get("r_series_awards") != null) {
                    softAssert.assertEquals(dev.get("r_series_awards").toString(), prod.get("r_series_awards").toString(), "Failed for r_series_awards : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_awards") != null, "Failed because Dev.r_series_awards is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("awards") != null) {
                ArrayList<Long> prodAwardId = new ArrayList<>();
                ArrayList<String> prodAwardAssociation = new ArrayList<>();
                ArrayList<String> prodAwardTLocale = new ArrayList<>();
                ArrayList<String> prodRecipientPersonId = new ArrayList<>();
                if (dev.get("awards") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("awards"))).size(); i++) {
                        if (((List<Document>) (prod.get("awards"))).get(i).get("id") != null) {
                            prodAwardId.add(((List<Document>) (prod.get("awards"))).get(i).getLong("id"));
                        }
                        if (((List<Document>) (prod.get("awards"))).get(i).get("association") != null) {
                            if (((List<Document>) (prod.get("awards"))).get(i).get("association", Document.class).get("name") != null) {
                                prodAwardAssociation.add(((List<Document>) (prod.get("awards"))).get(i).get("association", Document.class).getString("name"));
                            }
                        }
                        if (((List<Document>) (prod.get("awards"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    prodAwardTLocale.add(((List<Document>) (prod.get("awards"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (prod.get("awards"))).get(i).get("recipients") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("recipients"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("recipients"))).get(j).get("person") != null) {
                                    if (((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("recipients"))).get(j).get("person", Document.class).get("id") != null) {
                                        prodRecipientPersonId.add(((List<Document>) (prod.get("awards"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (prod.get("awards"))).get(i).get("recipients"))).get(j).get("person", Document.class).get("id").toString());
                                    }
                                }
                            }
                        }
                    }

                    ArrayList<Long> devAwardId = new ArrayList<>();
                    ArrayList<String> devAwardAssociation = new ArrayList<>();
                    ArrayList<String> devAwardTLocale = new ArrayList<>();
                    ArrayList<String> devRecipientPersonId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("awards"))).size(); i++) {
                        if (((List<Document>) (dev.get("awards"))).get(i).get("id") != null) {
                            devAwardId.add(((List<Document>) (dev.get("awards"))).get(i).getLong("id"));
                        }
                        if (((List<Document>) (dev.get("awards"))).get(i).get("association") != null) {
                            if (((List<Document>) (dev.get("awards"))).get(i).get("association", Document.class).get("name") != null) {
                                devAwardAssociation.add(((List<Document>) (dev.get("awards"))).get(i).get("association", Document.class).getString("name"));
                            }
                        }
                        if (((List<Document>) (dev.get("awards"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    devAwardTLocale.add(((List<Document>) (dev.get("awards"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (dev.get("awards"))).get(i).get("recipients") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("recipients"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("recipients"))).get(j).get("person") != null) {
                                    if (((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("recipients"))).get(j).get("person", Document.class).get("id") != null) {
                                        devRecipientPersonId.add(((List<Document>) (dev.get("awards"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (dev.get("awards"))).get(i).get("recipients"))).get(j).get("person", Document.class).get("id").toString());
                                    }
                                }
                            }
                        }
                    }
                    if (devAwardId.containsAll(prodAwardId)) {
                        softAssert.assertTrue(devAwardAssociation.containsAll(prodAwardAssociation), "Failed because Dev.awards.association.name " + dev.get("_id").toString());
                        softAssert.assertTrue(devAwardTLocale.containsAll(prodAwardTLocale), "Failed because Dev.awards.t_in.t_locale " + dev.get("_id").toString());
                        softAssert.assertTrue(devRecipientPersonId.containsAll(prodRecipientPersonId), "Failed because Dev.awards.recipients.person.id " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(devAwardId.containsAll(prodAwardId), "Failed because Dev.awards.id " + dev.get("_id").toString());
                    }
                }else {
                    softAssert.assertTrue(dev.get("awards") != null, "Failed because Dev.awards is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesCreditsTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_credits") != null) {
                if (dev.get("r_series_credits") != null) {
                    softAssert.assertEquals(dev.get("r_series_credits").toString(), prod.get("r_series_credits").toString(), "Failed for r_series_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_credits") != null, "Failed because Dev.r_series_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("credits") != null) {
                ArrayList<String> prodCreditTLocale = new ArrayList<>();
                if (dev.get("credits") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("credits"))).size(); i++) {
                        ArrayList<String> tLocale = new ArrayList<>();
                        String creditType = "";
                        String personID = "";
                        if (((List<Document>) (prod.get("credits"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    tLocale.add(((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (prod.get("credits"))).get(i).get("d_creditType") != null) {
                            if(((List<Document>) (prod.get("credits"))).get(i).get("d_creditType", Document.class).get("id") != null) {
                                creditType = ((List<Document>) (prod.get("credits"))).get(i).get("d_creditType", Document.class).get("id").toString();
                            }
                        }
                        if (((List<Document>) (prod.get("credits"))).get(i).get("person") != null) {
                            if(((List<Document>) (prod.get("credits"))).get(i).get("person", Document.class).get("id") != null) {
                                personID = ((List<Document>) (prod.get("credits"))).get(i).get("person", Document.class).get("id").toString();
                            }
                        }
                        String finalString = creditType + personID;
                        for(String t : tLocale){
                            String temp = finalString + t;
                            prodCreditTLocale.add(temp);
                        }
                        if(tLocale.isEmpty()){
                            prodCreditTLocale.add(finalString);
                        }
                    }

                    ArrayList<String> devCreditTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("credits"))).size(); i++) {
                        ArrayList<String> tLocale = new ArrayList<>();
                        String creditType = "";
                        String personID = "";
                        if (((List<Document>) (dev.get("credits"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    tLocale.add(((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (dev.get("credits"))).get(i).get("d_creditType") != null) {
                            if(((List<Document>) (dev.get("credits"))).get(i).get("d_creditType", Document.class).get("id") != null) {
                                creditType = ((List<Document>) (dev.get("credits"))).get(i).get("d_creditType", Document.class).get("id").toString();
                            }
                        }
                        if (((List<Document>) (dev.get("credits"))).get(i).get("person") != null) {
                            if(((List<Document>) (dev.get("credits"))).get(i).get("person", Document.class).get("id") != null) {
                                personID = ((List<Document>) (dev.get("credits"))).get(i).get("person", Document.class).get("id").toString();
                            }
                        }
                        String finalString = creditType + personID;
                        for(String t : tLocale){
                            String temp = finalString + t;
                            devCreditTLocale.add(temp);
                        }
                        if(tLocale.isEmpty()){
                            devCreditTLocale.add(finalString);
                        }
                    }
                    softAssert.assertTrue(devCreditTLocale.containsAll(prodCreditTLocale), "Failed because Dev.credits.t_in.t_locale + credittype + person id not matches " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("credits") != null, "Failed because Dev.credits is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesHistoryTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_history") != null) {
                if (dev.get("r_series_history") != null) {
                    softAssert.assertEquals(dev.get("r_series_history").toString(), prod.get("r_series_history").toString(), "Failed for r_series_history : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_history") != null, "Failed because Dev.r_series_history is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("history") != null) {
                if (dev.get("history") != null) {
                    ArrayList<String> prodSourceId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("history"))).size(); i++) {
                        ArrayList<String> sourceId = new ArrayList<>();
                        String start = "";
                        String end = "";
                        if (((List<Document>) (prod.get("history"))).get(i).get("source") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("history"))).get(i).get("source"))).size(); j++) {
                                sourceId.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (prod.get("history"))).get(i).get("source"))).get(j).get("id").toString()));
                            }
                        }
                        start = StringUtils.trimToEmpty(((List<Document>) (prod.get("history"))).get(i).get("start").toString());
                        end = StringUtils.trimToEmpty(((List<Document>) (prod.get("history"))).get(i).get("end").toString());
                        String finalString = start + end;
                        for(String t : sourceId){
                            String temp = finalString + t;
                            prodSourceId.add(StringUtils.trimToNull(temp));
                        }
                        if(sourceId.isEmpty()){
                            prodSourceId.add(StringUtils.trimToNull(finalString));
                        }
                    }

                    ArrayList<String> devSourceId = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("history"))).size(); i++) {
                        ArrayList<String> sourceId = new ArrayList<>();
                        String start = "";
                        String end = "";
                        if (((List<Document>) (dev.get("history"))).get(i).get("source") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("history"))).get(i).get("source"))).size(); j++) {
                                sourceId.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (dev.get("history"))).get(i).get("source"))).get(j).get("id").toString()));
                            }
                        }
                        start = StringUtils.trimToEmpty(((List<Document>) (dev.get("history"))).get(i).get("start").toString());
                        end = StringUtils.trimToEmpty(((List<Document>) (dev.get("history"))).get(i).get("end").toString());
                        String finalString = start + end;
                        for(String t : sourceId){
                            String temp = finalString + t;
                            devSourceId.add(StringUtils.trimToNull(temp));
                        }
                        if(sourceId.isEmpty()){
                            devSourceId.add(StringUtils.trimToNull(finalString));
                        }
                    }
                    softAssert.assertTrue(devSourceId.containsAll(prodSourceId), "Failed because Dev.history.source.id + start + end not matches " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("history") != null, "Failed because Dev.history is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesRatingTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_ratings") != null) {
                if (dev.get("r_series_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_series_ratings").toString(), prod.get("r_series_ratings").toString(), "Failed for r_series_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_ratings") != null, "Failed because Dev.r_series_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("RatingCollection") != null) {
                if (dev.get("RatingCollection") != null) {
                    ArrayList<String> prodRatingCollection = new ArrayList<>();
                    ArrayList<String> prodDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        //tDefault = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        //ratingSystemName = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        //ratingValue = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        //ratingMedium = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());
                        /*if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                prodDRatingReasons.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString()));
                            }
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        prodRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            prodRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            prodRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }

                    ArrayList<String> devRatingCollection = new ArrayList<>();
                    ArrayList<String> devDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        /*tDefault = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        ratingSystemName = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        ratingValue = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        ratingMedium = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());*/
                        /*if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                devDRatingReasons.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString());
                            }
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        devRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            devRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            devRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }
                    softAssert.assertTrue(devRatingCollection.containsAll(prodRatingCollection), "Failed because Dev.RatingCollection not matches Prod " + dev.get("_id").toString() );
                    softAssert.assertTrue(devDRatingReasons.containsAll(prodDRatingReasons), "Failed because Dev.RatingReasons not matches Prod " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("RatingCollection") != null, "Failed because Dev.RatingCollection is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seriesSeasonsTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_seasons") != null) {
                if (dev.get("r_series_seasons") != null) {
                    softAssert.assertEquals(dev.get("r_series_seasons").toString(), prod.get("r_series_seasons").toString(), "Failed for r_series_seasons : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_seasons") != null, "Failed because Dev.r_series_seasons is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("seasons") != null) {
                if (dev.get("seasons") != null) {
                    ArrayList<Long> prodSeasonId = new ArrayList<>();
                    ArrayList<String> prodSeasonLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("seasons"))).size(); i++) {
                        if (((List<Document>) (prod.get("seasons"))).get(i).get("id") != null) {
                            prodSeasonId.add(((List<Document>) (prod.get("seasons"))).get(i).getLong("id"));
                            if (((List<Document>) (prod.get("seasons"))).get(i).get("t_in") != null) {
                                for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("seasons"))).get(i).get("t_in"))).size(); j++) {
                                    if (((List<Document>) (((List<Document>) (prod.get("seasons"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                        prodSeasonLocale.add(((List<Document>) (prod.get("seasons"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (prod.get("seasons"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                    }
                                }
                            }
                        }
                    }

                    ArrayList<Long> devSeasonId = new ArrayList<>();
                    ArrayList<String> devSeasonLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("seasons"))).size(); i++) {
                        if (((List<Document>) (dev.get("seasons"))).get(i).get("id") != null) {
                            devSeasonId.add(((List<Document>) (dev.get("seasons"))).get(i).getLong("id"));
                            if (((List<Document>) (dev.get("seasons"))).get(i).get("t_in") != null) {
                                for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("seasons"))).get(i).get("t_in"))).size(); j++) {
                                    if (((List<Document>) (((List<Document>) (dev.get("seasons"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                        devSeasonLocale.add(((List<Document>) (dev.get("seasons"))).get(i).getLong("id").toString() + ((List<Document>) (((List<Document>) (dev.get("seasons"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                    }
                                }
                            }
                        }
                    }
                    if (devSeasonId.containsAll(prodSeasonId)) {
                        softAssert.assertTrue(devSeasonLocale.containsAll(prodSeasonLocale), "Failed because Dev.seasons.t_in.t_locale " + dev.get("_id").toString());
                    } else {
                        softAssert.assertTrue(devSeasonId.containsAll(prodSeasonId), "Failed because Dev.seasons.id " + dev.get("_id").toString());
                    }
                } else {
                    softAssert.assertTrue(dev.get("seasons") != null, "Failed because Dev.seasons is null " + dev.get("_id").toString());
                }
                i++;
                System.out.println(i);
            } else {
                System.out.println("ID not present in prod : " + dev.get("_id").toString());
            }
        }
    }

    public static void seriesSynopsesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_synopses") != null) {
                if (dev.get("r_series_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_series_synopses").toString(), prod.get("r_series_synopses").toString(), "Failed for r_series_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_synopses") != null, "Failed because Dev.r_series_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synopses") != null) {
                if (dev.get("synopses") != null) {
                    ArrayList<String> prodSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (prod.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (prod.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (prod.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                prodSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }

                    ArrayList<String> devSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (dev.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (dev.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (dev.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                devSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }
                    Collections.sort(prodSynopsisLength);
                    Collections.sort(devSynopsisLength);
                    int i = 0;
                    int j = 0;
                    for(;i < prodSynopsisLength.size();){
                        int temp = j;
                        for(; j <= devSynopsisLength.size();){
                            if(j< devSynopsisLength.size() && prodSynopsisLength.get(i).equalsIgnoreCase(devSynopsisLength.get(temp))){
                                j++;
                                break;
                            }else {
                                temp++;
                                if(temp >= devSynopsisLength.size()){
                                    softAssert.assertTrue(false, dev.get("_id").toString() + " Production Dev.synopses.length + t_locale + synopsis not present " + prodSynopsisLength.get(i));
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                } else {
                    softAssert.assertTrue(dev.get("synopses") != null, "Failed because Dev.synopses is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seasonEpisodesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season_episodes") != null) {
                if (dev.get("r_season_episodes") != null) {
                    softAssert.assertEquals(dev.get("r_season_episodes").toString(), prod.get("r_season_episodes").toString(), "Failed for r_season_episodes : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season_episodes") != null, "Failed because Dev.r_season_episodes is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season") != null) {
                if (dev.get("r_season") != null) {
                    softAssert.assertEquals(dev.get("r_season").toString(), prod.get("r_season").toString(), "Failed for r_season : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season") != null, "Failed because Dev.r_season is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("episodes") != null) {
                if (dev.get("episodes") != null) {
                    ArrayList<String> prodEpisodeIDLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("episodes"))).size(); i++) {
                        String episodeID = "";
                        if(((List<Document>) (prod.get("episodes"))).get(i).get("id") != null) {
                            episodeID = StringUtils.trimToEmpty(((List<Document>) (prod.get("episodes"))).get(i).get("id").toString());
                        }
                        prodEpisodeIDLocale.add(episodeID);
                        ArrayList<String> prodEpisodeTLocale = new ArrayList<>();
                        if (((List<Document>) (prod.get("episodes"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (prod.get("episodes"))).get(i).get("t_in")).size(); j++) {
                                if(((List<Document>) ((List<Document>) (prod.get("episodes"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    prodEpisodeTLocale.add(episodeID + StringUtils.trimToEmpty(((List<Document>) ((List<Document>) (prod.get("episodes"))).get(i).get("t_in")).get(j).get("t_locale").toString()));
                                }
                            }
                        }
                    }

                    ArrayList<String> devEpisodeIDLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("episodes"))).size(); i++) {
                        String episodeID = "";
                        if(((List<Document>) (dev.get("episodes"))).get(i).get("id") != null) {
                            episodeID = StringUtils.trimToEmpty(((List<Document>) (dev.get("episodes"))).get(i).get("id").toString());
                        }
                        devEpisodeIDLocale.add(episodeID);
                        ArrayList<String> devEpisodeTLocale = new ArrayList<>();
                        if (((List<Document>) (dev.get("episodes"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (dev.get("episodes"))).get(i).get("t_in")).size(); j++) {
                                if(((List<Document>) ((List<Document>) (dev.get("episodes"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    devEpisodeTLocale.add(episodeID + StringUtils.trimToEmpty(((List<Document>) ((List<Document>) (dev.get("episodes"))).get(i).get("t_in")).get(j).get("t_locale").toString()));
                                }
                            }
                        }
                    }
                    if(devEpisodeIDLocale.containsAll(prodEpisodeIDLocale)){
                        softAssert.assertTrue(devEpisodeIDLocale.containsAll(prodEpisodeIDLocale), "Failed because Episode Id's doesn't match " + dev.get("_id").toString());
                    }else {
                        softAssert.assertTrue(devEpisodeIDLocale.containsAll(prodEpisodeIDLocale),"Failed because Episode Locales doesn't match " + dev.get("_id").toString());
                    }

                } else {
                    softAssert.assertTrue(dev.get("episodes") != null, "Failed because Dev.episodes is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void seasonSynopsesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season_synopses") != null) {
                if (dev.get("r_season_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_season_synopses").toString(), prod.get("r_season_synopses").toString(), "Failed for r_season_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season_synopses") != null, "Failed because Dev.r_season_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_season") != null) {
                if (dev.get("r_season") != null) {
                    softAssert.assertEquals(dev.get("r_season").toString(), prod.get("r_season").toString(), "Failed for r_season : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_season") != null, "Failed because Dev.r_season is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synopses") != null) {
                if (dev.get("synopses") != null) {
                    ArrayList<String> prodSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (prod.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (prod.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (prod.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                prodSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }

                    ArrayList<String> devSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (dev.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (dev.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (dev.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                devSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }
                    Collections.sort(prodSynopsisLength);
                    Collections.sort(devSynopsisLength);
                    int i = 0;
                    int j = 0;
                    for(;i < prodSynopsisLength.size();){
                        int temp = j;
                        for(; j <= devSynopsisLength.size();){
                            if(j< devSynopsisLength.size() && prodSynopsisLength.get(i).equalsIgnoreCase(devSynopsisLength.get(temp))){
                                j++;
                                break;
                            }else {
                                temp++;
                                if(temp >= devSynopsisLength.size()){
                                    softAssert.assertTrue(false, dev.get("_id").toString() + " Production Dev.synopses.length + t_locale + synopsis not present " + prodSynopsisLength.get(i));
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                } else {
                    softAssert.assertTrue(dev.get("synopses") != null, "Failed because Dev.synopses is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void episodeCreditsTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_credits") != null) {
                if (dev.get("r_episode_credits") != null) {
                    softAssert.assertEquals(dev.get("r_episode_credits").toString(), prod.get("r_episode_credits").toString(), "Failed for r_episode_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_credits") != null, "Failed because Dev.r_episode_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("credits") != null) {
                ArrayList<String> prodCreditTLocale = new ArrayList<>();
                if (dev.get("credits") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("credits"))).size(); i++) {
                        ArrayList<String> tLocale = new ArrayList<>();
                        String creditType = "";
                        String personID = "";
                        if (((List<Document>) (prod.get("credits"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    tLocale.add(((List<Document>) (((List<Document>) (prod.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (prod.get("credits"))).get(i).get("d_creditType") != null) {
                            if(((List<Document>) (prod.get("credits"))).get(i).get("d_creditType", Document.class).get("id") != null) {
                                creditType = ((List<Document>) (prod.get("credits"))).get(i).get("d_creditType", Document.class).get("id").toString();
                            }
                        }
                        if (((List<Document>) (prod.get("credits"))).get(i).get("person") != null) {
                            if(((List<Document>) (prod.get("credits"))).get(i).get("person", Document.class).get("id") != null) {
                                personID = ((List<Document>) (prod.get("credits"))).get(i).get("person", Document.class).get("id").toString();
                            }
                        }
                        String finalString = creditType + personID;
                        for(String t : tLocale){
                            String temp = finalString + t;
                            prodCreditTLocale.add(temp);
                        }
                        if(tLocale.isEmpty()){
                            prodCreditTLocale.add(finalString);
                        }
                    }

                    ArrayList<String> devCreditTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("credits"))).size(); i++) {
                        ArrayList<String> tLocale = new ArrayList<>();
                        String creditType = "";
                        String personID = "";
                        if (((List<Document>) (dev.get("credits"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).size(); j++) {
                                if (((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    tLocale.add(((List<Document>) (((List<Document>) (dev.get("credits"))).get(i).get("t_in"))).get(j).get("t_locale").toString());
                                }
                            }
                        }
                        if (((List<Document>) (dev.get("credits"))).get(i).get("d_creditType") != null) {
                            if(((List<Document>) (dev.get("credits"))).get(i).get("d_creditType", Document.class).get("id") != null) {
                                creditType = ((List<Document>) (dev.get("credits"))).get(i).get("d_creditType", Document.class).get("id").toString();
                            }
                        }
                        if (((List<Document>) (dev.get("credits"))).get(i).get("person") != null) {
                            if(((List<Document>) (dev.get("credits"))).get(i).get("person", Document.class).get("id") != null) {
                                personID = ((List<Document>) (dev.get("credits"))).get(i).get("person", Document.class).get("id").toString();
                            }
                        }
                        String finalString = creditType + personID;
                        for(String t : tLocale){
                            String temp = finalString + t;
                            devCreditTLocale.add(temp);
                        }
                        if(tLocale.isEmpty()){
                            devCreditTLocale.add(finalString);
                        }
                    }
                    softAssert.assertTrue(devCreditTLocale.containsAll(prodCreditTLocale), "Failed because Dev.credits.t_in.t_locale + credittype + person id not matches " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("credits") != null, "Failed because Dev.credits is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void episodeRatingTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_ratings") != null) {
                if (dev.get("r_episode_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_episode_ratings").toString(), prod.get("r_episode_ratings").toString(), "Failed for r_episode_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_ratings") != null, "Failed because Dev.r_episode_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("RatingCollection") != null) {
                if (dev.get("RatingCollection") != null) {
                    ArrayList<String> prodRatingCollection = new ArrayList<>();
                    ArrayList<String> prodDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        //tDefault = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        //ratingSystemName = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        //ratingValue = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        //ratingMedium = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());
                        /*if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                prodDRatingReasons.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString()));
                            }
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        prodRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            prodRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            prodRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }

                    ArrayList<String> devRatingCollection = new ArrayList<>();
                    ArrayList<String> devDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        /*tDefault = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        ratingSystemName = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        ratingValue = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        ratingMedium = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());*/
                        /*if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                devDRatingReasons.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString());
                            }
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        devRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            devRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            devRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }
                    softAssert.assertTrue(devRatingCollection.containsAll(prodRatingCollection), "Failed because Dev.RatingCollection not matches Prod " + dev.get("_id").toString() );
                    softAssert.assertTrue(devDRatingReasons.containsAll(prodDRatingReasons), "Failed because Dev.RatingReasons not matches Prod " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("RatingCollection") != null, "Failed because Dev.RatingCollection is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void episodeReleasesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode_releases") != null) {
                if (dev.get("r_episode_releases") != null) {
                    softAssert.assertEquals(dev.get("r_episode_releases").toString(), prod.get("r_episode_releases").toString(), "Failed for r_episode_releases : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode_releases") != null, "Failed because Dev.r_episode_releases is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("releases")!=null){
                if(dev.get("releases")!= null){
                    ArrayList<String> prodReleaseTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("releases"))).size(); i++) {
                        ArrayList<String> prodtLocale = new ArrayList<>();
                        String releaseID = "";
                        String year = "";
                        if (((List<Document>) (prod.get("releases"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("releases"))).get(i).get("t_in"))).size(); j++) {
                                if(((List<Document>) (((List<Document>) (prod.get("releases"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    prodtLocale.add(StringUtils.trimToEmpty(((List<Document>) (((List<Document>) (prod.get("releases"))).get(i).get("t_in"))).get(j).get("t_locale").toString()));
                                }
                            }
                        }
                        if (((List<Document>) (prod.get("releases"))).get(i).get("id") != null) {
                            releaseID = StringUtils.trimToEmpty(((List<Document>) (prod.get("releases"))).get(i).get("id").toString());
                        }
                        if(((List<Document>) (prod.get("releases"))).get(i).get("year") != null){
                            year = StringUtils.trimToEmpty(((List<Document>) (prod.get("releases"))).get(i).get("year").toString());
                        }
                        String finalString = releaseID + year;
                        for(String t : prodtLocale){
                            String temp = finalString + t;
                            prodReleaseTLocale.add(StringUtils.trimToNull(temp));
                        }
                        if(prodtLocale.isEmpty()){
                            prodReleaseTLocale.add(StringUtils.trimToNull(finalString));
                        }
                    }

                    ArrayList<String> devReleaseTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("releases"))).size(); i++) {
                        ArrayList<String> devTLocale = new ArrayList<>();
                        String releaseID = "";
                        String year = "";
                        if (((List<Document>) (dev.get("releases"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("releases"))).get(i).get("t_in"))).size(); j++) {
                                if(((List<Document>) (((List<Document>) (dev.get("releases"))).get(i).get("t_in"))).get(j).get("t_locale") != null) {
                                    devTLocale.add(StringUtils.trimToEmpty(((List<Document>) (((List<Document>) (dev.get("releases"))).get(i).get("t_in"))).get(j).get("t_locale").toString()));
                                }
                            }
                        }
                        if (((List<Document>) (dev.get("releases"))).get(i).get("id") != null) {
                            releaseID = StringUtils.trimToEmpty(((List<Document>) (dev.get("releases"))).get(i).get("id").toString());
                        }
                        if(((List<Document>) (dev.get("releases"))).get(i).get("year") != null){
                            year = StringUtils.trimToEmpty(((List<Document>) (dev.get("releases"))).get(i).get("year").toString());
                        }
                        String finalString = releaseID + year;
                        for(String t : devTLocale){
                            String temp = finalString + t;
                            devReleaseTLocale.add(StringUtils.trimToNull(temp));
                        }
                        if(devTLocale.isEmpty()){
                            devReleaseTLocale.add(StringUtils.trimToNull(finalString));
                        }
                    }

                    softAssert.assertTrue(devReleaseTLocale.containsAll(prodReleaseTLocale) , "Failed because Dev.release.t_in.t_locale + id + year not matches " + dev.get("_id").toString() );
                }else{
                    softAssert.assertTrue(dev.get("releases")!= null , "Failed because Dev.releases is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void episodeSynopsesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series_synopses") != null) {
                if (dev.get("r_series_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_series_synopses").toString(), prod.get("r_series_synopses").toString(), "Failed for r_series_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series_synopses") != null, "Failed because Dev.r_series_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("synopses") != null) {
                if (dev.get("synopses") != null) {
                    ArrayList<String> prodSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (prod.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (prod.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (prod.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (prod.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                prodSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }

                    ArrayList<String> devSynopsisLength = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("synopses"))).size(); i++) {
                        String synopsesLength = "";
                        if(((List<Document>) (dev.get("synopses"))).get(i).get("length") != null) {
                            synopsesLength = StringUtils.trimToEmpty(((List<Document>) (dev.get("synopses"))).get(i).get("length").toString().toLowerCase());
                        }
                        if (((List<Document>) (dev.get("synopses"))).get(i).get("t_in") != null) {
                            for (int j = 0; j < ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).size(); j++) {
                                String tLocale = "";
                                String synopsis = "";
                                String finalString = "";
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale") != null) {
                                    tLocale = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("t_locale").toString();
                                }
                                if(((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis") != null){
                                    synopsis = ((List<Document>) ((List<Document>) (dev.get("synopses"))).get(i).get("t_in")).get(j).get("synopsis").toString();
                                }
                                finalString = StringUtils.trimToEmpty(tLocale + synopsis);
                                if(finalString.isEmpty()) {
                                    continue;
                                }
                                devSynopsisLength.add(finalString + synopsesLength);
                            }
                        }
                    }
                    Collections.sort(prodSynopsisLength);
                    Collections.sort(devSynopsisLength);
                    int i = 0;
                    int j = 0;
                    for(;i < prodSynopsisLength.size();){
                        int temp = j;
                        for(; j <= devSynopsisLength.size();){
                            if(j< devSynopsisLength.size() && prodSynopsisLength.get(i).equalsIgnoreCase(devSynopsisLength.get(temp))){
                                j++;
                                break;
                            }else {
                                temp++;
                                if(temp >= devSynopsisLength.size()){
                                    softAssert.assertTrue(false, dev.get("_id").toString() + " Production Dev.synopses.length + t_locale + synopsis not present " + prodSynopsisLength.get(i));
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                } else {
                    softAssert.assertTrue(dev.get("synopses") != null, "Failed because Dev.synopses is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void otherRelatedTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_related") != null) {
                if (dev.get("r_other_related") != null) {
                    softAssert.assertEquals(dev.get("r_other_related").toString(), prod.get("r_other_related").toString(), "Failed for r_other_related : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_related") != null, "Failed because Dev.r_other_related is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other") != null) {
                if (dev.get("r_other") != null) {
                    softAssert.assertEquals(dev.get("r_other").toString(), prod.get("r_other").toString(), "Failed for r_other : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other") != null, "Failed because Dev.r_other is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("related") != null) {
                ArrayList<String> prodContentId = new ArrayList<>();
                ArrayList<String> prodContentTLocale = new ArrayList<>();
                if (dev.get("related") != null) {
                    for (int i = 0; i < ((List<Document>) (prod.get("related"))).size(); i++) {
                        String contentID = "";
                        String tLocale = "";
                        String title = "";
                        String finalString = "";
                        if(((List<Document>) (prod.get("related"))).get(i).get("content") != null){
                            if(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("id") != null){
                                contentID = ((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("id").toString();
                                prodContentId.add(contentID);
                                if(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in") != null){
                                    for(int j=0; j < ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).size(); j++){
                                        if(((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale") != null){
                                            tLocale = ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale").toString();
                                        }
                                        if(((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title") != null){
                                            title = ((List<Document>)(((List<Document>) (prod.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title").toString();
                                        }
                                        finalString = StringUtils.trimToEmpty(tLocale + title);
                                        if(finalString.isEmpty()) {
                                            continue;
                                        }
                                        prodContentTLocale.add(contentID + " " + finalString);
                                    }
                                }
                            }
                        }
                    }

                    ArrayList<String> devContentId = new ArrayList<>();
                    ArrayList<String> devContentTLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("related"))).size(); i++) {
                        String contentID = "";
                        String tLocale = "";
                        String title = "";
                        String finalString = "";
                        if(((List<Document>) (dev.get("related"))).get(i).get("content") != null){
                            if(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("id") != null){
                                contentID = ((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("id").toString();
                                devContentId.add(contentID);
                                if(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in") != null){
                                    for(int j=0; j < ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).size(); j++){
                                        if(((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale") != null){
                                            tLocale = ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("t_locale").toString();
                                        }
                                        if(((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title") != null){
                                            title = ((List<Document>)(((List<Document>) (dev.get("related"))).get(i).get("content",Document.class).get("t_in"))).get(j).get("title").toString();
                                        }
                                        finalString = StringUtils.trimToEmpty(tLocale + title);
                                        if(finalString.isEmpty()) {
                                            continue;
                                        }
                                        devContentTLocale.add(contentID + " " + finalString);
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(prodContentTLocale);
                    Collections.sort(devContentTLocale);

                    if(devContentId.containsAll(prodContentId)){
                        int i = 0;
                        int j = 0;
                        for(;i < prodContentTLocale.size();){
                            int temp = j;
                            for(; j <= devContentTLocale.size();){
                                if(j< devContentTLocale.size() && prodContentTLocale.get(i).equalsIgnoreCase(devContentTLocale.get(temp))){
                                    j++;
                                    break;
                                }else {
                                    temp++;
                                    if(temp >= devContentTLocale.size()){
                                        softAssert.assertTrue(false, dev.get("_id").toString() + " Production Dev.synopses.length + t_locale + synopsis not present " + prodContentTLocale.get(i));
                                        break;
                                    }
                                }
                            }
                            i++;
                        }
                    }else{
                        softAssert.assertTrue(devContentId.containsAll(prodContentId), "Failed because Dev.related.content.id " + dev.get("_id").toString());
                    }
                } else {
                    softAssert.assertTrue(dev.get("related") != null, "Failed because Dev.related is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void contentTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content") != null) {
                if (dev.get("r_content") != null) {
                    softAssert.assertEquals(dev.get("r_content").toString(), prod.get("r_content").toString(), "Failed for r_content : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content") != null, "Failed because Dev.r_content is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie") != null) {
                if (dev.get("r_movie") != null) {
                    softAssert.assertEquals(dev.get("r_movie").toString(), prod.get("r_movie").toString(), "Failed for r_movie : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie") != null, "Failed because Dev.r_movie is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other") != null) {
                if (dev.get("r_other") != null) {
                    softAssert.assertEquals(dev.get("r_other").toString(), prod.get("r_other").toString(), "Failed for r_other : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other") != null, "Failed because Dev.r_other is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("f_market") != null) {
                if (dev.get("f_market") != null) {
                    List<Document> prodMarket = (List<Document>) (prod.get("f_market"));
                    List<Document> devMarket = (List<Document>) (dev.get("f_market"));
                    softAssert.assertTrue(devMarket.containsAll(prodMarket), "Failed for f_market : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("f_market") != null, "Failed because Dev.f_market is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_in") != null) {
                if (dev.get("t_in") != null) {
                    ArrayList<String> prodTinLocale = new ArrayList<>();
                    ArrayList<String> devTinLocale = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("t_in"))).size(); i++) {
                        String tLocale = "";
                        String title = "";
                        if(((List<Document>) (prod.get("t_in"))).get(i).get("t_locale") != null){
                            tLocale = ((List<Document>) (prod.get("t_in"))).get(i).get("t_locale").toString();
                        }
                        if(((List<Document>) (prod.get("t_in"))).get(i).get("title") != null){
                            title = ((List<Document>) (prod.get("t_in"))).get(i).get("title").toString();
                        }
                        prodTinLocale.add(StringUtils.trimToEmpty(tLocale + title));
                    }
                    for (int i = 0; i < ((List<Document>) (dev.get("t_in"))).size(); i++) {
                        String tLocale = "";
                        String title = "";
                        if(((List<Document>) (dev.get("t_in"))).get(i).get("t_locale") != null){
                            tLocale = ((List<Document>) (dev.get("t_in"))).get(i).get("t_locale").toString();
                        }
                        if(((List<Document>) (dev.get("t_in"))).get(i).get("title") != null){
                            title = ((List<Document>) (dev.get("t_in"))).get(i).get("title").toString();
                        }
                        devTinLocale.add(StringUtils.trimToEmpty(tLocale + title));
                    }
                    Collections.sort(prodTinLocale);
                    Collections.sort(devTinLocale);
                    softAssert.assertTrue(devTinLocale.containsAll(prodTinLocale), "Failed for t_in.locale : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_in") != null, "Failed because Dev.t_in is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("type") != null) {
                if (dev.get("type") != null) {
                    softAssert.assertEquals(dev.get("type").toString(), prod.get("type").toString(), "Failed for type : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("type") != null, "Failed because Dev.type is null " + dev.get("_id").toString());
                }
            }

            if (prod.get("synonyms") != null) {
                if (dev.get("synonyms") != null) {
                    ArrayList<String> prodSynonym = new ArrayList<>();
                    ArrayList<String> devSynonym = new ArrayList<>();
                    for(int i=0; i < ((List<Document>)dev.get("synonyms")).size(); i++){
                        String rv20 = "";
                        String program = "";
                        String iguide = "";
                        String amg = "";
                        String eidr = "";
                        String t_default = "";
                        String title = "";
                        if(((List<Document>)dev.get("synonyms")).get(i).get("rv20") != null){
                            rv20 = ((List<Document>)dev.get("synonyms")).get(i).get("rv20").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("program") != null){
                            program = ((List<Document>)dev.get("synonyms")).get(i).get("program").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("iguide") != null){
                            iguide = ((List<Document>)dev.get("synonyms")).get(i).get("iguide").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("amg") != null){
                            amg = ((List<Document>)dev.get("synonyms")).get(i).get("amg").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("eidr") != null){
                            eidr = ((List<Document>)dev.get("synonyms")).get(i).get("eidr").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("t_default") != null){
                            t_default = ((List<Document>)dev.get("synonyms")).get(i).get("t_default").toString();
                        }
                        if(((List<Document>)dev.get("synonyms")).get(i).get("title") != null){
                            title = ((List<Document>)dev.get("synonyms")).get(i).get("title").toString();
                        }
                        devSynonym.add(StringUtils.trimToEmpty(rv20 + program + iguide + amg + eidr + t_default + title));

                    }
                    for(int i=0; i < ((List<Document>)prod.get("synonyms")).size(); i++){
                        String rv20 = "";
                        String program = "";
                        String iguide = "";
                        String amg = "";
                        String eidr = "";
                        String t_default = "";
                        String title = "";
                        if(((List<Document>)prod.get("synonyms")).get(i).get("rv20") != null){
                            rv20 = ((List<Document>)prod.get("synonyms")).get(i).get("rv20").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("program") != null){
                            program = ((List<Document>)prod.get("synonyms")).get(i).get("program").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("iguide") != null){
                            iguide = ((List<Document>)prod.get("synonyms")).get(i).get("iguide").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("amg") != null){
                            amg = ((List<Document>)prod.get("synonyms")).get(i).get("amg").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("eidr") != null){
                            eidr = ((List<Document>)prod.get("synonyms")).get(i).get("eidr").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("t_default") != null){
                            t_default = ((List<Document>)prod.get("synonyms")).get(i).get("t_default").toString();
                        }
                        if(((List<Document>)prod.get("synonyms")).get(i).get("title") != null){
                            title = ((List<Document>)prod.get("synonyms")).get(i).get("title").toString();
                        }
                        prodSynonym.add(StringUtils.trimToEmpty(rv20 + program + iguide + amg + eidr + t_default + title));

                    }
                    Collections.sort(devSynonym);
                    Collections.sort(prodSynonym);
                    softAssert.assertTrue(devSynonym.containsAll(prodSynonym), "Failed for Synonym : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("synonyms") != null, "Failed because Dev.synonyms is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        }else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void contentImagesTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_content_images") != null) {
                if (dev.get("r_content_images") != null) {
                    softAssert.assertEquals(dev.get("r_content_images").toString(), prod.get("r_content_images").toString(), "Failed for r_content_images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_content_images") != null, "Failed because Dev.r_content_images is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie") != null) {
                if (dev.get("r_movie") != null) {
                    softAssert.assertEquals(dev.get("r_movie").toString(), prod.get("r_movie").toString(), "Failed for r_movie : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie") != null, "Failed because Dev.r_movie is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_series") != null) {
                if (dev.get("r_series") != null) {
                    softAssert.assertEquals(dev.get("r_series").toString(), prod.get("r_series").toString(), "Failed for r_series : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_series") != null, "Failed because Dev.r_series is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_episode") != null) {
                if (dev.get("r_episode") != null) {
                    softAssert.assertEquals(dev.get("r_episode").toString(), prod.get("r_episode").toString(), "Failed for r_episode : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_episode") != null, "Failed because Dev.r_episode is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other") != null) {
                if (dev.get("r_other") != null) {
                    softAssert.assertEquals(dev.get("r_other").toString(), prod.get("r_other").toString(), "Failed for r_other : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other") != null, "Failed because Dev.r_other is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("images") != null) {
                if (dev.get("images") != null) {
                    ArrayList<String> prodImages = new ArrayList<>();
                    ArrayList<String> devImages = new ArrayList<>();
                    for(int i=0; i < ((List<Document>)dev.get("images")).size(); i++){
                        String id = "";
                        String l_media_image = "";
                        String t_default = "";
                        String type = "";
                        String caption = "";
                        String credit = "";
                        String owner = "";
                        String zoom = "";
                        String people = "";
                        String weight = "";
                        if(((List<Document>)dev.get("images")).get(i).get("id") != null){
                            id = ((List<Document>)dev.get("images")).get(i).get("id").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("l_media_image") != null){
                            l_media_image = ((List<Document>)dev.get("images")).get(i).get("l_media_image").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("t_default") != null){
                            t_default = ((List<Document>)dev.get("images")).get(i).get("t_default").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("type") != null){
                            type = ((List<Document>)dev.get("images")).get(i).get("type").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("caption") != null){
                            caption = ((List<Document>)dev.get("images")).get(i).get("caption").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("credit") != null){
                            credit = ((List<Document>)dev.get("images")).get(i).get("credit").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("owner") != null){
                            owner = ((List<Document>)dev.get("images")).get(i).get("owner").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("zoom") != null){
                            zoom = ((List<Document>)dev.get("images")).get(i).get("zoom").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("people") != null){
                            people = ((List<Document>)dev.get("images")).get(i).get("people").toString();
                        }
                        if(((List<Document>)dev.get("images")).get(i).get("weight") != null){
                            weight = ((List<Document>)dev.get("images")).get(i).get("weight").toString();
                        }
                        devImages.add(StringUtils.trimToEmpty(id + l_media_image + t_default + type + caption + credit + owner + zoom + people + weight));
                    }
                    for(int i=0; i < ((List<Document>)prod.get("images")).size(); i++){
                        String id = "";
                        String l_media_image = "";
                        String t_default = "";
                        String type = "";
                        String caption = "";
                        String credit = "";
                        String owner = "";
                        String zoom = "";
                        String people = "";
                        String weight = "";
                        if(((List<Document>)prod.get("images")).get(i).get("id") != null){
                            id = ((List<Document>)prod.get("images")).get(i).get("id").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("l_media_image") != null){
                            l_media_image = ((List<Document>)prod.get("images")).get(i).get("l_media_image").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("t_default") != null){
                            t_default = ((List<Document>)prod.get("images")).get(i).get("t_default").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("type") != null){
                            type = ((List<Document>)prod.get("images")).get(i).get("type").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("caption") != null){
                            caption = ((List<Document>)prod.get("images")).get(i).get("caption").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("credit") != null){
                            credit = ((List<Document>)prod.get("images")).get(i).get("credit").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("owner") != null){
                            owner = ((List<Document>)prod.get("images")).get(i).get("owner").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("zoom") != null){
                            zoom = ((List<Document>)prod.get("images")).get(i).get("zoom").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("people") != null){
                            people = ((List<Document>)prod.get("images")).get(i).get("people").toString();
                        }
                        if(((List<Document>)prod.get("images")).get(i).get("weight") != null){
                            weight = ((List<Document>)prod.get("images")).get(i).get("weight").toString();
                        }
                        prodImages.add(StringUtils.trimToEmpty(id + l_media_image + t_default + type + caption + credit + owner + zoom + people + weight));
                    }
                    Collections.sort(devImages);
                    Collections.sort(prodImages);
                    softAssert.assertTrue(devImages.containsAll(prodImages), "Failed for Images : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("images") != null, "Failed because Dev.images is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        }else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void otherRatingTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other_ratings") != null) {
                if (dev.get("r_other_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_other_ratings").toString(), prod.get("r_other_ratings").toString(), "Failed for r_other_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other_ratings") != null, "Failed because Dev.r_other_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_other") != null) {
                if (dev.get("r_other") != null) {
                    softAssert.assertEquals(dev.get("r_other").toString(), prod.get("r_other").toString(), "Failed for r_other : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_other") != null, "Failed because Dev.r_other is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("RatingCollection") != null) {
                if (dev.get("RatingCollection") != null) {
                    ArrayList<String> prodRatingCollection = new ArrayList<>();
                    ArrayList<String> prodDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        //tDefault = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        //ratingSystemName = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        //ratingValue = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        //ratingMedium = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());
                        /*if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                prodDRatingReasons.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString()));
                            }
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        prodRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            prodRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            prodRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }

                    ArrayList<String> devRatingCollection = new ArrayList<>();
                    ArrayList<String> devDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        /*tDefault = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        ratingSystemName = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        ratingValue = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        ratingMedium = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());*/
                        /*if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                devDRatingReasons.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString());
                            }
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        devRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            devRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            devRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }
                    softAssert.assertTrue(devRatingCollection.containsAll(prodRatingCollection), "Failed because Dev.RatingCollection not matches Prod " + dev.get("_id").toString() );
                    softAssert.assertTrue(devDRatingReasons.containsAll(prodDRatingReasons), "Failed because Dev.RatingReasons not matches Prod " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("RatingCollection") != null, "Failed because Dev.RatingCollection is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void movieRatingTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie_ratings") != null) {
                if (dev.get("r_movie_ratings") != null) {
                    softAssert.assertEquals(dev.get("r_movie_ratings").toString(), prod.get("r_movie_ratings").toString(), "Failed for r_movie_ratings : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie_ratings") != null, "Failed because Dev.r_movie_ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_movie") != null) {
                if (dev.get("r_movie") != null) {
                    softAssert.assertEquals(dev.get("r_movie").toString(), prod.get("r_movie").toString(), "Failed for r_movie : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_movie") != null, "Failed because Dev.r_movie is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("n_total") != null) {
                if (dev.get("n_total") != null) {
                    softAssert.assertEquals(dev.get("n_total").toString(), prod.get("n_total").toString(), "Failed for n_total : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("n_total") != null, "Failed because Dev.n_total is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("RatingCollection") != null) {
                if (dev.get("RatingCollection") != null) {
                    ArrayList<String> prodRatingCollection = new ArrayList<>();
                    ArrayList<String> prodDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (prod.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        //tDefault = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        //ratingSystemName = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        //ratingValue = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        //ratingMedium = StringUtils.trimToNull(((List<Document>) (prod.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());
                        /*if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                prodDRatingReasons.add(StringUtils.trimToNull(((List<Document>) (((List<Document>) (prod.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString()));
                            }
                        }
                        if(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (prod.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        prodRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            prodRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            prodRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }

                    ArrayList<String> devRatingCollection = new ArrayList<>();
                    ArrayList<String> devDRatingReasons = new ArrayList<>();
                    for (int i = 0; i < ((List<Document>) (dev.get("RatingCollection"))).size(); i++) {
                        String tDefault = "";
                        String ratingSystemName = "";
                        String ratingValue = "";
                        String ratingMedium = "";
                        ArrayList<Document> countries = new ArrayList<>();
                        String tvRatingReasons = "";
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default") != null){
                            tDefault = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName") != null){
                            ratingSystemName = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue") != null){
                            ratingValue = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase();
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium") != null){
                            ratingMedium = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase();
                        }
                        /*tDefault = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("t_default").toString().toLowerCase());
                        ratingSystemName = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingSystemName").toString().toLowerCase());
                        ratingValue = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingValue").toString().toLowerCase());
                        ratingMedium = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("RatingMedium").toString().toLowerCase());*/
                        /*if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).size(); j++) {
                                countries.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("countries"))).get(j));
                            }
                        }*/
                        if (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons") != null) {
                            for (int j = 0; j < ((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).size(); j++) {
                                devDRatingReasons.add(((List<Document>) (((List<Document>) (dev.get("RatingCollection"))).get(i).get("d_ratingReasons"))).get(j).get("id").toString());
                            }
                        }
                        if(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons") != null){
                            tvRatingReasons = ((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase();
                        }
                        //tvRatingReasons = StringUtils.trimToEmpty(((List<Document>) (dev.get("RatingCollection"))).get(i).get("TvRatingReasons").toString().toLowerCase());
                        String finalString = tDefault + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                        devRatingCollection.add(finalString);
                        /*for(Document t : countries){
                            String temp = finalString + t.toString();
                            devRatingCollection.add(StringUtils.trimToNull(temp));
                        }
                        if(countries.isEmpty()){
                            devRatingCollection.add(StringUtils.trimToNull(finalString));
                        }*/
                    }
                    softAssert.assertTrue(devRatingCollection.containsAll(prodRatingCollection), "Failed because Dev.RatingCollection not matches Prod " + dev.get("_id").toString() );
                    softAssert.assertTrue(devDRatingReasons.containsAll(prodDRatingReasons), "Failed because Dev.RatingReasons not matches Prod " + dev.get("_id").toString() );
                }else {
                    softAssert.assertTrue(dev.get("RatingCollection") != null, "Failed because Dev.RatingCollection is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }
    }

    public static void airingTransformValidator(Document prod, Document dev, SoftAssert softAssert) {
        if (prod != null) {
            if (prod.get("_id") != null) {
                if (dev.get("_id") != null) {
                    softAssert.assertEquals(dev.get("_id").toString(), prod.get("_id").toString(), "Failed for _id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("_id") != null, "Failed because Dev._id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("id") != null) {
                if (dev.get("id") != null) {
                    softAssert.assertEquals(dev.get("id").toString(), prod.get("id").toString(), "Failed for id : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("id") != null, "Failed because Dev.id is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_airing") != null) {
                if (dev.get("r_airing") != null) {
                    softAssert.assertEquals(dev.get("r_airing").toString(), prod.get("r_airing").toString(), "Failed for r_airing : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_airing") != null, "Failed because Dev.r_airing is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_airing_credits") != null) {
                if (dev.get("r_airing_credits") != null) {
                    softAssert.assertEquals(dev.get("r_airing_credits").toString(), prod.get("r_airing_credits").toString(), "Failed for r_airing_credits : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_airing_credits") != null, "Failed because Dev.r_airing_credits is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("r_airing_synopses") != null) {
                if (dev.get("r_airing_synopses") != null) {
                    softAssert.assertEquals(dev.get("r_airing_synopses").toString(), prod.get("r_airing_synopses").toString(), "Failed for r_airing_synopses : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("r_airing_synopses") != null, "Failed because Dev.r_airing_synopses is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("t_default") != null) {
                if (dev.get("t_default") != null) {
                    softAssert.assertEquals(dev.get("t_default").toString(), prod.get("t_default").toString(), "Failed for t_default : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("t_default") != null, "Failed because Dev.t_default is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("ProgramTypeName") != null) {
                if (dev.get("ProgramTypeName") != null) {
                    softAssert.assertEquals(dev.get("ProgramTypeName").toString(), prod.get("ProgramTypeName").toString(), "Failed for ProgramTypeName : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("ProgramTypeName") != null, "Failed because Dev.ProgramTypeName is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("MasterTitle") != null) {
                if (dev.get("MasterTitle") != null) {
                    softAssert.assertEquals(dev.get("MasterTitle").toString(), prod.get("MasterTitle").toString(), "Failed for MasterTitle : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("MasterTitle") != null, "Failed because Dev.MasterTitle is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("alts") != null) {
                if (dev.get("alts") != null) {
                    if(prod.get("alts", Document.class).get("short15") != null){
                        if(dev.get("alts", Document.class).get("short15") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("short15"),prod.get("alts", Document.class).get("short15") , "Failed for alts.short15 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("short15") != null, "Failed because Dev.alts.short15 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("short30") != null){
                        if(dev.get("alts", Document.class).get("short30") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("short30"),prod.get("alts", Document.class).get("short30"), "Failed for alts.short30 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("short30") != null, "Failed because Dev.alts.short30 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("short8") != null){
                        if(dev.get("alts", Document.class).get("short8") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("short8"),prod.get("alts", Document.class).get("short8"), "Failed for alts.short8 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("short8") != null, "Failed because Dev.alts.short8 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("short50") != null){
                        if(dev.get("alts", Document.class).get("short50") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("short50"),prod.get("alts", Document.class).get("short50"), "Failed for alts.short50 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("short50") != null, "Failed because Dev.alts.short50 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("alias") != null){
                        if(dev.get("alts", Document.class).get("alias") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("alias"),prod.get("alts", Document.class).get("alias"), "Failed for alts.alias : " + dev.get("_id").toString() );
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("alias") != null, "Failed because Dev.alts.alias is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("alias2") != null){
                        if(dev.get("alts", Document.class).get("alias2") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("alias2"),prod.get("alts", Document.class).get("alias2"), "Failed for alts.alias2 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("alias2") != null, "Failed because Dev.alts.alias2 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("alias3") != null){
                        if(dev.get("alts", Document.class).get("alias3") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("alias3"),prod.get("alts", Document.class).get("alias3"), "Failed for alts.alias3 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("alias3") != null, "Failed because Dev.alts.alias3 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("alias4") != null){
                        if(dev.get("alts", Document.class).get("alias4") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("alias4"),prod.get("alts", Document.class).get("alias4"), "Failed for alts.alias4 : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("alias4") != null, "Failed because Dev.alts.alias4 is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("alts", Document.class).get("subtitle") != null){
                        if(dev.get("alts", Document.class).get("subtitle") != null){
                            softAssert.assertEquals(dev.get("alts", Document.class).get("subtitle"),prod.get("alts", Document.class).get("subtitle"), "Failed for alts.subtitle : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("alts", Document.class).get("subtitle") != null, "Failed because Dev.alts.subtitle is null " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("alts") != null, "Failed because Dev.alts is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("image") != null){
                if(dev.get("image") != null){
                    if(prod.get("image", Document.class).get("id") != null){
                        if(dev.get("image", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("image", Document.class).get("id"),prod.get("image", Document.class).get("id"), "Failed for image.id : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("image", Document.class).get("id") != null, "Failed because Dev.image.id is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("image", Document.class).get("l_media_image") != null){
                        if(dev.get("image", Document.class).get("l_media_image") != null) {
                            softAssert.assertEquals(dev.get("image", Document.class).get("l_media_image"),prod.get("image", Document.class).get("l_media_image"), "Failed for image.l_media_image : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("image", Document.class).get("l_media_image") != null, "Failed because Dev.image.l_media_image is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("image", Document.class).get("zoom") != null){
                        if(dev.get("image", Document.class).get("zoom") != null) {
                            softAssert.assertEquals(dev.get("image", Document.class).get("zoom"),prod.get("image", Document.class).get("zoom"), "Failed for image.zoom : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("image", Document.class).get("zoom") != null, "Failed because Dev.image.zoom is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("image", Document.class).get("type") != null){
                        if(dev.get("image", Document.class).get("type") != null) {
                            softAssert.assertEquals(dev.get("image", Document.class).get("type"),prod.get("image", Document.class).get("type") , "Failed for image.type : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("image", Document.class).get("type") != null, "Failed because Dev.image.type is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("image", Document.class).get("genres") != null){
                        if(dev.get("image", Document.class).get("genres") != null) {
                            softAssert.assertEquals(dev.get("image", Document.class).get("genres"),prod.get("image", Document.class).get("genres"), "Failed for image.genres : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("image", Document.class).get("genres") != null, "Failed because Dev.image.genres is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("image") != null, "Failed because Dev.image is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("content") != null){
                if(dev.get("content") != null){
                    if(prod.get("content", Document.class).get("id") != null){
                        if(dev.get("content", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("id"),prod.get("content", Document.class).get("id"), "Failed for content.id : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("id") != null, "Failed because Dev.content.id is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_episode") != null){
                        if(dev.get("content", Document.class).get("r_episode") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_episode"),prod.get("content", Document.class).get("r_episode"), "Failed for content.r_episode : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_episode") != null, "Failed because Dev.content.r_episode is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_movie") != null){
                        if(dev.get("content", Document.class).get("r_movie") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_movie"),prod.get("content", Document.class).get("r_movie"), "Failed for content.r_movie : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_movie") != null, "Failed because Dev.content.r_movie is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_series") != null){
                        if(dev.get("content", Document.class).get("r_series") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_series"),prod.get("content", Document.class).get("r_series"), "Failed for content.r_series : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_series") != null, "Failed because Dev.content.r_series is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_other") != null){
                        if(dev.get("content", Document.class).get("r_other") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_other"),prod.get("content", Document.class).get("r_other"), "Failed for content.r_other : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_other") != null, "Failed because Dev.content.r_other is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_season") != null){
                        if(dev.get("content", Document.class).get("r_season") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_season"),prod.get("content", Document.class).get("r_season"), "Failed for content.r_season : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_season") != null, "Failed because Dev.content.r_season is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_clip") != null){
                        if(dev.get("content", Document.class).get("r_clip") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_clip"),prod.get("content", Document.class).get("r_clip"), "Failed for content.r_clip : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_clip") != null, "Failed because Dev.content.r_clip is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("r_content_images") != null){
                        if(dev.get("content", Document.class).get("r_content_images") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("r_content_images"),prod.get("content", Document.class).get("r_content_images"), "Failed for content.r_content_images : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("r_content_images") != null, "Failed because Dev.content.r_content_images is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("title") != null){
                        if(dev.get("content", Document.class).get("title") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("title"),prod.get("content", Document.class).get("title"), "Failed for content.title : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("title") != null, "Failed because Dev.content.title is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("year") != null){
                        if(dev.get("content", Document.class).get("year") != null) {
                            softAssert.assertEquals(dev.get("content", Document.class).get("year"),prod.get("content", Document.class).get("year"), "Failed for content.year : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("year") != null, "Failed because Dev.content.year is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("content", Document.class).get("series") != null){
                        if(dev.get("content", Document.class).get("series") != null) {
                            if (prod.get("content", Document.class).get("series", Document.class).get("id") != null) {
                                if (dev.get("content", Document.class).get("series", Document.class).get("id") != null) {
                                    softAssert.assertEquals(dev.get("content", Document.class).get("series", Document.class).get("id"), prod.get("content", Document.class).get("series", Document.class).get("id"), "Failed for content.series.id : " + dev.get("_id").toString());
                                } else {
                                    softAssert.assertTrue(dev.get("content", Document.class).get("series", Document.class).get("id") != null, "Failed because Dev.content.series.id is null " + dev.get("_id").toString());
                                }
                            }
                            if (prod.get("content", Document.class).get("series", Document.class).get("r_series") != null) {
                                if (dev.get("content", Document.class).get("series", Document.class).get("r_series") != null) {
                                    softAssert.assertEquals(dev.get("content", Document.class).get("series", Document.class).get("r_series"), prod.get("content", Document.class).get("series", Document.class).get("r_series"), "Failed for content.series.r_series : " + dev.get("_id").toString());
                                } else {
                                    softAssert.assertTrue(dev.get("content", Document.class).get("series", Document.class).get("r_series") != null, "Failed because Dev.content.series.r_series is null " + dev.get("_id").toString());
                                }
                            }
                            if (prod.get("content", Document.class).get("series", Document.class).get("title") != null) {
                                if (dev.get("content", Document.class).get("series", Document.class).get("title") != null) {
                                    softAssert.assertEquals(dev.get("content", Document.class).get("series", Document.class).get("title"), prod.get("content", Document.class).get("series", Document.class).get("title"), "Failed for content.series.title : " + dev.get("_id").toString());
                                } else {
                                    softAssert.assertTrue(dev.get("content", Document.class).get("series", Document.class).get("title") != null, "Failed because Dev.content.series.title is null " + dev.get("_id").toString());
                                }
                            }
                        } else {
                            softAssert.assertTrue(dev.get("content", Document.class).get("series", Document.class) != null, "Failed because Dev.content.series is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("content") != null, "Failed because dev.content is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("source") != null) {
                if (dev.get("source") != null) {
                    if (prod.get("source", Document.class).get("id") != null) {
                        if (dev.get("source", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("source", Document.class).get("id"), prod.get("source", Document.class).get("id"), "Failed for source.id : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("source", Document.class).get("id") != null, "Failed because Dev.source.id is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("source", Document.class).get("r_source") != null) {
                        if (dev.get("source", Document.class).get("r_source") != null) {
                            softAssert.assertEquals(dev.get("source", Document.class).get("r_source"), prod.get("source", Document.class).get("r_source"), "Failed for source.r_source : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("source", Document.class).get("r_source") != null, "Failed because Dev.source.r_source is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("source", Document.class).get("name") != null) {
                        if (dev.get("source", Document.class).get("name") != null) {
                            softAssert.assertEquals(dev.get("source", Document.class).get("name"), prod.get("source", Document.class).get("name"), "Failed for source.name : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("source", Document.class).get("name") != null, "Failed because Dev.source.name is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("source") != null, "Failed because dev.source is null " + dev.get("_id").toString());
                }
            }
            if(prod.get("sport") != null) {
                if (dev.get("sport") != null) {
                    if (prod.get("sport", Document.class).get("id") != null) {
                        if (dev.get("sport", Document.class).get("id") != null) {
                            softAssert.assertEquals(dev.get("sport", Document.class).get("id"), prod.get("sport", Document.class).get("id"), "Failed for sport.id : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("id") != null, "Failed because Dev.sport.id is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("r_game") != null) {
                        if (dev.get("sport", Document.class).get("r_game") != null) {
                            softAssert.assertEquals(dev.get("sport", Document.class).get("r_game"), prod.get("sport", Document.class).get("r_game"), "Failed for sport.r_game : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("r_game") != null, "Failed because Dev.sport.r_game is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("type") != null) {
                        if (dev.get("sport", Document.class).get("type") != null) {
                            softAssert.assertEquals(dev.get("sport", Document.class).get("type"), prod.get("sport", Document.class).get("type"), "Failed for sport.type : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("type") != null, "Failed because Dev.sport.type is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("gender") != null) {
                        if (dev.get("sport", Document.class).get("gender") != null) {
                            softAssert.assertEquals(dev.get("sport", Document.class).get("gender"), prod.get("sport", Document.class).get("gender"), "Failed for sport.gender : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("gender") != null, "Failed because Dev.sport.gender is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("note") != null) {
                        if (dev.get("sport", Document.class).get("note") != null) {
                            softAssert.assertEquals(dev.get("sport", Document.class).get("note"), prod.get("sport", Document.class).get("note"), "Failed for sport.note : " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("note") != null, "Failed because Dev.sport.note is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("teams") != null) {
                        if (dev.get("sport", Document.class).get("teams") != null) {
                            ArrayList<String> prodSportDetail = new ArrayList<>();
                            for(int i = 0; i < ((List<Document>)(prod.get("sport", Document.class).get("teams"))).size(); i++){
                                String gameId = "";
                                String r_team = "";
                                String name = "";
                                String major = "";
                                String minor = "";
                                String isHome = "";
                                String imageId = "";
                                String l_mediaImage = "";
                                String zoom = "";
                                String type = "";
                                String schoolId = "";
                                String r_school = "";
                                String schoolName = "";
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("id") != null){
                                    gameId = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("id").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("r_team") != null){
                                    r_team = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("r_team").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("name") != null){
                                    name = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("name").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("major") != null){
                                    major = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("major").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("minor") != null){
                                    minor = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("minor").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("isHome") != null){
                                    isHome = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("isHome").toString();
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image") != null){
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("id") != null){
                                        imageId = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("id").toString();
                                    }
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("l_media_image") != null){
                                        l_mediaImage = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("l_media_image").toString();
                                    }
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("zoom") != null){
                                        zoom = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("zoom").toString();
                                    }
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("type") != null){
                                        type = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("type").toString();
                                    }
                                }
                                if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school") != null){
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("id") != null){
                                        schoolId = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("id").toString();
                                    }
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("r_school") != null){
                                        r_school = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("r_school").toString();
                                    }
                                    if(((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("name") != null){
                                        schoolName = ((List<Document>)(prod.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("name").toString();
                                    }
                                }
                                String finalString = gameId + r_team + name + major + minor + isHome + imageId + l_mediaImage + zoom + type + schoolId + r_school + schoolName;
                                prodSportDetail.add(finalString);
                            }

                            ArrayList<String> devSportDetail = new ArrayList<>();
                            for(int i = 0; i < ((List<Document>)(dev.get("sport", Document.class).get("teams"))).size(); i++){
                                String gameId = "";
                                String r_team = "";
                                String name = "";
                                String major = "";
                                String minor = "";
                                String isHome = "";
                                String imageId = "";
                                String l_mediaImage = "";
                                String zoom = "";
                                String type = "";
                                String schoolId = "";
                                String r_school = "";
                                String schoolName = "";
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("id") != null){
                                    gameId = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("id").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("r_team") != null){
                                    r_team = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("r_team").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("name") != null){
                                    name = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("name").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("major") != null){
                                    major = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("major").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("minor") != null){
                                    minor = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("minor").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("isHome") != null){
                                    isHome = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("isHome").toString();
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image") != null){
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("id") != null){
                                        imageId = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("id").toString();
                                    }
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("l_media_image") != null){
                                        l_mediaImage = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("l_media_image").toString();
                                    }
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("zoom") != null){
                                        zoom = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("zoom").toString();
                                    }
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("type") != null){
                                        type = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("image", Document.class).get("type").toString();
                                    }
                                }
                                if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school") != null){
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("id") != null){
                                        schoolId = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("id").toString();
                                    }
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("r_school") != null){
                                        r_school = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("r_school").toString();
                                    }
                                    if(((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("name") != null){
                                        schoolName = ((List<Document>)(dev.get("sport", Document.class).get("teams"))).get(i).get("school", Document.class).get("name").toString();
                                    }
                                }
                                String finalString = gameId + r_team + name + major + minor + isHome + imageId + l_mediaImage + zoom + type + schoolId + r_school + schoolName;
                                devSportDetail.add(finalString);
                            }
                            softAssert.assertTrue(devSportDetail.containsAll(prodSportDetail), " Failed because Sport Details not match " + dev.get("_id").toString());
                        } else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("teams") != null, "Failed because Dev.sport.teams is null " + dev.get("_id").toString());
                        }
                    }
                    if (prod.get("sport", Document.class).get("league") != null) {
                        if(dev.get("sport", Document.class).get("league") != null){
                            if(prod.get("sport", Document.class).get("league", Document.class).get("id") != null){
                                if(dev.get("sport", Document.class).get("league", Document.class).get("id") != null){
                                    softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("id"),prod.get("sport", Document.class).get("league", Document.class).get("id"), "Failed for sport.league.id : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("id") != null, " Failed because Dev.sport.league.id is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("sport", Document.class).get("league", Document.class).get("r_league") != null){
                                if(dev.get("sport", Document.class).get("league", Document.class).get("r_league") != null){
                                    softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("r_league"),prod.get("sport", Document.class).get("league", Document.class).get("r_league"), "Failed for sport.league.r_league : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("r_league") != null, " Failed because Dev.sport.league.r_league is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("sport", Document.class).get("league", Document.class).get("type") != null){
                                if(dev.get("sport", Document.class).get("league", Document.class).get("type") != null){
                                    softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("type"),prod.get("sport", Document.class).get("league", Document.class).get("type"), "Failed for sport.league.type : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("type") != null, " Failed because Dev.sport.league.type is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("sport", Document.class).get("league", Document.class).get("name") != null){
                                if(dev.get("sport", Document.class).get("league", Document.class).get("name") != null){
                                    softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("name"),prod.get("sport", Document.class).get("league", Document.class).get("name"), "Failed for sport.league.name : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("name") != null, " Failed because Dev.sport.league.name is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("sport", Document.class).get("league", Document.class).get("image") != null){
                                if(dev.get("sport", Document.class).get("league", Document.class).get("image") != null){
                                    if(prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("id") != null){
                                        if(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("id") != null){
                                            softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("id"),prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("id"), "Failed for sport.league.image.id : " + dev.get("_id").toString());
                                        } else {
                                            softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("id") != null, " Failed because Dev.sport.league.image.id is null " + dev.get("_id").toString());
                                        }
                                    }
                                    if(prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("l_media_image") != null){
                                        if(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("l_media_image") != null){
                                            softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("l_media_image"),prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("l_media_image"), "Failed for sport.league.image.l_media_image : " + dev.get("_id").toString());
                                        } else {
                                            softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("l_media_image") != null, " Failed because Dev.sport.league.image.l_media_image is null " + dev.get("_id").toString());
                                        }
                                    }
                                    if(prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("zoom") != null){
                                        if(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("zoom") != null){
                                            softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("zoom"),prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("zoom"), "Failed for sport.league.image.zoom : " + dev.get("_id").toString());
                                        } else {
                                            softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("zoom") != null, " Failed because Dev.sport.league.image.zoom is null " + dev.get("_id").toString());
                                        }
                                    }
                                    if(prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("type") != null){
                                        if(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("type") != null){
                                            softAssert.assertEquals(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("type"),prod.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("type"), "Failed for sport.league.image.type : " + dev.get("_id").toString());
                                        } else {
                                            softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("image", Document.class).get("type") != null, " Failed because Dev.sport.league.image.type is null " + dev.get("_id").toString());
                                        }
                                    }
                                }else {
                                    softAssert.assertTrue(dev.get("sport", Document.class).get("league", Document.class).get("image") != null, "Failed because Dev.sport.league.image is null " + dev.get("_id").toString());
                                }
                            }
                        }else {
                            softAssert.assertTrue(dev.get("sport", Document.class).get("league") != null, "Failed because Dev.sport.league is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("sport") != null, "Failed because dev.sport is null" + dev.get("_id").toString());
                }
            }
            if (prod.get("ScheduleTimeApproximateYn") != null) {
                if (dev.get("ScheduleTimeApproximateYn") != null) {
                    softAssert.assertEquals(dev.get("ScheduleTimeApproximateYn").toString(), prod.get("ScheduleTimeApproximateYn").toString(), "Failed for ScheduleTimeApproximateYn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("ScheduleTimeApproximateYn") != null, "Failed because Dev.ScheduleTimeApproximateYn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("AirDateTime") != null) {
                if (dev.get("AirDateTime") != null) {
                    softAssert.assertEquals(dev.get("AirDateTime").toString(), prod.get("AirDateTime").toString(), "Failed for AirDateTime : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("AirDateTime") != null, "Failed because Dev.AirDateTime is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("EndAirDateTime") != null) {
                if (dev.get("EndAirDateTime") != null) {
                    softAssert.assertEquals(dev.get("EndAirDateTime").toString(), prod.get("EndAirDateTime").toString(), "Failed for EndAirDateTime : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("EndAirDateTime") != null, "Failed because Dev.EndAirDateTime is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("SubjectToBlackoutYn") != null) {
                if (dev.get("SubjectToBlackoutYn") != null) {
                    softAssert.assertEquals(dev.get("SubjectToBlackoutYn").toString(), prod.get("SubjectToBlackoutYn").toString(), "Failed for SubjectToBlackoutYn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("SubjectToBlackoutYn") != null, "Failed because Dev.SubjectToBlackoutYn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("ScheduledDuration") != null) {
                if (dev.get("ScheduledDuration") != null) {
                    softAssert.assertEquals(dev.get("ScheduledDuration").toString(), prod.get("ScheduledDuration").toString(), "Failed for ScheduledDuration : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("ScheduledDuration") != null, "Failed because Dev.ScheduledDuration is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("AiringTypeName") != null) {
                if (dev.get("AiringTypeName") != null) {
                    softAssert.assertEquals(dev.get("AiringTypeName").toString(), prod.get("AiringTypeName").toString(), "Failed for AiringTypeName : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("AiringTypeName") != null, "Failed because Dev.AiringTypeName is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_showingType") != null) {
                if (dev.get("d_showingType") != null) {
                    softAssert.assertEquals(dev.get("d_showingType").toString(), prod.get("d_showingType").toString(), "Failed for d_showingType : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_showingType") != null, "Failed because Dev.d_showingType is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("parts") != null) {
                if (dev.get("parts") != null) {
                    if(prod.get("parts", Document.class).get("SchedulePartNumber") != null){
                        if(dev.get("parts", Document.class).get("SchedulePartNumber") != null){
                            softAssert.assertEquals(dev.get("parts", Document.class).get("SchedulePartNumber"), prod.get("parts", Document.class).get("SchedulePartNumber"), "Failed for parts.SchedulePartNumber : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("parts", Document.class).get("SchedulePartNumber") != null, " Failed because parts.SchedulePartNumber is null " + dev.get("_id").toString());
                        }
                    }
                    if(prod.get("parts", Document.class).get("ScheduleTotalParts") != null){
                        if(dev.get("parts", Document.class).get("ScheduleTotalParts") != null){
                            softAssert.assertEquals(dev.get("parts", Document.class).get("ScheduleTotalParts"), prod.get("parts", Document.class).get("ScheduleTotalParts"), "Failed for parts.ScheduleTotalParts : " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("parts", Document.class).get("ScheduleTotalParts") != null, " Failed because parts.ScheduleTotalParts is null " + dev.get("_id").toString());
                        }
                    }
                } else {
                    softAssert.assertTrue(dev.get("parts") != null, "Failed because Dev.parts is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_category") != null) {
                if (dev.get("d_category") != null) {
                    softAssert.assertEquals(dev.get("d_category").toString(), prod.get("d_category").toString(), "Failed for d_category : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_category") != null, "Failed because Dev.d_category is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("Ratings") != null){
                if(dev.get("Ratings") != null){
                    if(prod.get("Ratings", Document.class).get("MovieRatings") != null){
                        if(dev.get("Ratings", Document.class).get("MovieRatings") != null){
                            ArrayList<String> prodRating = new ArrayList<>();
                            for(int i=0; i < ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).size(); i++){
                                String t_default = "";
                                String ratingSystemName = "";
                                String ratingValue = "";
                                String ratingMedium = "";
                                String tvRatingReasons = "";
                                if(((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("t_default") != null){
                                    t_default = ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("t_default").toString().toLowerCase();
                                }
                                if(((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingSystemName") != null){
                                    ratingSystemName = ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingSystemName").toString().toLowerCase();
                                }
                                if(((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingValue") != null){
                                    ratingValue = ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingValue").toString().toLowerCase();
                                }
                                if(((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingMedium") != null){
                                    ratingMedium = ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingMedium").toString().toLowerCase();
                                }
                                if(((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("TvRatingReasons") != null){
                                    tvRatingReasons = ((List<Document>)prod.get("Ratings", Document.class).get("MovieRatings")).get(i).get("TvRatingReasons").toString().toLowerCase();
                                }
                                String finalString = t_default + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                                prodRating.add(finalString);
                            }

                            ArrayList<String> devRating = new ArrayList<>();
                            for(int i=0; i < ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).size(); i++){
                                String t_default = "";
                                String ratingSystemName = "";
                                String ratingValue = "";
                                String ratingMedium = "";
                                String tvRatingReasons = "";
                                if(((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("t_default") != null){
                                    t_default = ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("t_default").toString().toLowerCase();
                                }
                                if(((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingSystemName") != null){
                                    ratingSystemName = ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingSystemName").toString().toLowerCase();
                                }
                                if(((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingValue") != null){
                                    ratingValue = ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingValue").toString().toLowerCase();
                                }
                                if(((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingMedium") != null){
                                    ratingMedium = ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("RatingMedium").toString().toLowerCase();
                                }
                                if(((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("TvRatingReasons") != null){
                                    tvRatingReasons = ((List<Document>)dev.get("Ratings", Document.class).get("MovieRatings")).get(i).get("TvRatingReasons").toString().toLowerCase();
                                }
                                String finalString = t_default + ratingSystemName + ratingValue + ratingMedium + tvRatingReasons;
                                devRating.add(finalString);
                            }
                            softAssert.assertTrue(devRating.containsAll(prodRating), "Failed because MovieRatings doesn't match " + dev.get("_id").toString());
                        }else {
                            softAssert.assertTrue(dev.get("Ratings", Document.class).get("MovieRatings") != null, "Failed because Dev.Ratings.MovieRatings is null " + dev.get("_id").toString());
                        }
                    }

                    if(prod.get("Ratings", Document.class).get("tvRatings") != null) {
                        if (dev.get("Ratings", Document.class).get("tvRatings") != null) {
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("t_default") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("t_default") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("t_default"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("t_default"), "Failed for Dev.Ratings.tvRatings.t_default : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("t_default") != null, "Failed because Dev.Ratings.tvRatings.t_default is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingSystemName") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingSystemName") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingSystemName"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingSystemName"), "Failed for Dev.Ratings.tvRatings.RatingSystemName : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingSystemName") != null, "Failed because Dev.Ratings.tvRatings.RatingSystemName is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingValue") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingValue") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingValue"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingValue"), "Failed for Dev.Ratings.tvRatings.RatingValue : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingValue") != null, "Failed because Dev.Ratings.tvRatings.RatingValue is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingMedium") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingMedium") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingMedium"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingMedium"), "Failed for Dev.Ratings.tvRatings.RatingMedium : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("RatingMedium") != null, "Failed because Dev.Ratings.tvRatings.RatingMedium is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("countries") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("countries") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("countries"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("countries"), "Failed for Dev.Ratings.tvRatings.countries : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("countries") != null, "Failed because Dev.Ratings.tvRatings.countries is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("d_ratingReasons") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("d_ratingReasons") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("d_ratingReasons"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("d_ratingReasons"), "Failed for Dev.Ratings.tvRatings.d_ratingReasons : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("d_ratingReasons") != null, "Failed because Dev.Ratings.tvRatings.d_ratingReasons is null " + dev.get("_id").toString());
                                }
                            }
                            if(prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("TvRatingReasons") != null){
                                if(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("TvRatingReasons") != null){
                                    softAssert.assertEquals(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("TvRatingReasons"),prod.get("Ratings", Document.class).get("tvRatings", Document.class).get("TvRatingReasons"), "Failed for Dev.Ratings.tvRatings.TvRatingReasons : " + dev.get("_id").toString());
                                }else {
                                    softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings", Document.class).get("TvRatingReasons") != null, "Failed because Dev.Ratings.tvRatings.TvRatingReasons is null " + dev.get("_id").toString());
                                }
                            }
                        }else {
                            softAssert.assertTrue(dev.get("Ratings", Document.class).get("tvRatings") != null, "Failed because Dev.Ratings.tvRatings is null " + dev.get("_id").toString());
                        }
                    }
                }else {
                    softAssert.assertTrue(dev.get("Ratings") != null, "Failed because Dev.Ratings is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("Tvadvisory") != null) {
                if (dev.get("Tvadvisory") != null) {
                    softAssert.assertEquals(dev.get("Tvadvisory").toString(), prod.get("Tvadvisory").toString(), "Failed for Tvadvisory : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("Tvadvisory") != null, "Failed because Dev.Tvadvisory is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("isCC") != null) {
                if (dev.get("isCC") != null) {
                    softAssert.assertEquals(dev.get("isCC").toString(), prod.get("isCC").toString(), "Failed for isCC : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("isCC") != null, "Failed because Dev.isCC is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_captionType") != null) {
                if (dev.get("d_captionType") != null) {
                    softAssert.assertEquals(dev.get("d_captionType").toString(), prod.get("d_captionType").toString(), "Failed for d_captionType : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_captionType") != null, "Failed because Dev.d_captionType is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_audioLevel") != null) {
                if (dev.get("d_audioLevel") != null) {
                    softAssert.assertEquals(dev.get("d_audioLevel").toString(), prod.get("d_audioLevel").toString(), "Failed for d_audioLevel : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_audioLevel") != null, "Failed because Dev.d_audioLevel is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_aspectRatio") != null) {
                if (dev.get("d_aspectRatio") != null) {
                    softAssert.assertEquals(dev.get("d_aspectRatio").toString(), prod.get("d_aspectRatio").toString(), "Failed for d_aspectRatio : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_aspectRatio") != null, "Failed because Dev.d_aspectRatio is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_programColorType") != null) {
                if (dev.get("d_programColorType") != null) {
                    softAssert.assertEquals(dev.get("d_programColorType").toString(), prod.get("d_programColorType").toString(), "Failed for d_programColorType : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_programColorType") != null, "Failed because Dev.d_programColorType is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("LetterBoxYn") != null) {
                if (dev.get("LetterBoxYn") != null) {
                    softAssert.assertEquals(dev.get("LetterBoxYn").toString(), prod.get("LetterBoxYn").toString(), "Failed for LetterBoxYn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("LetterBoxYn") != null, "Failed because Dev.LetterBoxYn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("subTitle") != null) {
                if (dev.get("subTitle") != null) {
                    softAssert.assertEquals(dev.get("subTitle").toString(), prod.get("subTitle").toString(), "Failed for subTitle : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("subTitle") != null, "Failed because Dev.subTitle is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("isHdTV") != null) {
                if (dev.get("isHdTV") != null) {
                    softAssert.assertEquals(dev.get("isHdTV").toString(), prod.get("isHdTV").toString(), "Failed for isHdTV : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("isHdTV") != null, "Failed because Dev.isHdTV is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_hdtvLevel") != null) {
                if (dev.get("d_hdtvLevel") != null) {
                    softAssert.assertEquals(dev.get("d_hdtvLevel").toString(), prod.get("d_hdtvLevel").toString(), "Failed for d_hdtvLevel : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_hdtvLevel") != null, "Failed because Dev.d_hdtvLevel is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("d_threeD") != null) {
                if (dev.get("d_threeD") != null) {
                    softAssert.assertEquals(dev.get("d_threeD").toString(), prod.get("d_threeD").toString(), "Failed for d_threeD : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("d_threeD") != null, "Failed because Dev.d_threeD is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("Dvsyn") != null) {
                if (dev.get("Dvsyn") != null) {
                    softAssert.assertEquals(dev.get("Dvsyn").toString(), prod.get("Dvsyn").toString(), "Failed for Dvsyn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("Dvsyn") != null, "Failed because Dev.Dvsyn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("ScheduleSapyn") != null) {
                if (dev.get("ScheduleSapyn") != null) {
                    softAssert.assertEquals(dev.get("ScheduleSapyn").toString(), prod.get("ScheduleSapyn").toString(), "Failed for ScheduleSapyn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("ScheduleSapyn") != null, "Failed because Dev.ScheduleSapyn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("ScheduleSportsYn") != null) {
                if (dev.get("ScheduleSportsYn") != null) {
                    softAssert.assertEquals(dev.get("ScheduleSportsYn").toString(), prod.get("ScheduleSportsYn").toString(), "Failed for ScheduleSportsYn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("ScheduleSportsYn") != null, "Failed because Dev.ScheduleSportsYn is null " + dev.get("_id").toString());
                }
            }
            if (prod.get("SyndicatedYn") != null) {
                if (dev.get("SyndicatedYn") != null) {
                    softAssert.assertEquals(dev.get("SyndicatedYn").toString(), prod.get("SyndicatedYn").toString(), "Failed for SyndicatedYn : " + dev.get("_id").toString());
                } else {
                    softAssert.assertTrue(dev.get("SyndicatedYn") != null, "Failed because Dev.SyndicatedYn is null " + dev.get("_id").toString());
                }
            }
            i++;
            System.out.println(i);
        } else {
            System.out.println("ID not present in prod : " + dev.get("_id").toString());
        }

    }
}
