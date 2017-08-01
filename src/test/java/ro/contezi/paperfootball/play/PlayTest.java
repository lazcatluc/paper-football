package ro.contezi.paperfootball.play;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.input.UserInputProvider;

public class PlayTest {
    
    private static final Logger LOGGER = LogManager.getLogger(PlayTest.class);
    
    @Test
    public void playsUntilNorthScores() {
        UserInputProvider input = goSouth();
        Play play = new Play(new FootballField(), input , input);
        assertThat(play.playToTheEnd().getCurrentPosition())
            .isEqualTo(new Point(0, -6));
    }
    
    @Test
    public void retriesAfterException() throws Exception {
        UserInputProvider north = goSouth();
        UserInputProvider south = new UserInputProvider() {
            private boolean b;
            @Override
            public String getInput(String previousInput, FootballNode node) {
                b = !b;
                return b?"0":"4";
            }

            @Override
            public void notifyIncorrectInput(IllegalMoveException ime) {
                LOGGER.error(ime.getMessage(), ime);
            }
        };
        Play play = new Play(new FootballField(), north , south);
        assertThat(play.playToTheEnd().getCurrentPosition())
            .isEqualTo(new Point(0, -6));
    }

    public UserInputProvider goSouth() {
        UserInputProvider input = new UserInputProvider() {
            @Override
            public String getInput(String previousInput, FootballNode node) {
                return "4";
            }

            @Override
            public void notifyIncorrectInput(IllegalMoveException ime) {
                LOGGER.error(ime.getMessage(), ime);
            }
        };
        return input;
    }

}
