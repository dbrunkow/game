package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PassPlay extends Play {
    private static final Logger logger = LoggerFactory.getLogger(PassPlay.class);

    PassPlay(Game game, Field field) {
        super(game, field);
    }

    public void go() {
        double interception = rand.nextInt(1000);
        if (interception > 980) {
            interception();
        } else {
            passBall();
        }
    }

    public void interception() {
        nextEvent = GameEvent.NextEvent.INTERCEPTION;
        this.elapsedTime = 60;
    }

    public void passBall() {
        nextEvent = GameEvent.NextEvent.PASS;
        double yards;
        double group = rand.nextInt(1000);
        if (group <= 120.0) {
            yards = -1.0 * rand.nextInt(100) / 10.0;
        } else if (group < 210) {
            // 11 to 20 10%
            yards = rand.nextInt(100) / 10.0 + 20.0;
        } else if (group < 230) {
            // 20+  1% = 2
            yards = 100.0;
        } else  {
            // 0 to 10  0-79 80%
            yards = rand.nextInt(100) / 10.0 + 10;
        }
        if (field.getYardsToTD() < yards) {
            this.yards = field.getYardsToTD();
        } else {
            this.yards = yards;
        }
        this.elapsedTime = 60;
    }
}
