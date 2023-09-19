package com.brunkow.game.play;

import com.brunkow.game.GameContext;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnsideKickPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(OnsideKickPlay.class);

    public void go() {
        double rawPower = getRandomPower(1000);
        /*
        nextEvent = GameEvent.NextEvent.KICKOFF;
        if (gameContext.isDirection())
            gameContext.setYardLine(35);
        else
            gameContext.setYardLine(65);
        this.elapsedTime = 6;

         */
    }

    public double getTeamPlayPower() {
        return 0.0;
    }
}
