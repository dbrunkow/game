package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(SafetyEvent.class);
    SafetyEvent(Play play, Field field) {
        super(play, field);
    }
    @Override
    public void go() {
        this.gameSituation = GameSituation.SAFETYKICKOFF;
        logger.debug(field.getYardLine() + " " + play.getYards());
        field.addScore(1-field.getDirection(), 2);
    }
}
