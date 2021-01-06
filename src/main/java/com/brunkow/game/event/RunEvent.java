package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(RunEvent.class);
    RunEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    @Override
    public void go() {
    }
}
