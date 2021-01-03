package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraPointEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(ExtraPointEvent.class);
    ExtraPointEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    @Override
    public void go() {
        this.gameSituation = GameSituation.KICKOFF;
        if (gameContext.isDirection()) {
            gameContext.setYardLine(100);
        } else {
            gameContext.setYardLine(0);
        }
        gameContext.addScore(gameContext.getDirection(), 1);
    }
}
