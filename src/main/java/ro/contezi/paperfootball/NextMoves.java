package ro.contezi.paperfootball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class NextMoves {
    private final FootballField footballField;
    private final Point currentPosition;
    private final Set<SymmetricLine> alreadyPassed;
    private final Set<Point> pointsPassed;
    
    public NextMoves() {
        this(new FootballField(), new Point(), Collections.emptySet());
    }
    
    public NextMoves(FootballField footballField, Point currentPosition, Set<SymmetricLine> alreadyPassed) {
        this.footballField = footballField;
        this.currentPosition = currentPosition;
        this.alreadyPassed = alreadyPassed;
        pointsPassed = new HashSet<>();
        alreadyPassed.stream().flatMap(SymmetricLine::points).forEach(pointsPassed::add);
        footballField.edge().forEach(pointsPassed::add);
        pointsPassed.add(currentPosition);
    }
    
    private Set<Point> immediateNeighbors(Point position) {
        Set<Point> neighbors = new HashSet<>();
        IntStream.range(-1, 2).forEach(x -> 
            IntStream.range(-1, 2).forEach(y -> 
                neighbors.add(new Point(position.getX() + x, position.getY() + y))));
        neighbors.remove(position);
        neighbors.removeIf(footballField::isOut);
        if (footballField.isEdge(position)) {
            neighbors.removeIf(footballField::isEdge);
        }
        return neighbors;
    }
    
    private Set<List<SymmetricLine>> fullPaths(Point origin, List<SymmetricLine> currentPath, boolean stopIfNotPassed) {
        if (stopIfNotPassed && !pointsPassed.contains(origin)) {
            return Collections.singleton(currentPath);
        }
        Set<List<SymmetricLine>> ret = new HashSet<>();
        for (Point neighbor : immediateNeighbors(origin)) {
            SymmetricLine symmetricLine = new SymmetricLine(origin, neighbor);
            if (alreadyPassed.contains(symmetricLine) || currentPath.contains(symmetricLine)) {
                continue;
            }
            List<SymmetricLine> newPath = new ArrayList<>(currentPath);
            newPath.add(symmetricLine);
            ret.addAll(fullPaths(neighbor, newPath, true));
        }
        return ret;
    }
    
    public Set<List<SymmetricLine>> getPossibleMoves(Point position) {
        return fullPaths(position, Collections.emptyList(), false);
    }
    
    public Set<List<SymmetricLine>> getPossibleMoves() {
        return getPossibleMoves(currentPosition);
    }
}
