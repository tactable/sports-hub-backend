package org.example.sportshub.fixtures.controller;

import org.example.sportshub.fixtures.model.Fixture;
import org.example.sportshub.fixtures.model.FixtureStats;
import org.example.sportshub.fixtures.service.FootballApiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
public class FixturesController {

    private final FootballApiService footballApiService;

    public FixturesController(FootballApiService footballApiService) {
        this.footballApiService = footballApiService;
    }

    // Get all live fixtures
    @GetMapping("/live")
    public Mono<List<Fixture>> getLiveFixtures() {
        return footballApiService.getLiveFixtures();
    }

    // Get all fixtures today
    @GetMapping("/today")
    public Mono<List<Fixture>> getTodayFixtures() {
        return footballApiService.getTodayFixtures();
    }

    // Get statistics for a fixture
    @GetMapping("/{fixtureId}/stats")
    public Mono<List<FixtureStats>> getStats(@PathVariable int fixtureId) {
        return footballApiService.getFixtureStats(fixtureId);
    }

    // Continuously update live fixtures
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Fixture>> streamLiveFixtures() {
        return footballApiService.streamLiveFixtures();
    }
}
