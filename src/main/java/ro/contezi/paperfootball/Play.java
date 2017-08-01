package ro.contezi.paperfootball;

import java.util.List;

public class Play {
    public static void main(String[] args) {
        FootballNode node = new FootballNode();
    }
    
    static FootballNode play(String userInput, FootballNode original) {
        List<SymmetricLine> moves = parseMoves(userInput, original.getCurrentPosition());
        validate(moves, original);
        return new FootballNode(original, moves);
    }

    static void validate(List<SymmetricLine> moves, FootballNode original) {
        validateNoLinesAreCrossed(moves, original);
        validateAllIntermediaryPointsAreAlreadyPassed(moves, original);
        validateEndpointIsNotParsed(moves, original);
    }

    static void validateEndpointIsNotParsed(List<SymmetricLine> moves, FootballNode original) {
        // TODO Auto-generated method stub
        
    }

    static void validateAllIntermediaryPointsAreAlreadyPassed(List<SymmetricLine> moves,
            FootballNode original) {
        // TODO Auto-generated method stub
        
    }

    static void validateNoLinesAreCrossed(List<SymmetricLine> moves, FootballNode original) {
        // TODO Auto-generated method stub
        
    }

    static List<SymmetricLine> parseMoves(String userInput, Point currentPosition) {
        // TODO Auto-generated method stub
        return null;
    }
}
