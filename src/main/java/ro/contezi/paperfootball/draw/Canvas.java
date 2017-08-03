package ro.contezi.paperfootball.draw;

import ro.contezi.paperfootball.Point;

public interface Canvas {
    void draw(Point point);
    void drawCurrent(Point point);
    void drawLine(Point start, Point end);
}
