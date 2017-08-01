package ro.contezi.paperfootball.play;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class Play {
    public static void main(String[] args) {
        FootballNode node = new FootballNode();
    }
    
    static FootballNode play(String userInput, FootballNode original) {
        List<SymmetricLine> moves = parseMoves(userInput, original.getCurrentPosition());
        validate(moves, original);
        return new FootballNode(original, moves);
    }

    static void validate(List<SymmetricLine> moves, FootballNode original) throws IllegalMoveException {
        validateNoLinesAreCrossed(moves, original);
        validateNoPointsOut(moves, original);
        validateAllIntermediaryPointsAreAlreadyPassed(moves, original);
        validateEndpointIsNotParsed(moves, original);
    }

    private static void validateEndpointIsNotParsed(List<SymmetricLine> moves, FootballNode original) throws EndPointAlreadyParsedException {
        // TODO Auto-generated method stub
        
    }
    
    private static void validateNoPointsOut(List<SymmetricLine> moves, FootballNode original) throws PointOutException {
        // TODO Auto-generated method stub
        
    }

    private static void validateAllIntermediaryPointsAreAlreadyPassed(List<SymmetricLine> moves,
            FootballNode original) throws IllegalContinuationFromPointException {
        Set<Point> pointsNotParsed = moves.stream().flatMap(SymmetricLine::points).filter(point -> !original.alreadyPassedPoints().contains(point)).collect(Collectors.toSet());
        Point endpoint = SymmetricLine.findEndPoint(original.getCurrentPosition(), moves);
        pointsNotParsed.remove(endpoint);
        if (!pointsNotParsed.isEmpty()) {
            throw new IllegalContinuationFromPointException(pointsNotParsed.iterator().next());
        }
    }

    private static void validateNoLinesAreCrossed(List<SymmetricLine> moves, FootballNode original) throws LineAlreadyDrawnException {
        Set<SymmetricLine> currentLines = new HashSet<>();
        for (SymmetricLine line : moves) {
            if (line.points().allMatch(original.getFootballField()::isEdge) &&
                    (line.points().map(Point::getX).collect(Collectors.toSet()).size() == 1 ||
                     line.points().map(Point::getY).collect(Collectors.toSet()).size() == 1)) {
                throw new LineAlreadyDrawnException(line);
            }
            if (!currentLines.add(line)) {
                throw new LineAlreadyDrawnException(line);
            }
        }
    }

    private static List<SymmetricLine> parseMoves(String userInput, Point currentPosition) {
        // TODO Auto-generated method stub
        return null;
    }
}
