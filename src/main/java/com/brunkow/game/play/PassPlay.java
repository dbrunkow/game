package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(PassPlay.class);

    PassPlay(Game game, GameContext gameContext) {
        super(game, gameContext);
    }

    public void go() {
        double turnover = rand.nextInt(1000);
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
        }
    }

    public void passBall() {
        nextEvent = GameEvent.NextEvent.PASS;
        double yards;
        double group = rand.nextInt(1000);
        if (group <= 120.0) {
            yards = -1.0 * rand.nextInt(100) / 10.0;
        } else if (group < 210) {
            // 11 to 20 10%
            yards = rand.nextInt(100) / 10.0 + 20.0;
        } else if (group < 230) {
            // 20+  1% = 2
            yards = 100.0;
        } else  {
            // 0 to 10  0-79 80%
            yards = rand.nextInt(100) / 10.0 + 10;
        }
        if (gameContext.getYardsToTD() < yards) {
            this.yards = gameContext.getYardsToTD();
        } else {
            this.yards = yards;
        }

    }

    public double getPower() {
        return 0.0;
    }
}
