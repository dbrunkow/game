package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickoffEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(KickoffEvent.class);
    KickoffEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    public void go() {
        if (gameContext.isDirection()) {
            gameContext.setYardLine(80);
        } else {
            gameContext.setYardLine(20);
        }
        gameContext.changePossession();
    }
}
