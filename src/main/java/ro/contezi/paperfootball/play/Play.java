package ro.contezi.paperfootball.play;

import java.util.List;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;

public class Play {
    public static void main(String[] args) {
        FootballNode node = new FootballNode();
    }
    
    static FootballNode play(String userInput, FootballNode original) {
        List<SymmetricLine> moves = new Moves(original.getCurrentPosition())
                .fromString(userInput);
        new MoveValidator(moves, original).validate();
        return new FootballNode(original, moves);
    }
    
}
