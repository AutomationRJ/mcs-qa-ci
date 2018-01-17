package com.tivo.common;

/**
 * Created by rjaiswal on 4/27/2017.
 */
public interface EndPoint {

    /*Movie API's Endpoints*/
    String GET_MOVIE_LOOKUP_PATH_PARAM = "/movie.(*,moods,tones,themes,keywords,aesthetics,audiences,periods,characters,importance,flags,,facebooks,twitters)/{movieId};market=";
    String GET_MOVIE_AIRED_LOOKUP_PATH_PARAM = "/movie/{movieId}/aired";
    String GET_MOVIE_CAST_LOOKUP_PATH_PARAM = "/movie.(*,person.image:type=:first)/{movieId}/credits;isCast={cast}";
    String GET_MOVIE_CREDITS_LOOKUP_PATH_PARAM = "/movie.(*,person.image:type=:first)/{movieId}/credits";
    String GET_MOVIE_RATINGS_LOOKUP_PATH_PARAM = "/movie/{movieId}/ratings";
    String GET_MOVIE_RELATED_LOOKUP_PATH_PARAM = "/movie/{movieId}/related;relation=related";
    String GET_MOVIE_RELEASES_LOOKUP_PATH_PARAM = "/movie/{movieId}/releases";
    String GET_MOVIE_REVIEW_LOOKUP_PATH_PARAM = "/movie/{movieId}/review";
    String GET_MOVIE_SYNOPSES_LOOKUP_PATH_PARAM = "/movie/{movieId}/synopses";
    String GET_MOVIE_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/movie/{movieId}/synopses/first";
    String GET_MOVIE_THEATRICALS_LOOKUP_PATH_PARAM = "/movie.(*,)/{movieId}/theatricals";
    String GET_MOVIE_DELTA_PATH_PARAM = "/movie?day={date}&size={size}&next=";

    /*Series API's Endpoints*/
    String GET_SERIES_LOOKUP_PATH_PARAM = "/series.(*,moods,tones,themes,keywords,aesthetics,audiences,periods,characters,importance,flags,facebooks,twitters)/{seriesId};market=";
    String GET_SERIES_CAST_LOOKUP_PATH_PARAM = "/series.(*,person.image:type=:first)/{seriesId}/credits;isCast={cast}";
    String GET_SERIES_CREDITS_LOOKUP_PATH_PARAM = "/series.(*,person.image:type=:first)/{seriesId}/credits";
    String GET_SERIES_HISTORY_LOOKUP_PATH_PARAM = "/series/{seriesId}/history";
    String GET_SERIES_RATING_LOOKUP_PATH_PARAM = "/series/{seriesId}/ratings";
    String GET_SERIES_SYNOPSES_LOOKUP_PATH_PARAM = "/series/{seriesId}/synopses";
    String GET_SERIES_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/series/{seriesId}/synopses/first";
    String GET_SERIES_SEASONS_LOOKUP_PATH_PARAM = "/series/{seriesId}/seasons";
    String GET_SERIES_DELTA_PATH_PARAM = "/series?day={date}&size={size}&next=";

    /*Season API's Endpoints*/
    String GET_SEASON_LOOKUP_PATH_PARAM = "/season/{seasonId};market=";
    String GET_SEASON_EPISODES_LOOKUP_PATH_PARAM = "/season/{seasonId}/episodes";
    String GET_SEASON_SYNOPSES_LOOKUP_PATH_PARAM = "/season/{seasonId}/synopses";
    String GET_SEASON_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/season/{seasonId}/synopses/first";
    String GET_SEASON_DELTA_PATH_PARAM = "/season?day={date}&size={size}&next=";

