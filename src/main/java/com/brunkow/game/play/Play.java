package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.dao.DepthChartRepository;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public abstract class Play {
    private static final Logger logger = LoggerFactory.getLogger(Play.class);

    public GameEvent.NextEvent nextEvent;
    Random rand = new Random();
    int elapsedTime;
    double yards = 0.0;
    GameContext gameContext;
    Game game;
    DepthChartRepository depthChartRepository;

    public void addDepthChartRepository(DepthChartRepository depthChartRepository) {
        this.depthChartRepository = depthChartRepository;
    }

    abstract public double getTeamPlayPower();

    public double getRandomPower(int bounds) {
        return (double) rand.nextInt(1000);
    }

    public double getPlayPower(int bounds) {
        return (double) rand.nextInt(bounds);
    }

    public static Play createPlay(Game game, GameContext gameContext) {
        Random rand = new Random();
        Play play;
        if (GameEvent.GameSituation.KICKOFF.equals(gameContext.getGameSituation())) {
            play = new KickoffPlay();
        } else if (GameEvent.GameSituation.TOUCHDOWN.equals(gameContext.getGameSituation())) {
            play = new KickExtraPointPlay();
        } else if (GameEvent.GameSituation.SAFETYKICKOFF.equals(gameContext.getGameSituation())) {
            play = new SafetyKickoffPlay();
        } else {
            if ((gameContext.getClock() >= 870) && (gameContext.getYardsToTD() <= 30)
                    && (gameContext.getQuarter() == 2)) {
                play = new KickFieldGoalPlay();
            } else if ((gameContext.getClock() >= 870) && (gameContext.getYardsToTD() <= 35)
                    && (gameContext.getQuarter() >= 4)
                    && ((gameContext.getWinningBy() >= -3) && (gameContext.getWinningBy() <= 0))) {
                play = new KickFieldGoalPlay();

            } else if (gameContext.isFourthDown()) {
                if ((gameContext.getClock() >= 600)
                        && (gameContext.getQuarter() >= 4)
                        && (gameContext.getWinningBy() <= 0)) {
                    if (rand.nextInt(20) == 0) {
                        play = new RunPlay();
                    } else {
                        play = new PassPlay();
                    }
                } else if ((gameContext.getClock() >= 400) && (gameContext.getYardsToTD() <= 50)
                        && ((gameContext.getQuarter() >= 4) || (gameContext.getQuarter() == 2))
                        && (gameContext.getWinningBy() <= 0)) {
                    if (rand.nextInt(5) == 0) {
                        play = new RunPlay();
                    } else {
                        play = new PassPlay();
                    }
                } else if (gameContext.getYardsToTD() < 10) {
                    if (gameContext.getWinningBy() >= -2) {
                        play = new KickFieldGoalPlay();
                    } else {
                        logger.debug("Going for it!");
                        if (rand.nextInt(3) == 0) {
                            play = new RunPlay();
                        } else {
                            play = new PassPlay();
                        }
                    }
                } else if (gameContext.getYardsToTD() < 35) {
                    play = new KickFieldGoalPlay();
                } else {
                    play = new PuntPlay();
                }
            } else {
                if ((gameContext.getQuarter() >= 4) && (gameContext.getWinningBy() <= 0)) {
                    if (rand.nextInt(6) == 0) {
                        play = new RunPlay();
                    } else {
                        play = new PassPlay();
                    }
                } else {
                    if (rand.nextInt(2) == 0) {
                        play = new RunPlay();
                    } else {
                        play = new PassPlay();
                    }
                }
            }
        }
        play.setGame(game);
        play.setGameContext(gameContext);
        return play;
    }

    abstract public void go();

    public GameEvent.NextEvent getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(GameEvent.NextEvent nextEvent) {
        this.nextEvent = nextEvent;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public double getYards() {
        return yards;
    }

    public void setYards(double yards) {
        this.yards = yards;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public DepthChartRepository getDepthChartRepository() {
        return depthChartRepository;
    }

    public void setDepthChartRepository(DepthChartRepository depthChartRepository) {
        this.depthChartRepository = depthChartRepository;
    }
}
