package ro.contezi.paperfootball;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SymmetricLine {
    private final Set<Point> points;
    
    public SymmetricLine(Point start, Point end) {
        points = new HashSet<>();
        points.add(start);
        points.add(end);
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SymmetricLine other = (SymmetricLine) obj;
        return Objects.equals(points, other.points);
    }
    
}
