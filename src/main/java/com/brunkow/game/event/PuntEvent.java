package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuntEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(PuntEvent.class);
    PuntEvent(Play play, Field field) {
        super(play, field);
    }
    public void go() {
        field.addYards(40);
        if ((field.getYardLine() <= 0) && (!field.isDirection())) {
            field.setYardLine(20.0);
        } else if ((field.getYardLine() >= 100) && (field.isDirection())) {
            field.setYardLine(80.0);
        }
        field.changePossession();
    }
}
