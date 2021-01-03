package com.brunkow.game.vo;

import com.brunkow.game.exceptions.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Game  implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Team> teams;

    int week;
    String location;
    Date kickoffDateTime;
    int scoreTeamA;
    int scoreTeamB;

    public Game(Team teamA, Team teamB) {
        teams = new ArrayList();
        teams.add(teamA);
        teams.add(teamB);
    }

    public void play() {
        logger.debug("TeamA: " + teams.get(0).getLocation() + " " + teams.get(0).getName());
        logger.debug("TeamB: " + teams.get(1).getLocation() + " " + teams.get(1).getName());
    }

    public Team getTeam(int index) {
        return this.teams.get(index);
    }

    public Team getTeam(String name) throws GameException {
        if (teams.get(0).getName().equalsIgnoreCase(name)) {
            return teams.get(0);
        } else if (teams.get(1).getName().equalsIgnoreCase(name)) {
            return teams.get(1);
        } else {
            throw new GameException("Team not found");
        }
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
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

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
