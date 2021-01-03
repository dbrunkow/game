package com.brunkow.game.event;

import com.brunkow.game.GameContext;
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
        SAFETYKICKOFF,
        KICKFIELDGOAL,
        TURNOVERONDOWNS,
        MISSEDFIELDGOAL
    }
    GameContext gameContext;
    Play play;
    GameSituation gameSituation = GameSituation.NONE;
    public void go() {
        logger.debug("Go");
    }
    public GameEvent(Play play, GameContext gameContext) {
        this.gameContext = gameContext;
        this.play = play;
    }
    public static GameEvent getInstance(Play play, GameContext gameContext) {
        GameEvent event;
        if (gameContext.isTouchdown(play.getYards())) {
            event = new TouchdownEvent(play, gameContext);
        } else if (gameContext.isSafety(play.getYards())) {
            event = new SafetyEvent(play, gameContext);
        } else {
            if (NextEvent.RUN.equals(play.nextEvent)) {
                event = new RunEvent(play, gameContext);
            } else if (NextEvent.FUMBLE.equals(play.nextEvent)) {
                event = new FumbleEvent(play, gameContext);
            } else if (NextEvent.INTERCEPTION.equals(play.nextEvent)) {
                event = new InterceptionEvent(play, gameContext);
            } else if (NextEvent.PASS.equals(play.nextEvent)) {
                event = new PassEvent(play, gameContext);
            } else if (NextEvent.KICKOFF.equals(play.nextEvent)) {
                event = new KickoffEvent(play, gameContext);
            } else if (NextEvent.PUNT.equals(play.nextEvent)) {
                event = new PuntEvent(play, gameContext);
            } else if (NextEvent.EXTRAPOINT.equals(play.nextEvent)) {
                event = new ExtraPointEvent(play, gameContext);
            } else if (NextEvent.SAFETYKICKOFF.equals(play.nextEvent)) {
                event = new SafetyKickoffEvent(play, gameContext);
            } else if (NextEvent.KICKFIELDGOAL.equals(play.nextEvent)) {
                event = new KickFieldGoalEvent(play, gameContext);
            } else if (NextEvent.MISSEDFIELDGOAL.equals(play.nextEvent)) {
                event = new MissedFieldGoalEvent(play, gameContext);
            } else if (NextEvent.TURNOVERONDOWNS.equals(play.nextEvent)) {
                event = new TurnoverOnDownsEvent(play, gameContext);

            } else {
                event = new PenaltyEvent(play, gameContext);
            }
        }
        return event;
    }
    public GameSituation getGameSituation() {
        return this.gameSituation;
    }

}
