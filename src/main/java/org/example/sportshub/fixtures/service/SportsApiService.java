package org.example.sportshub.fixtures.service;

import org.example.sportshub.fixtures.client.SportsApiClient;
import org.example.sportshub.fixtures.model.Fixture;
import org.example.sportshub.fixtures.model.FixtureStats;
import org.example.sportshub.fixtures.parser.SportsDataParser;
import org.springframework.boot.sql.init.dependency.DatabaseInitializationDependencyConfigurer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SportsApiService {

    private final SportsApiClient sportsApiClient;
    private final SportsDataParser sportsDataParser;

    public SportsApiService(SportsApiClient sportsApiClient, SportsDataParser sportsDataParser) {
        this.sportsApiClient = sportsApiClient;
        this.sportsDataParser = sportsDataParser;
    }

    // Cached for manual requests
    @Cacheable(value = "liveFixtures", unless = "#result == null")
    public Mono<List<Fixture>> getLiveFixtures() {
        return getLiveFixturesFromApi();
    }

    public Mono<List<Fixture>> getLiveFixturesFromApi() {
        return sportsApiClient.getLiveFixturesJson()
                .map(sportsDataParser::parseFixtures)
                .defaultIfEmpty(new ArrayList<>());
    }

    @Cacheable(value = "todayFixtures", unless = "#result.isEmpty()")
    public Mono<List<Fixture>> getTodayFixtures () {
        String today = LocalDate.now().toString();
        return sportsApiClient.getFixturesByDateJson(today)
                .map(sportsDataParser::parseFixtures)
                .defaultIfEmpty(new ArrayList<>());
    }

    public Mono<List<FixtureStats>> getFixtureStats(int fixtureId) {
        return sportsApiClient.getFixtureStatsJson(fixtureId)
                .map(sportsDataParser::parseFixtureStats)
                .defaultIfEmpty(new ArrayList<>());
    }

    // Streaming fresh data
    public Flux<List<Fixture>> streamLiveFixtures() {
        return Flux.interval(Duration.ofSeconds(60))
                .flatMap(_ -> getLiveFixturesFromApi());
    }
}
