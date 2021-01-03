package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyKickoffPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(SafetyKickoffPlay.class);

    SafetyKickoffPlay(Game game, Field field) {
        super(game, field);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.SAFETYKICKOFF;
        //field.changePossession();
        if (field.isDirection())
            field.setYardLine(35);
        else
            field.setYardLine(65);
    }
}