    /*Episode API's Endpoints*/
    String GET_EPISODE_LOOKUP_PATH_PARAM = "/episode.(*,moods,tones,themes,keywords,aesthetics,audiences,periods,characters,importance,flags)/{episodeId};market=";
    String GET_EPISODE_AIRED_LOOKUP_PATH_PARAM = "/episode/{episodeId}/aired";
    String GET_EPISODE_CAST_LOOKUP_PATH_PARAM = "/episode.(*,person.image:type=:first)/{episodeId}/credits;isCast={cast}";
    String GET_EPISODE_CREDITS_LOOKUP_PATH_PARAM = "/episode.(*,person.image:type=:first)/{episodeId}/credits";
    String GET_EPISODE_RATING_LOOKUP_PATH_PARAM = "/episode/{episodeId}/ratings";
    String GET_EPISODE_RELEASES_LOOKUP_PATH_PARAM = "/episode/{episodeId}/releases";
    String GET_EPISODE_SYNOPSES_LOOKUP_PATH_PARAM = "/episode/{episodeId}/synopses";
    String GET_EPISODE_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/episode/{episodeId}/synopses/first";
    String GET_EPISODE_DELTA_PATH_PARAM = "/episode?day={date}&size={size}&next=";

    /*Other API's Endpoints*/
    String GET_OTHER_LOOKUP_PATH_PARAM = "/other.(*,,facebooks,twitters)/{otherId};market=";
    String GET_OTHER_AIRED_LOOKUP_PATH_PARAM = "/other/{otherId}/aired";
    String GET_OTHER_CAST_LOOKUP_PATH_PARAM = "/other.(*,person.image:type=:first)/{otherId}/credits;isCast={cast}";
    String GET_OTHER_CREDITS_LOOKUP_PATH_PARAM = "/other.(*,person.image:type=:first)/{otherId}/credits";
    String GET_OTHER_RATING_LOOKUP_PATH_PARAM = "/other/{otherId}/ratings";
    String GET_OTHER_RELATED_LOOKUP_PATH_PARAM = "/other/{otherId}/related;relation=related";
    String GET_OTHER_RELEASES_LOOKUP_PATH_PARAM = "/other/{otherId}/releases";
    String GET_OTHER_SYNOPSES_LOOKUP_PATH_PARAM = "/other/{otherId}/synopses";
    String GET_OTHER_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/other/{otherId}/synopses/first";
    String GET_OTHER_THEATRICALS_LOOKUP_PATH_PARAM = "/other.(*,)/{otherId}/theatricals";
    String GET_OTHER_DELTA_PATH_PARAM = "/other?day={date}&size={size}&next=";

    /*Release API's Endpoints*/
    String GET_RELEASE_LOOKUP_PATH_PARAM = "/release/{releaseId};market=";
    String GET_RELEASE_AIRED_LOOKUP_PATH_PARAM = "/release/{releaseId}/aired";
    String GET_RELEASE_CAST_LOOKUP_PATH_PARAM = "/release.(*,person.image:type=:first)/{releaseId}/credits;isCast={cast}";
    String GET_RELEASE_CREDITS_LOOKUP_PATH_PARAM = "/release.(*,person.image:type=:first)/{releaseId}/credits";
    String GET_RELEASE_RATING_LOOKUP_PATH_PARAM = "/release/{releaseId}/ratings";
    String GET_RELEASE_SYNOPSES_LOOKUP_PATH_PARAM = "/release/{releaseId}/synopses";
    String GET_RELEASE_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/release/{releaseId}/synopses/first";
    String GET_RELEASE_THEATRICALS_LOOKUP_PATH_PARAM = "release.(*,)/{releaseId}/theatricals";

    /*Content API's Endpoints*/
    String GET_CONTENT_LOOKUP_PATH_PARAM = "/content/{contentId};market=";

    /*Person API's Endpoints*/
    String GET_PERSON_LOOKUP_PATH_PARAM = "/person.(*,facebooks,twitters,image:type=:first)/{personId};market=";
    String GET_PERSON_NAME_LOOKUP_PATH_PARAM = "/name/{nameId}";
    String GET_PERSON_BIOS_LOOKUP_PATH_PARAM = "/person/{personId}/bios";
    String GET_PERSON_FILMOGRAPHY_LOOKUP_PATH_PARAM = "/person/{personId}/filmography";
    String GET_PERSON_RELATIVES_LOOKUP_PATH_PARAM = "/person.(*,person.image:type=:first)/{personId}/relatives";
    String GET_PERSON_DELTA_PATH_PARAM = "/person?day={date}&size={size}&next=";

