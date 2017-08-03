package ro.contezi.paperfootball;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.contezi.ab.ABNode;

public class FootballNode implements ABNode {
    
    private static final Logger LOGGER = LogManager.getLogger(FootballNode.class);

    public static final Comparator<FootballNode> NORTH_COMPARATOR = (fn1, fn2) -> Double.compare(fn2.heuristicValue(), fn1.heuristicValue());
    
    private final FootballField footballField;
    private final Set<SymmetricLine> lines;
    private final Point currentPosition;
    private final Set<List<SymmetricLine>> nextMoves;
    private final NextMoves nextMovesGenerator;
    private final double heuristic;
    private final Comparator<FootballNode> stateSorter;
    private final List<SymmetricLine> pathLeadingToThisNode;

    public FootballNode() {
        this(new FootballField());
    }
    
    public FootballNode(FootballField footballField) {
        this(footballField, Collections.emptySet(), new Point(), 
                NORTH_COMPARATOR);

    }
    
    public FootballNode(FootballNode original, List<SymmetricLine> newPath) {
        footballField = original.footballField;
        lines = new HashSet<>(original.lines);
        lines.addAll(newPath);
        currentPosition = SymmetricLine.findEndPoint(original.currentPosition, newPath);
        stateSorter = original.stateSorter.reversed();
        LOGGER.trace("Getting next moves for " + currentPosition);
        nextMovesGenerator = new NextMoves(footballField, currentPosition, lines);
        this.nextMoves = nextMovesGenerator.getPossibleMoves();
        LOGGER.trace("Found next moves: "+this.nextMoves.size());
        this.heuristic = computeHeuristic();
        LOGGER.trace("Found heuristic: "+this.heuristic);
        this.pathLeadingToThisNode = newPath;
    }
    
    public FootballNode(FootballField footballField, Set<SymmetricLine> lines, Point currentPosition, 
            Comparator<FootballNode> stateSorter) {
        this.footballField = footballField;
        this.lines = lines;
        this.currentPosition = currentPosition;
        this.stateSorter = stateSorter;
        nextMovesGenerator = new NextMoves(footballField, currentPosition, lines);
        this.nextMoves = nextMovesGenerator.getPossibleMoves();
        this.heuristic = computeHeuristic();
        this.pathLeadingToThisNode = Collections.emptyList();
    }

    private double computeHeuristic() {
        if (footballField.isGoalNorth(currentPosition)) {
            return Integer.MIN_VALUE;
        }
        if (footballField.isGoalSouth(currentPosition)) {
            return Integer.MAX_VALUE;
        }
        if (nextMoves.isEmpty()) {
            return 0;
        }

        return -currentPosition.getY() + Math.signum(currentPosition.getY()) * 
                Math.abs(currentPosition.getX()) / 10.0;
    }
    
    @Override
    public double heuristicValue() {
        return heuristic;
    }

    @Override
    public boolean isTerminal() {
        return footballField.isGoalNorth(currentPosition) || footballField.isGoalSouth(currentPosition) || nextMoves.isEmpty();
    }

    @Override
    public Collection<? extends ABNode> children() {
        List<FootballNode> children = nextMoves.stream().map(addedLines -> {
            return new FootballNode(this, addedLines);
        }).collect(Collectors.toList());
        Collections.sort(children, stateSorter);
        return children;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, footballField, lines);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        FootballNode other = (FootballNode) obj;
        return Objects.equals(currentPosition, other.currentPosition) &&
               Objects.equals(lines, other.lines) &&
               Objects.equals(footballField, other.footballField);
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Set<SymmetricLine> getLines() {
        return Collections.unmodifiableSet(lines);
    }
    
    public Set<Point> alreadyPassedPoints() {
        return nextMovesGenerator.getPointsPassed();
    }

    public FootballField getFootballField() {
        return footballField;
    }

    public List<SymmetricLine> getPathLeadingToThisNode() {
        return Collections.unmodifiableList(pathLeadingToThisNode);
    }

    @Override
    public String toString() {
        return "FootballNode [lines=" + lines + ", currentPosition=" + currentPosition + ", pathLeadingToThisNode="
                + pathLeadingToThisNode + "]";
    }
}

