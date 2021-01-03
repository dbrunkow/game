package com.brunkow.game.event;

import com.brunkow.game.GameContext;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickFieldGoalEvent extends GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(KickFieldGoalEvent.class);
    KickFieldGoalEvent(Play play, GameContext gameContext) {
        super(play, gameContext);
    }
    @Override
    public void go() {
        this.gameSituation = GameSituation.KICKOFF;
        gameContext.addScore(gameContext.getDirection(), 3);
    }
}
