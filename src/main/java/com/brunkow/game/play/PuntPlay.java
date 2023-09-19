package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuntPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(PuntPlay.class);

    public void go() {
        nextEvent = GameEvent.NextEvent.PUNT;
        double rawPower = getRandomPower(1000);
        this.elapsedTime = 10;
    }

    public double getTeamPlayPower() {
        return 0.0;
    }
}
