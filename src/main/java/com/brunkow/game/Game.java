package com.brunkow.game;

import com.brunkow.game.exceptions.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    Team[] teams = new Team[2];
    int week;
    String location;
    Date kickoffDateTime;

    public Game(Team teamA, Team teamB) {
        this.teams[0] = teamA;
        this.teams[1] = teamB;
    }

    public void play() {
        logger.debug("TeamA: " + teams[0].getLocation() + " " + teams[0].getName());
        logger.debug("TeamB: " + teams[1].getLocation() + " " + teams[1].getName());
    }

    public Team getTeam(int index) {
        return this.teams[index];
    }

    public Team getTeam(String name) throws GameException {
        if (teams[0].getName().equalsIgnoreCase(name)) {
            return teams[0];
        } else if (teams[1].getName().equalsIgnoreCase(name)) {
            return teams[1];
        } else {
            throw new GameException("Team not found");
        }
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getKickoffDateTime() {
        return kickoffDateTime;
    }

    public void setKickoffDateTime(Date kickoffDateTime) {
        this.kickoffDateTime = kickoffDateTime;
    }
}
