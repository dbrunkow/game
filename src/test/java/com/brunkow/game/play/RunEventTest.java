package com.brunkow.game.play;


import com.brunkow.game.GameContext;
import com.brunkow.game.dao.TeamRepository;
import com.brunkow.game.event.GameEvent;
import com.brunkow.game.vo.DepthChart;
import com.brunkow.game.vo.Game;
import com.brunkow.game.vo.Player;
import com.brunkow.game.vo.Team;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-local.properties")
public class RunEventTest {
    private static final Logger logger = LoggerFactory.getLogger(RunEventTest.class);

    Game game;
    GameContext gameContext;

    @BeforeEach
    public void  setup() {
        Team teamA = Mockito.mock(Team.class);
        Team teamB = Mockito.mock(Team.class);

        gameContext = new GameContext(teamA, teamB);
        game = new Game(teamA, teamB);
    }

    public RunPlay createRunPlay(int playPower, int fumblePower, int teamPlayPower, int randomPower) {
        RunPlay runPlay = new RunPlay() {
            public double getTeamPlayPower() {
                return teamPlayPower;
            }
            public double getPlayPower(int max) {
                return playPower;
            }
            public double getFumblePower(int max) {
                return fumblePower;
            }
            public double getRandomPower(int max) {
                return randomPower;
            }
            public Player getRunningBack(Long id) {
                Player player = new Player();
                player.setId(Long.valueOf(1));
                player.setTeam(game.getTeam(0));
                player.setFirstName("FirstName");
                player.setLastName("LastName");
                return player;
            }
        };
        runPlay.setGame(game);
        runPlay.setGameContext(gameContext);
        return runPlay;
    }

    @Test
    public void testFumble() {
        RunPlay runPlay;
        int playPower = 0;
        //int fumblePower;
        int teamPlayPower = 0;
        int randomPower = 0;
        for (int fumblePower=981; fumblePower<1000; fumblePower++) {
            runPlay = createRunPlay(playPower, fumblePower, teamPlayPower, randomPower);
            runPlay.go();
            assertEquals(GameEvent.NextEvent.FUMBLE, runPlay.getNextEvent());
        }
    }

    @Test
    public void testRunLessThan120() {
        RunPlay runPlay;
        int playPower = 10;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 100;
        runPlay = createRunPlay(playPower, fumblePower, teamPlayPower, randomPower);
        runPlay.go();
        assertEquals(-1, runPlay.getYards());
        assertEquals(GameEvent.NextEvent.RUN, runPlay.getNextEvent());
    }

    @Test
    public void testRunLessThan210() {
        RunPlay runPlay;
        int playPower = 10;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 200;
        runPlay = createRunPlay(playPower, fumblePower, teamPlayPower, randomPower);
        runPlay.go();
        assertEquals(11, runPlay.getYards());
        assertEquals(GameEvent.NextEvent.RUN, runPlay.getNextEvent());
    }

    @Test
    public void testRunLessThan230() {
        RunPlay runPlay;
        int playPower = 10;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 220;
        runPlay = createRunPlay(playPower, fumblePower, teamPlayPower, randomPower);
        runPlay.go();
        assertEquals(100, runPlay.getYards());
        assertEquals(GameEvent.NextEvent.RUN, runPlay.getNextEvent());
    }

    @Test
    public void testRunGreaterThanOrEqual230() {
        RunPlay runPlay;
        int playPower = 10;
        int fumblePower = 0;
        int teamPlayPower = 0;
        int randomPower = 500;
        runPlay = createRunPlay(playPower, fumblePower, teamPlayPower, randomPower);
        runPlay.go();
        assertEquals(1, runPlay.getYards());
        assertEquals(GameEvent.NextEvent.RUN, runPlay.getNextEvent());
    }

    @AfterEach
    void teardown() {

    }
}
