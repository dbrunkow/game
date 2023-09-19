package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TouchdownEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(TouchdownEvent.class);
    TouchdownEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }

    public void go() {
        this.gameSituation = GameSituation.TOUCHDOWN;
        //gameContext.addYards(play.getYards());
        gameContext.setSeries(0);
        gameContext.addScore(6);
    }
}
