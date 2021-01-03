package com.brunkow.game.event;

import com.brunkow.game.Field;
import com.brunkow.game.play.Play;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameEvent {
    private static final Logger logger = LoggerFactory.getLogger(GameEvent.class);
    public static enum GameSituation {
        NONE,
        KICKOFF,
        SAFETY,
        TOUCHDOWN,
        FIELDGOAL,
        SAFETYKICKOFF

    }
    public static enum NextEvent {
        RUN,
        FUMBLE,
        INTERCEPTION,
        PASS,
        PUNT,
        KICKOFF,
        TOUCHDOWN,
        EXTRAPOINT,
        SAFETYKICKOFF
    }
    Field field;
    Play play;
    GameSituation gameSituation = GameSituation.NONE;
    public void go() {
        logger.debug("Go");
    }
    public GameEvent(Play play, Field field) {
        this.field = field;
        this.play = play;
    }
    public static GameEvent getInstance(Play play, Field field) {
        GameEvent event;
        if (field.isTouchdown(play.getYards())) {
            event = new TouchdownEvent(play, field);
        } else if (field.isSafety(play.getYards())) {
            event = new SafetyEvent(play, field);
        } else {
            if (NextEvent.RUN.equals(play.nextEvent)) {
                event = new RunEvent(play, field);
            } else if (NextEvent.FUMBLE.equals(play.nextEvent)) {
                event = new FumbleEvent(play, field);
            } else if (NextEvent.INTERCEPTION.equals(play.nextEvent)) {
                event = new InterceptionEvent(play, field);
            } else if (NextEvent.PASS.equals(play.nextEvent)) {
                event = new PassEvent(play, field);
            } else if (NextEvent.KICKOFF.equals(play.nextEvent)) {
                event = new KickoffEvent(play, field);
            } else if (NextEvent.PUNT.equals(play.nextEvent)) {
                event = new PuntEvent(play, field);
            } else if (NextEvent.EXTRAPOINT.equals(play.nextEvent)) {
                event = new ExtraPointEvent(play, field);
            } else if (NextEvent.SAFETYKICKOFF.equals(play.nextEvent)) {
                event = new SafetyKickoffEvent(play, field);

            } else {
                event = new PenaltyEvent(play, field);
            }
        }
        return event;
    }
    public GameSituation getGameSituation() {
        return this.gameSituation;
    }

}
