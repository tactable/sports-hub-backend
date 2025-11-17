package org.example.sportshub.fixtures.service;

import org.example.sportshub.fixtures.model.Fixture;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.sportshub.fixtures.model.FixtureStats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FootballApiService {

    private final WebClient webClient;

    @Value("${api-football.api-key}")
    private String apiKey;

    public FootballApiService(@Value("${api-football.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    // Cached for manual requests
    @Cacheable(value = "liveFixtures", unless = "#result == null")
    public Mono<List<Fixture>> getLiveFixtures() {
        return getLiveFixturesFromApi();
    }

    public Mono<List<Fixture>> getLiveFixturesFromApi() {
        return webClient.get()
                .uri("/fixtures?live=all")
                .header("x-apisports-key", apiKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(this::parseMatches)
                .onErrorResume(e -> {
                    System.err.println("Error fetching live fixtures: " + e.getMessage());
                    return Mono.just(new ArrayList<>());
                });
    }

    @Cacheable(value = "todayFixtures", key = "#root.methodName", unless = "#result == null")
    public Mono<List<Fixture>> getTodayFixtures() {
        String today = LocalDate.now().toString();
        return webClient.get()
                .uri("/fixtures?date=" + today)
                .header("x-apisports-key", apiKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(this::parseMatches)
                .onErrorResume(e -> {
                    System.err.println("Error fetching today's fixtures: " + e.getMessage());
                    return Mono.just(new ArrayList<>());
                });
    }

    // Get fixture stats
    public Mono<List<FixtureStats>> getFixtureStats(int fixtureId) {
        return webClient.get()
                .uri("/fixtures/statistics?fixture=" + fixtureId)
                .header("x-apisports-key", apiKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(this::parseFixtureStats)
                .onErrorResume(e -> {
                    System.err.println("Error fetching fixture statistics: " + e.getMessage());
                    return Mono.just(new ArrayList<>());
                });
    }

    private List<Fixture> parseMatches(JsonNode node) {
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
                fixture.setAwayTeam(teams.get("away").get("name").asText());

                // Extract scores
                JsonNode goals = item.get("goals");
                fixture.setHomeScore(goals.get("home").asInt());
                fixture.setAwayScore(goals.get("away").asInt());

                fixtures.add(fixture);
            }
        }
        return fixtures;
    }

    private List<FixtureStats> parseFixtureStats(JsonNode response) {
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

    // Streaming fresh data
    public Flux<List<Fixture>> streamLiveFixtures() {
        return Flux.interval(Duration.ofSeconds(60))
                .flatMap(tick -> getLiveFixturesFromApi());
    }
}
