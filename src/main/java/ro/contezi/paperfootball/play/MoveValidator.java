package ro.contezi.paperfootball.play;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class MoveValidator {

    private final List<SymmetricLine> moves;
    private final FootballNode original;

    public MoveValidator(List<SymmetricLine> moves, FootballNode original) {
        this.moves = moves;
        this.original = original;
    }

    public void validate() throws IllegalMoveException {
        validateNoLinesAreCrossed();
        validateNoPointsOut();
        validateAllPointsAreAlreadyPassedExceptLast();
    }

    private void validateNoPointsOut() throws PointOutException {
        Optional<Point> pointOut = moves.stream().flatMap(SymmetricLine::points)
                .filter(original.getFootballField()::isOut).findAny();
        if (pointOut.isPresent()) {
            throw new PointOutException(pointOut.get());
        }
    }

    private void validateAllPointsAreAlreadyPassedExceptLast() throws IllegalMoveException {
        Set<Point> pointsNotParsed = moves.stream().flatMap(SymmetricLine::points)
                .filter(point -> !original.alreadyPassedPoints().contains(point)).collect(Collectors.toSet());
        Point endpoint = SymmetricLine.findEndPoint(original.getCurrentPosition(), moves);
        if (!pointsNotParsed.remove(endpoint)) {
            throw new EndPointAlreadyParsedException(endpoint);
        }
        if (!pointsNotParsed.isEmpty()) {
            throw new IllegalContinuationFromPointException(pointsNotParsed.iterator().next());
        }
    }

    private void validateNoLinesAreCrossed() throws LineAlreadyDrawnException {
        Set<SymmetricLine> currentLines = new HashSet<>();
        for (SymmetricLine line : moves) {
            if (line.points().allMatch(original.getFootballField()::isEdge)
                    && (line.points().map(Point::getX).collect(Collectors.toSet()).size() == 1
                            || line.points().map(Point::getY).collect(Collectors.toSet()).size() == 1)) {
                throw new LineAlreadyDrawnException(line);
            }
            if (!currentLines.add(line) || original.getLines().contains(line)) {
                throw new LineAlreadyDrawnException(line);
            }

        }
    }
}
