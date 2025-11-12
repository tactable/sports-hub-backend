package org.example.sportshub.fixtures.service;

import org.example.sportshub.fixtures.model.Fixture;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
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

    public Mono<List<Fixture>> getLiveFixtures() {
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

    public Flux<List<Fixture>> streamLiveMatches() {
        return Flux.interval(Duration.ofSeconds(60))
                .flatMap(_ -> getLiveFixtures());
    }
}
