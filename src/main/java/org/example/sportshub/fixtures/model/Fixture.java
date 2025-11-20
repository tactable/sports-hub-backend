package org.example.sportshub.fixtures.model;

public class Fixture {
    private int id;
    private String date;
    private String live;
    private String status;
    private String statusShort;
    private int elapsed;

    private String venue;
    private String country;

    private int league;
    private String leagueName;
    private int season;

    private String homeTeam;
    private int homeScore;
    private String homeTeamLogo;
    private String awayTeam;
    private int awayScore;
    private String awayTeamLogo;

    public Fixture() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLive() {
        return live;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public int getElapsed() {
        return elapsed;
    }

    public String getVenue() {
        return venue;
    }

    public String getCountry() {
        return country;
    }

    public int getLeague() {
        return league;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public int getSeason() {
        return season;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setHomeTeamLogo(String homeTeamLogo) { this.homeTeamLogo = homeTeamLogo; }

    public void setAwayTeamLogo(String awayTeamLogo) { this.awayTeamLogo = awayTeamLogo; }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }
}
