package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassEvent extends GameEvent  {
    private static final Logger logger = LoggerFactory.getLogger(PassEvent.class);
    PassEvent(Play play, Field field) {
        super(play, field);
    }
    @Override
    public void go() {
        field.addYards(play.getYards());
        if (field.isFourthDown()) {
            logger.debug("Turnover on downs");
            field.changePossession();
        } else if (field.isFirstDown()) {
            //logger.debug("First Down");
            //Game.logOffenseDefence(game);
            field.setSeries(0.0);
            field.setDown(1);
        } else {
            field.addDown();
        }
    }
}
