package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.Point;

public class PointOutException extends IllegalMoveException {

    private static final long serialVersionUID = 1L;

    public PointOutException(Point point) {
        super("Point " + point + " is out of the field.");
    }

}
