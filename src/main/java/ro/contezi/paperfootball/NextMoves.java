package ro.contezi.paperfootball;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NextMoves {
    private final FootballField footballField;
    private final Point currentPosition;
    private final Set<SymmetricLine> alreadyPassed;
    
    public NextMoves(FootballField footballField, Point currentPosition, Set<SymmetricLine> alreadyPassed) {
        this.footballField = footballField;
        this.currentPosition = currentPosition;
        this.alreadyPassed = alreadyPassed;
    }
    
    private Set<Point> immediateNeighbors() {
        Set<Point> neighbors = new HashSet<>();
        IntStream.range(-1, 2).forEach(x -> 
            IntStream.range(-1, 2).forEach(y -> 
                neighbors.add(new Point(currentPosition.getX() + x, currentPosition.getY() + y))));
        neighbors.remove(currentPosition);
        return neighbors;
    }
    
    public Set<List<SymmetricLine>> getPossibleMoves() {
        return immediateNeighbors().stream()
                .map(neighbor -> new SymmetricLine(currentPosition, neighbor))
                .filter(line -> !alreadyPassed.contains(line))
                .map(line -> Arrays.asList(line))
                .collect(Collectors.toSet());
    }
}
