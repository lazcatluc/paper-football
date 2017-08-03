package ro.contezi.paperfootball;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class Point {

    private final Integer x;
    private final Integer y;
    
    public Point() {
        this(0, 0);
    }
    
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Set<Point> getNeighbors() {
        Set<Point> neighbors = new HashSet<>();
        IntStream.range(-1, 2).forEach(x -> 
            IntStream.range(-1, 2).forEach(y -> 
                neighbors.add(new Point(this.getX() + x, this.getY() + y))));
        neighbors.remove(this);
        return neighbors;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
