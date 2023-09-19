package com.brunkow.game;

import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameStats {
    private static final Logger logger = LoggerFactory.getLogger(GameStats.class);
    public static final String RUN = "RUN";
    public static final String RUN_ATTEMPTS = "RUN_ATTEMPTS";
    public static final String PASS = "PASS";
    public static final String FUMBLE = "FUMBLE";
    public static final String TEAM_RUN = "TEAM_RUN";
    public static final String TEAM_PASS = "TEAM_PASS";
    public static final String PASS_ATTEMPTS = "PASS_ATTEMPTS";
    public static final String PASS_COMPLETIONS = "PASS_COMPLETIONS";
    public static final String PASS_TDS = "PASS_TDS";
    public static final String PASS_INTERCEPTIONS = "PASS_INTERCEPTIONS";
    List<HashMap<String, HashMap<Player, Double>>> playerStats = new ArrayList();
    List<HashMap<String, Double>> teamsStats = new ArrayList();

    //List<HashMap<Player,Double>> runYards = new ArrayList<>();
    //List<HashMap<Player,Double>> passYards = new ArrayList<>();

    public GameStats() {
        playerStats.add(GameContext.TEAM_A, createGains());
        playerStats.add(GameContext.TEAM_B, createGains());
        teamsStats.add(GameContext.TEAM_A, new HashMap<String, Double>());
        teamsStats.add(GameContext.TEAM_B, new HashMap<String, Double>());
    }

    private static HashMap<String, HashMap<Player, Double>> createGains() {
        HashMap<String, HashMap<Player, Double>> gains = new HashMap<>();
        gains.put(RUN, new HashMap<>());
        gains.put(RUN_ATTEMPTS, new HashMap<>());
        gains.put(FUMBLE, new HashMap<>());
        gains.put(PASS_ATTEMPTS, new HashMap<>());
        gains.put(PASS_COMPLETIONS, new HashMap<>());
        gains.put(PASS_INTERCEPTIONS, new HashMap<>());
        gains.put(PASS, new HashMap<>());
        return gains;
    }

    /*
    public void addRunningYards(int team, String pos, double yards) {
        addPlayerStats(team, RUN, pos, yards);
        addTeamStats(team, TEAM_RUN, yards);
    }
    */
    public void addPlayerStats(int team, String key, Player player, double value) {
        if (playerStats.get(team).get(key).get(player) == null) {
            playerStats.get(team).get(key).put(player, value);
        } else {
            playerStats.get(team).get(key).put(player, playerStats.get(team).get(key).get(player) + value);
        }
    }

    public void addTeamStats(int team, String key, double value) {
        if (teamsStats.get(team).get(key) == null) {
            teamsStats.get(team).put(key, value);
        } else {
            teamsStats.get(team).put(key, teamsStats.get(team).get(key) + value);
        }
    }

    public Double getRunningYards(int team, String pos) {
        return playerStats.get(team).get(RUN).get(pos);
    }


    public Double getPassingYards(int team, String pos) {
        return playerStats.get(team).get(PASS).get(pos);
    }

    List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    public void printStats() {
        printPlayerStats(RUN, GameContext.TEAM_A);
        printPlayerStats(RUN_ATTEMPTS, GameContext.TEAM_A);
        printPlayerStats(RUN, GameContext.TEAM_B);
        printPlayerStats(RUN_ATTEMPTS, GameContext.TEAM_B);
        printPlayerStats(PASS, GameContext.TEAM_A);
        printPlayerStats(PASS_COMPLETIONS, GameContext.TEAM_A);
        printPlayerStats(PASS_ATTEMPTS, GameContext.TEAM_A);
        printPlayerStats(PASS_INTERCEPTIONS, GameContext.TEAM_A);
        printPlayerStats(PASS, GameContext.TEAM_B);
        printPlayerStats(PASS_COMPLETIONS, GameContext.TEAM_B);
        printPlayerStats(PASS_ATTEMPTS, GameContext.TEAM_B);
        printPlayerStats(PASS_INTERCEPTIONS, GameContext.TEAM_B);
        printPlayerStats(FUMBLE, GameContext.TEAM_A);
        printPlayerStats(FUMBLE, GameContext.TEAM_B);
        printTeamYards(GameContext.TEAM_A, TEAM_RUN);
        printTeamYards(GameContext.TEAM_B, TEAM_RUN);
        printTeamYards(GameContext.TEAM_A, TEAM_PASS);
        printTeamYards(GameContext.TEAM_B, TEAM_PASS);
    }

    public void printTeamYards(int team, String key) {
        /*
        logger.debug(StringUtils.rightPad(getTeams().get(team).getName(), 6) + " " +
                StringUtils.rightPad(key, 12) + " " +
                StringUtils.leftPad(Double.toString(Precision.round(teamsStats.get(team).get(key),1)), 10));

         */
    }

    public void printPlayerStats(String poskey, int team) {
        for (Player player: playerStats.get(team).get(poskey).keySet()) {
            logger.debug(StringUtils.rightPad(getTeams().get(team).getName(), 6) + " " +
                    StringUtils.rightPad(player.getFirstName() + " " + player.getLastName(), 25) +
                    StringUtils.rightPad(poskey, 16) + " " +
                    StringUtils.leftPad(Double.toString(Precision.round(
                            playerStats.get(team).get(poskey).get(player),1)),6));
        }
    }

    public void addPassStats(int team, Player player, int attempt, int completion, double yards) {
        addPlayerStats(team, PASS_ATTEMPTS, player, attempt);
        addPlayerStats(team, PASS_COMPLETIONS, player, completion);
        addPlayerStats(team, PASS, player, yards);
        addTeamStats(team, TEAM_PASS, yards);
    }

    public void addRunStats(int team, Player player, int attempts, double yards) {
        addPlayerStats(team, RUN, player, yards);
        addPlayerStats(team, RUN_ATTEMPTS,player, attempts);
        addTeamStats(team, TEAM_RUN, yards);
    }

    public void addFumble(int team, Player player) {
        addPlayerStats(team, FUMBLE, player, 1);
    }
}
