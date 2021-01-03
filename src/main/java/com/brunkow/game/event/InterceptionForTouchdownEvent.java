package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterceptionForTouchdownEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(InterceptionForTouchdownEvent.class);
    InterceptionForTouchdownEvent(Play play, Field field) {
        super(play, field);
    }
}