    /*Award API's Endpoints*/
    String GET_MOVIE_AWARD_LOOKUP_PATH_PARAM = "/movie.(*,recipients.person.image:type=:first)/{movieId}/awards";
    String GET_SERIES_AWARD_LOOKUP_PATH_PARAM = "/series.(*,recipients.person.image:type=:first)/{seriesId}/awards";
    String GET_OTHER_AWARD_LOOKUP_PATH_PARAM = "/other.(*,recipients.person.image:type=:first)/{otherId}/awards";
    String GET_PERSON_AWARD_LOOKUP_PATH_PARAM = "/person/{personId}/awards";
    String GET_AWARD_LOOKUP_PATH_PARAM = "/award.(*,winners.recipients.person.image:type=:first,nominees.recipients.person.image:type=:first)/{awardId}";

    /*TV Schedule API's Endpoints*/
    String GET_SERVICE_BROWSE_PATH_PARAM = "/service;country={countryCode};postalCode=560017;msoId=;offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR?idns=&page=1&size=20";
    String GET_SERVICE_LOOKUP_PATH_PARAM = "/service/{serviceId};market=";
    String GET_SERVICE_CHANNELS_LOOKUP_PATH_PARAM = "/service/{serviceId}/channels?idns=";
    String GET_SERVICE_SCHEDULE_LOOKUP_PATH_PARAM = "/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/schedule/{date}?page=1";
    String GET_SOURCE_LOOKUP_PATH_PARAM = "/source/{sourceId}";
    String GET_SOURCE_AIRING_LOOKUP_PATH_PARAM = "/source/{sourceId}/airings/{date}?duration=24";
    String GET_AIRING_LOOKUP_PATH_PARAM = "/airing/{airingId}";
    String GET_MOVIE_APPEARANCES_LOOKUP_PATH_PARAM = "/movie/{movieId}/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/appearances/{date}?size=20&page=1";
    String GET_SERIES_APPEARANCES_LOOKUP_PATH_PARAM = "/series/{seriesId}/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/appearances/{date}?size=20&page=1";
    String GET_EPISODE_APPEARANCES_LOOKUP_PATH_PARAM = "/episode/{episodeId}/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/appearances/{date}?size=20&page=1";
    String GET_OTHER_APPEARANCES_LOOKUP_PATH_PARAM = "/other/{otherId}/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/appearances/{date}?size=20&page=1";
    String GET_PERSON_APPEARANCES_LOOKUP_PATH_PARAM = "/person/{personId}/service/{serviceId};offering=AU,BR,CA,CN,IN,EUR,LTA,RU,SEA,US,TR/appearances/{date}?size=20&page=1";
    String GET_AIRING_CAST_LOOKUP_PATH_PARAM = "/airing.(*,person.image:type=:first)/{airingId}/credits;isCast={cast}";
    String GET_AIRING_CREDITS_LOOKUP_PATH_PARAM = "/airing.(*,person.image:type=:first)/{airingId}/credits";
    String GET_AIRING_SYNOPSES_LOOKUP_PATH_PARAM = "/airing/{airingId}/synopses";
    String GET_AIRING_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/airing/{airingId}/synopses/first";

    /*Sports API's Endpoints*/
    String GET_GAME_LOOKUP_PATH_PARAM = "/game/{gameId};market=";
    String GET_LEAGUE_LOOKUP_PATH_PARAM = "/league/{leagueId};market=";
    String GET_LEAGUE_COMPETITION_LOOKUP_PATH_PARAM = "/league/{leagueId}/competitions";
    String GET_SCHOOL_LOOKUP_PATH_PARAM = "/school/{schoolId};market=";
    String GET_TEAM_LOOKUP_PATH_PARAM = "/team/{teamId};market=";
    String GET_TEAM_ROSTER_LOOKUP_PATH_PARAM = "/team/{teamId}/roster";
    String GET_TEAM_COMPETITION_LOOKUP_PATH_PARAM = "/team/{teamId}/competitions";
    String GET_COMPETITION_LOOKUP_PATH_PARAM = "/competition/{competitionId}";
    String GET_COMPETITION_CHILDREN_LOOKUP_PATH_PARAM = "/competition/{competitionId}/children";
    String GET_PERSON_COMPETITION_LOOKUP_PATH_PARAM = "/person/{personId}/competitions";
    String GET_GROUP_LOOKUP_PATH_PARAM = "/group/{groupId}";
    String GET_ORGANIZATION_CONCEPT_LOOKUP_PATH_PARAM = "/organizationconcept/{organizationId}";
    String GET_ORGANIZATION_CONCEPT_COMPETITION_LOOKUP_PATH_PARAM = "/organizationconcept/{organizationId}/competitions";

