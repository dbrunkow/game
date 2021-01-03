package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TouchdownEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(TouchdownEvent.class);
    TouchdownEvent(Play play, Field field) {
        super(play, field);
    }

    public void go() {
        this.gameSituation = GameSituation.TOUCHDOWN;
        field.addYards(play.getYards());
        field.setSeries(0);
        field.addScore(field.getDirection(), 6);
    }
}
