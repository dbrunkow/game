package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.DepthChart;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RunPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(RunPlay.class);

    RunPlay(Game game, GameContext gameContext) {
        super(game, gameContext);
    }

    public void go() {
        double fumble = rand.nextInt(1000);
        if (fumble > 980) {
            fumble();
            this.elapsedTime = 6;
        } else {
            runBall();
            this.elapsedTime = 35;
        }
        DepthChart depthChart =
            depthChartRepository.findByTeamIdAndPositionAndDepth(
                    gameContext.getOffenseTeam().getId(), "RB", 1);
        gameContext.addRunYards(getYards(), gameContext.getTeamOnOffense(), depthChart.getPlayer());
        if (gameContext.isFourthDown()) {
            logger.debug("Turnover on downs");
            gameContext.changePossession();
        } else if (gameContext.isFirstDown()) {
            gameContext.setSeries(0.0);
            gameContext.setDown(1);
        } else {
            gameContext.addDown();
        }

    }

    public void fumble() {
        nextEvent = GameEvent.NextEvent.FUMBLE;
        this.elapsedTime = 10;
    }

    public void runBall() {
        nextEvent = GameEvent.NextEvent.RUN;
        double yards;
        double rawPower = rand.nextInt(1000);
        double totalPower = rawPower + (getPower() * 15.0);
        logger.debug("Power: " + (getPower() * 15.0));
        if (totalPower <= 120.0) {
            yards = -1.0 * rand.nextInt(100) / 10.0;
        } else if (totalPower < 210) {
            // 11 to 20 10%
            yards = rand.nextInt(100) / 10.0 + 10.0;
        } else if (totalPower < 230) {
            // 20+  1% = 2
            yards = 100.0;
        } else  {
            // 0 to 10  0-79 80%
            yards = rand.nextInt(100) / 10.0;
        }
        if (gameContext.getYardsToTD() < yards) {
            this.yards = gameContext.getYardsToTD();
        } else {
            this.yards = yards;
        }
    }

    public double getPower() {
        Team offense = gameContext.getOffenseTeam();
        int offensivePower = offense.getPower();
        Team defense = gameContext.getDefensiveTeam();
        int defensivePower = defense.getPower();
        DepthChart depthChart = depthChartRepository.findByTeamIdAndPositionAndDepth(offense.getId(), "RB", 1);
        Player runningBack = depthChart.getPlayer();
        logger.debug("Power: O: " + offensivePower + " D: " + defensivePower + " RB: " + runningBack.getPower());
        return Double.valueOf(runningBack.getPower() + offensivePower - defensivePower - defensivePower);
    }
}
