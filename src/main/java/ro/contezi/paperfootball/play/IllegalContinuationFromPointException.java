package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.Point;

public class IllegalContinuationFromPointException extends IllegalMoveException {

    private static final long serialVersionUID = 1L;

    public IllegalContinuationFromPointException(Point point) {
        super("You cannot continue from point: " + point);
    }

}
