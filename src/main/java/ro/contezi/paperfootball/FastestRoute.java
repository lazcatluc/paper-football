package ro.contezi.paperfootball;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FastestRoute {
    private final NextMoves nextMoves;
    private final Map<Point, Integer> movesToGetThereFromStart;
    
    public FastestRoute() {
        this(new FootballField(), new Point(), Collections.emptySet());
    }
    
    public FastestRoute(FootballField footballField, Point start, Set<SymmetricLine> alreadyPassed) {
        this.nextMoves = new NextMoves(footballField, start, alreadyPassed);
        movesToGetThereFromStart = new HashMap<>();
        movesToGetThereFromStart.put(start, 0);
        initMoves(0);
    }
    
    private void initMoves(int round) {
        Set<Point> newFoundPoints = movesToGetThereFromStart.entrySet().stream()
            .filter(entry -> entry.getValue().equals(round))
            .map(Map.Entry::getKey)
            .map(nextMoves::getPossibleMoves)
            .flatMap(Set::stream)
            .map(list -> list.get(list.size() - 1))
            .flatMap(SymmetricLine::points)
            .filter(point -> !movesToGetThereFromStart.containsKey(point))
            .collect(Collectors.toSet());
        newFoundPoints.forEach(point -> movesToGetThereFromStart.put(point, round + 1));
        if (!newFoundPoints.isEmpty()) {
            initMoves(round + 1);
        }
    }

    public Optional<Integer> getFastestRoute(Point target) {
        return Optional.ofNullable(movesToGetThereFromStart.get(target));
    }
}
