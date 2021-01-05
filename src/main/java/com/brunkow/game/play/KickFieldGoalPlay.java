package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickFieldGoalPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(KickFieldGoalPlay.class);
    KickFieldGoalPlay(Game game, GameContext gameContext) {
        super(game, gameContext);
    }
    public void go() {
        if (kick()) {
            nextEvent = GameEvent.NextEvent.KICKFIELDGOAL;
        } else {
            nextEvent = GameEvent.NextEvent.MISSEDFIELDGOAL;
        }
        this.elapsedTime = 5;
    }

    private boolean kick() {
        // Kicker leg
        // Kicker accuracy
        // Distance
        // Wind
        int kicking = rand.nextInt(100);
        if (gameContext.getYardsToTD() <=25) {
            return kicking <= 95;
        } else if (gameContext.getYardsToTD() < 35) {
            return kicking <= 80;
        } else if (gameContext.getYardsToTD() < 45) {
            return kicking <= 70;
        } else if (gameContext.getYardsToTD() < 55) {
            return kicking <= 50;
        } else {
            return kicking <= 10;
        }
    }

    public double getPower() {
        return 0.0;
    }
}
