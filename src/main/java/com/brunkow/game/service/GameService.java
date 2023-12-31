package com.brunkow.game.service;

import com.brunkow.game.GameContext;
import com.brunkow.game.dao.GameRepository;
import com.brunkow.game.dao.TeamRepository;
import com.brunkow.game.play.Play;
import com.brunkow.game.dao.DepthChartRepository;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Team;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brunkow.game.display.Print;

import javax.transaction.Transactional;

@Service
public class GameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    DepthChartRepository depthChartRepository;

    @Transactional
    public void doGame() {
        Game game;
        Team teamA = teamRepository.findByName("Vikings");
        Team teamB = teamRepository.findByName("Pirates");
        GameContext gameContext = new GameContext(teamA, teamB);
        gameContext.setYardLine(35);
        game = new Game(teamA, teamB);
        runQuarter(game, gameContext);
        gameContext.switchSides();
        gameContext.nextQuarter();
        runQuarter(game, gameContext);
        halftime(game, gameContext);
        runQuarter(game, gameContext);
        gameContext.switchSides();
        gameContext.nextQuarter();
        runQuarter(game, gameContext);
        // if tied runQuarter(5);
        game.setScoreTeamA(0);
        game.setScoreTeamB(0);
        gameRepository.save(game);
        gameContext.printStats();
    }

    private void runQuarter(Game game, GameContext gameContext) {
        GameEvent event;
        Play play;
        logger.debug(gameContext.getQuarter() + " ===================================================================================");
        if ((gameContext.getQuarter() == 1) || (gameContext.getQuarter() == 3))
            gameContext.setGameSituation(GameEvent.GameSituation.KICKOFF);
        StringBuffer printBuffer;
        while ((gameContext.getClock() < 900) || (gameContext.getGameSituation().equals(GameEvent.GameSituation.TOUCHDOWN))){
            play = Play.createPlay(game, gameContext);
            play.addDepthChartRepository(depthChartRepository);
            printBuffer = new StringBuffer();
            printBuffer.append(
                    StringUtils.rightPad(gameContext.getOffenseTeamName(), 30) + " " +
                            StringUtils.rightPad(play.getClass().getSimpleName(), 20) + " " +
                            " L:" + round(gameContext.getYardLine()) +
                            " D:" + StringUtils.leftPad(Integer.toString(gameContext.getDown()), 3));
            play.go();
            event = GameEvent.getInstance(play, gameContext);
            event.go();
            gameContext.addClock(play.getElapsedTime());
            printBuffer.append(
                    "\t\tY:" + round(play.getYards()) +
                    " S:" + round(gameContext.getSeries()) +
                    " L:" + round(gameContext.getYardLine()) + " ");
            printBuffer.append(StringUtils.rightPad(event.getClass().getSimpleName(), 20) + " ");
            printBuffer.append(" C:" + gameContext.getClock() + " ");
            printBuffer.append(gameContext.getTeams().get(gameContext.TEAM_A).getFullName() +
                                    " ( " + gameContext.getScore(gameContext.TEAM_A) + " )   " +
                                gameContext.getTeams().get(gameContext.TEAM_B).getFullName() +
                                    " ( " + gameContext.getScore(gameContext.TEAM_B) + " )   ");
            logger.debug(Print.print(gameContext.getYardLine(), gameContext.getOffenseTeam().getName().charAt(0)));
            //logger.debug(printBuffer.toString());
            gameContext.setGameSituation(event.getGameSituation());
        }
    }

    private void halftime(Game game, GameContext gameContext) {
        gameContext.setGameSituation(GameEvent.GameSituation.NONE);
        gameContext.halftime();
        gameContext.nextQuarter();

    }

    private static String round(double num) {
        return StringUtils.leftPad(Double.toString(Precision.round(num, 1)),5);
    }

    private static String round(int num) {
        return round((double) num);
    }
}
