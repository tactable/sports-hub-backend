package org.example.sportshub.fixtures.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SportsApiClient {

    private final WebClient webClient;

    @Value("${api-football.api-key}")
    private String apiKey;

    public SportsApiClient(@Value("${api-football.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<JsonNode> fetch(String path) {
        return webClient.get()
                .uri(path)
                .header("x-apisports-key", apiKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .onErrorResume(e -> {
                    System.err.println("Error fetching data from path: " + path + ": " + e.getMessage());
                    return Mono.just(JsonNodeFactory.instance.objectNode());
                });
    }

    public Mono<JsonNode> getLiveFixturesJson() {
        return fetch("/fixtures?live=all");
    }

    public Mono<JsonNode> getFixturesByDateJson(String date) {
        return fetch("/fixtures?date=" + date);
    }

    public Mono<JsonNode> getFixtureStatsJson(int fixtureId) {
        return fetch("/fixtures/statistics?fixture=" + fixtureId);
    }
}
