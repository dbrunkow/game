package com.brunkow.game.play;

import com.brunkow.game.Field;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public abstract class Play {
    private static final Logger logger = LoggerFactory.getLogger(Play.class);

    public GameEvent.NextEvent nextEvent;
    Random rand = new Random();
    int elapsedTime;
    double yards = 0.0;
    Field field;
    Game game;

    public static Play createPlay(Game game, Field field) {
        Random rand = new Random();
        Play play;
        if (GameEvent.GameSituation.KICKOFF.equals(field.getGameSituation())) {
            play = new KickoffPlay(game, field);
        } else if (GameEvent.GameSituation.TOUCHDOWN.equals(field.getGameSituation())) {
            play = new KickExtraPointPlay(game, field);
        } else if (GameEvent.GameSituation.SAFETYKICKOFF.equals(field.getGameSituation())) {
            play = new SafetyKickoffPlay(game, field);

        } else {
            if (field.isFourthDown()) {
                play = new PuntPlay(game, field);
            } else {
                if (rand.nextInt(2) == 0) {
                    play = new PassPlay(game, field);
                } else {
                    play = new RunPlay(game, field);
                }
            }
        }
        return play;
    }



    public Play(Game game, Field field) {
        this.field = field;
        this.game = game;
    }

    abstract public void go();

    public GameEvent.NextEvent getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(GameEvent.NextEvent nextEvent) {
        this.nextEvent = nextEvent;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public double getYards() {
        return yards;
    }

    public void setYards(double yards) {
        this.yards = yards;
    }
}
