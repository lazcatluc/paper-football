package ro.contezi.paperfootball.draw;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.Point;

public class DrawFootballField {
    private final Canvas canvas;
    private final FootballField field;
    
    public DrawFootballField(Canvas canvas, FootballField field) {
        this.canvas = canvas;
        this.field = field;
    }

    public void drawGoalNorth() {
        Point goalNorth = field.getGoalNorth();
        Point left = new Point(goalNorth.getX() - 1, goalNorth.getY());
        Point right = new Point(goalNorth.getX() + 1, goalNorth.getY());
        canvas.drawLine(left, goalNorth);
        canvas.drawLine(right, goalNorth);
        canvas.drawLine(new Point(left.getX(), left.getY() - 1), left);
        canvas.drawLine(new Point(right.getX(), right.getY() - 1), right);
    }
    
    public void drawEdges() {
        field.edge().forEach(edgePoint -> 
            edgePoint.getNeighbors().stream()
                .filter(field::isEdge)
                .filter(neighbor -> neighbor.getY().equals(edgePoint.getY()) ||
                        neighbor.getX().equals(edgePoint.getX()))
                .forEach(neighbor -> canvas.drawLine(edgePoint, neighbor)));
    }
}
