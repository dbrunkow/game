package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickoffEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(KickoffEvent.class);
    KickoffEvent(Play play, Field field) {
        super(play, field);
    }
    public void go() {
        if (field.isDirection()) {
            field.setYardLine(80);
        } else {
            field.setYardLine(20);
        }
        field.changePossession();
    }
}