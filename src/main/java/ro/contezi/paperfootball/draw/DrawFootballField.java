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
        drawGoal(field.getGoalNorth(), -1);
    }
    
    public void drawEdges() {
        field.edge().forEach(edgePoint -> 
            edgePoint.getNeighbors().stream()
                .filter(field::isEdge)
                .filter(neighbor -> neighbor.getY().equals(edgePoint.getY()) ||
                        neighbor.getX().equals(edgePoint.getX()))
                .forEach(neighbor -> canvas.drawLine(edgePoint, neighbor)));
    }

    public void drawGoalSouth() {
        drawGoal(field.getGoalSouth(), 1);
    }

    private void drawGoal(Point goal, int deltaY) {
        Point left = new Point(goal.getX() - 1, goal.getY());
        Point right = new Point(goal.getX() + 1, goal.getY());
        canvas.drawLine(left, goal);
        canvas.drawLine(right, goal);
        canvas.drawLine(new Point(left.getX(), left.getY() + deltaY), left);
        canvas.drawLine(new Point(right.getX(), right.getY() + deltaY), right);
    }
}
