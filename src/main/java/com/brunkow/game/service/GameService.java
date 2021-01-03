package com.brunkow.game.service;

import com.brunkow.game.Field;
import com.brunkow.game.event.*;
import com.brunkow.game.play.Play;
import com.brunkow.game.dao.GameRepository;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Team;
import com.brunkow.game.dao.TeamRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional
    public void doGame() {
        Game game;
        Team teamA = teamRepository.findByName("Vikings");
        Team teamB = teamRepository.findByName("Pirates");
        Field field = new Field(teamA, teamB);
        field.setYardLine(35);
        game = new Game(teamA, teamB);
        runQuarter(game, field, 1);
        field.switchSides();
        runQuarter(game, field, 2);
        halftime(game, field);
        runQuarter(game, field,3);
        field.switchSides();
        runQuarter(game, field, 4);
        // if tied runQuarter(5);
        game.setScoreTeamA(0);
        game.setScoreTeamB(0);
        gameRepository.save(game);
    }

    private void runQuarter(Game game, Field field, int quarter) {
        int clock = 0;
        GameEvent event;
        Play play;
        logger.debug(quarter + " ===================================================================================");
        if ((quarter == 1) || (quarter == 3))
            field.setGameSituation(GameEvent.GameSituation.KICKOFF);
        StringBuffer printBuffer;
        while ((clock < 900) || (field.getGameSituation().equals(GameEvent.GameSituation.TOUCHDOWN))){
            play = Play.createPlay(game, field);
            play.go();
            printBuffer = new StringBuffer();
            printBuffer.append(
                    StringUtils.rightPad(field.getTeamName(), 30) + " " +
                            StringUtils.rightPad(play.getClass().getSimpleName(), 20) + " " +
                            " L:" + round(field.getYardLine()) +
                            " D:" + StringUtils.leftPad(Integer.toString(field.getDown()), 3));
            event = GameEvent.getInstance(play, field);
            event.go();
            clock += play.getElapsedTime();
            printBuffer.append(
                    " Y:" + round(play.getYards()) +
                    " S:" + round(field.getSeries()) +
                    " L:" + round(field.getYardLine()) + " ");
            printBuffer.append(StringUtils.rightPad(event.getClass().getSimpleName(), 20) + " ");
            printBuffer.append(field.getScore(0) + " - " + field.getScore(1));
            logger.debug(printBuffer.toString());
            field.setGameSituation(event.getGameSituation());
        }
    }

    private void halftime(Game game, Field field) {
        field.setGameSituation(GameEvent.GameSituation.NONE);
        field.halftime();
    }

    private static String round(double num) {
        return StringUtils.leftPad(Double.toString(Precision.round(num, 1)),5);
    }

    private static String round(int num) {
        return round((double) num);
    }
}
