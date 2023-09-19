package com.brunkow.game.play;


import com.brunkow.game.GameContext;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PassEventTest {
    private static final Logger logger = LoggerFactory.getLogger(PassEventTest.class);

    Game game;
    GameContext gameContext;

    @BeforeEach
    public void  setup() {
        Team teamA = Mockito.mock(Team.class);
        Team teamB = Mockito.mock(Team.class);

        gameContext = new GameContext(teamA, teamB);
        game = new Game(teamA, teamB);
    }

    public PassPlay createPassPlay(int playPower, int fumblePower,
                                   int interceptionPower, int teamPlayPower, int randomPower) {
        PassPlay passPlay = new PassPlay() {
            public double getTeamPlayPower() {
                return teamPlayPower;
            }
            public double getPlayPower(int max) {
                return playPower;
            }
            public double getInterceptionPower(int max) {
                return interceptionPower;
            }
            public double getFumblePower(int max) {
                return fumblePower;
            }
            public double getRandomPower(int max) {
                return randomPower;
            }
            public Player getQuarterBack(Long id) {
                Player player = new Player();
                player.setId(Long.valueOf(1));
                player.setTeam(game.getTeam(0));
                player.setFirstName("FirstName");
                player.setLastName("LastName");
                return player;
            }
        };
        passPlay.setGame(game);
        passPlay.setGameContext(gameContext);
        return passPlay;
    }

    @Test
    public void testInterception() {
        PassPlay passPlay;
        int playPower = 0;
        //int interceptionPower;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 0;
        for (int interceptionPower=981; interceptionPower<1000; interceptionPower++) {
            passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
            passPlay.go();
            assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
        }
    }

    @Test
    public void testFumble() {
        PassPlay passPlay;
        int playPower = 0;
        int interceptionPower = 0;
        //int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 0;
        for (int fumblePower=971; fumblePower<980; fumblePower++) {
            passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
            passPlay.go();
            assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
        }
    }

    @Test
    public void testRunLessThan180() {
        PassPlay passPlay;
        int playPower = 10;
        int interceptionPower = 0;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 100;
        passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
        passPlay.go();
        assertEquals(-1, passPlay.getYards());
        assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
    }

    @Test
    public void testRunLessThan250() {
        PassPlay passPlay;
        int playPower = 10;
        int interceptionPower = 0;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 240;
        passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
        passPlay.go();
        assertEquals(21, passPlay.getYards());
        assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
    }

    @Test
    public void testRunLessThan280() {
        PassPlay passPlay;
        int playPower = 10;
        int interceptionPower = 0;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 260;
        passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
        passPlay.go();
        assertEquals(100, passPlay.getYards());
        assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
    }

    @Test
    public void testRunGreaterThanOrEqual280() {
        PassPlay passPlay;
        int playPower = 10;
        int interceptionPower = 0;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 500;
        passPlay = createPassPlay(playPower, fumblePower, interceptionPower, teamPlayPower, randomPower);
        passPlay.go();
        assertEquals(11, passPlay.getYards());
        assertEquals(GameEvent.NextEvent.PASS, passPlay.getNextEvent());
    }

    @AfterEach
    void teardown() {

    }
}
