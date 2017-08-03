package ro.contezi.paperfootball;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Point> neighbors = position.getNeighbors();
        neighbors.removeIf(footballField::isOut);
        if (footballField.isEdge(position)) {
            neighbors.removeIf(footballField::isEdge);
        }
        return neighbors;
    }
    
    private Set<List<SymmetricLine>> fullPaths(Point origin, Collection<SymmetricLine> currentPath, boolean stopIfNotPassed) {
        if (stopIfNotPassed && !pointsPassed.contains(origin)) {
            return Collections.singleton(new ArrayList<>(currentPath));
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
        return getPossibleMoves(position, Collections.emptyList());
    }
    
    public Set<List<SymmetricLine>> getPossibleMoves(Point position, Collection<SymmetricLine> currentPath) {
        return fullPaths(position, currentPath, false);
    }
    
    public Set<List<SymmetricLine>> getPossibleMoves() {
        return getPossibleMoves(currentPosition);
    }

    public Set<Point> getPointsPassed() {
        return Collections.unmodifiableSet(pointsPassed);
    }
    
}
