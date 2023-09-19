package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.DepthChart;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.brunkow.game.GameStats.PASS;

public class PassPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(PassPlay.class);

    public void go() {
        double turnover = getInterceptionPower(1000);
        if (turnover > 980) {
            nextEvent = GameEvent.NextEvent.INTERCEPTION;
            this.elapsedTime = 10;
        } else if (turnover > 970) {
            nextEvent = GameEvent.NextEvent.FUMBLE;
            this.elapsedTime = 10;
        } else {
            passBall();
            if (this.yards == 0) {
                this.elapsedTime = 6;
            } else {
                this.elapsedTime = 35;
            }
            Player quarterBack = getQuarterBack(gameContext.getOffenseTeam().getId());
            gameContext.addPassYards(getYards(), gameContext.getTeamOnOffense(), quarterBack);
            gameContext.addTeamStats( gameContext.getTeamOnOffense(), PASS, getYards());
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
    }

    public void passBall() {
        nextEvent = GameEvent.NextEvent.PASS;
        double yards;
        double totalPower = getTotalPower();
        //logger.debug("TotalPower: " + totalPower);
        if (totalPower > 400) {
            double randomPower = getRandomPower(1000);
            //logger.debug("RandomPower: " + randomPower);

            if (randomPower <= 20) {
                // Loss 2% of time of 0 to 5 yards
                yards = -1.0 * getPlayPower(5);
            } else if (randomPower < 300) {
                // 0 28% Incomplete
                yards = 0;
            } else if (randomPower < 650) {
                // 1 to 10 30%
                yards = getPlayPower(10) + 1.0;
            } else if (randomPower < 800) {
                // 11 to 20 15%
                yards = getPlayPower(10) + 11.0;
            } else if (randomPower < 900) {
                // 21 to 30 10%
                yards = getPlayPower(5) + 15.0;
            } else if (randomPower < 950) {
                // 20 to 29 5%
                yards = getPlayPower(10) + 20.0;
            } else if (randomPower < 980) {
                // 30 to 99 3%
                yards = getPlayPower(70) + 30.0;
            } else {
                // TD  2%
                yards = 100.0;
            }
            if (yards == -0.0) {
                yards = 0.0;
            }
        } else {
            yards = 0.0;
        }
        if (gameContext.getYardsToTD() < yards) {
            this.yards = gameContext.getYardsToTD();
        } else {
            this.yards = yards;
        }

    }

    public double getInterceptionPower(int max) {
        return getRandomPower(max);
    }

    public double getFumblePower(int max) {
        return getRandomPower(max);
    }

    public double getTotalPower() {
        double randomPower = getRandomPower(1000);
        double totalPower = randomPower * getTeamPlayPower();
        return totalPower;
    }

    public double getTeamPlayPower() {
        Team offense = gameContext.getOffenseTeam();
        int offensivePower = offense.getPower();
        Team defense = gameContext.getDefensiveTeam();
        int defensivePower = defense.getPower();
        Player quarterBack = getQuarterBack(offense.getId());
        //logger.debug("Power: O: " + offensivePower + " " + offense.getName() + "   D: " + defensivePower + " " + defense.getName() + " RB: " + quarterBack.getPower());
        double tmpOffensePower = (quarterBack.getPower()+offensivePower);
        double tmpDefensivePower = defensivePower;
        double power = ((tmpOffensePower * 6) - (3 * tmpDefensivePower))/100;
        //logger.debug("Power is " + power);
        return power;
    }

    public Player getQuarterBack(Long id) {
        DepthChart depthChart =
                depthChartRepository.findByTeamIdAndPositionAndDepth(
                        gameContext.getOffenseTeam().getId(), "QB", 1);
        return depthChart.getPlayer();
    }
}
