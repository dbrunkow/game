package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickExtraPointPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(KickExtraPointPlay.class);

    KickExtraPointPlay(Game game, Field field) {
        super(game, field);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.EXTRAPOINT;
        if (field.isDirection()) {
            field.setYardLine(97);
        } else {
            field.setYardLine(3);
        }
        field.setDown(1);
    }
}
