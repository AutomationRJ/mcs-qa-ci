package com.tivo.test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tivo.Validators.SportsValidator;
import com.tivo.common.*;
import com.tivo.utility.Util;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.bson.Document;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.gte;

/**
 * Created by rjaiswal on 5/25/2017.
 */
public class SportsAPI {

    private static Map<String,String> map;
    private static  String version;
    private static MongoDatabase db;

    @BeforeTest(alwaysRun = true)
    public void setup() throws Exception{
        map = Util.loadProperties();
        RequestSpec.setBaseURI(RequestSpec.getBaseURI());
        RequestSpec.setBasePath(map.get("LookupBasePath"));
        version = map.get("Version");
        db = new MongoConn().db(map.get("MongoHost"),map.get("MongoDatabase"));
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateGame_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("GameCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("GameCollection"));
        Document findQuery = coll.find(and(exists("teams.image",true),exists("league.image",true),exists("teams.school",true),exists("tv",true),exists("teams.id",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("gameId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_GAME_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.gameValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateGame_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("GameCollection"));
        Document findQuery = coll.find(and(exists("teams.image",true),exists("league.image",true),exists("teams.school",true),exists("tv",true),exists("teams.id",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "gameId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_GAME_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateGame_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("gameId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_GAME_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateGame_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("gameId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_GAME_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateLeague_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("LeagueCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("LeagueCollection"));
        Document findQuery = coll.find(and(exists("image",true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("leagueId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_LEAGUE_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.leagueValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateLeague_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("LeagueCollection"));
        Document findQuery = coll.find(and(exists("image",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "leagueId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_LEAGUE_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateLeague_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("leagueId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_LEAGUE_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateLeague_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("leagueId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_LEAGUE_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"} , enabled = false)
    public void validateLeagueCompetition_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("LeagueCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("LeagueCompetitionCollection"));
        Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("leagueId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_LEAGUE_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.leagueCompetitionValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateLeagueCompetition_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("LeagueCompetitionCollection"));
        Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "leagueId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_LEAGUE_COMPETITION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateLeagueCompetition_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("leagueId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_LEAGUE_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateLeagueCompetition_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("leagueId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_LEAGUE_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateSchool_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("SchoolCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("SchoolCollection"));
        Document findQuery = coll.find(and(exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("schoolId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_SCHOOL_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.schoolValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateSchool_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("SchoolCollection"));
        Document findQuery = coll.find(and(exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "schoolId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_SCHOOL_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateSchool_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("schoolId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SCHOOL_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateSchool_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("schoolId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_SCHOOL_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateTeam_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("TeamCollection"));
        Document findQuery = coll.find(and(exists("coach.image",true),exists("league.image",true),exists("school", true),exists("_del",false),gte("_v",version))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("teamId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_TEAM_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.teamValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateTeam_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("TeamCollection"));
        Document findQuery = coll.find(and(exists("coach.image",true),exists("league.image",true),exists("school", true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "teamId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_TEAM_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateTeam_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateTeam_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateTeamRoster_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("TeamRosterCollection"));
        Document findQuery = coll.find(and(exists("roster",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("teamId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_TEAM_ROSTER_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.teamRosterValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateTeamRoster_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("TeamRosterCollection"));
        Document findQuery = coll.find(and(exists("roster",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "teamId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_TEAM_ROSTER_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateTeamRoster_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_ROSTER_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateTeamRoster_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_ROSTER_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"}, enabled = false)
    public void validateTeamCompetition_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("TeamCompetitionCollection"));
        Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("teamId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_TEAM_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.teamCompetitionValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateTeamCompetition_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("TeamCompetitionCollection"));
        Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "teamId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_TEAM_COMPETITION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateTeamCompetition_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateTeamCompetition_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("teamId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_TEAM_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateCompetition_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("CompetitionCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("competitionId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.competitionValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateCompetition_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("CompetitionCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "competitionId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_COMPETITION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateCompetition_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("competitionId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateCompetition_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("competitionId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateCompetitionChildren_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("CompetitionChildrenCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("competitionId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_COMPETITION_CHILDREN_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        //SportsValidator.teamValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateCompetitionChildren_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("CompetitionChildrenCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "competitionId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_COMPETITION_CHILDREN_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateCompetitionChildren_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("competitionId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_COMPETITION_CHILDREN_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateCompetitionChildren_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("competitionId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_COMPETITION_CHILDREN_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"}, enabled = false)
    public void validatePersonCompetition_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCompetitionCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("personId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_PERSON_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.personCompetitionValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validatePersonCompetition_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("PersonCompetitionCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "personId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_PERSON_COMPETITION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validatePersonCompetition_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"},enabled = false)
    public void validatePersonCompetition_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("personId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_PERSON_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateGroup_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("GroupCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("groupId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_GROUP_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.groupValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateGroup_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("GroupCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "groupId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_GROUP_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateGroup_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("groupId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_GROUP_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateGroup_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("groupId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_GROUP_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"})
    public void validateOrganizationConcept_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OrganizationConceptCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("organizationId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_ORGANIZATION_CONCEPT_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.organizationConceptValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"})
    public void validateOrganizationConcept_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OrganizationConceptCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "organizationId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_ORGANIZATION_CONCEPT_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateOrganizationConcept_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("organizationId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_ORGANIZATION_CONCEPT_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"})
    public void validateOrganizationConcept_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("organizationId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_ORGANIZATION_CONCEPT_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression", "Sanity"}, enabled = false)
    public void validateOrganizationConceptCompetition_200_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
        MongoCollection<Document> coll = db.getCollection(map.get("OrganizationConceptCompetitionCollection"));
        Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
        //Document findQuery = coll.find(eq("_id",903593619)).first();
        String id = findQuery.get("_id").toString();
        requestSpecification.pathParam("organizationId",id);
        Response response = new ResponseSpec().getResponse(requestSpecification, EndPoint.GET_ORGANIZATION_CONCEPT_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_OK);
        SportsValidator.organizationConceptCompetitionValidator(response,findQuery);
    }

    @Test(groups = {"Regression", "Etag"}, enabled = false)
    public void validateOrganizationConceptCompetition_304_response(){
        MongoCollection<Document> coll = db.getCollection(map.get("OrganizationConceptCollection"));
        Document findQuery = coll.find(exists("_del",false)).first();
        String id = findQuery.get("_id").toString();
        String pathParam = "organizationId";
        EtagVerfication.lookupAPIeTagVerfication(pathParam,id,EndPoint.GET_ORGANIZATION_CONCEPT_COMPETITION_LOOKUP_PATH_PARAM,map);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateOrganizationConceptCompetition_404_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("organizationId",Long.toString(Util.randomNumber()));
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_ORGANIZATION_CONCEPT_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_NOT_FOUND);
        //System.out.println(response);
    }

    @Test(groups = {"Sports", "Regression"}, enabled = false)
    public void validateOrganizationConceptCompetition_400_response(){
        RequestSpec request = new RequestSpec();
        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
        requestSpecification.pathParam("organizationId","");
        Response response = new ResponseSpec().getResponse(requestSpecification,EndPoint.GET_ORGANIZATION_CONCEPT_COMPETITION_LOOKUP_PATH_PARAM, HttpStatus.SC_BAD_REQUEST);
        //System.out.println(response);
    }

//    @Test(groups = {"Regression"})
//    public void testVeveo_200_response(){
//        RequestSpec request = new RequestSpec();
//        RequestSpecification requestSpecification = request.setOAuth(map.get("ConsumerKey").toString(),map.get("ConsumerSecret").toString()).log().all();
//        //MongoCollection<Document> coll = new MongoConn().mongoJDBC(map.get("MongoHost"),map.get("MongoDatabase"),map.get("TeamCollection"));
//        //MongoCollection<Document> coll = db.getCollection(map.get("OrganizationConceptCompetitionCollection"));
//        //Document findQuery = coll.find(and(exists("competitions",true),exists("_del",false))).first();
//        //Document findQuery = coll.find(eq("_id",903593619)).first();
//        //String id = findQuery.get("_id").toString();
//        //requestSpecification.pathParam("organizationId",id);
//        Response response = requestSpecification.get("http://cdn-dev.zreem.com/discover/2.1/2/conversation?W=show%20me%20movies%20of%20tom%20cruise&lineupId=4068852743&vsc=&ECT=5&ver=3.0&XUA=rovicondor&PN=524300&RFTR=256&RPR=10&idns=dl,-cat&criteria=W-query,vsc-context&me=discover_all_video_by_conversation&sub=content");
//        System.out.println(response.getStatusCode());
//        //SportsValidator.organizationConceptCompetitionValidator(response,findQuery);
//    }

    @AfterTest
    public void afterTest (){
        //Reset Values
        RequestSpec.resetBaseURI();
        RequestSpec.resetBasePath();
    }
}
