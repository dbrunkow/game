package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
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
    }

    public void fumble() {
        nextEvent = GameEvent.NextEvent.FUMBLE;
        this.elapsedTime = 10;
    }

    public void runBall() {
        nextEvent = GameEvent.NextEvent.RUN;
        double yards;
        double group = rand.nextInt(1000);
        if (group <= 120.0) {
            yards = -1.0 * rand.nextInt(100) / 10.0;
        } else if (group < 210) {
            // 11 to 20 10%
            yards = rand.nextInt(100) / 10.0 + 10.0;
        } else if (group < 230) {
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
}
