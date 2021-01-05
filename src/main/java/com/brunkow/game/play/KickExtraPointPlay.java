package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickExtraPointPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(KickExtraPointPlay.class);

    KickExtraPointPlay(Game game, GameContext gameContext) {
        super(game, gameContext);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.EXTRAPOINT;
        if (gameContext.isDirection()) {
            gameContext.setYardLine(97);
        } else {
            gameContext.setYardLine(3);
        }
        gameContext.setDown(1);
    }

    public double getPower() {
        return 0.0;
    }
}
