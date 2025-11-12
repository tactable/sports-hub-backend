package org.example.sportshub.fixtures.model;

public class Fixture {
    private int id;
    private String ids;
    private String live;
    private String date;
    private int league;
    private String leagueName;
    private int season;
    private String status;
    private String venue;
    private String country;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String statusShort;
    private int elapsed;

    public Fixture() {
    }

    public Fixture(int id, String ids, String live, String date, int league, String leagueName,
                   int season, String status, String venue, String country, String homeTeam,
                   String awayTeam, int homeScore, int awayScore, String statusShort, int elapsed) {
        this.id = id;
        this.ids = ids;
        this.live = live;
        this.date = date;
        this.league = league;
        this.leagueName = leagueName;
        this.season = season;
        this.status = status;
        this.venue = venue;
        this.country = country;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.statusShort = statusShort;
        this.elapsed = elapsed;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getIds() {
        return ids;
    }

    public String getLive() {
        return live;
    }

    public String getDate() {
        return date;
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

    public String getStatus() {
        return status;
    }

    public String getVenue() {
        return venue;
    }

    public String getCountry() {
        return country;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public int getElapsed() {
        return elapsed;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setLive(String live) {
        this.live = live;
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

    public boolean isLive(String live) {
        return live.equals("1H") || live.equals("HT") || live.equals("2H") || live.equals("ET")
                || live.equals("BT") || live.equals("P");
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

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
