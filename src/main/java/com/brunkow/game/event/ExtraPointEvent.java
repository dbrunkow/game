package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraPointEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(ExtraPointEvent.class);
    ExtraPointEvent(Play play, Field field) {
        super(play, field);
    }
    @Override
    public void go() {
        this.gameSituation = GameSituation.KICKOFF;
        if (field.isDirection()) {
            field.setYardLine(100);
        } else {
            field.setYardLine(0);
        }
        field.addScore(field.getDirection(), 1);
    }
}
