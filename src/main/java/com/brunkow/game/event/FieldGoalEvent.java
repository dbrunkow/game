package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldGoalEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(FieldGoalEvent.class);
    FieldGoalEvent(Play play, Field field) {
        super(play, field);
    }
}
