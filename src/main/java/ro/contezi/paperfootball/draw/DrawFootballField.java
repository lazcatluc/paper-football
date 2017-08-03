package ro.contezi.paperfootball.draw;

import ro.contezi.paperfootball.FootballField;

public class DrawFootballField {
    private final Canvas canvas;
    private final FootballField field;
    
    public DrawFootballField(Canvas canvas, FootballField field) {
        this.canvas = canvas;
        this.field = field;
    }

    public void draw() {
        field.edge().forEach(edgePoint -> 
            edgePoint.getNeighbors().stream()
                .filter(neighbor -> neighbor.getY() == edgePoint.getY())
                .forEach(neighbor -> canvas.drawLine(edgePoint, neighbor)));
    }
}
