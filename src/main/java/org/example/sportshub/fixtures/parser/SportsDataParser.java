package org.example.sportshub.fixtures.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.sportshub.fixtures.model.Fixture;
import org.example.sportshub.fixtures.model.FixtureStats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportsDataParser {

    public List<Fixture> parseFixtures(JsonNode node) {
        List<Fixture> fixtures = new ArrayList<>();
        JsonNode responseArray = node.get("response");

        if (responseArray != null && responseArray.isArray()) {
            for (JsonNode item : responseArray) {
                Fixture fixture = new Fixture();

                // Extract fixture info
                JsonNode fixtureInfo = item.get("fixture");
                fixture.setId(fixtureInfo.get("id").asInt());
                fixture.setDate(fixtureInfo.get("date").asText());
                fixture.setVenue(fixtureInfo.get("venue").get("city").asText());
                fixture.setStatus(fixtureInfo.get("status").get("long").asText());
                fixture.setStatusShort(fixtureInfo.get("status").get("short").asText());
                fixture.setElapsed(fixtureInfo.get("status").get("elapsed").asInt());

                // Extract league info
                JsonNode league = item.get("league");
                fixture.setLeague(league.get("id").asInt());
                fixture.setLeagueName(league.get("name").asText());
                fixture.setCountry(league.get("country").asText());
                fixture.setSeason(league.get("season").asInt());

                // Extract teams
                JsonNode teams = item.get("teams");
                fixture.setHomeTeam(teams.get("home").get("name").asText());
                fixture.setHomeTeamLogo(teams.get("home").get("logo").asText());
                fixture.setAwayTeam(teams.get("away").get("name").asText());
                fixture.setAwayTeamLogo(teams.get("away").get("logo").asText());

                // Extract scores
                JsonNode goals = item.get("goals");
                fixture.setHomeScore(goals.get("home").asInt());
                fixture.setAwayScore(goals.get("away").asInt());

                fixtures.add(fixture);
            }
        }
        return fixtures;
    }

    public List<FixtureStats> parseFixtureStats(JsonNode response) {
        List<FixtureStats> statsList = new ArrayList<>();

        JsonNode responseArray = response.get("response");

        for (JsonNode teamData : responseArray) {
            JsonNode statistics = teamData.get("statistics");
            FixtureStats stats = new FixtureStats();

            for (JsonNode stat : statistics) {
                String type = stat.path("type").asText();
                JsonNode valueNode = stat.path("value");
                String value = valueNode.isNull() ? "0" : valueNode.asText();

                switch (type) {
                    case "Shots on Goal":
                        stats.setShotsOnGoal(Integer.parseInt(value));
                        break;
                    case "Shots off Goal":
                        stats.setShotsOffGoal(Integer.parseInt(value));
                        break;
                    case "Total Shots":
                        stats.setTotalShots(Integer.parseInt(value));
                        break;
                    case "Blocked Shots":
                        stats.setBlockedShots(Integer.parseInt(value));
                        break;
                    case "Shots insidebox":
                        stats.setShotsInsideBox(Integer.parseInt(value));
                        break;
                    case "Shots outsidebox":
                        stats.setShotsOutsideBox(Integer.parseInt(value));
                        break;
                    case "Fouls":
                        stats.setFouls(Integer.parseInt(value));
                        break;
                    case "Corner Kicks":
                        stats.setCornerKicks(Integer.parseInt(value));
                        break;
                    case "Offsides":
                        stats.setOffsides(Integer.parseInt(value));
                        break;
                    case "Ball Possession":
                        stats.setBallPossession(value);
                        break;
                    case "Yellow Cards":
                        stats.setYellowCards(Integer.parseInt(value));
                        break;
                    case "Red Cards":
                        stats.setRedCards(Integer.parseInt(value));
                        break;
                    case "Goalkeeper Saves":
                        stats.setGoalkeeperSaves(Integer.parseInt(value));
                        break;
                    case "Total passes":
                        stats.setTotalPasses(Integer.parseInt(value));
                        break;
                    case "Passes accurate":
                        stats.setPassesAccurate(Integer.parseInt(value));
                        break;
                    case "Passes %":
                        stats.setPassesPercent(value);
                        break;
                }
            }
            statsList.add(stats);
        }
        return statsList;
    }
}
