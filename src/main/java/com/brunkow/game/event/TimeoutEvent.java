package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeoutEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(TimeoutEvent.class);
    TimeoutEvent(Play play, Field field) {
        super(play, field);
    }
    @Override
    public void go() {
        logger.debug("TimeoutEvent");
    }
}