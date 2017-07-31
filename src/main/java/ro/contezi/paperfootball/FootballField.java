package ro.contezi.paperfootball;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FootballField {
    private final Set<Point> goalNorth;
    private final Set<Point> goalSouth;
    
    public FootballField(int width, int height) {
        int goalHeight = height / 2 + 1;
        goalNorth = IntStream.range(-1, 2).mapToObj(x -> new Point(x, goalHeight)).collect(Collectors.toSet());
        goalSouth = IntStream.range(-1, 2).mapToObj(x -> new Point(x, -goalHeight)).collect(Collectors.toSet());
    }

    public boolean isGoalNorth(Point point) {
        return goalNorth.contains(point);
    }
    
    public boolean isGoalSouth(Point point) {
        return goalSouth.contains(point);
    }
}