    /*Digital First API's Endpoint*/
    String GET_WEBSOURCE_LOOKUP_PATH_PARAM = "/websource/{webSourceID}";
    String GET_WEBSOURCE_BROWSE_PATH_PARAM = "/websource";
    String GET_WEBSOURCE_SERIES_LOOKUP_PATH_PARAM = "/websource/{webSourceID}/series";
    String GET_WEBSOURCE_VIDEOS_LOOKUP_PATH_PARAM = "/websource/{webSourceID}/videos";
    String GET_CLIP_LOOKUP_PATH_PARAM = "/clip.(*)/{clipID};market=AL,AU,BR,CN,HK,IN,MY,PH,RU,SG,TR,TW;dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_CLIP_CAST_LOOKUP_PATH_PARAM = "/clip.(*,person.image:type=:first)/{clipID}/credits;isCast=true?by=role,role,role,role,role&in=,,";
    String GET_CLIP_CREW_LOOKUP_PATH_PARAM = "/clip.(*,person.image:type=:first)/{clipID}/credits;isCast=false?by=role,role,role,role,role&in=,,";
    String GET_CLIP_CREDITS_LOOKUP_PATH_PARAM = "/clip.(*,person.image:type=:first)/{clipID}/credits?page=1&size=20&by=role,role,role,role,role&in=,,";
    String GET_CLIP_RELATED_LOOKUP_PATH_PARAM = "/clip/{clipID}/related;relation=related;content.dfsource=AWES,BUZZ,CCEN,CLEV,EENT,IGNE,MASH,NWSY,POPS,RF29,TIME,VICE,WTCH?in=,,";
    String GET_CLIP_RELEASES_LOOKUP_PATH_PARAM = "/clip/{clipID}/releases?in=,,&page=1&size=50";
    String GET_CLIP_SYNOPSES_BEST_LOOKUP_PATH_PARAM = "/clip/{clipID}/synopses/first?by=length=short,length=long,length=plain,length=extended&in=,,";
    String GET_CLIP_SYNOPSES_COLLECTION_LOOKUP_PATH_PARAM = "/clip/{clipID}/synopses?in=,,";
    String GET_WEBSERIES_LOOKUP_PATH_PARAM = "/series.(*,facebooks,twitters)/{seriesId};market=;dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_SERIES_EPISODES_LOOKUP_PATH_PARAM = "/series/{seriesId}/episodes";
    String GET_SERIES_RELATED_LOOKUP_PATH_PARAM = "/series/{seriesId}/related;relation=related;content.dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_WEBEPISODE_LOOKUP_PATH_PARAM = "/episode/{episodeId};market=;dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_WEBEPISODE_RELATED_LOOKUP_PATH_PARAM = "/episode/{episodeId}/related;relation=related;content.dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_WEBSEASON_LOOKUP_PATH_PARAM = "/season/{seasonId};market=;dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_WEBPERSON_LOOKUP_PATH_PARAM = "/person.(*,facebooks,twitters,web.featured.content:dfsource=(WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME),image:type=:first)/{personId}?in=,,";
    String GET_PERSON_WEBOGRAPHY_LOOKUP_PATH_PARAM = "/person/{personId}/webography;content.dfsource=WTCH,AWES,RF29,BUZZ,MASH,VICE,EENT,CLEV,CCEN,POPS,NWSY,IGNE,TIME?in=,,";
    String GET_WEBSOURCE_POPULAR_LOOKUP_PATH_PARAM = "/websource/{webSourceID}/popular?page=1&size=10&in=,,";
    String GET_WEBSOURCE_RECENT_LOOKUP_PATH_PARAM = "/websource/{webSourceID}/recent?page=1&size=10&in=,,";
}
