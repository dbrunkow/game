package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(SafetyEvent.class);
    SafetyEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    @Override
    public void go() {
        this.gameSituation = GameSituation.SAFETYKICKOFF;
        logger.debug(gameContext.getYardLine() + " " + play.getYards());
        gameContext.addScore(1- gameContext.getTeamOnOffense(), 2);
    }
}
