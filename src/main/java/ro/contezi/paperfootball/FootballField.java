package ro.contezi.paperfootball;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FootballField {
    private final Set<Point> goalNorth;
    private final Set<Point> goalSouth;
    private final Set<Point> edge;
    private final int halfWidth;
    private final int halfHeight;

    public FootballField(int width, int height) {
        halfWidth = width / 2;
        halfHeight = height / 2;
        int goalHeight = halfHeight + 1;
        goalNorth = IntStream.range(-1, 2).mapToObj(x -> new Point(x, goalHeight)).collect(Collectors.toSet());
        goalSouth = IntStream.range(-1, 2).mapToObj(x -> new Point(x, -goalHeight)).collect(Collectors.toSet());
        edge = new HashSet<>();
        IntStream.range(-halfWidth, halfWidth + 1).mapToObj(x -> new Point(x, halfHeight)).forEach(edge::add);
        IntStream.range(-halfWidth, halfWidth + 1).mapToObj(x -> new Point(x, -halfHeight)).forEach(edge::add);
        IntStream.range(-halfHeight, halfHeight + 1).mapToObj(y -> new Point(halfWidth, y)).forEach(edge::add);
        IntStream.range(-halfHeight, halfHeight + 1).mapToObj(y -> new Point(-halfWidth, y)).forEach(edge::add);
        edge.remove(new Point(0, -halfHeight));
        edge.remove(new Point(0, halfHeight));
    }

    public boolean isGoalNorth(Point point) {
        return goalNorth.contains(point);
    }

    public boolean isGoalSouth(Point point) {
        return goalSouth.contains(point);
    }

    public boolean isEdge(Point point) {
        return edge.contains(point);
    }
    
    public boolean isOut(Point point) {
        if (point.getX().equals(0) && (isGoalNorth(point) || isGoalSouth(point))) {
            return false;
        }
        return point.getX() < -halfWidth || point.getX() > halfWidth || point.getY() < -halfHeight || point.getY() > halfHeight;
    }
    
    public Stream<Point> edge() {
        return edge.stream();
    }
}
