package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuntEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(PuntEvent.class);
    PuntEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    public void go() {
        gameContext.addYards(40);
        if ((gameContext.getYardLine() <= 0) && (!gameContext.isDirection())) {
            gameContext.setYardLine(20.0);
        } else if ((gameContext.getYardLine() >= 100) && (gameContext.isDirection())) {
            gameContext.setYardLine(80.0);
        }
        gameContext.changePossession();
    }
}
