package com.brunkow.game.play;

import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.DepthChart;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.brunkow.game.GameStats.RUN;


public class RunPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(RunPlay.class);

    public RunPlay() {

    }

    public void go() {
        double fumble = getFumblePower(1000);
        if (fumble > 980) {
            fumble();
            this.elapsedTime = 6;
        } else {
            runBall();
            this.elapsedTime = 35;
            Player runningBack = getRunningBack(gameContext.getOffenseTeam().getId());
            gameContext.addRunYards(getYards(), gameContext.getTeamOnOffense(), runningBack);
            gameContext.addTeamStats( gameContext.getTeamOnOffense(), RUN, getYards());
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

    public void fumble() {
        nextEvent = GameEvent.NextEvent.FUMBLE;
        this.elapsedTime = 10;
    }

    public void runBall() {
        nextEvent = GameEvent.NextEvent.RUN;
        double yards;
        double totalPower = getTotalPower();
        //logger.debug("TotalPower: " + totalPower);
        if (totalPower > 400) {
            double randomPower = getRandomPower(1000);
            //logger.debug("RandomPower: " + randomPower);
            if (randomPower <= 50) {
                // Loss 5% of time of 0 to 5 yards
                yards = -1.0 * getPlayPower(5);
            } else if (randomPower < 300) {
                // 0 to 4 30%
                yards = getPlayPower(5);
            } else if (randomPower < 650) {
                // 5 to 9 30%
                yards = getPlayPower(5) + 5.0;
            } else if (randomPower < 850) {
                // 10 to 14 20%
                yards = getPlayPower(5) + 10.0;
            } else if (randomPower < 950) {
                // 15 to 19 10%
                yards = getPlayPower(5) + 15.0;
            } else if (randomPower < 980) {
                // 20 to 29 3%
                yards = getPlayPower(10) + 20.0;
            } else if (randomPower < 990) {
                // 30 to 99 1%
                yards = getPlayPower(70) + 30.0;
            } else {
                // TD  1%
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
        Player runningBack = getRunningBack(offense.getId());
        //logger.debug("Power: O: " + offensivePower + " " + offense.getName() + "   D: " + defensivePower + " " + defense.getName() + " RB: " + runningBack.getPower());
        double power = Double.valueOf((runningBack.getPower() + offensivePower))/Double.valueOf(3*(defensivePower));
        //logger.debug("Power is " + power);
        return power;
    }

    public Player getRunningBack(Long offensiveId) {
        DepthChart depthChart = depthChartRepository.findByTeamIdAndPositionAndDepth(offensiveId, "RB", 1);
        return depthChart.getPlayer();
    }
}
