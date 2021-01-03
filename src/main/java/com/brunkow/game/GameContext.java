package com.brunkow.game;

import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class GameContext {
    private static final Logger logger = LoggerFactory.getLogger(GameContext.class);
    public static final int WEST = 0; // false
    public static final int EAST = 1; // true
    GameEvent.GameSituation gameSituation;
    int scores[] = { 0, 0 };
    double yardLine;
    List<Team> teams;
    double series;
    int down = 1;
    int direction;
    int quarter;
    int clock;

    public GameContext(Team teamA, Team teamB) {
        teams = new ArrayList<Team>();
        teams.add(0, teamA);
        teams.add(1, teamB);
        direction = EAST; // Going towards B/East
        gameSituation = GameEvent.GameSituation.NONE;
        clock = 0;
        quarter = 1;

    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void addClock(int clock) {
        this.clock += clock;
    }

    public void switchSides() {
        List<Team> newTeams = new ArrayList<Team>();
        newTeams.add(0, this.teams.get(1));
        newTeams.add(1, this.teams.get(0));
        this.teams = newTeams;
        this.yardLine = 100 - this.yardLine;
        this.direction = 1 - this.direction;
        int[] newScores = new int[2];
        newScores[0] = scores[1];
        newScores[1] = scores[0];
        scores = newScores;
        clock = 0;
    }

    public void halftime() {
        List<Team> newTeams = new ArrayList<Team>();
        newTeams.add(0, this.teams.get(1));
        newTeams.add(1, this.teams.get(0));
        this.teams = newTeams;
        this.yardLine = 100 - this.yardLine;
        this.direction = WEST;
        int[] newScores = new int[2];
        newScores[0] = scores[1];
        newScores[1] = scores[0];
        scores = newScores;
        clock = 0;
    }

    public void nextQuarter() {
        this.quarter++;
        this.clock = 0;
    }

    public int getQuarter() {
        return this.quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }
    public int getWinningBy() {
        return getScore(this.direction) - getScore(1-direction);
    }


    public String getTeamName() {
        return teams.get(this.direction).getFullName();
    }

    public void addScore(int team, int points) {
        scores[team] += points;
    }

    public int[] getScores() {
        return scores;
    }

    public int getScore(int team) {
        return scores[team];
    }

    public void addYards(double yards) {
        if (this.direction == EAST) {
            this.yardLine += yards;
        } else {
            this.yardLine -= yards;
        }
        this.series += yards;
    }

    public boolean isEndzone() {
        if (this.direction == EAST) {
            return this.yardLine >= 100;
        } else {
            return this.yardLine <= 0;
        }
    }

    public void changePossession() {
        this.series = 0.0;
        this.down = 1;
        this.direction = 1-this.direction;
    }

    public boolean isSafety() {
        return isSafety(0.0);
    }

    public boolean isSafety(double yards) {
        if (isTouchdown(yards)) {
            return false;
        } else {
            return ((getYardsToTD(yards) < 0) || (getYardsToTD(yards) > 100));
        }
    }

    public double getYardsToTD(double yards) {
        if (this.direction == EAST) {
            return 100.00 - this.yardLine - yards;
        } else {
            return this.yardLine - yards;
        }
    }

    public double getYardsToTD() {
        return getYardsToTD(0.0);
    }


   // public boolean isActiveDrive() { return ((this.down < 5) && (!event.isEvent())); }
    public boolean isFirstDown() {
        return this.series >= 10.0;
    }
    public boolean isFourthDown() {
        return ((this.series < 10.0) && (this.down == 4));
    }
    public void addDown() {
        this.down += 1;
    }
    public void addDown(int down) {
        this.down += down;
    }
    public int getDown() {
        return down;
    }
    public void setDown(int down) {
        this.down = down;
    }
    public double getYardLine() {
        return this.yardLine;
    }
    public void setYardLine(double yards) {
        this.yardLine = yards;
    }
    public double getSeries() {
        return series;
    }
    public void setSeries(double series) {
        this.series = series;
    }
     public boolean isDirection() {
        return direction == EAST;
    }
    public int getDirection() {
        return this.direction;
    }
    public boolean isDirectionEast() { return direction == EAST; }
    public boolean isDirectionWest() { return direction == WEST; }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void switchDirection() {
        this.direction = 1-this.direction;
    }
    public boolean isTouchdown() {
        if (this.direction == EAST) {
            return this.yardLine >= 100.0;
        } else {
            return this.yardLine <= 0.0;
        }
    }
    public boolean isTouchdown(double yards) {
        if (this.direction == EAST) {
            return ((this.yardLine + yards) >= 100.0);
        } else {
            return ((this.yardLine - yards) <= 0.0);
        }
    }

    public GameEvent.GameSituation getGameSituation() {
        return gameSituation;
    }

    public void setGameSituation(GameEvent.GameSituation gameSituation) {
        this.gameSituation = gameSituation;
    }

}
