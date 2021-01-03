package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickoffPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(KickoffPlay.class);

    KickoffPlay(Game game, Field field) {
        super(game, field);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.KICKOFF;
        if (field.isDirection())
            field.setYardLine(35);
        else
            field.setYardLine(65);
    }
}
