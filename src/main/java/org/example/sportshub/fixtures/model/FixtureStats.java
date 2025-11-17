package org.example.sportshub.fixtures.model;

public class FixtureStats {
    private int shotsOnGoal;
    private int shotsOffGoal;
    private int shotsInsideBox;
    private int shotsOutsideBox;
    private int totalShots;
    private int blockedShots;
    private int fouls;
    private int cornerKicks;
    private int offsides;
    private String ballPossession;
    private int yellowCards;
    private int redCards;
    private int goalkeeperSaves;
    private int totalPasses;
    private int passesAccurate;
    private String passesPercent;

    // Constructor
    public FixtureStats() {
    }

    public FixtureStats(int shotsOnGoal, int shotsOffGoal, int shotsInsideBox,
                        int shotsOutsideBox, int totalShots, int blockedShots,
                        int fouls, int cornerKicks, int offsides, String ballPossession,
                        int yellowCards, int redCards, int goalkeeperSaves,
                        int totalPasses, int passesAccurate, String passesPercent) {
        this.shotsOnGoal = shotsOnGoal;
        this.shotsOffGoal = shotsOffGoal;
        this.shotsInsideBox = shotsInsideBox;
        this.shotsOutsideBox = shotsOutsideBox;
        this.totalShots = totalShots;
        this.blockedShots = blockedShots;
        this.fouls = fouls;
        this.cornerKicks = cornerKicks;
        this.offsides = offsides;
        this.ballPossession = ballPossession;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.goalkeeperSaves = goalkeeperSaves;
        this.totalPasses = totalPasses;
        this.passesAccurate = passesAccurate;
        this.passesPercent = passesPercent;
    }

    // Getters
    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

    public int getShotsOffGoal() {
        return shotsOffGoal;
    }

    public int getShotsInsideBox() {
        return shotsInsideBox;
    }

    public int getShotsOutsideBox() {
        return shotsOutsideBox;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public int getBlockedShots() {
        return blockedShots;
    }

    public int getFouls() {
        return fouls;
    }

    public int getCornerKicks() {
        return cornerKicks;
    }

    public int getOffsides() {
        return offsides;
    }

    public String getBallPossession() {
        return ballPossession;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getGoalkeeperSaves() {
        return goalkeeperSaves;
    }

    public int getTotalPasses() {
        return totalPasses;
    }

    public int getPassesAccurate() {
        return passesAccurate;
    }

    public String getPassesPercent() {
        return passesPercent;
    }

    // Setters
    public void setShotsOnGoal(int shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }

    public void setShotsOffGoal(int shotsOffGoal) {
        this.shotsOffGoal = shotsOffGoal;
    }

    public void setShotsInsideBox(int shotsInsideBox) {
        this.shotsInsideBox = shotsInsideBox;
    }

    public void setShotsOutsideBox(int shotsOutsideBox) {
        this.shotsOutsideBox = shotsOutsideBox;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }

    public void setBlockedShots(int blockedShots) {
        this.blockedShots = blockedShots;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public void setCornerKicks(int cornerKicks) {
        this.cornerKicks = cornerKicks;
    }

    public void setOffsides(int offsides) {
        this.offsides = offsides;
    }

    public void setBallPossession(String ballPossession) {
        this.ballPossession = ballPossession;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public void setGoalkeeperSaves(int goalkeeperSaves) {
        this.goalkeeperSaves = goalkeeperSaves;
    }

    public void setTotalPasses(int totalPasses) {
        this.totalPasses = totalPasses;
    }

    public void setPassesAccurate(int passesAccurate) {
        this.passesAccurate = passesAccurate;
    }

    public void setPassesPercent(String passesPercent) {
        this.passesPercent = passesPercent;
    }
}
