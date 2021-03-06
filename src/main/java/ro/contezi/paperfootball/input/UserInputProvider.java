package ro.contezi.paperfootball.input;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.play.IllegalMoveException;

public interface UserInputProvider {
    String getInput(String previousInput, FootballNode node);
    void notifyIncorrectInput(IllegalMoveException ime);
}
