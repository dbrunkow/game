package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyKickoffPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(SafetyKickoffPlay.class);

    SafetyKickoffPlay(Game game, GameContext gameContext) {
        super(game, gameContext);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.SAFETYKICKOFF;
        //gameContext.changePossession();
        if (gameContext.isDirection())
            gameContext.setYardLine(35);
        else
            gameContext.setYardLine(65);
        this.elapsedTime = 6;
    }
}
