package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuntPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(PuntPlay.class);

    PuntPlay(Game game, Field field) {
        super(game, field);
    }

    public void go() {
        nextEvent = GameEvent.NextEvent.PUNT;
    }
}
