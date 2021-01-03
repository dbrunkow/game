package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PenaltyEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(PenaltyEvent.class);
    PenaltyEvent(Play play, Field field) {
        super(play, field);
    }
    public void go() {
        logger.debug("PenaltyEvent");
    }
}
