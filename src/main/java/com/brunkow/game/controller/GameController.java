package com.brunkow.game.controller;

import com.brunkow.game.FootballApplication;
import com.brunkow.game.dao.CoachRepository;
import com.brunkow.game.dao.DepthChartRepository;
import com.brunkow.game.dao.PlayerRepository;
import com.brunkow.game.dao.TeamRepository;
import com.brunkow.game.service.GameService;
import com.brunkow.game.vo.DepthChart;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    DepthChartRepository depthChartRepository;

    @Autowired
    GameService gameService;

    @PostMapping("/game")
    public String doGame() {
        logger.debug("Running");
        Iterable<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            logger.debug("Team: " + team.getLocation() + " " + team.getName());
            if (team.getOffensiveCoordinator() != null) {
                logger.debug("   OC: " + team.getOffensiveCoordinator().getFirstName() +
                        " " + team.getOffensiveCoordinator().getLastName());
            }
            if (team.getDefensiveCoordinator() != null) {
                logger.debug("   DC: " + team.getDefensiveCoordinator().getFirstName() +
                        " " + team.getDefensiveCoordinator().getLastName());
            }
            if (team.getSpecialTeamsCoordinator() != null) {
                logger.debug("   STC: " + team.getSpecialTeamsCoordinator().getFirstName() +
                        " " + team.getSpecialTeamsCoordinator().getLastName());
            }
            if (team.getHeadCoach() != null) {
                logger.debug("   HC: " + team.getHeadCoach().getFirstName() +
                        " " + team.getHeadCoach().getLastName());
            }

            logger.debug("Find starting runningback");
            logger.debug(depthChartRepository.findByTeamIdAndPositionAndDepth(team.getId(), "RB", 1).getPlayer().getLastName());

            logger.debug("Players");
            Iterable<Player> players = playerRepository.findByTeamId(team.getId());
            players.forEach(player -> {
                logger.debug("   Player: " + player.getPosition() + " " +
                        player.getFirstName() + " " + player.getLastName());
            });
            logger.debug("Depth Chart");
            Iterable<DepthChart> depthChartPlayers = depthChartRepository.findByTeamId(team.getId());
            depthChartPlayers.forEach(depthChartPlayer -> {
                logger.debug("   Player: " + depthChartPlayer.getPosition() + " " + depthChartPlayer.getDepth() + " " +
                        depthChartPlayer.getPlayer().getFirstName() + " " + depthChartPlayer.getPlayer().getLastName());
            });
            logger.debug("==============================================");
        });
        logger.debug("Done");
        Team teamA = teamRepository.findByName("Vikings");
        Team teamB = teamRepository.findByName("Pirates");
        if ((teamA != null) && (teamB != null)) {
            Game game = new Game(teamA, teamB);

            game.play();
        } else {
            logger.debug("Team not found");
        }
        gameService.doGame();
        return "Game";
    }
}
