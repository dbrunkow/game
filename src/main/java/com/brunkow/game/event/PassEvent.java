package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassEvent extends GameEvent  {
    private static final Logger logger = LoggerFactory.getLogger(PassEvent.class);
    PassEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    @Override
    public void go() {
        gameContext.addYards(play.getYards());
        if (gameContext.isFourthDown()) {
            logger.debug("Turnover on downs");
            gameContext.changePossession();
        } else if (gameContext.isFirstDown()) {
            //logger.debug("First Down");
            //Game.logOffenseDefence(game);
            gameContext.setSeries(0.0);
            gameContext.setDown(1);
        } else {
            gameContext.addDown();
        }
    }
}
