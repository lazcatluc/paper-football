package ro.contezi.paperfootball;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ro.contezi.ab.ABNode;

public class FootballNode implements ABNode {
    
    private final FootballField footballField;
    private final Set<SymmetricLine> lines;
    private final Point currentPosition;
    private final Set<List<SymmetricLine>> nextMoves;
    private final double heuristic;
    private final Comparator<FootballNode> stateSorter;

    public FootballNode() {
        this(new FootballField(), Collections.emptySet(), new Point(), 
                (fn1, fn2) -> Double.compare(fn2.heuristicValue(), fn1.heuristicValue()));
    }
    
    public FootballNode(FootballField footballField, Set<SymmetricLine> lines, Point currentPosition, 
            Comparator<FootballNode> stateSorter) {
        this.footballField = footballField;
        this.lines = lines;
        this.currentPosition = currentPosition;
        this.stateSorter = stateSorter;
        this.nextMoves = new NextMoves(footballField, currentPosition, lines).getPossibleMoves();
        this.heuristic = computeHeuristic();
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
        FastestRoute fastestRoute = new FastestRoute(footballField, currentPosition, lines);
        Integer distanceToSouth = fastestRoute.getFastestRoute(footballField.getGoalSouth()).orElse(Integer.MAX_VALUE);
        Integer distanceToNorth = fastestRoute.getFastestRoute(footballField.getGoalNorth()).orElse(Integer.MAX_VALUE);
        return distanceToNorth - distanceToSouth;
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
            Set<SymmetricLine> newLines = new HashSet<>(lines);
            newLines.addAll(addedLines);
            Point newStartPoint = findEndPoint(currentPosition, addedLines);
            return new FootballNode(footballField, newLines, newStartPoint, stateSorter.reversed());
        }).collect(Collectors.toList());
        Collections.sort(children, stateSorter);
        return children;
    }

    private Point findEndPoint(Point startPoint, List<SymmetricLine> lines) {
        Point currentPoint = startPoint;
        for (SymmetricLine line : lines) {
            currentPoint = line.opposite(currentPoint);
        }
        return currentPoint;
    }
}

