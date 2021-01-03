package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FumbleEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(FumbleEvent.class);
    FumbleEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    public void go() {
        gameContext.changePossession();
    }
}
