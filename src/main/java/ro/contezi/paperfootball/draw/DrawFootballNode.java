package ro.contezi.paperfootball.draw;

import java.util.stream.Collectors;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;

public class DrawFootballNode {
    private final FootballNode footballNode;
    private final Canvas canvas;
    private final DrawFootballField drawFootballField;

    public DrawFootballNode(FootballNode footballNode, Canvas canvas) {
        this.footballNode = footballNode;
        this.canvas = canvas;
        this.drawFootballField = new DrawFootballField(canvas, footballNode.getFootballField());
    }
    
    public void draw() {
        drawFootballField.drawEdges();
        drawFootballField.drawGoalNorth();
        drawFootballField.drawGoalSouth();
        footballNode.getLines().stream().map(SymmetricLine::points)
            .map(stream -> stream.collect(Collectors.toList()))
            .forEach(list -> canvas.drawLine(list.get(0), list.get(1)));
        footballNode.getPathLeadingToThisNode().stream().flatMap(SymmetricLine::points)
            .forEach(canvas::draw);
        canvas.drawCurrent(footballNode.getCurrentPosition());
    }
}
